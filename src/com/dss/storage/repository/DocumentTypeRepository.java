package com.dss.storage.repository;

import com.dss.storage.model.DocumentType;

public interface DocumentTypeRepository {

//	DocumentType findByName(DocumentTypeBean type);
	public DocumentType newType();
	public DocumentType getRootDocumentType();
    
}
