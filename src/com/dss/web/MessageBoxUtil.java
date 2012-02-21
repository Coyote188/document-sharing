package com.dss.web;

import org.zkoss.zul.Messagebox;

public class MessageBoxUtil
{

    public static boolean message()
            throws InterruptedException
    {
        int reply = Messagebox
                .show("����δ��¼ϵͳ���Ƿ����ڵ�¼?", "��¼ ?", Messagebox.YES | Messagebox.NO, null);
        if (reply == Messagebox.YES) {
            return true;
        } else {
            return false;
        }
    }

    public boolean message(String str)
            throws InterruptedException
    {
        int reply = Messagebox.show(str, "��ʾ !", Messagebox.YES | Messagebox.NO, null);
        if (reply == Messagebox.YES) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean isUserAgree(String message, String head)
    {
        int reply = 0;
        try {
            reply = Messagebox.show(message, head, Messagebox.YES | Messagebox.NO, null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (reply == Messagebox.YES) {
            return true;
        } else {
            return false;
        }

    }
 
}
