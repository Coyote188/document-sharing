package com.dss.storage.ui.control;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Space;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;

import com.dss.storage.service.DocumentService;

@SuppressWarnings({"serial","unused"})
public class MyDocControl extends GenericForwardComposer {
	
//	private DocumentService docService;
//	
//	private DocumentBean document;
//	private List<DocumentBean> docList;
//	private List<DocumentDirectoryBean> directoryList;
//	private DocumentDirectoryBean documentDirectory;
//	
//	private Grid grdSearchResult;
//	private Grid grdDrectory;
//	private Textbox txtNewDirectory;
//	private Button btnAddNewDirectory,btnSaveDirectory;
//
//	@Override
//	public void doAfterCompose(Component comp) throws Exception {
//		super.doAfterCompose(comp);
//		docList = docService.getMyDocs();
//		setDirectoryList(docService.getAllDirectory());
//		grdDrectory.setRowRenderer(new MyDocDirectoryButtonsRenderer());
//	}
//	
//	public void onRemove(DocumentBean document){
//
//	}
//	
//	private void onEdit(DocumentBean document) {
//		
//	}
//	
//	private void onSearchTheDirectory(DocumentDirectoryBean directory){
//		this.docList = docService.getDocsByDirectory(directory);
//		grdSearchResult.setRowRenderer(new MyDocControllerListRenderer());
//	}
//	
//	private void onEditDirectory(DocumentDirectoryBean directory){
//		
//	}
//	
//	private void onRemoveDirectory(DocumentDirectoryBean directory) throws InterruptedException{
//		int reply = Messagebox.show("是否确认删除文件夹" + directory.getName()+"?", "警告", Messagebox.YES|Messagebox.NO, null);
//		if(reply == 1){
////			service.removeDirectory(direcotry);
//		}
//	}
//	
//	public void onClick$btnAddNewDirectory(){
//		txtNewDirectory.setVisible(true);
//		btnSaveDirectory.setVisible(true);
//		btnAddNewDirectory.setVisible(false);
//	}
//	
//	public void onClick$btnSaveDirectory() throws InterruptedException{
//		/**
//		 * int reply = MessageBox.show("是否确认创建文件夹"+this.documentDirectory.getName+"?"
//		 * 								,Messagebox.Yes|Messagebox.No,Message.Question)
//		 * if(reply == 1){
//		 * 		service.addDirectory(documentDirectory);
//		 * }
//		 */
//		txtNewDirectory.setVisible(false);
//		btnSaveDirectory.setVisible(false);
//		btnAddNewDirectory.setVisible(true);
//	}
//
//
//	/**
//	 * 生成文档目录grid rows
//	 * @author 崔立刚
//	 *
//	 */
//	private class MyDocDirectoryButtonsRenderer implements RowRenderer{
//
//		@Override
//		public void render(Row row, Object data) throws Exception {
//			final DocumentDirectoryBean directory = (DocumentDirectoryBean) data;
//			
//			Hbox box = new Hbox();
//			final Toolbarbutton btnDirecotry = new Toolbarbutton(directory.getName());
//			btnDirecotry.addEventListener(Events.ON_CLICK, new EventListener(){
//				@Override
//				public void onEvent(Event arg0) throws Exception {
//					onSearchTheDirectory(directory);
//				}
//			});
//			box.appendChild(btnDirecotry);
//			
//			final Button btnEditDirectory = new Button(null, "/images/edit.png");
//			btnEditDirectory.addEventListener(Events.ON_CLICK, new EventListener() {
//				
//				@Override
//				public void onEvent(Event arg0) throws Exception {
//					// TODO Auto-generated method stub
//					onEditDirectory(directory);				
//				}
//			});
//			box.appendChild(btnEditDirectory);
//			box.setParent(row);
//		}
//		
//	}
//
//	/**
//	 * 生成文档列表 grid rows
//	 * @author 崔立刚
//	 *
//	 */
//	private class MyDocControllerListRenderer implements RowRenderer {
//
//		@Override
//		public void render(Row row, Object data) throws Exception {
//			final DocumentBean document = (DocumentBean) data;
//
//			new Label(document.getTitle()).setParent(row);
//			// new Label(document.getUploadDate()).setParent(row);
//			Hbox box = new Hbox();
//			box.setWidth("100%");
//			box.setParent(row);
//			final Button btnEdit = new Button("编辑");
//			btnEdit.addEventListener(Events.ON_CLICK, new EventListener() {
//				@Override
//				public void onEvent(Event event) throws Exception {
//					// TODO Auto-generated method stub
//					 onEdit(document);
//				}
//			});
//			box.appendChild(btnEdit);
//			final Space space = new Space();
//			space.setWidth("15px");
//			box.setParent(space);
//			final Button btnRemove = new Button("删除");
//			btnRemove.addEventListener(Events.ON_CLICK, new EventListener() {
//
//				@Override
//				public void onEvent(Event event) throws Exception {
//					// TODO Auto-generated method stub
//					 onRemove(document);
//				}
//			});
//			box.appendChild(btnEdit);
//		}
//	}
//
//	public DocumentBean getDocument() {
//		return document;
//	}
//
//	public void setDocument(DocumentBean document) {
//		this.document = document;
//	}
//
//	public List<DocumentBean> getDocList() {
//		return docList;
//	}
//
//	public void setDocList(List<DocumentBean> docList) {
//		this.docList = docList;
//	}
//
//	public void setDocumentDirectory(DocumentDirectoryBean documentDirectory) {
//		this.documentDirectory = documentDirectory;
//	}
//
//	public DocumentDirectoryBean getDocumentDirectory() {
//		return documentDirectory;
//	}
//
//	public void setDirectoryList(List<DocumentDirectoryBean> directoryList) {
//		this.directoryList = directoryList;
//	}
//
//	public List<DocumentDirectoryBean> getDirectoryList() {
//		return directoryList;
//	}

}
