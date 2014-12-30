package com.spean.mina;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MyCodecFactory implements ProtocolCodecFactory {

	private MyDecoder myDecoder;
	private MyEncoder myEncoder;
	
	public MyCodecFactory() {
		myDecoder = new MyDecoder();
		myEncoder = new MyEncoder();
	}
	
	@Override
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		return myDecoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		return myEncoder;
	}

}
