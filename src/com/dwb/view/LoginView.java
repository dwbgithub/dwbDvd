package com.dwb.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.dwb.myclass.User;
import com.dwb.service.IUserService;
import com.dwb.servicempl.UserService_mpl;


public class LoginView extends JFrame{
	 JPanel panel_main ;
	 JPanel panel_left ;
	 JPanel panel_right ;
	 JLabel lb_uname ;
	 JLabel lb_upass ;
	 JLabel lb_type ;
	 JLabel lb_img ;
	 JButton btn_login ;
	 JButton btn_register ;
	 JTextField tf_uname ;
	 JPasswordField pf_pass ;
	 JComboBox<String> cb_type ;

	private void initForm() {
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/admin.jpg")));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel_main=new JPanel(new BorderLayout());
		panel_left=new JPanel();
		panel_right=new JPanel(new GridLayout(4, 2,0,50));
		
		tf_uname = new JTextField(8);
		tf_uname.setBounds(0,0,10,10);
		pf_pass = new JPasswordField(8);
		cb_type = new JComboBox<String>(new String[] { "普通用户", "管理员" });
		cb_type.setForeground(Color.black);
		btn_login=new JButton("登录");
		btn_login .setForeground(Color.black);
		btn_login.setFont(new Font("", Font.BOLD, 20));
		btn_register=new JButton("注册");
		btn_register .setForeground(Color.black);
		btn_register.setFont(new Font("", Font.BOLD, 20));
		lb_uname = new JLabel("用    户:", JLabel.CENTER);
		lb_uname .setForeground(Color.black);
		lb_uname.setFont(new Font("", Font.BOLD, 20));
		lb_upass = new JLabel("密    码:", JLabel.CENTER);
		lb_upass  .setForeground(Color.black);
		lb_upass.setFont(new Font("", Font.BOLD, 20));
		lb_type = new JLabel("类    型:", JLabel.CENTER);
		lb_type .setForeground(Color.black);
		lb_type.setFont(new Font("", Font.BOLD, 20));
		lb_img = new JLabel(new ImageIcon(ClassLoader.getSystemResource("images/i.jpg")));
		panel_left.add(lb_img);
		
		panel_right.add(lb_uname);
		panel_right.add(tf_uname);
		panel_right.add(lb_upass);
		panel_right.add(pf_pass);
		panel_right.add(lb_type);
		panel_right.add(cb_type);
		panel_right.add(btn_login);
		panel_right.add(btn_register);
		panel_main.add(panel_left,BorderLayout.WEST);
		panel_main.add(panel_right,BorderLayout.EAST);
		
		this.getContentPane().add(panel_main);
		this.setTitle("登录窗口");
		this.pack();
		this.setLocationRelativeTo(null);// 窗口居中显示
		this.setVisible(true);
	}
	
	public LoginView() {
		initForm();
		allListener();
	}
	
	private void allListener() {
		btn_login.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String uname = tf_uname.getText();
				String upass = new String(pf_pass.getPassword());
				int type = cb_type.getSelectedIndex();
				if (uname.equals("")) {
					JOptionPane.showMessageDialog(LoginView.this, "用户名不能为空!");
					return;
				} else if (upass.equals("")) {
					JOptionPane.showMessageDialog(LoginView.this, "密码不能为空!");
					return;
				}
				User user = new User(uname, upass, type);
				user = new UserService_mpl().login(user);
				if (user != null && user.getUname() != null) {
					if (user.getType() == 1) {
						new AdminMainView();// 管理员操作窗口
					} else {
						new UserMainView(user);// 用户操作窗口
					}
					LoginView.this.dispose();
				} else {
					JOptionPane.showMessageDialog(LoginView.this, "用户名或密码错误!");
				}
			
			}
		});
		
		btn_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String string=cb_type.getSelectedItem().toString();
				if (string.equals("管理员")) {
					JOptionPane.showMessageDialog(null, "管理员无法注册！请改为普通用户！");
				}else{
					new UserRegisterView();
				}
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
