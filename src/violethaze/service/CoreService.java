package violethaze.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
		String respMessage = null;

		try {
			String responseContent = "default message, cannot process your request";

			Map<String, String> requestElements = MessageUtil.parseXML(request);

			// get elements;
			String fromUserName = requestElements.get("FromUserName");
			String toUserName = requestElements.get("ToUserName");
			String msgType = requestElements.get("MsgType");

			// create a text reponse
			TextResponseMessage textMessage = new TextResponseMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime() / 1000);
			textMessage.setMsgType(MessageUtil.RESPONSE_MSG_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			if (msgType.equals(MessageUtil.REQUEST_MSG_TYPE_TEXT)) {
				responseContent = "欢迎使用Diaosi微信自动回复系统";
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

			textMessage.setContent(responseContent);
			respMessage = MessageUtil.textResponseMsgToXML(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}
}
