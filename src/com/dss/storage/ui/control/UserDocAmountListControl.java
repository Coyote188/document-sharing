package com.dss.storage.ui.control;

import java.util.List;

import javax.annotation.Resource;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.*;

import com.dss.account.bean.MemberBean;
import com.dss.storage.service.DocumentService;

@SuppressWarnings({"serial","unchecked","unused"})
public class UserDocAmountListControl extends GenericForwardComposer implements EventListener{

//	@Resource
//	private DocumentService dService;
//	private List<UserRank> urList;
//	
//	private Window posterDocsWindow;
//	
//	@Override
//	public void doAfterCompose(Component comp) throws Exception {
//		// TODO Auto-generated method stub
//		super.doAfterCompose(comp);
//		setUrList(dService.getAllRank());
//	}
//	
//	/**
//	 * binding page events
//	 * @author ´ÞÁ¢¸Õ
//	 *
//	 */
//	public void viewPosterDocs(MemberBean member){
//		param.put("poster", member);
//		posterDocsWindow = (Window) Executions.createComponents("viewposterdocs.zul", null, param);
//	}
//	
//	private class UserListRenderer implements RowRenderer{
//
//		@Override
//		public void render(Row row, Object data) throws Exception {
//			// TODO Auto-generated method stub
//			final UserRank ur = (UserRank) data;
//			final Div divRankUsername = new Div();
//			divRankUsername.setAlign("center");
//			final Label labRank = new Label();
//			labRank.setValue(String.valueOf(ur.getRank()));
//			labRank.setParent(divRankUsername);
//			final Label labUsername = new Label();
//			labUsername.setValue(ur.getMember().getUsername());
//			labUsername.setParent(divRankUsername);
//			divRankUsername.setParent(row);
//			final Label labDocAmount = new Label();
//			labDocAmount.setValue(String.valueOf(ur.getDocs()));
//			labDocAmount.setParent(row);
//			final Label labPoint = new Label();
//			labPoint.setValue(String.valueOf(ur.getCurrentPoint()));
//			labPoint.setParent(row);
//			
//			final Button btnViewPosterDocList = new Button("²é¿´");
//			btnViewPosterDocList.addEventListener(Events.ON_CLICK, new EventListener() {
//				
//				@Override
//				public void onEvent(Event event) throws Exception {
//					// TODO Auto-generated method stub
//					viewPosterDocs(ur.getMember());
//				}
//			});
//			
//		}
//		
//	}
//
//	public List<UserRank> getUrList() {
//		return urList;
//	}
//
//	public void setUrList(List<UserRank> urList) {
//		this.urList = urList;
//	}

}
