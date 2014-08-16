package violethaze.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import violethaze.util.SignatureUtil;

/**
 * Servlet implementation class CoreSerlet
 */
@WebServlet("/CoreSerlet")
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CoreServlet() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// get signature
		String signature = request.getParameter("signature");
		// get time stamp
		String timestamp = request.getParameter("timestamp");
		// get nonce (what's this?)
		String nonce = request.getParameter("nonce");
		// get return string
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();

		if (SignatureUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}

		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
