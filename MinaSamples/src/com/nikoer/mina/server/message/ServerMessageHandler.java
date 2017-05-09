package com.nikoer.mina.server.message;



import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerMessageHandler implements IoHandler {
	private final static Logger log = LoggerFactory.getLogger(ServerMessageHandler.class);
	@Override
	public void exceptionCaught(IoSession arg0, Throwable arg1)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("服务器发生异常： {}", arg1.getMessage());
	}

	@Override
	public void inputClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		log.info("服务器接收到数据： {}", message);
        String content = message.toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String datetime = sdf.format(new Date());
        
        log.info("转发 messageReceived: " + datetime + "\t" + content);
        
        // 拿到所有的客户端Session
        Collection<IoSession> sessions = session.getService().getManagedSessions().values();
        // 向所有客户端发送数据
        for (IoSession sess : sessions) {
            sess.write(datetime + "\t" + content);
        }
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
        log.info("服务器发送消息： {}", message);
    }

	@Override
	public void sessionClosed(IoSession session) throws Exception {
        log.info("关闭当前session：{}#{}", session.getId(), session.getRemoteAddress());
        
        CloseFuture closeFuture = session.close(true);
        closeFuture.addListener(new IoFutureListener<IoFuture>() {
            public void operationComplete(IoFuture future) {
                if (future instanceof CloseFuture) {
                    ((CloseFuture) future).setClosed();
                    log.info("sessionClosed CloseFuture setClosed-->{},", future.getSession().getId());
                }
            }
        });
    }

	@Override
	public void sessionCreated(IoSession session) throws Exception {
        log.info("创建一个新连接：{}", session.getRemoteAddress());
        session.write("welcome to the chat room !");
    }

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        log.info("当前连接{}处于空闲状态：{}", session.getRemoteAddress(), status);
    }

	@Override
	public void sessionOpened(IoSession session) throws Exception {
        log.info("打开一个session：{}#{}", session.getId(), session.getBothIdleCount());
    }

}
