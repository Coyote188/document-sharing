package com.dss.storage.ui.control;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.dss.storage.bean.DocumentBean;
import com.dss.storage.service.DocumentService;
import com.dss.storage.service.ManageDocumentService;
import com.dss.storage.service.StorageService;

@SuppressWarnings({"unchecked","unused", "serial",})
@Controller("docManControl")
@Scope("request")
public class DocManageControl extends GenericForwardComposer{
	
	@Resource
	private ManageDocumentService mService;
	@Resource
	private DocumentService searchService;

	private List<DocumentBean> docList;
	private Grid grdManageDocList;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		this.docList = searchService.findMyDocs();
		grdManageDocList.setRowRenderer(new ManageDocRowRenderer());
	}
	
	
	private void onEdit(DocumentBean doc){
		param.put("document", doc);
		Window frmFillDocumentInfo = (Window) Executions.createComponents("document-info.zul", null, param);
	}

	
	public void setDocList(List<DocumentBean> docList) {
		this.docList = docList;
	}

	public List<DocumentBean> getDocList() {
		return docList;
	}


	private class ManageDocRowRenderer implements RowRenderer{

		@Override
		public void render(Row row, Object data) throws Exception {
			final DocumentBean doc = (DocumentBean) data;
			
			new Label(doc.getName()).setParent(row);
			new Label(String.valueOf(doc.getUploadDate())).setParent(row);
			final Toolbarbutton btnEdit = new Toolbarbutton();
			btnEdit.addEventListener(Events.ON_CLICK, new EventListener() {
				
				@Override
				public void onEvent(Event arg0) throws Exception {
					// TODO Auto-generated method stub
					onEdit(doc);
				}
			});
			row.appendChild(btnEdit);
		}
		
	}
}
