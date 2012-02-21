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
     * ע���Ա,��ʼ����Ա��Ϣ
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
     * ��Ա�����˻�������ֵ�����ִ��� 1����ֵ 2���ӻ��� 3������ 4��ʹ�û��� 5�������µĻ����˻� 6�������µĻ����˻�
     * 7���õ�cashAccount�˻�
     */

    public void fill(Double money)
    {
        Member member = (Member) securityService.getLoginMember();
        // System.out.println("test in memberservice" + money);
        member.getCashAccount().fill(money, member);

    }

    /**
     * ��ǰ�û�������������
     * 
     * @param point
     */
    @SuppressWarnings("unused")
    private void gainCash(Double point)
    {
        cashAccount.gainCash(point, (Member) securityService.getLoginMember());
    }

    /**
     * �ĵ��������û���׬ȡ�Ļ���
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
     * �����ĵ��ӿ�
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
