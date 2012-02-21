package com.dss.account.control;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.dss.account.bean.MemberBean;
import com.dss.account.ui.EditableMember;
import com.dss.util.log.LogUtil;

@SuppressWarnings("serial")
@Controller("authenticateControl")
@Scope("prototype")
/**
 * Events:
 * onLoginSuccess - when login is success, sent with current login user as event data
 * 
 */
public class AuthenticateControl
        extends MemberControl
{
    private EditableMember authenticateMember;
    private Window saveMemberWindow;

    public EditableMember getAuthenticateMember()
    {
        return authenticateMember;

    }

    public void setAuthenticateMember(EditableMember authenticateMember)
    {
        this.authenticateMember = authenticateMember;
    }

    @Override
    public void doAfterCompose(Component comp)
            throws Exception
    {
        super.doAfterCompose(comp);
        authenticateMember = new EditableMember();
    }

    public void onClick$btnRegister()
            throws SuspendNotAllowedException, InterruptedException
    {
        saveMemberWindow = (Window) Executions.createComponents("/signupUi.zul", self, null);
        saveMemberWindow.addEventListener("onRegisterMemberSuccess", this);
        saveMemberWindow.doModal();
    }

    public void onRegisterMemberSuccess(Event env)
            throws InterruptedException
    {
        LogUtil.debug(getClass(), "auto login for just register member: "+ env.getData());
        setAuthenticateMember((EditableMember) env.getData());
        env.getTarget().detach();        
        authenticate();
    }

    public void onClick$btnAuthenticate()
            throws InterruptedException
    {
        LogUtil.debug(getClass(), "controller of this page: "+ this);
        authenticate();
    }

    private void authenticate()
            throws InterruptedException
    {
        LogUtil.debug(getClass(),"authenticate: "+authenticateMember.getPassword() );
        boolean status = securityService.authenticate(getAuthenticateMember());
        if (!status) {
            Messagebox.show("账户信息错误，请重新输入！");
            reshow();
        } else {
            Events.postEvent(new Event("onLoginSuccess", self, getAuthenticateMember()));
        }
    }
   

}
