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
				Messagebox.show("�ĵ�  \"" + document.getTitle() + "\" �ɹ��ŵ����ﳵ�С�");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				Messagebox.show("�ĵ�  \"" + document.getTitle() + "\" �Ѿ����ڹ��ﳵ�С�");
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
