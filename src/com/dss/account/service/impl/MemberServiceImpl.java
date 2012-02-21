package com.dss.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dss.account.bean.CashAccountBean;
import com.dss.account.bean.MemberBean;
import com.dss.account.bean.ProfileBean;
import com.dss.account.model.CashAccount;
import com.dss.account.model.Member;
import com.dss.account.repository.CashAccountRepository;
import com.dss.account.repository.MemberRepository;
import com.dss.account.service.MemberService;
import com.dss.account.service.SecurityService;
import com.dss.storage.service.ManageDocumentService;

@SuppressWarnings( { "unchecked", "null" })
@Service("memberService")
@Transactional
public class MemberServiceImpl
        implements MemberService
{
    @Resource
    private MemberRepository memberRepository;
    @Resource(name = "member")
    private Member member;
    @Resource(name = "cashAccount")
    private CashAccount cashAccount;
    @Resource
    private SecurityService securityService;
    @Resource(name = "cashAccountRepository")
    private CashAccountRepository cashAccountRepository;
    @Resource
    private ManageDocumentService mService;

    public MemberRepository getMemberRepository()
    {
        return memberRepository;
    }

    public void setMemberRepository(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberBean newMember()
    {
        return memberRepository.newMember();
    }

    /**
     * 注册会员,初始化会员信息
     */

    // public void saveMember(MemberBean member){
    // this.member.inputData(member);
    // memberRepository.saveMember(this.member);
    // CashAccountBean cashAccount = newCashAccount();
    // cashAccount.setMember(this.member);
    // saveCashAccount(cashAccount);
    // mService.createDefaultDirectory(this.member);
    // }

    public boolean saveMember(MemberBean member)
    {
        List result = memberRepository.isUsernameExist(member.getUsername());
        if (result.isEmpty()) {
            memberRepository.saveMember(member);
            CashAccountBean cashAccount = newCashAccount();
            cashAccount.setMember((Member) member);
            saveCashAccount(cashAccount);
            mService.createDefaultDirectory(member);
            return true;
        } else {
            return false;
        }
    }

    public void saveProfile(ProfileBean profile)
    {
        System.out.println("test profile is null" + profile.getMember());
        memberRepository.saveProfile(profile);
    }
    
    public boolean registerAsMember(MemberBean member,ProfileBean profile){
    	boolean isDone = saveMember(member);
    	profile.setMember(member);
    	saveProfile(profile);
    	return isDone;
    }

    @Override
    public ProfileBean getProfile()
    {
        System.out.println(securityService == null ? true : false);
        if (securityService.getLoginMember() == null)
            return null;
        else
            return memberRepository.getProfile(securityService.getLoginMember());
    }

    @Override
    public ProfileBean newProfile()
    {
        return memberRepository.newProfile();
    }

    @Override
    public void updateProfile(ProfileBean profile)
    {
        memberRepository.updateProfile(profile);
    }

    /**
     * 会员积分账户处理及充值、提现处理 1、充值 2、加积分 3、提现 4、使用积分 5、创建新的积分账户 6、保存新的积分账户
     * 7、得到cashAccount账户
     */

    public void fill(Double money)
    {
        Member member = (Member) securityService.getLoginMember();
        // System.out.println("test in memberservice" + money);
        member.getCashAccount().fill(money, member);

    }

    /**
     * 当前用户操作奖励积分
     * 
     * @param point
     */
    @SuppressWarnings("unused")
    private void gainCash(Double point)
    {
        cashAccount.gainCash(point, (Member) securityService.getLoginMember());
    }

    /**
     * 文档被下载用户所赚取的积分
     * 
     * @param point
     * @param documentOwner
     */
    @Override
    public void gainCash(double point, Member documentOwner)
    {
        cashAccount.gainCash(point, documentOwner);
    }

    @Override
    public boolean exchange(Double point)
    {
        System.out.println("test in memberservice exchange!" + point);
        cashAccount.getCurrentPoint((Member) securityService.getLoginMember());
        return cashAccount.exchange(point, (Member) securityService.getLoginMember());
    }

    @Override
    public boolean useCash(Double point)
    {
        return cashAccount.useCash(point, (Member) securityService.getLoginMember());
    }

    @Override
    public CashAccountBean newCashAccount()
    {
        return cashAccountRepository.newCashAccount();
    }

    @Override
    public void saveCashAccount(CashAccountBean cashAccount)
    {
        // cashAccount.setMember((Member)securityService.getLoginMember());
        cashAccountRepository.save(cashAccount);
    }

    @Override
    public CashAccountBean getCashAccount()
    {
        return cashAccountRepository.getCashAccount((Member) securityService.getLoginMember());
    }

    /**
     * 下载文档接口
     * 
     * @param point
     * @param documentOwner
     * @return the cash is enough?
     */
    @Override
    public boolean downloadDocument(double point, Member documentOwner)
    {
        boolean isDone = useCash(point);
        if (isDone) {
            gainCash(point, documentOwner);
        }
        return isDone;

    }

    @Override
    public List<CashAccountBean> getAllCashAccount()
    {
        List<CashAccount> cashList = cashAccountRepository.findAll();
        List<CashAccountBean> result = null;
        for (CashAccount cashAccount : cashList) {
            result.add(cashAccount);
        }
        return result;
    }

}
