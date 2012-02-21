package com.dss.storage.ui.control;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import com.dss.storage.bean.DocumentBean;
import com.dss.storage.bean.DocumentContentBean;
import com.dss.storage.service.ManageDocumentService;

@SuppressWarnings("serial")
@Controller("viewDocumentController")
@Scope("prototype")
public class ViewOnlineControl
        extends GenericForwardComposer
{

    // private List<BufferedImage> pageList;
    private DocumentBean currentDocument;
    private Grid grdViewOnline;
    @Resource
    private ManageDocumentService service;
    private DocumentContentBean content;

    @Override
    public void doAfterCompose(Component comp)
            throws Exception
    {
        super.doAfterCompose(comp);
        currentDocument = (DocumentBean) arg.get("document");
        if (currentDocument != null) {
            content = service.getDocumentContent(currentDocument);
        }
        grdViewOnline.setRowRenderer(new ViewOnlineRenderer());
    }

    // <rows >
    // <row >
    // <image id="imge"/>
    // </row>
    // </rows>
    private class ViewOnlineRenderer
            implements RowRenderer
    {
        @Override
        public void render(Row row, Object data)
                throws Exception
        {
            BufferedImage pageContent = (BufferedImage) data;
            // ImageIO.write(pageContent, "png", new
            // File("D://tmp//testww"+pageNumber+".png"));
            System.out.println(data.getClass());
            org.zkoss.zul.Image page = new org.zkoss.zul.Image();
            page.setParent(row);
            page.setContent(pageContent);
        }
    }

    public List<BufferedImage> getPageList()
    {
        if (content == null) {
            return new ArrayList<BufferedImage>();
        } else {
            return content.getAllPages();
        }
    }
}
