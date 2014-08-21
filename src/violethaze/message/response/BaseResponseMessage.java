package violethaze.message.response;

public class BaseResponseMessage {
	private String ToUserName; // wecaht id who receives response
	private String FromUserName; // wechat id of developer
	private String MsgType;

	private long CreateTime;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	/**
	 * for override
	 * @return
	 */
	public String toXML(){
		return null;
	}
}
