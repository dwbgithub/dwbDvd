package com.dwb.servicempl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.dwb.daompl.DvdDao_mpl;
import com.dwb.daompl.RecordDao_mpl;
import com.dwb.myclass.Dvd;
import com.dwb.myclass.Record;
import com.dwb.myclass.Recordview;
import com.dwb.myclass.User;
import com.dwb.service.UserEvent;

public class UserEvent_mpl implements UserEvent{
@Override
public Object[][] queryalldvd() {
   java.util.List<Dvd> list=new DvdDao_mpl().queryDvds();
	Object[][] obj=new Object[list.size()][4];
	for (int i = 0; i < obj.length; i++) {
		obj[i][0]=list.get(i).getId();
		obj[i][1]=list.get(i).getDname();
		obj[i][2]=list.get(i).getDcount();
		if(list.get(i).getStatus()==0){
			obj[i][3]="不可借";
		}else {
			obj[i][3]="可借";			
			}
	}
	return obj;
}
@Override
public Object[][] queryhotdvd() {
	   java.util.List<Dvd> list=new DvdDao_mpl().queryDvdByRanking();
		Object[][] obj=new Object[list.size()][4];
		for (int i = 0; i < obj.length; i++) {
			obj[i][0]=list.get(i).getId();
			obj[i][1]=list.get(i).getDname();
			obj[i][2]=list.get(i).getDcount();
			if(list.get(i).getStatus()==0){
				obj[i][3]="不可借";
			}else {
				obj[i][3]="可借";			
				}
		}
		return obj;
}
@Override
public Object[][] querydvds0() {
	   java.util.List<Dvd> list=new DvdDao_mpl().queryDvdByStatus(0);
		Object[][] obj=new Object[list.size()][4];
		for (int i = 0; i < obj.length; i++) {
			obj[i][0]=list.get(i).getId();
			obj[i][1]=list.get(i).getDname();
			obj[i][2]=list.get(i).getDcount();
			obj[i][3]="不可借";
		}
		return obj;
}

@Override
public Object[][] querydvds1() {
	   java.util.List<Dvd> list=new DvdDao_mpl().queryDvdByStatus(1);
			Object[][] obj=new Object[list.size()][4];
			for (int i = 0; i < obj.length; i++) {
				obj[i][0]=list.get(i).getId();
				obj[i][1]=list.get(i).getDname();
				obj[i][2]=list.get(i).getDcount();
				obj[i][3]="可借";
			}
			return obj;
}
	@Override
	public int lenddvd(Dvd dvd,User user) {
		int a=0;
		int b=0;
		Dvd dvd2=new Dvd();
		dvd2.setId(dvd.getId());
		dvd2.setDname(dvd.getDname());
		dvd2.setDcount(dvd.getDcount()+1);
		dvd2.setStatus(dvd.getStatus()-1);
		a=new DvdDao_mpl().updateDvd(dvd2);
		
		List<Record> list=new ArrayList<>();
		int did1=dvd.getId();
		int did2=dvd.getId();
		list=new RecordDao_mpl().queryRecordByDid(did1);//返回的是list
		
		Record record1=new Record();
		Record record2=new Record();
		int uids=user.getId();
		record1.setUids(uids);
		record1.setDid(did2);
		record2.setUids(uids);
		record2.setDid(did2);
		if (list.isEmpty()) {
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String lendTime=format.format(date);
			record1.setLendTime(lendTime);
			record1.setReturnTime("未归还");
			b=new RecordDao_mpl().saveRecord(record1);
		}else {
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String lendTime=format.format(date);
			record2.setId(list.get(0).getId());
			record2.setLendTime(lendTime);
			record2.setReturnTime("未归还");
			b=new RecordDao_mpl().updateRecord(record2);
		}
		return a*b;
	}
	@Override
	public Object[][] queryUserAllrecord(String uname) {
		   List<Recordview> list=new RecordDao_mpl().queryRecordByuname(uname);
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
	public Object[][] queryUserAllrecord0(String uname) {
		   List<Recordview> list=new RecordDao_mpl().queryRecordByuname0(uname);
				Object[][] obj=new Object[list.size()][6];
				for (int i = 0; i < obj.length; i++) {
					obj[i][0]=list.get(i).getId();
					obj[i][1]=list.get(i).getDid();
					obj[i][2]=list.get(i).getUname();
					obj[i][3]=list.get(i).getDname();
					obj[i][4]=list.get(i).getLendTime();
					if (list.get(i).getReturnTime().equals("未归还")) {
						obj[i][5]=list.get(i).getReturnTime();
					}
				}
				return obj;
	}
	@Override
	public Object[][] queryUserAllrecord1(String uname) {
		   List<Recordview> list=new RecordDao_mpl().queryRecordByuname1(uname);
				Object[][] obj=new Object[list.size()][6];
				for (int i = 0; i < obj.length; i++) {
					obj[i][0]=list.get(i).getId();
					obj[i][1]=list.get(i).getDid();
					obj[i][2]=list.get(i).getUname();
					obj[i][3]=list.get(i).getDname();
					obj[i][4]=list.get(i).getLendTime();
					if (list.get(i).getReturnTime().equals("未归还")) {
					}else{
					obj[i][5]=list.get(i).getReturnTime();
					}
				}
				return obj;
	}
	@Override
	public int returnRecord(int id ,int did) {
		int a=0;
		int b=0;
		Dvd dvd=new Dvd();
		dvd=new DvdDao_mpl().queryDvdById(did);
		int dcount=dvd.getDcount();
		int status=dvd.getStatus();
		if (status==1) {
			JOptionPane.showMessageDialog(null, "DVD已归还!请点击查询查看!");
		}else{
			b=new DvdDao_mpl().updateDvd(did, dcount, status);
		}
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String str=format.format(date);
		a=new RecordDao_mpl().renturnRecord(str, id);
		
		return a*b;
	}
}
