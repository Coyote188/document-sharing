package com.dss.storage.ui.control;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Configuration;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.dss.storage.bean.DocumentBean;
import com.dss.storage.bean.DocumentFormat;
import com.dss.storage.service.StorageService;
import com.dss.storage.service.TradeManager;
import com.dss.util.log.LogUtil;

@SuppressWarnings("serial")
@Controller("uploadFlow")
@Scope("prototype")
public class DocumentUploadControl
        extends GenericForwardComposer
{

    @Resource
    private TradeManager service;
    private DocumentBean document;
    private Window frmUpload;
    private Window frmFillDocumentInfo;
    private Window documentUploadUi;
    private Media uploadData;
    private boolean isUploadOk;
    private Fileupload btnUpload;
    private Div infoHolder;
    private Label lblMessage;

    @Override
    // do the initialization
    public void doAfterCompose(Component comp)
            throws Exception
    {
        super.doAfterCompose(comp);
        prepareNewDocument();
        setUploadCritic();

    }

    private void setUploadCritic()
    {
        String upload = "true,maxsize=5000";
        btnUpload.setUpload(upload);
    }

    public void onUpload$btnUpload(UploadEvent event)
            throws Exception
    {
        LogUtil.debug(getClass(), "finish upload: " + event.getMedia());
        Media m = event.getMedia();
        if (m == null) {
            Messagebox.show("请选择文件！");
        } else if (document == null) {
            Messagebox.show("内部错误！");
        } else {
            updateDocumentInfo(m, document);
        }
        refreshView();
    }

    @SuppressWarnings("unchecked")
    private void refreshView()
    {
        if (isUploadOk) {
            lblMessage.setValue("请填写文件信息...");
            param.put(DocumentBean.class, document);
            if (!infoHolder.hasFellow("frmFillDocumentInfo")) {
                frmFillDocumentInfo = (Window) Executions.createComponents("/document-info.zul",
                        infoHolder, param);
                frmFillDocumentInfo.addEventListener("onFillDocumentInfoFinish", this);
                frmFillDocumentInfo.addEventListener("onFillDocumentInfoCancel", this);
                frmFillDocumentInfo.setBorder("none");
                frmFillDocumentInfo.setTitle(null);
                frmFillDocumentInfo.setWidth("100%");
            }
            // reset counter
            isUploadOk = false;
        } else {
            lblMessage.setValue("请选择一个文件...");
            if (infoHolder.hasFellow("frmFillDocumentInfo")) {
                infoHolder.removeChild(infoHolder.getFellow("frmFillDocumentInfo"));
            }
        }
    }

    private void updateDocumentInfo(Media m, DocumentBean document)
            throws InterruptedException
    {
        try {
            document.setName(m.getName());
            document.setTitle(m.getName());
            document.setFormat(Enum.valueOf(DocumentFormat.class, m.getFormat().toUpperCase()));
            document.setSize(IOUtils.toByteArray(m.getStreamData()).length);
            uploadData = m;
            isUploadOk = true;
        } catch (IllegalArgumentException ex) {
            Messagebox.show("您选择不支持的文件，请重新选！");
            isUploadOk = false;
        } catch (IOException ex) {
            Messagebox.show("文件传送有误！");
            isUploadOk = false;
        } catch (Exception ex) {
            isUploadOk = false;
        }
    }

    public void onFillDocumentInfoFinish(Event e) throws IOException
    {
        LogUtil
                .debug(getClass(), "button ok is clicked, caught at web upload flow: "
                        + e.getData());
        service.upload(IOUtils.toByteArray(uploadData.getStreamData()), document);
        Events.postEvent(new Event("onUploadFinish", self, null));
    }

    public void onFillDocumentInfoCancel(Event e)
    {
        LogUtil.debug(getClass(), "button cancel is clicked , caught at web upload flow");
        Events.postEvent(new Event("onUploadFinish", self, null));
    }

    private void prepareNewDocument()
    {
        document = service.createDocument();
        isUploadOk = false;
    }

}
