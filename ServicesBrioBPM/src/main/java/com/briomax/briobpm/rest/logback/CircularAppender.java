package com.briomax.briobpm.rest.logback;

import java.io.IOException;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;


public class CircularAppender extends AppenderBase<ILoggingEvent> {
	CircularWriter cw;
	PatternLayoutEncoder encoder;
	String maxWriterSize;

	public CircularAppender() {
		cw = new CircularWriter(5 * 1024);
	}

	public String getLogContent() {
		return cw.toString();
	}

	public void setMaxWriterSize(String maxWriterSize) {
		this.maxWriterSize = maxWriterSize;
		cw = new CircularWriter(Integer.parseInt(maxWriterSize));
	}

	@Override
	public void start() {
		if (this.encoder == null) {
			addError("No encoder set for the appender named [" + name + "].");
			return;
		}
		super.start();
	}

	@Override
	protected void append(ILoggingEvent event) {
		byte[] bs = this.encoder.encode(event);
		try {
			cw.append((new String(bs)) + "<BR>");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public PatternLayoutEncoder getEncoder() {
		return encoder;
	}

	public void setEncoder(PatternLayoutEncoder encoder) {
		this.encoder = encoder;
	}
}
