package com.spean.mina;

import java.util.Date;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class MyMinaHandler extends IoHandlerAdapter{

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		super.exceptionCaught(session, cause);
		cause.printStackTrace();
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		super.messageReceived(session, message);
		String str = message.toString();
		if(str.trim().equalsIgnoreCase("quit")){
			session.close();
			return;
		}
		Date date = new Date();
		session.write(date.toString());
		System.out.println("message written....");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		super.sessionIdle(session, status);
		System.out.println("IDLE:"+session.getIdleCount(status));
	}

	
}
