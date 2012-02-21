package com.dss.storage.ui.control;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Image;

import com.dss.account.bean.ProfileBean;
import com.dss.account.service.MemberService;
import com.dss.storage.bean.DocumentBean;

@Controller("documentDetailControl")
@Scope("prototype")
public class DocumentDetailControl extends GenericForwardComposer
{
    private static final long serialVersionUID = 8751784806008352239L;
    
    
    private DocumentBean document;
    
    private Image documentImage;


    @Override
    public void doAfterCompose(Component comp)
            throws Exception
    {
        super.doAfterCompose(comp);        
        document = (DocumentBean) arg.get(DocumentBean.class);
//        documentImage.setContent(document.getPreviewPicture());
    }
    
    public DocumentBean getDocument(){
        return document;
    }
    
    public ProfileBean getUploader(){
        System.out.println("view document detail: "+document.getUploader());
        return document.getUploader().getProfile();
    }
    
}
