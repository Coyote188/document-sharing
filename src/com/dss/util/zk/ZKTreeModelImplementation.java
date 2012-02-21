package com.dss.util.zk;

import org.zkoss.zul.AbstractTreeModel;

@SuppressWarnings("serial")
public class ZKTreeModelImplementation extends AbstractTreeModel{
	/**
	 * Constructor
	 * @param root - the root of tree 
	 */
	@SuppressWarnings("unchecked")
	public ZKTreeModelImplementation(TreeNode root) {
		super(root);
	}
	
	//--TreeModel--//
	@SuppressWarnings("unchecked")
	public Object getChild(Object parent, int index) {
		TreeNode node = (TreeNode)parent;
		return node.getChildAt(index);
	}
	
	//--TreeModel--//
	@SuppressWarnings("unchecked")
	public int getChildCount(Object parent) {
		TreeNode node = (TreeNode)parent;
		return node.getChildCount();
	}
	
	//--TreeModel--//
	@SuppressWarnings("unchecked")
	public boolean isLeaf(Object node) {
		if(node instanceof TreeNode){
			TreeNode node_ = (TreeNode)node;
			return node_.isLeaf();
		}else{
			return true;
		}
	}
}
