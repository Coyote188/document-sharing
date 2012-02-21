package com.dss.util.zk;

import java.util.List;

public class TreeNode {
	private Object  data;

	private List children;
	
	/**
	 * Contructor
	 * @param data  data of the receiver
	 * @param children children of the receiver
	 * <br>
	 * Notice: Only <code>TreeNode</code> can be contained in The List <code>children</code>
	 */
	public TreeNode(Object  data, List children) {
		this.data = data;
		this.children = children;
	}

	/**
	 * Return data of the receiver
	 * 
	 * @return data of the receiver
	 */
	public Object  getData() {
		return data;
	}

	/**
	 * Return children of the receiver
	 * 
	 * @return children of the receiver
	 */
	public List getChildren() {
		return children;
	}

	/**
	 * Return data.toString(). If data is null, return String "Data is null"
	 * 
	 * @return data.toString(). If data is null, return String "Data is null"
	 */
	@Override
	public String toString() {
		return (data == null) ? "Data is null" : data.toString();
	}

	/**
	 * Returns true if the receiver is a leaf.
	 * 
	 * @return true if the receiver is a leaf.
	 */
	public boolean isLeaf() {
		return (children.size() == 0);
	}

	/**
	 * Returns the child at index childIndex.
	 * 
	 * @return the child at index childIndex.
	 */
	public Object  getChildAt(int childIndex) {
		return children.get(childIndex);
	}

	/**
	 * Returns the number of children the receiver contains.
	 * 
	 * @return the number of children the receiver contains.
	 */
	public int getChildCount() {
		return children.size();
	}
}
