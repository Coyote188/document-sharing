package com.dss.storage.service.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.dss.account.bean.MemberBean;
import com.dss.account.model.Member;
import com.dss.account.service.SecurityService;
import com.dss.storage.bean.DocumentBean;
import com.dss.storage.bean.DocumentContentBean;
import com.dss.storage.bean.DocumentDirectoryBean;
import com.dss.storage.bean.DocumentTypeBean;
import com.dss.storage.model.Document;
import com.dss.storage.model.DocumentContent;
import com.dss.storage.model.DocumentDirectory;
import com.dss.storage.model.DocumentType;
import com.dss.storage.repository.DocumentDirectoryRepository;
import com.dss.storage.repository.DocumentTypeRepository;
import com.dss.storage.service.ManageDocumentService;
import com.dss.util.log.LogUtil;

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ManageDocumentServiceImpl implements ManageDocumentService {
	@Resource
	private DocumentDirectoryRepository directoryRepo;
	@Resource
	private DocumentTypeRepository typeRepo;
	@Resource
	private SecurityService sService;

	// private DocumentDirectory dir;

	@Override
	public DocumentTypeBean getRootDocumentType() {

		return typeRepo.getRootDocumentType();
	}

	@Override
	public void updateDocument(DocumentBean data, DocumentTypeBean type,
			DocumentDirectoryBean directory) {
		Document document = (Document) data;
		document.save((DocumentType) type, (DocumentDirectoryBean) directory);
	}

	@Override
	public DocumentContentBean getDocumentContent(DocumentBean currentDocument) {
		Document d = (Document) currentDocument;
		return d.retrieveContent();
	}

	/**
	 * directory
	 * 
	 * @return
	 */
	public DocumentDirectoryBean createDirectory() {
		return directoryRepo.newDirectory();
	}

	public void createDefaultDirectory(MemberBean member) {
		DocumentDirectory dir = (DocumentDirectory) createDirectory();
		dir.setName("д╛хо");
		dir.setMember((Member) member);
		dir.save();
	}

	public void createSubDirectory(DocumentDirectoryBean dir) {
		Member member = (Member) sService.getLoginMember();
		DocumentDirectory directory = (DocumentDirectory) dir;
		DocumentDirectoryBean rootDir = directoryRepo.getDirectory(member);
		directory.setParent((DocumentDirectory) rootDir);
		directory.setMember(member);
		directory.save();
	}

	public void updateDirectory(DocumentDirectoryBean dir) {
		DocumentDirectory directory = (DocumentDirectory) dir;
		directory.save();
	}

	public void removeDirectory(DocumentDirectoryBean dir) {
		DocumentDirectory directory = (DocumentDirectory) dir;
		directory.remove();
	}

	public List<DocumentDirectoryBean> findAllDirectory() {
		return directoryRepo
				.findDirByMember((Member) sService.getLoginMember());
	}

	@Override
	public DocumentDirectoryBean getRootDocumentDirectory() {
		if (sService.isLogin()) {
			return directoryRepo.getDirectory((Member) sService
					.getLoginMember());
		}
		else {
			return null;
		}
	}

    @Override
    public void updateDirectory(DocumentDirectoryBean parent, DocumentDirectoryBean dir)
    {
        DocumentDirectory directory = (DocumentDirectory) dir;
        DocumentDirectory p=(DocumentDirectory) parent;
        p.addChild(directory);
//        directory.setParent((DocumentDirectory) parent);
        directory.setMember((Member) sService.getLoginMember());
        directory.save();
    }

}
