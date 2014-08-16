package violethaze.message.request;

/**
 * image message
 * 
 * @author violethaze
 *
 */
public class ImageRequestMessage extends BaseRequestMessage {
	private String PicUrl;
	private String MediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
}
