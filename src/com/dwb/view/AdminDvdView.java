package com.dwb.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.text.html.HTMLDocument.Iterator;

import com.dwb.dao.RecordDao;
import com.dwb.daompl.DvdDao_mpl;
import com.dwb.myclass.Dvd;
import com.dwb.myclass.User;
import com.dwb.service.UserEvent;
import com.dwb.service.AdminEvent;
import com.dwb.servicempl.UserEvent_mpl;
import com.dwb.servicempl.AdminEvent_mpl;

public class AdminDvdView extends JFrame {
	String[] type1 = { "全部DVD", "指定编号查询", "指定DVD名字查询" };
	String[] type2 = { "可借", "不可借" };
	String[] titles = { "影碟ID号", "影碟名字", "借用次数", "影碟状态" };
	Object[][] strs;
	JButton select_jbutton;
	JButton save_jbutton;
	JButton upate_jbutton;
	JButton del_jbutton;
	JButton exit_jbutton;
	DefaultTableModel dft;
	JTable record_table;
	JTextField jt_check1;
	JTextField jt_check2;
	JTextField jt_select;
	JLabel title_label;
	JLabel lb_img;
	JLabel lb_check1;
	JLabel lb_check2;
	JLabel lb_check3;
	JComboBox<String> jc;
	JComboBox<String> jco;
	JScrollPane table_jsc;
	JPanel table_jpanel;
	JPanel check_jpanel;
	JPanel button_jpanel;
	private User user;
	public void initFrom() {
		this.setTitle("管理员DVD操作");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/admin.jpg")));
		this.setBounds(400, 400, 700, 500);
		this.setLocationRelativeTo(null);
		// center
		dft = new DefaultTableModel(strs, titles);
		record_table = new JTable(dft);
		table_jsc = new JScrollPane(record_table);
		table_jpanel = new JPanel();
		table_jpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DVD查询记录",
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		// east
		button_jpanel = new JPanel();
		button_jpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "查询功能",
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		title_label = new JLabel("查询类型");
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		jco = new JComboBox<>(type1);
		jt_select = new JTextField();
		select_jbutton = new JButton("查询");
		save_jbutton = new JButton("添加DVD");
		upate_jbutton = new JButton("更新DVD");
		del_jbutton = new JButton("删除DVD");
		exit_jbutton = new JButton("退出窗口");
		button_jpanel.setLayout(new GridLayout(8, 1, 20, 30));
		button_jpanel.add(title_label);
		button_jpanel.add(jco);
		button_jpanel.add(jt_select);
		button_jpanel.add(select_jbutton);
		button_jpanel.add(save_jbutton);
		button_jpanel.add(upate_jbutton);
		button_jpanel.add(del_jbutton);
		button_jpanel.add(exit_jbutton);

		check_jpanel = new JPanel();
		lb_check1 = new JLabel("DVD名字");
		jt_check1 = new JTextField();
		lb_check2 = new JLabel("借出次数");
		jt_check2 = new JTextField();
		lb_check3 = new JLabel("DVD状态");
		jc = new JComboBox<>(type2);
		check_jpanel.setLayout(new GridLayout(1, 6, 0, 0));
		check_jpanel.add(lb_check1);
		check_jpanel.add(jt_check1);
		check_jpanel.add(lb_check2);
		check_jpanel.add(jt_check2);
		check_jpanel.add(lb_check3);
		check_jpanel.add(jc);
		table_jpanel.setLayout(new BorderLayout());
		table_jpanel.add(check_jpanel, BorderLayout.SOUTH);
		table_jpanel.add(table_jsc, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(button_jpanel, BorderLayout.EAST);
		this.add(table_jpanel, BorderLayout.CENTER);
		this.setVisible(true);
	}

	public AdminDvdView(User user) {
		this.user = user;
		initFrom();
		allLisener();
	}

	public AdminDvdView() {
		initFrom();
		allLisener();
	}

	public void allLisener() {
		record_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rows = record_table.getSelectedRow();// 有几行从0开始
				String dname = (String) dft.getValueAt(rows, 1);
				int dcount = (int) dft.getValueAt(rows, 2);
				String status = (String) dft.getValueAt(rows, 3);
				String string = String.valueOf(dcount);
				jt_check1.setText(dname);
				jt_check2.setText(string);
				if (status.equals("可借")) {
					jc.setSelectedIndex(0);
				} else if (status.equals("不可借")) {
					jc.setSelectedIndex(1);
				}
			}
		});
		upate_jbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int rows = record_table.getSelectedRow();// 有几行从0开始
				int did = -1;
				String dname=null;
				int dcount=0;
				try {
					did = (int) dft.getValueAt(rows, 0);
					dname = jt_check1.getText();
					dcount = Integer.valueOf(jt_check2.getText());
				} catch (Exception e2) {
				}
				int status = 0;
				String types = jc.getSelectedItem().toString();
				if (types.equals("可借")) {
					status = 1;
				} else if (types.equals("不可借")) {
					status = 0;
				}
				Dvd dvd = new Dvd();
				dvd.setDcount(dcount);
				dvd.setDname(dname);
				dvd.setId(did);
				dvd.setStatus(status);
				int a=new AdminEvent_mpl().upatedvd(dvd);
				if (a==1) {
					JOptionPane.showMessageDialog(null, "更新成功！请刷新查询查看是否已经更新！");
				}else{
					JOptionPane.showMessageDialog(null, "更新失败！请选择需要更新的行！并修改需要更新的选项！");
				}
			}
		});

		del_jbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int rows = record_table.getSelectedRow();// 有几行从0开始
				int did =0;
				String dname=null;
				int dcount=0;
				String str="测试";
				int status = 0;
				int zz=0;
				try {
				did = (int) dft.getValueAt(rows, 0);
				dname = (String) dft.getValueAt(rows, 1);
				dcount = (int) dft.getValueAt(rows, 2);
				str = (String) dft.getValueAt(rows, 3);
				} catch (Exception e2) {
					zz=1;
					JOptionPane.showMessageDialog(null, "请点击查询！选择需要删除的行！");
				}
				if (zz==1) {
				}else{
				if (str.equals("可借")) {
					status = 1;
				}
				Dvd dvd = new Dvd();
				dvd.setId(did);
				dvd.setDname(dname);
				dvd.setDcount(dcount);
				dvd.setStatus(status);
				int a=0;
				int z=JOptionPane.showConfirmDialog(null, "是否确定删除此DVD", "确定窗口",
						JOptionPane.YES_NO_OPTION);
				if (z==JOptionPane.YES_OPTION) {
					 a= new AdminEvent_mpl().deldvd(dvd);
					 if (a != 0) {
					 JOptionPane.showMessageDialog(null, "删除成功！请点击查询查看是否已经删除！");
					 } else {
						 JOptionPane.showMessageDialog(null, "删除失败！请点击查询查看是否已经删除！");
					 }
				}else {
					JOptionPane.showMessageDialog(null, "删除失败！");
			   }}
			}
		});

		select_jbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String types = jco.getSelectedItem().toString();
				if (types.equals("全部DVD")) {
					strs = new UserEvent_mpl().queryalldvd();
					dft = new DefaultTableModel(strs, titles);
					record_table.setModel(dft);
				} else if (types.equals("指定编号查询")) {
					String str = jt_select.getText();
					boolean check = false;
					for (int i = str.length(); --i >= 0;) {
						check = Character.isDigit(str.charAt(i));
					}
					if (check) {
						int did = Integer.valueOf(str);
						strs = new AdminEvent_mpl().querydvdbyid(did);
						if (strs[0][0].equals(0)) {
							JOptionPane.showMessageDialog(null, "未查询到此id号的影碟! 请检查是否输入有误！");
						}else {
							dft = new DefaultTableModel(strs, titles);
							record_table.setModel(dft);
						}
					    }else 
					    {
						JOptionPane.showMessageDialog(null, "请输入影碟id号！");
					    }
				} else if (types.equals("指定DVD名字查询")) {
					String str = jt_select.getText();
					strs = new AdminEvent_mpl().querydvdbydname(str);
					if (strs[0][0].equals(0)) {
						JOptionPane.showMessageDialog(null, "未查询到《" + str + "》! 请检查是否输入有误！");
					}else {
						dft = new DefaultTableModel(strs, titles);
						record_table.setModel(dft);
					}
				}
			}
		});

		save_jbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String dname = jt_check1.getText();
				if (dname.equals("")) {
					JOptionPane.showMessageDialog(AdminDvdView.this, "DVD名不能为空");
				} else {

					String str = jt_check2.getText();
					int dcount = 0;
					if (str.equals("")) {
						JOptionPane.showConfirmDialog(AdminDvdView.this, "如果借出次数为空，默认为0！是否执行操作？");
					} else {
						boolean check = false;
						for (int i = str.length(); --i >= 0;) {
							check = Character.isDigit(str.charAt(i));
						}
						if (check) {
							dcount = Integer.valueOf(str);
						} else {
							JOptionPane.showMessageDialog(null, "请输入数字！");
						}
						int status = 0;
						if (jc.getSelectedIndex() == 0) {
							status = 1;
						}
						Dvd dvd = new Dvd();
						dvd.setDname(dname);
						dvd.setDcount(dcount);
						dvd.setStatus(status);
						int a = new AdminEvent_mpl().savedvd(dvd);
						if (a != 0) {
							JOptionPane.showMessageDialog(null, "添加成功！");
						} else {
							JOptionPane.showMessageDialog(null, "添加失败！影碟已存在！");
						}

					}
				}
			}
		});

		exit_jbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				AdminDvdView.this.dispose();
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
