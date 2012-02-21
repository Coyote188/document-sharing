package com.dss.util.zk;

import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;

public class ZkUtil
{
    public static Object getComponentController(Component c)
    {
        return c.getAttribute(c.getId() + "$" + "composer");
    }

   
}
