package com.dss.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Window;

import com.dss.util.log.LogUtil;

@Controller("functionToolbarControl")
@Scope(value = "prototype")
/**
 * Event:
 * onViewProfileClose :fire when the view profile window is closed.
 * onManageDocumentClick : fire when button my document is clicked.
 * onPersonalWantedClick : fire when button view my wanted is clicked.
 * onBrowseDocumentClick : fire when button browse document is clicked.
 */
public class FunctionToolbarControl
        extends GenericForwardComposer
{
    private static final long serialVersionUID = -1747486456502930987L;

    public void onClick$btnBrowseDocument()
    {
        LogUtil.debug(getClass(), "btnBrowseDocument is clicked");
        Events.sendEvent(new Event("onBrowseDocumentClick", self, null));
    }

    public void onClick$btnFillAccount()
            throws SuspendNotAllowedException, InterruptedException
    {
        IndexUtil.doSecurityCheck("onShowFill",self);
    }

    public void onShowFill$leftToolbar()
            throws InterruptedException
    {
        LogUtil.debug(getClass(), "btnFillAccount is clicked");
        Window w = (Window) Executions.createComponents("/fill.zul", self, null);
        w.doModal();
    }

    public void onClick$btnExchangeCash()
            throws SuspendNotAllowedException, InterruptedException
    {
        IndexUtil.doSecurityCheck("onShowExchange",self);
    }

    public void onShowExchange$leftToolbar()
            throws InterruptedException
    {
        LogUtil.debug(getClass(), "btnExchangeCash is clicked");
        Window w = (Window) Executions.createComponents("/exchangeWindow.zul", self, null);
        w.doModal();
    }

    public void onClick$btnCreateWanted()
            throws SuspendNotAllowedException, InterruptedException
    {
        IndexUtil.doSecurityCheck("onShowNewWanted",self);
    }

    public void onShowNewWanted$leftToolbar()
            throws InterruptedException
    {
        LogUtil.debug(getClass(), "btnCreateWanted is clicked");
        Window w = (Window) Executions.createComponents("/createNewWanted.zul", self, null);
        w.doModal();
    }

    public void onClick$btnMyWanted()
    {
        LogUtil.debug(getClass(), "btnMyWanted is clicked");
        Events.sendEvent(new Event("onPersonalWantedClick", self, null));
    }

    public void onClick$btnMyDocument()
    {
        LogUtil.debug(getClass(), "btnMyDocument is clicked");
        Events.sendEvent(new Event("onManageDocumentClick", self, null));
    }

    public void onClick$btnMyInfo()
            throws SuspendNotAllowedException, InterruptedException
    {
        IndexUtil.doSecurityCheck("onShowProile",self);
    }

    public void onShowProile$leftToolbar()
            throws InterruptedException
    {
        LogUtil.debug(getClass(), "btnMyInfo is clicked");
        Window w = (Window) Executions.createComponents("/viewProfile.zul", self, null);
        w.addForward("onClose", self, "onViewProfileClose");
        w.doModal();
    }

}
