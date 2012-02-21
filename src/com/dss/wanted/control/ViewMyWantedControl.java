package com.dss.wanted.control;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import com.dss.account.service.SecurityService;
import com.dss.wanted.bean.WantedBean;
import com.dss.wanted.service.WantedService;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component("viewMyWantedControl")
@Scope("prototype")
public class ViewMyWantedControl
        extends GenericForwardComposer
{
    
    @Resource(name = "wantedService")
    private WantedService wantedService;
    @Resource(name = "SecurityService")
    private SecurityService securityService;
    
    private List<WantedBean> wanteds;

    
    protected List<WantedBean> getWanteds()
    {
        return wanteds;
    }

    @Override
    public void doAfterCompose(Component comp)
            throws Exception
    {
        super.doAfterCompose(comp);
        wanteds=wantedService.findMyWanteds(securityService.getLoginMember());
    }
}
