package violethaze.message.response;

/**
 * Article object in article message
 * 
 * @author violethaze
 *
 */
public class Article {
	private String Title;
	private String Description;
	private String PicUrl; // url of image in the article
	private String Url; // jump url for clicking

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
}
