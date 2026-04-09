package com.briomax.briobpm.rest.logback;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

import org.apache.commons.collections4.queue.CircularFifoQueue;

public class CircularWriter extends Writer {
	
	CircularFifoQueue<char[]> cq;
	
	public CircularWriter(int capacidad){
		cq = new CircularFifoQueue<char[]>(capacidad);
		this.lock = cq;
	}



	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		if ((off < 0) || (off > cbuf.length) || (len < 0) || ((off + len) > cbuf.length) || ((off + len) < 0)) {
			throw new IndexOutOfBoundsException();
		} else if (len == 0) {
			return;
		}

		char [] rango = Arrays.copyOfRange(cbuf, off, off + len);
		//System.out.println("Size: " + cq.size() + " Max Size:" + cq.maxSize());
		cq.offer(rango);
	}
	
	@Override
	public void close() throws IOException {
	}

	@Override
	public void flush() throws IOException {
	}
	
	public synchronized String toString() {
		StringBuffer sb = new StringBuffer();
	    for(char[] buf: cq){
	    	sb.append(buf);
	    }
	    return sb.toString();
	}

}
