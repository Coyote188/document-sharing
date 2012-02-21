package com.dss.storage.ui.control;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;

import com.dss.storage.bean.DocumentBean;
import com.dss.storage.service.DocumentService;

@Controller("recentlyUploadDocumentControl")
@Scope("prototype")
public class RecentlyUploadDocumentControl extends GenericForwardComposer
{

    private static final long serialVersionUID = -5814654806802236864L;
    
    private Toolbar recentlyUploadDocument;

    @Resource
    private DocumentService service;
    
    @Override
    public void doAfterCompose(Component comp)
            throws Exception
    {
        super.doAfterCompose(comp);
        
        List<DocumentBean> docList = service.findLatestUploadedDoc();
        contructView(docList);
    }

//    <div >  
//    <image id="documentImage" width="150pxpx" height="300px" content="@{document.previewPicture }"/>
//    <label value="@{document.title }" />
//    </div>
    private void contructView(List<DocumentBean> docList)
    {
        for(DocumentBean document: docList){
//            Div div = new Div();
            Vbox box = new Vbox();
            Image img = new Image();
            Label lbl = new Label(document.getTitle());
            
            lbl.setMaxlength(30);
            
            img.setContent(document.getPreviewPicture());
            img.setWidth("150px");
            img.setHeight("200px");
            
//            div.appendChild(img);
//            div.appendChild(lbl);
            box.appendChild(img);
            box.appendChild(lbl);
            
            recentlyUploadDocument.appendChild(box);
        }
    }
    
    

}
