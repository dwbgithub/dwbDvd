package com.dwb.servicempl;

import java.util.List;

import com.dwb.daompl.DvdDao_mpl;
import com.dwb.daompl.RecordDao_mpl;
import com.dwb.myclass.Dvd;
import com.dwb.myclass.Recordview;
import com.dwb.service.AdminEvent;

public class AdminEvent_mpl implements AdminEvent{
	@Override
	public int savedvd(Dvd dvd) {
		int a=0;
		a=new DvdDao_mpl().saveDvd(dvd);
		return a;
	}
	@Override
	public int upatedvd(Dvd dvd) {
		int a=0;
		a=new DvdDao_mpl().updateDvd(dvd);
		return a;
	}
	@Override
	public int deldvd(Dvd dvd) {
		int a=0;
		int b=0;
		int did;
		did=dvd.getId();
		b=new RecordDao_mpl().delRecord(did);
		a=new DvdDao_mpl().delDvd(did);
		return a*b;
	}
	@Override
	public Object[][] querydvdbyid(int id) {
		Dvd dvd=new Dvd();
		dvd=new DvdDao_mpl().queryDvdById(id);
		Object[][] obj=new Object[1][4];
			obj[0][0]=dvd.getId();
			obj[0][1]=dvd.getDname();
			obj[0][2]=dvd.getDcount();
			obj[0][3]=dvd.getStatus();
		return obj;
	}
	@Override
	public Object[][] querydvdbydname(String dname) {
		Dvd dvd=new Dvd();
		dvd=new DvdDao_mpl().queryDvdByName(dname);
		Object[][] obj=new Object[1][4];
			obj[0][0]=dvd.getId();
			obj[0][1]=dvd.getDname();
			obj[0][2]=dvd.getDcount();
			obj[0][3]=dvd.getStatus();
		return obj;
	}
	@Override
	public Object[][] queryrecord() {
		   List<Recordview> list=new RecordDao_mpl().queryAllRecords();
			Object[][] obj=new Object[list.size()][6];
			for (int i = 0; i < obj.length; i++) {
				obj[i][0]=list.get(i).getId();
				obj[i][1]=list.get(i).getDid();
				obj[i][2]=list.get(i).getUname();
				obj[i][3]=list.get(i).getDname();
				obj[i][4]=list.get(i).getLendTime();
				obj[i][5]=list.get(i).getReturnTime();
			}
			return obj;
	}
	@Override
	public Object[][] queryrecordbydid(int id) {
		   List<Recordview> list=new RecordDao_mpl().queryRecordById(id);
			Object[][] obj=new Object[list.size()][6];
			for (int i = 0; i < obj.length; i++) {
				obj[i][0]=list.get(i).getId();
				obj[i][1]=list.get(i).getDid();
				obj[i][2]=list.get(i).getUname();
				obj[i][3]=list.get(i).getDname();
				obj[i][4]=list.get(i).getLendTime();
				obj[i][5]=list.get(i).getReturnTime();
			}
			return obj;
	}
	@Override
	public Object[][] queryrecordbydname(String dname) {
		   Recordview recordview=new RecordDao_mpl().queryRecordBydname(dname);
					Object[][] obj=new Object[1][6];
						obj[0][0]=recordview.getId();
						obj[0][1]=recordview.getDid();
						obj[0][2]=recordview.getUname();
						obj[0][3]=recordview.getDname();
						obj[0][5]=recordview.getReturnTime();
					return obj;
	}
}
