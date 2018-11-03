package com.dwb.myclass;

public class Record {
	private int id;
	private int uids;
	private int did;
	private String lendTime;
	private String ReturnTime;
	public Record() {
		
	}
	public Record(int id, int uids, int did, String lendTime, String returnTime) {
		super();
		this.id = id;
		this.uids = uids;
		this.did = did;
		this.lendTime = lendTime;
		ReturnTime = returnTime;
	}
	public Record(int uids, int did, String lendTime, String returnTime) {
		super();
		this.uids = uids;
		this.did = did;
		this.lendTime = lendTime;
		ReturnTime = returnTime;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUids() {
		return uids;
	}
	public void setUids(int uids) {
		this.uids = uids;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getLendTime() {
		return lendTime;
	}
	public void setLendTime(String lendTime) {
		this.lendTime = lendTime;
	}
	public String getReturnTime() {
		return ReturnTime;
	}
	public void setReturnTime(String returnTime) {
		ReturnTime = returnTime;
	}
	
}
