package com.dss.storage.ui.control;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.CustomConstraint;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.dss.storage.bean.DocumentDirectoryBean;
import com.dss.storage.service.ManageDocumentService;
import com.dss.util.log.LogUtil;

@Controller("documentDirectoryEditControl")
@Scope("prototype")
/**
 * Responsible for create new or edit existed DocumentDirectory
 * Event:
 * onEditDirectoryFinish
 */
public class DocumentDirectoryEditControl
        extends GenericForwardComposer
{

    /**
     * 
     */
    private static final long serialVersionUID = -6124138311037261363L;
    private Bandbox txtSelectedDirectory;
    private Textbox txtName;
    private Label txtError;

    private DocumentDirectoryBean editDirectory;
    private DocumentDirectoryBean parentDirectory;

    @Resource
    private ManageDocumentService service;

    public DocumentDirectoryBean getDirectory()
    {
        return editDirectory;
    }

    @Override
    public void doAfterCompose(Component comp)
            throws Exception
    {
        super.doAfterCompose(comp);
        try {
            prepareData();
        } catch (EditRootDocumentDirectoryException ex) {
            self.detach();
            // Events.postEvent(new Event("onEditDirectoryFinish", self, null));
            throw ex;
        }
    }

    private void prepareData()
            throws EditRootDocumentDirectoryException
    {
        editDirectory = (DocumentDirectoryBean) arg.get(DocumentDirectoryBean.class);
        if (editDirectory == null) {
            editDirectory = service.createDirectory();
            parentDirectory = service.getRootDocumentDirectory();
        } else {
            parentDirectory = editDirectory.getParent();
            if (parentDirectory == null) {
                throw new EditRootDocumentDirectoryException();
            }
        }
        txtSelectedDirectory.setValue(parentDirectory.getName());
    }

    public void onClick$btnOk()
            throws InterruptedException
    {
        // try {
        validateData();
        service.updateDirectory(parentDirectory, editDirectory);
        // } catch (IllegalArgumentException ex) {
        // Messagebox.show(ex.getMessage());
        // } finally {
        Events.postEvent(new Event("onEditDirectoryFinish", self, null));
        // }
    }

    private void validateData()
    {
        // boolean isOk=false;

        txtSelectedDirectory.getValue();
        txtName.getValue();
        // if (editDirectory == parentDirectory) {
        // isOk=true;
        // throw new IllegalArgumentException("目录不能移动到自己下！");
        // }

        // return isOk;
    }

    public Constraint getRootConstraint()
    {
        return new RootDirectoryConstraint();
    }

    public void onClick$btnCancel()
    {
        Events.postEvent(new Event("onEditDirectoryFinish", self, null));
    }

    public void onSelectDirectory$documentDirectoryUi(ForwardEvent e)
    {
        LogUtil.debug(getClass(), "" + e.getOrigin().getData());
        parentDirectory = (DocumentDirectoryBean) e.getOrigin().getData();
        txtSelectedDirectory.setValue(parentDirectory.getName());
        txtSelectedDirectory.close();
    }

    private class RootDirectoryConstraint
            implements Constraint, CustomConstraint
    {

        @Override
        public void validate(Component arg0, Object arg1)
                throws WrongValueException
        {
            System.out.println("parent child the same");
            if (editDirectory == parentDirectory) {
                throw new WrongValueException(arg0, "目录不能移动到自己下！");
            }
            if (parentDirectory == null) {
                throw new WrongValueException(arg0, "根目录不能为空！");
            }

        }

        @Override
        public void showCustomError(Component arg0, WrongValueException arg1)
        {
            txtError.setValue(arg1!=null? arg1.getMessage():"");

        }

    }
}
