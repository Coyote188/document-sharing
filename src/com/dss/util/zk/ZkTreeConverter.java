package com.dss.util.zk;

import java.util.ArrayList;
import java.util.List;

import com.dss.storage.bean.DocumentDirectoryBean;
import com.dss.storage.bean.DocumentTypeBean;
import com.dss.storage.model.DocumentDirectory;
@SuppressWarnings("unchecked")
public class ZkTreeConverter {
	
	public static TreeNode toTreeNode(DocumentTypeBean bean) {
		if (bean.getChildren() == null || bean.getChildren().size() == 0) {
			return new TreeNode(bean, new ArrayList());
		} else {
			ArrayList subTree = new ArrayList();
			for (DocumentTypeBean inx : bean.getChildren()) {
				subTree.add(toTreeNode(inx));
			}
			TreeNode result = new TreeNode(bean, subTree);
			return result;
		}
	}

	public static TreeNode toTreeNode(DocumentDirectoryBean bean) {
		if (bean.getChildren() == null || bean.getChildren().size() == 0) {
			return new TreeNode(bean, new ArrayList());
		} else {
			ArrayList subTree = new ArrayList();
			for (DocumentDirectoryBean inx : bean.getChildren()) {
				subTree.add(toTreeNode(inx));
			}
			TreeNode result = new TreeNode(bean, subTree);
			return result;
		}
		
	}
	
}
