package violethaze.message.response;

import java.util.List;

public class NewsResponseMessage extends BaseResponseMessage {
	private int ArticleCount;
	private List<Article> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}

	/**
	 * creata a response new message xml string
	 */
	public String toXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>\n");
		// toUser
		sb.append("<ToUserName><![CDATA[" + this.getToUserName()
				+ "]]></ToUserName>\n");
		// fromUser
		sb.append("<FromUserName><![CDATA[" + this.getFromUserName()
				+ "]]></FromUserName>\n");
		sb.append("<CreateTime>" + this.getCreateTime() + "</CreateTime>\n");
		sb.append("<MsgType><![CDATA[" + this.getMsgType() + "]]></MsgType>\n");
		sb.append("<ArticleCount>" + this.getArticleCount()
				+ "</ArticleCount>\n");
		// articles
		sb.append("<Articles>\n");
		// create article details
		for (int i = 0; i < this.getArticleCount(); i++) {
			Article article = this.getArticles().get(i);
			sb.append("<item>\n");
			sb.append("<Title><![CDATA[" + article.getTitle() + "]]></Title>\n");
			sb.append("<Description><![CDATA[" + article.getDescription() + "]]></Description>\n");
			sb.append("<PicUrl><![CDATA[" + article.getPicUrl()
					+ "]]></PicUrl>\n");
			sb.append("<Url><![CDATA[" + article.getUrl() + "]]></Url>\n");
			sb.append("</item>\n");
		}
		sb.append("</Articles>\n");
		sb.append("</xml>");

		return sb.toString();
	}

}
