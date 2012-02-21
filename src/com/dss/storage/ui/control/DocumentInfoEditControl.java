package com.dss.storage.ui.control;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Bandpopup;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;

import com.dss.storage.bean.DocumentBean;
import com.dss.storage.bean.DocumentDirectoryBean;
import com.dss.storage.bean.DocumentTypeBean;
import com.dss.storage.service.ManageDocumentService;
import com.dss.storage.service.StorageService;
import com.dss.util.log.LogUtil;

@Controller("documentInfoEditController")
@Scope("prototype")
/**
 * Render Document form
 * Events:
 * onFillDocumentInfoCancel
 * onFillDocumentInfoFinish, sent with current edited document as data 
 * 
 * Will auto update document info 
 */
public class DocumentInfoEditControl
        extends GenericForwardComposer
{

    private static final long serialVersionUID = 2463486243729265973L;

    private DocumentBean document;
    @Resource
    private ManageDocumentService service;
    private DocumentDirectoryBean directory;
    private DocumentTypeBean type;

    private Bandbox txtSelectedType;
    private Bandbox txtSelectedDirectory;
    private Textbox documentTitle;
    private Decimalbox documentCash;

    @Override
    public void doAfterCompose(Component comp)
            throws Exception
    {
        super.doAfterCompose(comp);
        prepareData();
    }

    private void prepareData()
    {
        document = (DocumentBean) arg.get(DocumentBean.class);
        
        if (document.getDirectory() != null) {
            directory = document.getDirectory();
        } else {
            directory = service.getRootDocumentDirectory();
        }

        if (document.getType() != null) {
            type = document.getType();
            txtSelectedType.setValue(type.getName());
        } else {
            type = service.getRootDocumentType();            
        }        
        txtSelectedDirectory.setValue(directory.getName());
    }

    public DocumentBean getDocument()
    {
        return document;
    }

    public void onClick$btnOk()
    {
        validateData();
        service.updateDocument(document, type, directory);
        Events.postEvent(new Event("onFillDocumentInfoFinish", self, document));
        LogUtil.debug(getClass(), "button ok is clicked");
    }

    private void validateData()
    {
        documentTitle.getValue();
        txtSelectedDirectory.getValue();
        txtSelectedType.getValue();
        documentCash.getValue();
    }

    public void onClick$btnCancel()
    {
        Events.postEvent(new Event("onFillDocumentInfoCancel", self, document));
        LogUtil.debug(getClass(), "button cancel is clicked");
    }

    public void onSelectType$typeSelection(ForwardEvent e)
    {
        LogUtil.debug(getClass(), e.getOrigin().getData());
        type = (DocumentTypeBean) e.getOrigin().getData();
        txtSelectedType.setValue(type.getName());
        txtSelectedType.close();
    }

    public void onSelectDirectory$documentDirectoryUi(ForwardEvent e)
    {
        LogUtil.debug(getClass(), "" + e.getOrigin().getData());
        directory = (DocumentDirectoryBean) e.getOrigin().getData();
        txtSelectedDirectory.setValue(directory.getName());
        txtSelectedDirectory.close();
    }

}