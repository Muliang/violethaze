package violethaze.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import violethaze.message.response.Article;
import violethaze.message.response.NewsResponseMessage;
import violethaze.message.response.TextResponseMessage;
import violethaze.util.MessageUtil;

/**
 * core service for processing request
 * 
 * @author violethaze
 *
 */
public class CoreService {

	public static String processRequest(HttpServletRequest request) {
		// XML string returned to wechat app client
		String respMessage = null;

		try {
			String responseContent = "default message, cannot process your request";

			Map<String, String> requestElements = MessageUtil.parseXML(request);

			// get elements;
			String fromUserName = requestElements.get("FromUserName");
			String toUserName = requestElements.get("ToUserName");
			String msgType = requestElements.get("MsgType");
			
			//Potential bug
			String content = requestElements.get("Content");

			// create a text response
//			TextResponseMessage textMessage = new TextResponseMessage(
//					toUserName, fromUserName);
			
			//for debug
			NewsResponseMessage newsMsg = new NewsResponseMessage();
			
			if (msgType.equals(MessageUtil.REQUEST_MSG_TYPE_TEXT)) {
//				responseContent = "你好" + content + ", 欢迎使用Diaosi微信自动回复系统";
				
				/************ test news response ************/
				
				
				newsMsg.setMsgType(MessageUtil.RESPONSE_MSG_TYPE_NEWS);
				newsMsg.setFromUserName(toUserName);
				newsMsg.setToUserName(fromUserName);
	
				newsMsg.setCreateTime(new Date().getTime() / 1000);
				newsMsg.setArticleCount(2);
				newsMsg.setArticles(new ArrayList<Article>());
				
				Article a0 = new Article();
				Article a1 = new Article();
				
				a0.setDescription("text 0	" + content);
				a0.setTitle("Zero");
				a0.setPicUrl("http://img5.douban.com/view/photo/large/public/p2166606146.jpg");
				a0.setUrl("http://www.sina.com.cn");
				
				a1.setDescription("text 1	" + content);
				a1.setTitle("One");
				a1.setPicUrl("http://img5.douban.com/view/photo/large/public/p2167824408.jpg");
				a1.setUrl("http://www.163.com");
				
				newsMsg.getArticles().add(a0);
				newsMsg.getArticles().add(a1);
				/************ end ************/
				
			} else if (msgType.equals(MessageUtil.REQUEST_MSG_TYPE_LOCATION)) {
				responseContent = "location";
			} else if (msgType.equals(MessageUtil.REQUEST_MSG_TYPE_IMAGE)) {
				responseContent = "image";
			} else if (msgType.equals(MessageUtil.REQUEST_MSG_TYPE_LINK)) {
				responseContent = "link";
			} else if (msgType.equals(MessageUtil.REQUEST_MSG_TYPE_VOICE)) {
				responseContent = "voice";
			} else if (msgType.equals(MessageUtil.REQUEST_MSG_TYPE_EVENT)) {
				String eventType = requestElements.get("Event");

				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					responseContent = "subscribe";
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					// unsubscribe: do nothing
				}
			}

			// debug
			System.out.println(responseContent);

			//textMessage.setContent(responseContent);
			respMessage = newsMsg.toXML();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

}
