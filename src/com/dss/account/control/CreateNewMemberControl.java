package com.dss.account.control;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.dss.account.bean.MemberBean;
import com.dss.account.bean.ProfileBean;
import com.dss.account.service.MemberService;
import com.dss.account.ui.EditableMember;
import com.dss.util.log.LogUtil;

@SuppressWarnings("serial")
@Controller("createNewMemberControl")
@Scope("prototype")
/**
 * Events :
 * onRegisterMemberSuccess : newly register member success, sent with MemberBean as event data
 */
public class CreateNewMemberControl
        extends MemberControl
{
    
    
    private Textbox password2;
    @Resource(name="profile")
    private ProfileBean profile;
    @Resource
    private MemberBean member;
    @Resource
    private MemberService memberService;
    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }
    @Override
    public void doAfterCompose(Component comp)
            throws Exception
    {
        // TODO Auto-generated method stub
        super.doAfterCompose(comp);
//        member = memberService.newMember();        
    }

    public void onClick$memberSubmit()
            throws InterruptedException
    {
        if (!getMember().getPassword().equals(password2.getValue().toString())) {
            throw new WrongValueException(password2, "两次密码不一致，请重新输入！");
        } else {
//            memberService.saveMember(getMember());
        	memberService.registerAsMember(getMember(), getProfile());
            Events.postEvent(new Event("onRegisterMemberSuccess", self, getMember()));
            LogUtil.debug(getClass(), "register success");
        }
    }

	public ProfileBean getProfile() {
		return profile;
	}

}
