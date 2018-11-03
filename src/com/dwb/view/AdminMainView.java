package com.dwb.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import com.dwb.myclass.User;


public class AdminMainView extends JFrame{
	JPanel mainPanel  ;// 主面板容器
	 JPanel btn_panel ;// 按钮面板
	 JPanel picPanel  ;// 图片面板
	 JDesktopPane funcDesktop  ;// 业务面板
	 JButton btn_operator_dvd  ;// 查看所有的DVD信息
	 JButton btn_dvd_record  ;// 查看本人DVD租赁记录
	 JButton btn_exit  ;// 退出
	 JLabel picLabel  ;// 标题
	 ImageIcon image ;// 图标组件
	 JLabel deskLabel  ;// 存放图片的Label
	private User user;
	
    public void  init() {
		btn_panel = new JPanel();
		btn_operator_dvd = new JButton("管理员DVD操作");
		btn_dvd_record = new JButton("DVD租赁记录查询");
		btn_exit = new JButton("退出窗口");
		btn_panel.setLayout(new GridLayout(7, 1, 0, 35));
		btn_panel.add(new JLabel());
		btn_panel.add(new JLabel());
		btn_panel.add(btn_operator_dvd);
		btn_panel.add(btn_dvd_record);
		btn_panel.add(btn_exit);
		btn_panel.add(new JLabel());
		btn_panel.add(new JLabel());
		picPanel = new JPanel();
		funcDesktop = new JDesktopPane();
		image = new ImageIcon("src/images/Login.jpg");
//		deskLabel = new JLabel(new ImageIcon(ClassLoader.getSystemResource("images/Login.jpg")));
		deskLabel = new JLabel(image);// 图片Label
//		 非常重要,图片Label的起始位置和大小
		deskLabel.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());//?
		funcDesktop.add(deskLabel, new Integer(Integer.MAX_VALUE));//?
//		funcDesktop.add(deskLabel);
		picLabel = new JLabel(
				"欢     迎     "+user.getUname()+"       来      到     火      影      影      社    ");
		picLabel.setFont(new Font("", Font.BOLD, 25));
		picLabel.setForeground(Color.red);
		picPanel.add(picLabel);
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(btn_panel, BorderLayout.EAST);
		mainPanel.add(picPanel, BorderLayout.NORTH);
		mainPanel.add(funcDesktop, BorderLayout.CENTER);
		this.add(mainPanel);
		this.setTitle("影碟租赁管理系统");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/admin.jpg")));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000, 650);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);//居中显示
	}
    public AdminMainView(User user) {
    	this.user=user;
    	init();
    	allListener();
	}
    public AdminMainView() {
    	init();
    	allListener();
	}
    public void allListener() {
    	btn_dvd_record.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mousePressed(MouseEvent e) {
    			
    		}
		});
    	btn_dvd_record.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mousePressed(MouseEvent e) {
    			
    		}
		});
    	
    	btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
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
