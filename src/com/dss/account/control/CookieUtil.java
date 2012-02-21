package com.dss.account.control;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.zk.ui.*;

public class CookieUtil {

//	public static String getToken() {
//		Session s = Sessions.getCurrent();
//		return	(String) s.getAttribute("token");
//	    return getCookie("token");
//	}
	
//	public static void removeToken(){
//	    Session s = Sessions.getCurrent();
//       s.removeAttribute("token");
//	}
	public static void setCookie(String name, String value) {
	    Cookie newCookie = new Cookie(name, value);
        ((HttpServletResponse) Executions.getCurrent().getNativeResponse()).addCookie(newCookie);
    }

    public static String getCookie(String name) {
        Cookie[] cookies = ((HttpServletRequest) Executions.getCurrent().getNativeRequest())
                .getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }    
   
}
