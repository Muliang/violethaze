package violethaze.message.request;

/**
 * text message
 * 
 * @author violethaze
 *
 */
public class TextRequestMessage extends BaseRequestMessage {
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
