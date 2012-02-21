package com.dss.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;

import com.dss.account.service.SecurityService;

@org.springframework.stereotype.Component
@Scope("singleton")
public class IndexUtil
{
    private static final long serialVersionUID = -1255177258327090789L;
    
    private static SecurityService security;
    
    @Autowired(required = true)
    public void setSecurity(SecurityService security)
    {
        IndexUtil.security = security;
    }
    
    public static void doSecurityCheck(final String doWhenOkEvent, final Component target)
            throws InterruptedException
    {
        if (!security.isLogin()) {
            final Window w = (Window) Executions.createComponents("/signinUi.zul", getIndex(), null);           
            w.addEventListener("onLoginSuccess", new EventListener() {
                @Override
                public void onEvent(Event e)
                        throws Exception
                {
                    w.detach();
                    Events.echoEvent("onMemberLogin", getIndex(), null);
                    Events.postEvent(doWhenOkEvent, target, null);
                }
            });
            w.doModal();
        } else {
            Events.postEvent(doWhenOkEvent, target, null);
        }
    }
    
    public static Component getIndex(){
        return Path.getComponent("/frmIndex");
    }
    public static Component getMainView(){
        return Path.getComponent("/mainViewHolder");
    }

}
