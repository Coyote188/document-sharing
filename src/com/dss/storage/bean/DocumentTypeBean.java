package com.dss.storage.bean;

import java.util.List;


public interface DocumentTypeBean {
    List<DocumentBean> getDocuments();
    String getName();
    List<DocumentTypeBean> getChildren();
}
