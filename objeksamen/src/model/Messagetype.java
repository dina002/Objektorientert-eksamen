package model;

import java.io.ByteArrayOutputStream;

public class Messagetype {
	
	private String mtype, msg;

	public String getMsg() {
		return msg;
	}
	
	public String getType() {
		return mtype;
	}

	public Messagetype(String mtype, ByteArrayOutputStream msg) {
		super();
		this.mtype = mtype;
		this.msg = msg.toString();
	}

	public Messagetype(String mtype, String msg) {
		super();
		this.mtype = mtype;
		this.msg = msg;
	}

	

}
