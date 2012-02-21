package com.dss.trade;

import java.util.List;

import com.dss.storage.bean.DocumentBean;

public interface ShoppingCart {

	public abstract void addDocument(DocumentBean document);

	public abstract void removeDocument(DocumentBean document);

	public abstract List<DocumentBean> getDocumentsInCart();

	public abstract boolean isDocumentExist(DocumentBean document);

}