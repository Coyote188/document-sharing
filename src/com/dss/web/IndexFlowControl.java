package com.dss.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Window;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.dss.account.service.SecurityService;
import com.dss.storage.bean.DocumentBean;
import com.dss.storage.bean.Searcher;
import com.dss.util.log.LogUtil;
import com.dss.util.zk.ZkUtil;

@Controller("pageFlow")
@Scope("prototype")
/**
 * Control the flow of pages
 */
public class IndexFlowControl
        extends GenericForwardComposer
{
    private static final long serialVersionUID = 6429094220666818446L;

    private Component supportToolbar;
    private Component mainViewHolder;

    private MainViewControl viewController;
    
    @Resource
    private SecurityService security;

    @Override
    public void doAfterCompose(Component comp)
            throws Exception
    {
        super.doAfterCompose(comp);
        LogUtil.debug(getClass(), "main view: " + mainViewHolder.getId());
        viewController = (MainViewControl) ZkUtil.getComponentController(mainViewHolder);
    }

    public void onViewProfileClose$leftToolbar()
    {
        Events.sendEvent(supportToolbar, new Event("onRefreshView", self, null));

    }

    public void onManageDocumentClick$leftToolbar()
            throws SuspendNotAllowedException, InterruptedException
    {
        IndexUtil.doSecurityCheck("onShowManageDocument", self);
    }

    public void onShowManageDocument$frmIndex()
    {
        viewController.addView("管理文档", "/manage-uploaded-document.zul", null);
    }

    public void onPersonalWantedClick$leftToolbar()
            throws InterruptedException
    {
        IndexUtil.doSecurityCheck("onShowPersonalWanted", self);
    }

    public void onShowPersonalWanted$frmIndex()
    {
        viewController.addView("管理悬赏", "/viewMyWanteds.zul", null);
    }

    public void onBrowseDocumentClick$leftToolbar()
    {
        throw new NotImplementedException();
    }

    @SuppressWarnings("unchecked")
    public void onSearch$mainToolbar(ForwardEvent e)
    {
        param.put(Searcher.class, e.getOrigin().getData());
        Component c = viewController.addView("搜索文档", "/search-result.zul", param);
        c.addEventListener("onViewDocumentDetail", this);
    }

    @SuppressWarnings("unchecked")
    public void onViewDocumentDetail(Event e)
    {
        System.out.println("view document detail: " + e.getData());
        param.put(DocumentBean.class, e.getData());
        viewController.addView("文档信息", "/document-detail.zul", param);
    }

    public void onMemberLogin()
    {
        Events.sendEvent(supportToolbar, new Event("onRefreshView", self, null));
    }   

}
