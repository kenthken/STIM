import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener{
	

	JFrame frame;

	JLabel loginlbl, usernamelbl, passwordlbl;
	JPanel loginpnl, centerpnl,centerpnl1, bottompnl, usernamepnl, passwordpnl, usernamepnl1, passwordpnl1, loginbuttonpnl, registerbuttonpnl
			,newpanel;
	public static JTextField usernametf;
	JButton loginbtn, registerbtn;
	public static JPasswordField passwordtf;
	
	Connect con = Connect.getInstance();

	
	
	
	public Login() {
		// TODO Auto-generated constructor stub
		frame();
	}
	
	public void screen() {
		setLayout(new BorderLayout());
		loginpnl=new JPanel();
		loginpnl.setLayout(new FlowLayout(FlowLayout.CENTER,0,30));
		loginpnl.setPreferredSize(new Dimension (50,70));
		
		
		centerpnl=new JPanel();
		
		
		
		
		
		bottompnl=new JPanel(new GridLayout(1,2));
		bottompnl.setPreferredSize(new Dimension (100,120));
		bottompnl.setLayout(new FlowLayout(FlowLayout.CENTER,-1,0));
		usernamepnl=new JPanel();
		usernamepnl1=new JPanel();
		passwordpnl=new JPanel();
		passwordpnl1=new JPanel();
		loginbuttonpnl=new JPanel();
		registerbuttonpnl=new JPanel();
	
		
		loginlbl=new JLabel("Login");
		usernamelbl=new JLabel("Username :");
		usernametf=new JTextField();
		passwordlbl=new JLabel("Password :");
		passwordtf=new JPasswordField();
		
		usernametf.setPreferredSize(new Dimension(300,20));
		passwordtf.setPreferredSize(new Dimension(300,20));
		usernametf.setBackground(Color.DARK_GRAY);
		passwordtf.setBackground(Color.DARK_GRAY);
		usernametf.setForeground(Color.white);
		passwordtf.setForeground(Color.white);
		

		
		loginlbl.setFont(new Font("Times New Roman",Font.PLAIN,30));
		loginlbl.setForeground(Color.WHITE);
		usernamelbl.setForeground(Color.WHITE);
		passwordlbl.setForeground(Color.WHITE);
		
		
		usernamepnl.add(usernamelbl);
		passwordpnl.add(passwordlbl);
		usernamepnl1.add(usernametf);
		passwordpnl1.add(passwordtf);
		
		loginbtn=new JButton("Login");
		registerbtn=new JButton("Register");
		loginbtn.setPreferredSize(new Dimension(100,25));
		registerbtn.setPreferredSize(new Dimension(100,25));
		
		loginbuttonpnl.add(loginbtn);
		registerbuttonpnl.add(registerbtn);	
		
		//top
		loginpnl.add(loginlbl);
		
		//Center
		centerpnl.add(usernamepnl);
		centerpnl.add(usernamepnl1);
		centerpnl.add(passwordpnl);
		centerpnl.add(passwordpnl1);
		
		//Bottom
		bottompnl.add(loginbuttonpnl);
		bottompnl.add(registerbuttonpnl);
		
		loginpnl.setBackground(Color.DARK_GRAY);
		usernamepnl.setBackground(Color.DARK_GRAY);
		passwordpnl.setBackground(Color.DARK_GRAY);
		usernamepnl1.setBackground(Color.DARK_GRAY);
		passwordpnl1.setBackground(Color.DARK_GRAY);
		loginbuttonpnl.setBackground(Color.DARK_GRAY);
		registerbuttonpnl.setBackground(Color.DARK_GRAY);
		bottompnl.setBackground(Color.DARK_GRAY);
		centerpnl.setBackground(Color.DARK_GRAY);
		
		
		
		add(loginpnl,BorderLayout.NORTH);
		add(centerpnl,BorderLayout.CENTER);
		add(bottompnl,BorderLayout.SOUTH);
		
		loginbtn.addActionListener(this);
		registerbtn.addActionListener(this);
	}
	
	public void frame() {
		screen();
		//Set Size
		setSize(new Dimension (460,300));
		//set kalo close, program di terminate
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//set frame di tengah
		setLocationRelativeTo(null);
		//Keluarin Frame
		setVisible(true);
		//disable resizeable
		setResizable(false);
	}

	public void login() {
		String username=usernametf.getText();
		String password=passwordtf.getText();
		if(username.equals("")&&password.equals("")) {
			JOptionPane.showMessageDialog(null, "Username and Password cannot be empty","Warning",JOptionPane.WARNING_MESSAGE);
			return;
		}else if(username.equals("")) {
			JOptionPane.showMessageDialog(null, "Username cannot be empty","Warning",JOptionPane.WARNING_MESSAGE);
			return;
		}else if(password.equals("")){
			JOptionPane.showMessageDialog(null, "Password cannot be empty","Warning",JOptionPane.WARNING_MESSAGE);
			return;
		}	
		
		String query =String.format("SELECT * FROM user WHERE username = '%s'"+ "AND password = '%s'",
						username,password);
		ResultSet res = con.executeQuery(query);
		
		
		
		try {
			if(res.next()) {
				if(username.equals(res.getString("username"))&&password.equals(res.getString("password"))){					
					JOptionPane.showMessageDialog(null, "Login Success","Warning",JOptionPane.WARNING_MESSAGE);
					if(res.getString("role").equals("Player")) {
						this.dispose();
						new PlayerForm();
					}else {
						this.dispose();
						new DeveloperForm();
					}
					
				}
			}else {
				JOptionPane.showMessageDialog(null, "Username/Password is Wrong","Warning",JOptionPane.WARNING_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==registerbtn) {
			this.dispose();
			new RegistrationForm();	
		}else if(e.getSource()==loginbtn) {
			login();
		}
	}

}
