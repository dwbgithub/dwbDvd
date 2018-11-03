package com.dwb.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import com.dwb.myclass.Dvd;
import com.dwb.myclass.User;
import com.dwb.servicempl.UserEvent_mpl;
import com.dwb.servicempl.AdminEvent_mpl;

public class UserquerylendView  extends JFrame {
	String[] type={"全部DVD","查看热门DVD","已借出DVD","未借出DVD"};
	String[] titles={"影碟ID号","影碟名字","租赁次数","影碟状态"};
	Object[][] strs;
	JButton select_jbutton;
	JButton lend_jbutton;
	JButton exit_jbutton;
	DefaultTableModel dft;
	JTable record_table;
	JLabel title_label;
	JLabel lb_img;
	JComboBox<String> jco;
	JScrollPane table_jsc;
	JPanel button_jpanel;
	private User user;
	private Dvd dvd;
	
	public void initFrom(){
		this.setTitle("DVD信息查询");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/admin.jpg")));
		this.setBounds(400, 400, 600, 400);
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
		lend_jbutton=new JButton("借DVD");
		exit_jbutton=new JButton("退出窗口");
		button_jpanel.setLayout(new GridLayout(6,1,20,30));
		button_jpanel.add(title_label);
		button_jpanel.add(jco);
		button_jpanel.add(select_jbutton);
		button_jpanel.add(lend_jbutton);
		button_jpanel.add(exit_jbutton);
		
		this.setLayout(new BorderLayout());
		this.add(button_jpanel,BorderLayout.EAST);
		this.add(table_jsc, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);// 窗口居中显示
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//
	}	
	public UserquerylendView(User user){
		this.user=user;
		initFrom();
		allLisener();
	}
	public UserquerylendView() {
		initFrom();	
		allLisener();
		}
	public void allLisener(){
		select_jbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String types=jco.getSelectedItem().toString();
				if (types.equals("全部DVD")) {
					strs=new UserEvent_mpl().queryalldvd();
				}else if (types.equals("查看热门DVD")) {
					strs=new UserEvent_mpl().queryhotdvd();
				}else if (types.equals("已借出DVD")) {
					strs=new UserEvent_mpl().querydvds0();
				}else if (types.equals("未借出DVD")) {
					strs=new UserEvent_mpl().querydvds1();
				}
				dft=new DefaultTableModel(strs, titles);
				record_table.setModel(dft);
			}
		});
		lend_jbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int rows=0;
				String dname=null;
				int dcount=0;
				int did=0;
				int status;
				boolean check=false;
				int z=0;
				try {
				 rows=record_table.getSelectedRow();//有几行从0开始
				 did=(int) dft.getValueAt(rows,0);
				 dname=(String) dft.getValueAt(rows, 1);
				 dcount=(int) dft.getValueAt(rows, 2);
				 check=dft.getValueAt(rows, 3).equals("可借");
				} catch (Exception e2) {
					z=1;
					JOptionPane.showMessageDialog(null, "请点击查询!选择要借的DVD!");
				}if (z!=1) {
				 if (check) {
					  status=1;
					  Dvd dvd=new Dvd();
					  dvd.setId(did);
					  dvd.setDname(dname);
					  dvd.setDcount(dcount);
					  dvd.setStatus(status);
					  int a=new UserEvent_mpl().lenddvd(dvd,user);
					  if (a==0) {
						  JOptionPane.showMessageDialog(null, "借书失败!请联系管理员!");
					  }else{
						  int aa=JOptionPane.showConfirmDialog(null, "是否确定租赁此DVD", "确定窗口",
									JOptionPane.YES_NO_OPTION);
						  if (aa==JOptionPane.YES_OPTION) {
							  JOptionPane.showMessageDialog(null, "租赁成功!");
						  }
					  }
				}else {
					status=0;
					JOptionPane.showMessageDialog(null, "此DVD已被借走!");
				}
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
