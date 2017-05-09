package com.nikoer.mina.code;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.log4j.Logger;

public class CharsetEncoder implements ProtocolEncoder {
	private final static Logger log = Logger.getLogger(CharsetEncoder.class);
	private final static Charset charset = Charset.forName("UTF-8");
	@Override
	public void dispose(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		log.info("#############dispose############");
	}

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        log.info("#############字符编码############");
        IoBuffer buff = IoBuffer.allocate(100).setAutoExpand(true);
        buff.putString(message.toString(), charset.newEncoder());
        // put 当前系统默认换行符
        buff.putString(LineDelimiter.DEFAULT.getValue(), charset.newEncoder());
        // 为下一次读取数据做准备
        buff.flip();
        
        out.write(buff);
    }

}
