package com.dss.storage.ui.control;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Html;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

import com.dss.storage.bean.DocumentBean;
import com.dss.storage.bean.Searcher;
import com.dss.storage.service.StorageService;
import com.dss.trade.ShoppingCart;



@Controller("searchResultController")
@Scope("prototype")
public class SearchResultControl extends GenericForwardComposer {
	private List<DocumentBean> documentList;
	@Resource
	private StorageService service;
	@Resource(name="shoppingCart")
	private ShoppingCart cart;
	private Searcher<DocumentBean> searcher;
	
	private Grid grdSearchResult;
	private Window frmViewOnline;

	public List<DocumentBean> getDocumentList() {
		return documentList;
	}

	@SuppressWarnings("unchecked")
    @Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
//		documentList=service.findAllDocuments();
		searcher=(Searcher<DocumentBean>) arg.get(Searcher.class);
		documentList=searcher.search();
		grdSearchResult.setRowRenderer(new SearchResultViewRenderer());
	}
	
	public void onAddToCart(DocumentBean document){
		cart.addDocument(document);
		
	}
	public void onClickViewDetail(DocumentBean document) throws InterruptedException{
//		Messagebox.show("read on line: "+document.getTitle());
//		param.put("document", document);
//		frmViewOnline = (Window) Executions.createComponents("/view-online.zul", null, param);
	   
	    Events.postEvent(new Event("onViewDocumentDetail", self, document));
	}
	public void onDownload(DocumentBean document){
		Filedownload.save(service.download(document), "application/pdf", document.getName());
	}
	
//	<row self="@{each=document}">
//	<label id="documentPicture" value="preview picture" />
//	<vbox>
//		<label id="documentTitle"
//			value="@{document.title }" />
//		<html id="documentDescription"
//			content="@{document.simpleDescription}" />
//		<hbox>						
//			<button id="addToCart" label="Add to cart"
//				onClick="" />
//			<button id="readOnline" label="read online"
//				onClick="" />
//			<button id="download" label="download this document"
//		onClick="" />
//		</hbox>
//	</vbox>
//	<label value="uploader picture" />
//  </row>
	private class SearchResultViewRenderer implements RowRenderer {
		@Override
		public void render(final Row row, Object data) throws Exception {
			final DocumentBean document = (DocumentBean) data;
			new Label("document picture").setParent(row);
			Vbox box = new Vbox();
			box.setParent(row);
			new Label(document.getTitle()).setParent(box);
			new Html(document.getSimpleDescription()).setParent(box);
			Hbox hbox = new Hbox();
			hbox.setParent(box);
			final Button addToCart = new Button("Add to cart");			
			addToCart.addEventListener(Events.ON_CLICK, new EventListener() {
				public void onEvent(Event event) throws Exception {
						onAddToCart(document);
				}
			});
			hbox.appendChild(addToCart);
			final Button readOnline = new Button("View Detail");			
			readOnline.addEventListener(Events.ON_CLICK, new EventListener() {
				public void onEvent(Event event) throws Exception {
						onClickViewDetail(document);
				}
			});
			hbox.appendChild(readOnline);
			final Button donwload = new Button("Download");			
			donwload.addEventListener(Events.ON_CLICK, new EventListener() {
				public void onEvent(Event event) throws Exception {
					onDownload(document);
				}
			});
			hbox.appendChild(donwload);
			new Label("uploader picture").setParent(row);
		}
	}
}
