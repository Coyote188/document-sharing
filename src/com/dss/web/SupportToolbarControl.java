package com.dss.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.dss.account.bean.ProfileBean;
import com.dss.account.service.MemberService;
import com.dss.account.service.SecurityService;
import com.dss.storage.ui.control.IRefreshable;
import com.dss.util.log.LogUtil;

@Controller("supportToolbarControl")
@Scope("prototype")
public class SupportToolbarControl
        extends GenericForwardComposer implements IRefreshable
{
    private static final long serialVersionUID = -4752715757084715117L;
    private Label lblWelcome;
    private Toolbarbutton btnRegister;
    private Toolbarbutton btnLogout;
    private Toolbarbutton btnLogin;
    private ProfileBean profile;

    @Resource
    private MemberService memberService;
    @Resource
    private SecurityService securityService;

    @Override
    public void doAfterCompose(Component comp)
            throws Exception
    {
        super.doAfterCompose(comp);
        refresh();
    }

    public void onClick$btnLogin()
            throws SuspendNotAllowedException, InterruptedException
    {
        IndexUtil.doSecurityCheck("onLoginSuccess",self);
    }

    public void onClick$btnRegister()
            throws SuspendNotAllowedException, InterruptedException
    {
        Window w = (Window) Executions.createComponents("/signupUi.zul", self, null);
        w.addEventListener("onRegisterMemberSuccess", this);
        w.doModal();
    }

    public void onClick$btnLogout()
    {
        if (MessageBoxUtil.isUserAgree("您确定要退出？", "退出")) {
            securityService.loginOff();
            refresh();
        }
    }

    private void showViewWhenNotLogin()
    {
        lblWelcome.setValue(null);
        btnLogin.setVisible(true);
        btnRegister.setVisible(true);
        btnLogout.setVisible(false);

    }

    private void showViewWhenLogin()
    {
        lblWelcome.setValue("欢迎: " + profile.getCalledName());
        btnLogin.setVisible(false);
        btnRegister.setVisible(false);
        btnLogout.setVisible(true);
    }

    /**
     * Handler for register success
     * 
     * @param env
     * @throws InterruptedException
     */
    public void onRegisterMemberSuccess(Event env)
            throws InterruptedException
    {
        LogUtil.debug(getClass(), "auto login for just register member: " + env.getData());
        env.getTarget().detach();
    }


    @Override
    public void refresh()
    {       
        if (securityService.isLogin()) {
            profile = memberService.getProfile();
            showViewWhenLogin();
        } else {
            showViewWhenNotLogin();
        }        
    }
    
    public void onRefreshView(Event e){
        refresh();
    }
}
