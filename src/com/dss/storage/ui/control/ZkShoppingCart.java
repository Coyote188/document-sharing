package com.dss.storage.ui.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Messagebox;

import com.dss.storage.bean.DocumentBean;
import com.dss.trade.ShoppingCart;

@Controller("shoppingCart")
@SuppressWarnings("unchecked")
@Scope("singleton")
public class ZkShoppingCart implements ShoppingCart {

	public ZkShoppingCart() {
	}

	public void addDocument(DocumentBean document) {
		if (!isDocumentExist(document)) {
			getDocumentsInCart().add(document);
			try {
				Messagebox.show("文档  \"" + document.getTitle() + "\" 成功放到购物车中。");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				Messagebox.show("文档  \"" + document.getTitle() + "\" 已经存在购物车中。");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void removeDocument(DocumentBean document) {
		getDocumentsInCart().remove(document);
	}

	public List<DocumentBean> getDocumentsInCart() {
		if (Sessions.getCurrent().getAttribute("documentList") == null) {
			List<DocumentBean> documentList = new ArrayList<DocumentBean>();
			Sessions.getCurrent().setAttribute("documentList", documentList);
		}
		return (List<DocumentBean>) Sessions.getCurrent().getAttribute(
				"documentList");
	}

	public boolean isDocumentExist(DocumentBean document) {
		return getDocumentsInCart().contains(document);
	}

}
