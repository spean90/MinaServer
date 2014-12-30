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
		System.out.println("message written...."+str);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		super.sessionIdle(session, status);
		System.out.println("IDLE:"+session.getIdleCount(status));
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		super.messageSent(session, message);
		System.out.println("messageSent");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
		System.out.println("sessionClosed");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		super.sessionCreated(session);
		System.out.println("sessionCreated");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		super.sessionOpened(session);
		System.out.println("sessionOpened");
	}

	
}
