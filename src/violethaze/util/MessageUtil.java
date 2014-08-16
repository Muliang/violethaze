package violethaze.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.core.util.QuickWriter;

import violethaze.message.response.MusicResponseMessage;
import violethaze.message.response.NewsResponseMessage;
import violethaze.message.response.TextResponseMessage;
import violethaze.message.response.Article;

/**
 * utility class for processing message
 * 
 * @author violethaze
 *
 */
public class MessageUtil {
	// constant value: message types

	// response messages
	private static final String RESPONSE_MSG_TYPE_TEXT = "text";
	private static final String RESPONSE_MSG_TYPE_MUSIC = "music";
	private static final String RESPONSE_MSG_TYPE_NEWS = "news";

	// request messages
	private static final String REQUEST_MSG_TYPE_TEXT = "text";
	private static final String REQUEST_MSG_TYPE_IMAGE = "image";
	private static final String REQUEST_MSG_TYPE_LINK = "link";
	private static final String REQUEST_MSG_TYPE_LOCATION = "location";
	private static final String REQUEST_MSG_TYPE_VOICE = "voice";

	private static final String REQUEST_MSG_TYPE_EVENT = "event"; // event

	// event type
	private static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	private static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 
	 * parse XML message
	 * 
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXML(HttpServletRequest request)
			throws ServletException, IOException, DocumentException {
		/*
		 * use a map to store the parsed result. key is the item in XML message
		 */
		Map<String, String> result = new HashMap<String, String>();

		// input stream
		InputStream inputStream = request.getInputStream();

		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(inputStream);

		// get XML root element
		Element root = document.getRootElement();
		List<Element> elements = root.elements();

		for (Element e : elements) {
			result.put(e.getName(), e.getText());
		}

		inputStream.close();

		return result;
	}

	public static String textResponseMsgToXML(
			TextResponseMessage textResponseMessage) {
		xstream.alias("xml", textResponseMessage.getClass());
		return xstream.toXML(textResponseMessage);
	}

	public static String musicResponseMsgToXML(
			MusicResponseMessage musicResponseMessage) {
		xstream.alias("xml", musicResponseMessage.getClass());
		return xstream.toXML(musicResponseMessage);
	}

	public static String newsResponseMsgToXML(
			NewsResponseMessage newsResponseMessage) {
		xstream.alias("xml", newsResponseMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsResponseMessage);
	}

	
	/**
	 * what hell is this?
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				//
				boolean cdata = true;

				@SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

}
