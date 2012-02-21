package com.dss.storage.service.impl;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dss.account.model.Member;
import com.dss.account.service.MemberService;
import com.dss.account.service.SecurityService;
import com.dss.storage.bean.DocumentBean;
import com.dss.storage.model.Document;
import com.dss.storage.service.StorageService;
import com.dss.storage.service.TradeManager;

@Transactional
@Service
public class TradeManageImpl implements TradeManager {

    @Resource
	private MemberService memberService;
    @Resource
	private SecurityService securityService;
    @Resource
	private StorageService storageService;

	public boolean download(DocumentBean document) {
		boolean isDone = memberService.useCash(document.getDownloadCash());
		if (isDone) {
			storageService.download(document);
		}
		return isDone;
	}

	public boolean download(List<DocumentBean> documents) {
		List<DocumentBean> docs = documents;
		double cash = 0;
		for (DocumentBean doc : docs) {
			cash += doc.getDownloadCash();
		}
		boolean isDone = memberService.useCash(cash);
		if (isDone) {
			storageService.download(documents);
		}
		return isDone;
	}

	@Override
	public void upload(byte[] docData, DocumentBean document) {
		((Document)document).setUploader((Member) securityService.getLoginMember());
		storageService.upload(docData, document);
	}

    @Override
    public DocumentBean createDocument()
    {
        // TODO Auto-generated method stub
        return storageService.createDocument();
    }
}
