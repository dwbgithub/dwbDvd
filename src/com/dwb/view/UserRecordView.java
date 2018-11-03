package com.dwb.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.dwb.daompl.DvdDao_mpl;
import com.dwb.myclass.User;
import com.dwb.service.UserEvent;
import com.dwb.servicempl.UserEvent_mpl;
import com.dwb.servicempl.AdminEvent_mpl;

public class UserRecordView  extends JFrame {
	String[] type={"全部租赁记录","未归还记录","已归还记录"};
	String[] titles={"记录id号","影碟id号","用户名","影碟名字","租赁时间","归还时间"};
	Object[][] strs;
	JButton select_jbutton;
	JButton return_jbutton;
	JButton exit_jbutton;
	DefaultTableModel dft;
	JTable record_table;
	JLabel title_label;
	JLabel lb_img;
	JComboBox<String> jco;
	JScrollPane table_jsc;
	JPanel button_jpanel;
	private User user;

	public void initFrom(){
		this.setTitle("用户记录查询界面");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/admin.jpg")));
		this.setBounds(400, 400, 600, 400);
		this.setLocationRelativeTo(null);
		
		dft=new DefaultTableModel(strs, titles);
		record_table=new JTable(dft);
		table_jsc=new JScrollPane(record_table);
		
		button_jpanel=new JPanel();
		button_jpanel.setBorder(BorderFactory.createTitledBorder
				(BorderFactory.createEtchedBorder(),"查询功能",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION));
		title_label=new JLabel("查询类型");
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		jco=new JComboBox<>(type);
		select_jbutton=new JButton("查询");
		return_jbutton=new JButton("还DVD");
		exit_jbutton=new JButton("退出窗口");
		button_jpanel.setLayout(new GridLayout(6,1,20,30));
		button_jpanel.add(title_label);
		button_jpanel.add(jco);
		button_jpanel.add(select_jbutton);
		button_jpanel.add(return_jbutton);
		button_jpanel.add(exit_jbutton);
		
		this.setLayout(new BorderLayout());
		this.add(button_jpanel,BorderLayout.EAST);
		this.add(table_jsc, BorderLayout.CENTER);
		this.setVisible(true);
	}	
	public UserRecordView(User user){
		this.user=user;
		initFrom();
		allLisener();
	}
	public UserRecordView() {
		initFrom();	
		allLisener();
		}
	public void allLisener(){
		select_jbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String types=jco.getSelectedItem().toString();
				String uname= user.getUname();
				if (types.equals("全部租赁记录")) {
					strs=new UserEvent_mpl().queryUserAllrecord(uname);/////数据库出现错误，无效的列索引
				}else if (types.equals("未归还记录")) {
					strs=new UserEvent_mpl().queryUserAllrecord0(uname);
				}else if (types.equals("已归还记录")) {
					strs=new UserEvent_mpl().queryUserAllrecord1(uname);
				}
			dft=new DefaultTableModel(strs, titles);
			record_table.setModel(dft);
			}
		});
		return_jbutton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				int row=0;
				int id=0;
				int did=0;
				boolean check=false;
				try {
					row=record_table.getSelectedRow();
					id= (int) dft.getValueAt(row, 0);
					did=(int) dft.getValueAt(row, 1);
					check=dft.getValueAt(row, 5).equals("未归还");
				if (check) {
					int a=new UserEvent_mpl().returnRecord(id,did);
					if (a>0) {
						JOptionPane.showMessageDialog(null, "归还DVD成功!");
					}else {
						JOptionPane.showMessageDialog(null, "归还DVD失败!");
					}
				}else {
					JOptionPane.showMessageDialog(null, "DVD已归还!请不要重复归还！");
				}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "请选择归还的DVD!");
				}
			}
		});
		exit_jbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				 System.exit(0);
			}
		});
	}

}
