package com.dss.storage.ui.control;

import java.util.List;

import javax.annotation.Resource;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Html;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Space;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

import com.dss.storage.service.DocumentService;

@SuppressWarnings({"serial"})
public class PosterDocsControl extends GenericForwardComposer implements EventListener{

//	private List<DocumentBean> documentList;
//	@Resource
//	private DocumentService service;
////	@Resource(name="shoppingCart")
////	private ShoppingCart cart;
//	
//	private Grid grdPosterDocs;
//	private Window frmViewOnline;
//	private Window searchWindow;
//
//	
//	/**
//	 * getters & setters
//	 * @return
//	 */
//	public List<DocumentBean> getDocumentList() {
//		return documentList;
//	}
//	
//	public void setDocumentList(List<DocumentBean> documentList){
//		this.documentList = documentList;
//	}
//
//	@Override
//	public void doAfterCompose(Component comp) throws Exception {
//		super.doAfterCompose(comp);
////		documentList=service.findAllDocuments();
//		grdPosterDocs.setRowRenderer(new SearchResultViewRenderer());
//	}
//	
//	/**
//	 * binding page buttons events
//	 * @param document
//	 * @throws InterruptedException
//	 */
////	public void onAddToCart(DocumentBean document){
////		cart.addDocument(document);
////		
////	}
//
//	public void onReadOnline(DocumentBean document) throws InterruptedException{
////		Messagebox.show("read on line: "+document.getTitle());
//		param.put("document", document);
//		frmViewOnline = (Window) Executions.createComponents("/view-online.zul", null, param);
//	}
//	
//	public void onDownload(DocumentBean document){
////		Filedownload.save(service.download(document), "application/pdf", document.getName());
//	}
//	
//	private class SearchResultViewRenderer implements RowRenderer {
//		@Override
//		public void render(final Row row, Object data) throws Exception {
//			final DocumentBean document = (DocumentBean) data;
//			new Label("document picture").setParent(row);
//			Vbox box = new Vbox();
//			box.setParent(row);
//			new Label(document.getTitle()).setParent(box);
//			new Html(document.getDesc()).setParent(box);
//			Hbox hbox = new Hbox();
//			hbox.setParent(box);
////			final Button addToCart = new Button("Add to cart");
//			final Toolbarbutton addToCart = new Toolbarbutton();
//			addToCart.setImage("/images/shoppingcart.png");
//			addToCart.addEventListener(Events.ON_CLICK, new EventListener() {
//				public void onEvent(Event event) throws Exception {
////						onAddToCart(document);
//				}
//			});
//			hbox.appendChild(addToCart);
//			final Space space = new Space();
//			space.setWidth("15px");
//			hbox.appendChild(space);
//			final Button readOnline = new Button("ÔÄ¶Á");			
//			readOnline.addEventListener(Events.ON_CLICK, new EventListener() {
//				public void onEvent(Event event) throws Exception {
//						onReadOnline(document);
//				}
//			});
//			hbox.appendChild(readOnline);
//			final Space space1 = new Space();
//			space1.setWidth("15px");
//			hbox.appendChild(space1);
//			final Button donwload = new Button("ÏÂÔØ");			
//			donwload.addEventListener(Events.ON_CLICK, new EventListener() {
//				public void onEvent(Event event) throws Exception {
//					onDownload(document);
//				}
//			});
//			hbox.appendChild(donwload);
//			new Label("uploader picture").setParent(row);
//		}
//	}
}
