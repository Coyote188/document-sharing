package com.dss.storage.ui.control;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.dss.storage.bean.DocumentBean;
import com.dss.storage.bean.DocumentDirectoryBean;
import com.dss.storage.service.DocumentService;
import com.dss.storage.service.ManageDocumentService;
import com.dss.util.log.LogUtil;
import com.dss.util.zk.ZkUtil;

@Controller("uploadedDocumentController")
@Scope("request")
public class ManageUploadedDocumentController
        extends GenericForwardComposer
{

    private static final long serialVersionUID = -1498669468251335346L;
    @Resource
    private DocumentService service;
    @Resource
    private ManageDocumentService mService;
    private List<DocumentBean> documentList;

    private Component documentDirectoryUi;
    private Grid grdViewDocument;

    @SuppressWarnings("unchecked")
    @Override
    public void doAfterCompose(Component comp)
            throws Exception
    {
        super.doAfterCompose(comp);
        IItemContainer<DocumentDirectoryBean> d = (IItemContainer<DocumentDirectoryBean>) ZkUtil
                .getComponentController(documentDirectoryUi);
        setDocumentList(service.findDocsByDirectory(d.getCurrentItem()));
    }

    public List<DocumentBean> getDocumentList()
    {
        return documentList;
    }

    private void setDocumentList(List<DocumentBean> documentList)
    {
        this.documentList = documentList;
    }

    public void onClick$btnAdd()
            throws SuspendNotAllowedException, InterruptedException
    {
        Window w = (Window) Executions.createComponents("/document-directory-edit.zul", self, null);
        w.addEventListener("onEditDirectoryFinish", this);
        w.doModal();
    }

    @SuppressWarnings("unchecked")
    public void onClick$btnEdit()
            throws Throwable
    {
        try {
            IItemContainer<DocumentDirectoryBean> d = (IItemContainer<DocumentDirectoryBean>) ZkUtil
                    .getComponentController(documentDirectoryUi);
            param.put(DocumentDirectoryBean.class, d.getCurrentItem());
            Window w = (Window) Executions.createComponents("/document-directory-edit.zul", self,
                    param);
            w.addEventListener("onEditDirectoryFinish", this);
            w.doModal();
        } catch (Throwable ex) {
            if (ex.getCause() instanceof EditRootDocumentDirectoryException)
                Messagebox.show(ex.getCause().getMessage());
            else
                throw ex.getCause();
        }
    }
    
    @SuppressWarnings("unchecked")
    public void onClick$btnRemove(){
        IItemContainer<DocumentDirectoryBean> d = (IItemContainer<DocumentDirectoryBean>) ZkUtil.getComponentController(documentDirectoryUi);
        mService.removeDirectory(d.getCurrentItem());
    }

    public void onEditDirectoryFinish(Event e)
    {
        e.getTarget().detach();
        IRefreshable w = (IRefreshable) ZkUtil.getComponentController(documentDirectoryUi);
        w.refresh();
    }

    public void onSelectDirectory$documentDirectoryUi(ForwardEvent e)
    {
        LogUtil.debug(getClass(), "directory change: " + e.getOrigin());
        LogUtil.debug(getClass(), "to: "
                + ((DocumentDirectoryBean) e.getOrigin().getData()).getName());
        setDocumentList(service
                .findDocsByDirectory((DocumentDirectoryBean) e.getOrigin().getData()));
        refresh();
    }

    @SuppressWarnings("unchecked")
    private void refresh()
    {        
        IItemContainer<DocumentDirectoryBean> d = (IItemContainer<DocumentDirectoryBean>) ZkUtil
                .getComponentController(documentDirectoryUi);
        setDocumentList(service.findDocsByDirectory(d.getCurrentItem()));
        grdViewDocument.setModel(new SimpleListModel(getDocumentList()));
    }

    @SuppressWarnings("unchecked")
    public void onEditDocument$grdViewDocument(ForwardEvent e)
            throws SuspendNotAllowedException, InterruptedException
    {
        LogUtil.debug(getClass(), "edit document: " + e.getOrigin().getData());
        param.put(DocumentBean.class, e.getOrigin().getData());
        Window w = (Window) Executions.createComponents("/document-info.zul", self, param);
        w.addEventListener("onFillDocumentInfoFinish", this);
        w.addEventListener("onFillDocumentInfoCancel", this);
        w.doModal();
    }

    public void onFillDocumentInfoFinish(Event e)
    {
        e.getTarget().detach();
        refresh();
    }

    public void onFillDocumentInfoCancel(Event e)
    {
        e.getTarget().detach();
    }

}
