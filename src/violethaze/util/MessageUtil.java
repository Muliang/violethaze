package violethaze.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * utility class for processing message
 * 
 * @author violethaze
 *
 */
public class MessageUtil {
	// constant value: message types

	// response messages
	public static final String RESPONSE_MSG_TYPE_TEXT = "text";
	public static final String RESPONSE_MSG_TYPE_MUSIC = "music";
	public static final String RESPONSE_MSG_TYPE_NEWS = "news";

	// request messages
	public static final String REQUEST_MSG_TYPE_TEXT = "text";
	public static final String REQUEST_MSG_TYPE_IMAGE = "image";
	public static final String REQUEST_MSG_TYPE_LINK = "link";
	public static final String REQUEST_MSG_TYPE_LOCATION = "location";
	public static final String REQUEST_MSG_TYPE_VOICE = "voice";

	public static final String REQUEST_MSG_TYPE_EVENT = "event"; // event

	// event type
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

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

		//debug
		System.out.println(result);
		
		inputStream.close();

		return result;
	}
}
