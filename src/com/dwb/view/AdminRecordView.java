package com.dwb.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.dwb.servicempl.AdminEvent_mpl;

public class AdminRecordView extends JFrame{
	String[] type={"全部租赁记录","指定DVD名租赁记录","指定编号租赁记录"};
	String[] titles={"记录id号","影碟id号","用户名","影碟名字","租赁时间","归还时间"};
	Object[][] strs;
	JButton select_jbutton;
	JButton exit_jbutton;
	JTextField jtc;
	DefaultTableModel dft;
	JTable record_table;
	JLabel title_label;
	JLabel lb_img;
	JComboBox<String> jco;
	JScrollPane table_jsc;
	JPanel button_jpanel;

	public void initFrom(){
		this.setTitle("管理员DVD租赁记录查询界面");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/admin.jpg")));
		this.setBounds(400, 400, 600, 400);
		this.setLocationRelativeTo(null);
		
		dft=new DefaultTableModel(strs, titles);
		record_table=new JTable(dft);
		table_jsc=new JScrollPane(record_table);
		table_jsc.setBorder(BorderFactory.createTitledBorder
				(BorderFactory.createEtchedBorder(),"DVD租赁记录查询",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION));
		
		button_jpanel=new JPanel();
		button_jpanel.setBorder(BorderFactory.createTitledBorder
				(BorderFactory.createEtchedBorder(),"查询功能",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION));
		title_label=new JLabel("查询类型");
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		jco=new JComboBox<>(type);
		jtc=new JTextField();
		select_jbutton=new JButton("查询");
		exit_jbutton=new JButton("退出窗口");
		button_jpanel.setLayout(new GridLayout(6,1,20,30));
		button_jpanel.add(title_label);
		button_jpanel.add(jco);
		button_jpanel.add(jtc);
		button_jpanel.add(select_jbutton);
		button_jpanel.add(exit_jbutton);
		
		this.setLayout(new BorderLayout());
		this.add(button_jpanel,BorderLayout.EAST);
		this.add(table_jsc, BorderLayout.CENTER);
		this.setVisible(true);
	}	
	 public AdminRecordView() {
		 initFrom();
		 allListener();
	}
	public void allListener() {
		select_jbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String types=jco.getSelectedItem().toString();
				if (types.equals("全部租赁记录")) {
					strs=new AdminEvent_mpl().queryrecord();
					dft=new DefaultTableModel(strs, titles);
					record_table.setModel(dft);
				}else if (types.equals("指定DVD名租赁记录")) {
					String dname = jtc.getText();
					strs=new AdminEvent_mpl().queryrecordbydname(dname);
					if (strs[0][0].equals(0)) {
						JOptionPane.showMessageDialog(null, "未查询到<" + dname + ">租赁记录! 请检查是否输入有误！");
					}else {
						dft = new DefaultTableModel(strs, titles);
						record_table.setModel(dft);
					}
				}else if (types.equals("指定编号租赁记录")) {
					
					String str = jtc.getText();
					boolean check = false;
					for (int i = str.length(); --i >= 0;) {
						check = Character.isDigit(str.charAt(i));
					}
					if (check) {
						int id = Integer.valueOf(str);
						strs=new AdminEvent_mpl().queryrecordbydid(id);
						if (strs[0][0].equals(0)) {
							JOptionPane.showMessageDialog(null, "未查询到此DVD租赁记录! 请检查是否输入有误！");
						}else {
							dft = new DefaultTableModel(strs, titles);
							record_table.setModel(dft);
						}
					    }else 
					    {
						JOptionPane.showMessageDialog(null, "请输入影碟id号！");
					    }	
				}
			}
		});
		exit_jbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminRecordView.this.dispose();
			}
		});
		this.addWindowFocusListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}
	
}
