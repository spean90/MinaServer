package com.spean.mina;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class MyEncoder implements ProtocolEncoder {

	@Override
	public void dispose(IoSession session) throws Exception {
		
	}

	@Override
	public void encode(IoSession session, Object msg, ProtocolEncoderOutput out)
			throws Exception {
		String s = null;
		if(msg instanceof String) {
			s = (String)msg+"\n";
		}
		if (s!=null) {
			CharsetEncoder encoder = (CharsetEncoder) session.getAttribute("encoder");
			if (encoder == null) {
				 encoder = Charset.forName("utf-8").newEncoder();
				 session.setAttribute("encdoer", encoder);
			}
			IoBuffer ioBuffer = IoBuffer.allocate(s.length());
			ioBuffer.setAutoExpand(true);
			ioBuffer.putString(s,encoder);
			ioBuffer.flip();
			out.write(ioBuffer);
		}
	}

}
