package com.dss.web;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;

@Controller("mainViewControl")
@Scope("prototype")
public class MainViewControl
        extends GenericForwardComposer
{

    private static final long serialVersionUID = 4545970622234246674L;

//    private Tabbox mainViewHolder;
    private Map<String, Integer> tabCreated;

    @Override
    public void doAfterCompose(Component comp)
            throws Exception
    {
        super.doAfterCompose(comp);
        tabCreated = new ConcurrentHashMap<String, Integer>();
//        desktop=Executions.getCurrent().getDesktop();
    }
    
    private Tabbox getViewHolder(){
        return (Tabbox) self;
    }

    @SuppressWarnings("unchecked")
    public Component addView(String name, String uri, Map data)
    {
        Component result = null;
        if (isViewExisted(uri)) {
            result = refreshOldView(uri, data);
        } else {           
            createTabHead(name);
            Tabpanel newPanel = createTabPanel();
            result = createView(uri, data, newPanel);
            logViewCreated(uri);
        }

        return result;
    }

    private boolean isViewExisted(String uri)
    {
        return tabCreated.containsKey(uri);
    }

    private Component createView(String uri, Map data, Tabpanel newPanel)
    {
        Component result;
        result = Executions.createComponents(uri, newPanel, data);
        getViewHolder().setSelectedPanel(newPanel);
        return result;
    }

    private Tabpanel createTabPanel()
    {
        Tabpanel newPanel = new Tabpanel();
        newPanel.setParent( getViewHolder().getTabpanels());
        return newPanel;
    }

    private void createTabHead(String name)
    {
        Tab newTab = new Tab(name);
        newTab.setClosable(true);
//        System.out.println("mainViewHolder: "+mainViewHolder.getTabs());
        newTab.setAttribute("tabId", tabCreated.size());
        newTab.addForward("onClose", self, "onCloseTab", newTab.getAttribute("tabId"));
        
        getViewHolder().getTabs().appendChild(newTab);
    }

    private void logViewCreated(String uri)
    {
        tabCreated.put(uri, tabCreated.size() + 1);
    }

    private Component refreshOldView(String uri, Map data)
    {
        Tabpanel panel = (Tabpanel)  getViewHolder().getTabpanels().getChildren().get(
                tabCreated.get(uri));
        panel.getFirstChild().detach();
        return createView(uri, data, panel);
    }

    public void onCloseTab$mainViewHolder(ForwardEvent e)
            throws SuspendNotAllowedException, InterruptedException
    {

        removeFromTabCreatedLog((Integer) e.getOrigin().getData());
    }

    private void removeFromTabCreatedLog(Integer index) throws SuspendNotAllowedException, InterruptedException
    {
            for (String i : tabCreated.keySet()) {
                if (tabCreated.containsValue(index)) {
                    tabCreated.remove(i);
                }
            }
    }
}
