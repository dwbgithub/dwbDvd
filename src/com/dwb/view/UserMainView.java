package com.dwb.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.dwb.myclass.User;

public class UserMainView extends JFrame{
	JPanel Panel_all ;
	JPanel panel_btn;
	JPanel picPanel;
	JDesktopPane funcDesktop;
	JButton btn_operator_dvd;
	JButton btn_dvd_record;
	JButton btn_exit;
	JLabel picLabel;
	JLabel deskLabel;
	ImageIcon image;
	private User user;
	
	public void init() {
	panel_btn = new JPanel();
	btn_operator_dvd = new JButton("DVD查询租赁操作");
	btn_dvd_record = new JButton("DVD租赁记录查询");
	btn_exit = new JButton("退出窗口");
//	btn_dvd_record.setBounds(0,0, 10, 10);
//	btn_exit.setBackground(Color.green);
//	btn_exit.setFont(new Font("", Font.BOLD, 25));
//	btn_exit.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,new Color(128,255,255),new Color(128,255,255)) );
	// 面板添加相应的组件
	panel_btn.setLayout(new GridLayout(7, 0, 0, 35));
	panel_btn.add(new JLabel());
	panel_btn.add(new JLabel());
	panel_btn.add(btn_operator_dvd);
	panel_btn.add(btn_dvd_record);
	panel_btn.add(btn_exit);
	panel_btn.add(new JLabel());
	panel_btn.add(new JLabel());
	panel_btn.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createRaisedBevelBorder(), "快捷功能区"));// 设计面板的边框外观
	// 图片面板
	picPanel = new JPanel();
	funcDesktop = new JDesktopPane();
	image = new ImageIcon("src/images/Login.jpg");
//	deskLabel = new JLabel(new ImageIcon(ClassLoader.getSystemResource("images/Login.jpg")));
	deskLabel = new JLabel(image);// 图片Label
//	 非常重要,图片Label的起始位置和大小
	deskLabel.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());//?
	funcDesktop.add(deskLabel, new Integer(Integer.MAX_VALUE));//?
//	funcDesktop.add(deskLabel);
	
	picLabel = new JLabel(
	"欢     迎    "+user.getUname()+"   来      到     火      影      影      社    ");
	picLabel.setFont(new Font("", Font.BOLD, 25));
	picLabel.setForeground(Color.red);
	picPanel.add(picLabel);
//	picPanel.setForeground(Color.yellow);//没用
//	picPanel.add(deskLabel);
	Panel_all = new JPanel(new BorderLayout());
	Panel_all.add(panel_btn, BorderLayout.EAST);
	Panel_all.add(picPanel, BorderLayout.NORTH);
	Panel_all.add(funcDesktop, BorderLayout.CENTER);
	getContentPane().add(Panel_all);
	this.setTitle("影碟租赁管理系统");
	this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/admin.jpg")));
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setSize(1000, 650);
	this.setResizable(false);
	this.setVisible(true);
}
	public UserMainView(User user) {
		this.user=user;
		init();
		allListener();
	}	
	public void  allListener() {
		btn_operator_dvd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserquerylendView(user);
			}
		});
		
		btn_dvd_record.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserRecordView(user);
			}
		});
		
		btn_exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
