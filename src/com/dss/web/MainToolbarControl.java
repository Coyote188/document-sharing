package com.dss.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Window;

import com.dss.storage.bean.DocumentBean;
import com.dss.storage.bean.SearchOptions;
import com.dss.storage.bean.Searcher;
import com.dss.storage.service.StorageService;
import com.dss.util.log.LogUtil;
import com.dss.util.zk.ZkUtil;

@Controller("mainToolbarControl")
@Scope(value = "prototype")
/**
 * Controller class to create search page.
 * Events:
 * onSearch, with value of input text.
 */
public class MainToolbarControl
        extends GenericForwardComposer
{

    private static final long serialVersionUID = -5957022608435395260L;
    private Bandbox searchInput;
    private Radiogroup btnSearchOptions;
    int searcherId = 0;

    @Resource
    private StorageService service;
    private Searcher<DocumentBean> currentSearchOption;
    private SearchOptions<DocumentBean> searchOptions;

    @Override
    public void doAfterCompose(Component comp)
            throws Exception
    {
        super.doAfterCompose(comp);
        searchOptions = service.getSearchOptions();
        searcherId = 0;
        btnSearchOptions.setSelectedIndex(0);
       
    }

    public void onClick$btnSearch()
    {
        LogUtil.debug(getClass(), "search button is clicked with options: "
                + btnSearchOptions.getSelectedItem().getLabel());
        currentSearchOption = searchOptions.getSearcher(searcherId);
        currentSearchOption.setKeyWord(searchInput.getValue());
        Events.postEvent("onSearch", self, currentSearchOption);
//        param.put(Searcher.class,currentSearchOption);
//        MainViewControl main = (MainViewControl) ZkUtil.getComponentController(IndexUtil.getMainView());
//        main.addView("ËÑË÷ÎÄµµ", "/search-result.zul", param);
    }

    public void onClick$btnUpload()
            throws SuspendNotAllowedException, InterruptedException
    {
        IndexUtil.doSecurityCheck("onShowUpload",self);
    }

    public void onShowUpload$mainToolbar()
            throws InterruptedException
    {
        LogUtil.debug(getClass(), "upload button is clicked");
        Window w = (Window) Executions.createComponents("/documentUploadUi.zul", self, null);
        w.addEventListener("onUploadFinish", this);
        w.doModal();
    }

    public void onClick$btnViewCart()
            throws SuspendNotAllowedException, InterruptedException
    {
        LogUtil.debug(getClass(), "show cart button is clicked");
        Window w = (Window) Executions.createComponents("/shoppingcart-view.zul", self, null);
        w.doModal();

    }

    public void onCheck$btnSearchByTitle()
    {
        LogUtil.debug(getClass(), "search by title");
        searcherId = 0;
        searchInput.close();
    }

    public void onCheck$btnSearchByTags()
    {
        LogUtil.debug(getClass(), "search by tags");
        searcherId = 1;
        searchInput.close();
    }

    public void onCheck$btnSearchByDescription()
    {
        LogUtil.debug(getClass(), "search by description");
        searcherId = 2;
        searchInput.close();
    }

    public void onUploadFinish(Event e)
    {
        e.getTarget().detach();
    }
}
