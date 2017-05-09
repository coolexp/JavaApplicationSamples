package com.nikoer.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * Servlet implementation class MinaServlet
 */
@WebServlet("/MinaServlet")
public class MinaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MinaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String param1 = request.getParameter("param1");
		IoConnector connector = new NioSocketConnector();
		connector.setHandler(new ClientHandler());
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1", 3456));
		future.awaitUninterruptibly();
		IoSession session = future.getSession();
		String flag = request.getLocalAddr() + UUID.randomUUID().toString();
		session.setAttribute("SendRequestFlag", flag);
		session.write(param1+"\n");
		session.getCloseFuture().awaitUninterruptibly();
		connector.dispose();
		Object result = ClientHandler.results.get(flag);
		ClientHandler.results.remove(flag);
		response.getWriter().write(result.toString());
	}

}
