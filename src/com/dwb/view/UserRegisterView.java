package com.dwb.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.dwb.myclass.User;
import com.dwb.service.IUserService;
import com.dwb.servicempl.UserService_mpl;

public class UserRegisterView extends JFrame{
	 JTextField tfuname ;// 用户名
	 JPasswordField userPassInit ;// 初始密码
	 JPasswordField userPassConfirm ;// 确认密码
	 JButton btn_confirm;// 确认按钮
	 JButton btn_back ;// 返回按钮
     JPanel mainPanel ;// 主面板
     IUserService userService  ;

	private void initForm() {
		tfuname = new JTextField(10);
		userPassInit = new JPasswordField(10);
		userPassConfirm = new JPasswordField(10);
		btn_confirm = new JButton("确认提交");
		btn_back = new JButton("退出");
	    mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(5, 1, 0, 0));
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JLabel lb_name = new JLabel("用户名:        ");
		lb_name.setFont(new Font("", Font.BOLD, 15));
		JLabel lb_init_pass = new JLabel("初始化密码:");
		lb_init_pass.setFont(new Font("", Font.BOLD, 15));
		JLabel lb_confirm_pass = new JLabel("确认密码:    ");
		lb_confirm_pass.setFont(new Font("", Font.BOLD, 15));
		panel2.add(lb_name);
		panel2.add(tfuname);
		panel3.add(lb_init_pass);
		panel3.add(userPassInit);
		panel4.add(lb_confirm_pass);
		panel4.add(userPassConfirm);
		panel5.add(btn_confirm);
		panel5.add(btn_back);
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);
		mainPanel.add(panel4);
		mainPanel.add(panel5);
		this.add(mainPanel);
		this.setTitle("用户注册窗口");
		this.setSize(415, 220);
		this.setVisible(true);
		this.setLocationRelativeTo(null);// 窗口居中显示
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/admin.jpg")));
		this.setLocationRelativeTo(null);// 窗口居中显示
		this.setResizable(false);
		this.getRootPane().setDefaultButton(btn_confirm);// 设置默认按钮（就是回车的时候）
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//
	}

	public UserRegisterView() {
		userService=new UserService_mpl();
		initForm();
		registerListener();
	}

	private void registerListener() {
		btn_confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String uname=tfuname.getText().trim();
				String upass1=new String(userPassInit.getPassword());
				String upass2=new String(userPassConfirm.getPassword());
				if(uname.equals("")){
					JOptionPane.showMessageDialog(
							 UserRegisterView.this, "用户名不能为空！");
				}else if(upass1.equals("")){
					JOptionPane.showMessageDialog(
							 UserRegisterView.this, "密码不能为空！");
				}else if(!upass1.equals(upass2)){
					JOptionPane.showMessageDialog(
							 UserRegisterView.this, "两次密码不一致!");
				}else{
					User user=new User(uname, upass1, 0);
					int res = userService.registerUser(user);//调用业务层的注册方法
					if(res==-1){
						JOptionPane.showMessageDialog(
								 UserRegisterView.this, "此用户名已经存在!");
					}else if(res>0){
						JOptionPane.showMessageDialog(
								 UserRegisterView.this, "注册成功!");
						UserRegisterView.this.dispose();
					}else{
						JOptionPane.showMessageDialog(
								 UserRegisterView.this, "注册失败,请联系管理员!");
					}
				}
			}
		});
		
		btn_back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserRegisterView.this.dispose();
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