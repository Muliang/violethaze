package violethaze.message.response;

import java.util.Date;

import violethaze.util.MessageUtil;

;
/**
 * text response
 * 
 * @author violethaze
 *
 */
public class TextResponseMessage extends BaseResponseMessage {
	private String Content;

	/**
	 * constructor 1
	 * 
	 * @param content
	 * @param fromUserName
	 * @param toUserName
	 */
	public TextResponseMessage(String fromUserName, String toUserName) {
		this.setFromUserName(fromUserName);
		this.setToUserName(toUserName);
		this.setMsgType(MessageUtil.RESPONSE_MSG_TYPE_TEXT);
		this.setCreateTime(new Date().getTime() / 1000);
	}

	/**
	 * constructor 2
	 * 
	 * @param content
	 * @param fromUserName
	 * @param toUserName
	 */
	public TextResponseMessage(String content, String fromUserName,
			String toUserName) {
		this.setFromUserName(fromUserName);
		this.setToUserName(toUserName);
		this.setMsgType(MessageUtil.RESPONSE_MSG_TYPE_TEXT);
		this.setCreateTime(new Date().getTime() / 1000);
		Content = content;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
	public String toXML(){
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>\n");
		//toUser
		sb.append("<ToUserName><![CDATA[" + this.getToUserName() + "]]></ToUserName>\n");
		//fromUser
		sb.append("<FromUserName><![CDATA[" + this.getFromUserName() + "]]></FromUserName>\n");
		sb.append("<CreateTime>" + this.getCreateTime() + "</CreateTime>\n");
		sb.append("<MsgType><![CDATA[" + this.getMsgType() + "]]></MsgType>\n");
		sb.append("<Content><![CDATA[" + this.getContent() + "]]></Content>\n");
		sb.append("</xml>");
		
		return sb.toString();
	}
}
