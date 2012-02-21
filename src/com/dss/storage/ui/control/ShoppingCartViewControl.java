package com.dss.storage.ui.control;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Html;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import com.dss.storage.bean.DocumentBean;
import com.dss.storage.service.StorageService;
import com.dss.trade.ShoppingCart;

@SuppressWarnings("serial")
@Controller("shoppingCartView")
@Scope("prototype")
public class ShoppingCartViewControl extends GenericForwardComposer
		implements EventListener {
	
	@Resource(name="shoppingCart")
	private ShoppingCart cart;
	@Resource
	private StorageService service;
	
	private Button btnDownload;
	private Grid grdDocumentList;
	

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		grdDocumentList.addEventListener("onRemove", this);
		grdDocumentList.setRowRenderer(new ShoppingcartViewRenderer());
		setDownloadButtonVisible(cart.getDocumentsInCart().size());
//		setSample();
	}

	public List<DocumentBean> getDocumentList() {
		return cart == null ? null : cart.getDocumentsInCart();
	}

	public double getTotalPoint() {
		double total = 0;
		for (DocumentBean document : cart.getDocumentsInCart()) {
			total += document.getDownloadCash();
		}
		return total;
	}

	private void setDownloadButtonVisible(int documentCount) {
		if (documentCount <= 0)
			btnDownload.setVisible(false);
		else
			btnDownload.setVisible(true);
	}

	public void onClick$btnClose() {
		self.detach();
	}
	public void onClick$btnDownload(){
		Filedownload.save(service.download(cart.getDocumentsInCart()),"application/zip",String.valueOf(this.hashCode())+".zip");
	}

	public void onRemove$grdDocumentList() throws InterruptedException {
		setDownloadButtonVisible(cart.getDocumentsInCart().size());
	}
	
	private class ShoppingcartViewRenderer implements RowRenderer {

		@Override
		public void render(final Row row, Object data) throws Exception {
			final DocumentBean document = (DocumentBean) data;
//			new Label(document.getTitle()).setParent(row);
			new Html(document.getTitle()).setParent(row);
			new Label(String.valueOf(document.getSize())).setParent(row);
			new Label(String.valueOf(document.getDownloadCash())).setParent(row);
			final Button rm = new Button("Remove");
			rm.addEventListener(Events.ON_CLICK, new EventListener() {
				public void onEvent(Event event) throws Exception {
					if (Messagebox.show("您确定从购物车移除 \"" + document.getTitle()
							+ "\" 吗?","确认移除",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES) {
						cart.removeDocument(document);
						Events.postEvent("onRemove", row.getGrid(), new Event(
								"onRemove", row));
						row.getGrid().setModel(
								new ListModelList(cart.getDocumentsInCart()));
					}
				}
			});
			row.appendChild(rm);
		}
	}

}
