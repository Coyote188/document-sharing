package com.dss.storage.ui.control;

import java.awt.Rectangle;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;

import com.dss.storage.bean.DocumentBean;
import com.dss.storage.bean.DocumentDirectoryBean;
import com.dss.storage.model.Document;
import com.dss.storage.model.DocumentDirectory;
import com.dss.storage.service.ManageDocumentService;
import com.dss.storage.service.StorageService;
import com.dss.util.log.LogUtil;
import com.dss.util.zk.TreeNode;
import com.dss.util.zk.ZKTreeModelImplementation;
import com.dss.util.zk.ZkTreeConverter;

@SuppressWarnings("serial")
@Controller("documentDirectoryController")
@Scope("prototype")
/**
 * Events
 * onSelectDirectory :fired when a directory is selected, with current directory as data
 */
public class DocumentDirectoryControl
        extends GenericForwardComposer
        implements IRefreshable, IItemContainer<DocumentDirectoryBean>
{

    private ZKTreeModelImplementation model;
    private Tree directorySelection;
    private Listbox rootDirectoryList;

    @Resource
    private ManageDocumentService service;
    private DocumentDirectoryBean currentDirectory;
    private DocumentDirectoryBean rootDirectory;

    @Override
    public void doAfterCompose(Component comp)
            throws Exception
    {
        super.doAfterCompose(comp);
        rootDirectory = service.getRootDocumentDirectory();
        currentDirectory = rootDirectory;
        model = new ZKTreeModelImplementation(ZkTreeConverter.toTreeNode(currentDirectory));
        directorySelection.setModel(model);
        directorySelection.setTreeitemRenderer(new DirectoryTreeRenderer());
        rootDirectoryList.selectItem(rootDirectoryList.getItemAtIndex(0));
    }

    public DocumentDirectoryBean getRoot()
    {
        return rootDirectory;
    }

    @Override
    public DocumentDirectoryBean getCurrentItem()
    {
        // TODO Auto-generated method stub
        return currentDirectory;
    }

    public void refresh()
    {
         currentDirectory = service.getRootDocumentDirectory();
        model = new ZKTreeModelImplementation(ZkTreeConverter.toTreeNode(currentDirectory));
        directorySelection.setModel(model);
        directorySelection.setSelectedItem(null);
        rootDirectoryList.selectItem(rootDirectoryList.getItemAtIndex(0));
    }

    public void onSelect$directorySelection(DocumentDirectoryBean directory)
    {
        rootDirectoryList.clearSelection();
        currentDirectory = directory;
        Events.postEvent(new Event("onSelectDirectory", self, directory));
        LogUtil.debug(getClass(), "directory is selected: " + directory);
    }

    public void onSelect$rootDirectoryList(SelectEvent e)
    {
        directorySelection.clearSelection();
        currentDirectory = rootDirectory;
        Events.postEvent(new Event("onSelectDirectory", self, rootDirectory));
        LogUtil.debug(getClass(), "directory is selected: " + rootDirectory);
    }

    private class DirectoryTreeRenderer
            implements TreeitemRenderer
    {

        @Override
        public void render(Treeitem item, Object data)
                throws Exception
        {
            TreeNode t = (TreeNode) data;
            final DocumentDirectoryBean directory = (DocumentDirectoryBean) t.getData();
            // Contruct treecells
            Treecell tcName = new Treecell(directory.getName());
            // Treecell tcAdd = new Treecell();
            // Treecell tcRemove = new Treecell();
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
            // Add button
            // Button btnAdd = new Button();
            // Toolbarbutton btnAdd= new Toolbarbutton();
            // btnAdd.setImage("/img/plus_button.jpg");
            // btnAdd.addEventListener(Events.ON_CLICK, new EventListener() {
            //
            // @Override
            // public void onEvent(Event arg0) throws Exception {
            // // TODO Auto-generated method stub
            // onAddNewDirectory(directory);
            // }
            // });
            // Button btnRemove = new Button("remove");
            // Toolbarbutton btnRemove= new Toolbarbutton();
            // btnRemove.setImage("/img/minus_button.jpg");
            // btnRemove.addEventListener(Events.ON_CLICK, new EventListener() {
            //
            // @Override
            // public void onEvent(Event arg0) throws Exception {
            // // TODO Auto-generated method stub
            // onRemoveDirectory(directory);
            // }
            // });
            // tcAdd.appendChild(btnAdd);
            // tcRemove.appendChild(btnRemove);
            // Attach treecells to treerow
            tcName.setParent(tr);
            // tcAdd.setParent(tr);
            // tcRemove.setParent(tr);

            bindingOnSelectDirectoryEvent(tcName, directory);
            // bindingOnSelectDirectoryEvent(tcAdd, directory);
            // bindingOnSelectDirectoryEvent(tcRemove, directory);
            item.setOpen(false);

        }

        private void bindingOnSelectDirectoryEvent(Treecell tc,
                final DocumentDirectoryBean directory)
        {
            tc.addEventListener(Events.ON_CLICK, new EventListener() {
                public void onEvent(Event event)
                        throws Exception
                {
                    onSelect$directorySelection(directory);
                }
            });
        }

    }

}
