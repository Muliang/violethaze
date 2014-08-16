package violethaze.message.response;

/**
 * text response
 * @author violethaze
 *
 */
public class TextResponseMessage extends BaseResponseMessage {
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
