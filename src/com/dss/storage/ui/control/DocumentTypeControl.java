package com.dss.storage.ui.control;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;

import com.dss.storage.bean.DocumentTypeBean;
import com.dss.storage.service.ManageDocumentService;
import com.dss.storage.service.StorageService;
import com.dss.util.zk.TreeNode;
import com.dss.util.zk.ZKTreeModelImplementation;
import com.dss.util.zk.ZkTreeConverter;

@Controller("documentTypeController")
@Scope("prototype")
/**
 * Render DocumentTypeBean as a tree.
 * Events:
 * onSelectType, current DocumentTypeBean as data
 */
public class DocumentTypeControl extends GenericForwardComposer {

// private ZKTreeModelImplementation model;
	private Tree typeSelection;
	@Resource
	private ManageDocumentService service;
	private boolean isEdittable;
	public final static String EDITTABLE_MODE = "DocumentTypeEdittable";

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);		
		isEdittable = isEditThisType();
		ZKTreeModelImplementation model;
		model = constructModelForTree(service.getRootDocumentType());
		constructTree(model);
	}

	private void constructTree(ZKTreeModelImplementation model) {
		typeSelection.setModel(model);
		typeSelection.setTreeitemRenderer(new TypeTreeRenderer());
	}

	private ZKTreeModelImplementation constructModelForTree(
			DocumentTypeBean type) {
		return new ZKTreeModelImplementation(ZkTreeConverter.toTreeNode(type));
	}

	private boolean isEditThisType() {
		if (arg.get(EDITTABLE_MODE) == null)
			return false;
		else
			return true;
	}

	private class TypeTreeRenderer implements TreeitemRenderer {

		@Override
		public void render(Treeitem item, Object data) throws Exception {
			TreeNode t = (TreeNode) data;
			DocumentTypeBean type = (DocumentTypeBean) t.getData();
			// Contruct treecells

			Treerow tr = null;
			/*
			 * Since only one treerow is allowed, if treerow is not null, append
			 * treecells to it. If treerow is null, contruct a new treerow and
			 * attach it to item.
			 */
			if (item.getTreerow() == null) {
				tr = new Treerow();
				tr.setParent(item);
			} else {
				tr = item.getTreerow();
				tr.getChildren().clear();
			}
			createName(type, tr);
			if (isEdittable) {
				createAddButton(type, tr);
				createRemoveButton(type, tr);
			}
			// Attach treecells to treerow

			item.setOpen(false);

		}
		private void createRemoveButton(DocumentTypeBean type, Treerow tr) {
			Treecell tcRemove = new Treecell();			
			tcRemove.appendChild(new Button("remove"));			
			tcRemove.setParent(tr);			
			bindingOnSelectDirectoryEvent(tcRemove, type);
		}
		private void createAddButton(DocumentTypeBean type, Treerow tr) {
			Treecell tcAdd = new Treecell();
			tcAdd.appendChild(new Button("add"));
			tcAdd.setParent(tr);
			bindingOnSelectDirectoryEvent(tcAdd, type);
		}
		private void createName(DocumentTypeBean type, Treerow tr) {
			Treecell tcName = new Treecell(type.getName());
			tcName.setParent(tr);
			bindingOnSelectDirectoryEvent(tcName, type);
		}
		private void bindingOnSelectDirectoryEvent(Treecell tc,
				final DocumentTypeBean type) {
			tc.addEventListener(Events.ON_CLICK, new EventListener() {
				public void onEvent(Event event) throws Exception {
					Events.postEvent(new Event("onSelectType", self, type));
				}
			});
		}

	}
}
