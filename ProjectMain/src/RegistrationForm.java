import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationForm extends JFrame implements ActionListener{
	
	JLabel titlelbl, titlelbl2, blanklbl, usernamelbl, passwordlbl, genderlbl, countrylbl, rolelbl;
	JPanel mainpnl, titlepnl, centerpnl, bottompnl, genderpnl, rolepnl, passpnl, userpnl, countrypnl, backpnl, registerpnl;
	JTextField usernametf;
	JPasswordField passwordtf;
	JRadioButton male, female, player, developer;
	JComboBox country;
	JButton backbtn, registerbtn;
	ButtonGroup gendergp, rolegp;
	
	Connect con = Connect.getInstance();
	private Statement st;
	
	public RegistrationForm() {
		// TODO Auto-generated constructor stub
		
		setLayout(new BorderLayout());
		JFrame frame = new JFrame("Registration");
		frame.setSize(new Dimension(550,400));
		frame.setLocationRelativeTo(null);
		
		mainpnl = new JPanel(new FlowLayout());
		mainpnl.setBackground(Color.DARK_GRAY);
		
		titlepnl = new JPanel(new BorderLayout());
		
		centerpnl = new JPanel(new GridLayout(6,2,0,0));
		centerpnl.setBackground(Color.DARK_GRAY);
		
		bottompnl = new JPanel(new FlowLayout());
		bottompnl.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		bottompnl.setBackground(Color.DARK_GRAY);
		
		genderpnl = new JPanel(new FlowLayout());
		genderpnl.setLayout(new FlowLayout(FlowLayout.LEFT, 14,0));
		genderpnl.setBackground(Color.DARK_GRAY);
		
		rolepnl = new JPanel(new FlowLayout());
		rolepnl.setLayout(new FlowLayout(FlowLayout.LEFT, 12,0));
		rolepnl.setBackground(Color.DARK_GRAY);
		
		passpnl = new JPanel(new FlowLayout());
		passpnl.setBackground(Color.DARK_GRAY);
		
		userpnl = new JPanel(new FlowLayout());
		userpnl.setBackground(Color.DARK_GRAY);
		
		countrypnl = new JPanel(new FlowLayout());
		countrypnl.setLayout(new FlowLayout(FlowLayout.LEFT, 8,0));
		countrypnl.setBackground(Color.DARK_GRAY);
		
		backpnl = new JPanel(new FlowLayout());
		backpnl.setBackground(Color.DARK_GRAY);
		
		registerpnl = new JPanel(new FlowLayout());
		registerpnl.setBackground(Color.DARK_GRAY);
		
		//Blank
		blanklbl = new JLabel("");
		blanklbl.setPreferredSize(new Dimension(0,270));
		
		//Title
		titlelbl = new JLabel("Create An Account");
		titlelbl.setForeground(Color.white);
		titlelbl.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		titlelbl2 = new JLabel("");
		
		//Username
		usernamelbl = new JLabel("Username");
		usernamelbl.setForeground(Color.white);
		usernamelbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		usernametf = new JTextField(20);
		usernametf.setPreferredSize(new Dimension(0,25));
		usernametf.setForeground(Color.white);
		usernametf.setBackground(Color.DARK_GRAY);
		
		userpnl.add(usernametf);
		
		//Password
		passwordlbl = new JLabel("Password");
		passwordlbl.setForeground(Color.white);
		passwordlbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		passwordtf = new JPasswordField(20);
		passwordtf.setForeground(Color.white);
		passwordtf.setPreferredSize(new Dimension(0,25));
		passwordtf.setBackground(Color.DARK_GRAY);
		
		passpnl.add(passwordtf);
		
		//Gender
		genderlbl = new JLabel("Gender");
		genderlbl.setForeground(Color.white);
		genderlbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		male = new JRadioButton();
		female = new JRadioButton();
		
		male.setBackground(Color.DARK_GRAY);
		male.setForeground(Color.white);
		female.setBackground(Color.DARK_GRAY);
		female.setForeground(Color.white);
		
		male.setText("Male");
		female.setText("Female");
		
		gendergp = new ButtonGroup();
		gendergp.add(male);
		gendergp.add(female);
		
		genderpnl.add(male);
		genderpnl.add(female);
		
		//Country
		
		String countrylist[] = {"Select a Country","Indonesia", "America", "England", "Malaysia", "Singapore", "South Korea", "German"};
		
		countrylbl = new JLabel("Country");
		countrylbl.setForeground(Color.white);
		countrylbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		country = new JComboBox(countrylist);
		country.setPreferredSize(new Dimension(130,30));
		country.setBackground(Color.DARK_GRAY);
		country.setForeground(Color.white);
		
		countrypnl.add(country);
		
		//Role
		rolelbl = new JLabel("Choose a role :");
		rolelbl.setForeground(Color.white);
		rolelbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		player = new JRadioButton();
		player.setBackground(Color.DARK_GRAY);
		player.setForeground(Color.white);
		developer = new JRadioButton();
		developer.setBackground(Color.DARK_GRAY);
		developer.setForeground(Color.white);
		
		player.setText("Player");
		developer.setText("Developer");
		
		rolegp = new ButtonGroup();
		rolegp.add(player);
		rolegp.add(developer);
		
		rolepnl.add(player);
		rolepnl.add(developer);
		
		//Button
		backbtn = new JButton("Back");
		backbtn.setBackground(Color.DARK_GRAY);
		backbtn.setForeground(Color.white);
		
		registerbtn = new JButton("Register");
		registerbtn.setBackground(Color.DARK_GRAY);
		registerbtn.setForeground(Color.white);

		backpnl.add(backbtn);
		backpnl.setLayout(new FlowLayout(FlowLayout.RIGHT,10,0));
		
		registerpnl.add(registerbtn);
		registerpnl.setLayout(new FlowLayout(FlowLayout.LEFT,10,0));
		
		titlepnl.add(blanklbl);
		
		centerpnl.add(titlelbl);
		centerpnl.add(titlelbl2);
		centerpnl.add(usernamelbl);
		centerpnl.add(userpnl);
		centerpnl.add(passwordlbl);
		centerpnl.add(passpnl);
		centerpnl.add(genderlbl);
		centerpnl.add(genderpnl);
		centerpnl.add(countrylbl);
		centerpnl.add(countrypnl);
		centerpnl.add(rolelbl);
		centerpnl.add(rolepnl);
		
		bottompnl.add(backpnl);
		bottompnl.add(registerpnl);
		
		mainpnl.add(titlepnl, BorderLayout.NORTH);
		mainpnl.add(centerpnl);
		mainpnl.add(bottompnl);
		add(mainpnl);
		
		frame();
		backbtn.addActionListener(this);
		registerbtn.addActionListener(this);
	}
	
	
	
	public void frame() {
		//Set Size
		setSize(new Dimension (500,380));	
		//set kalo close, program di terminate
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//set frame di tengah
		setLocationRelativeTo(null);
		//Keluarin Frame
		setVisible(true);
		//disable resizeable
		setResizable(false);
	}
	
	public void validation() {
		
		
		String username=usernametf.getText();
		String password=passwordtf.getText();
		String gender=male.isSelected() ? "Male" : "Female";
		String country1=(String) country.getSelectedItem();
		String role=player.isSelected() ? "Player" : "Developer";
		
		if(username.equals("")&&password.equals("")&&male.isSelected()==false&&female.isSelected()==false&&country1.equals("Select a Country")&&player.isSelected()==false&&developer.isSelected()==false) {
			JOptionPane.showMessageDialog(null, "All Field Must be Input","Warning",JOptionPane.WARNING_MESSAGE);
		}else if(!(username.length()>4&&username.length()<16)) {
			JOptionPane.showMessageDialog(null, "Username Length Must be at least 5-15 chars","Warning",JOptionPane.WARNING_MESSAGE);
		}else if(!(password.length()>2&&password.length()<11)) {
			JOptionPane.showMessageDialog(null, "Password Lenght Must be at least 3-10 chars","Warning",JOptionPane.WARNING_MESSAGE);
		}else if(male.isSelected()==false&&female.isSelected()==false) {
			JOptionPane.showMessageDialog(null, "Please select a gender","Warning",JOptionPane.WARNING_MESSAGE);
		}else if(country1.equals("Select a Country")) {
			JOptionPane.showMessageDialog(null, "Please select a Country","Warning",JOptionPane.WARNING_MESSAGE);
		}else if(player.isSelected()==false&&developer.isSelected()==false) {
			JOptionPane.showMessageDialog(null, "Please select a role","Warning",JOptionPane.WARNING_MESSAGE);
		}else {
			
			String query = String.format("SELECT * FROM user WHERE username = '%s'",username);
			ResultSet res = con.executeQuery(query);
			
			try {
				if(res.next()) {
					JOptionPane.showMessageDialog(null, "Username already exists!","Warning",JOptionPane.WARNING_MESSAGE);
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			Integer id=0;
			
			String userid =String.format("SELECT MAX(userid) AS 'total' FROM user ");
			ResultSet resid = con.executeQuery(userid);
			
			try {
				if(resid.next()) {
					id = Integer.parseInt(resid.getString("total"));
					id++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			query = "INSERT INTO `user`(`userId`, `username`, `password`, `gender`, `country`, `role`) VALUES ('" + id + "','" + username + "','" + password + "','" + gender + "','" + country1 + "','" + role + "')";
			con.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Successfuly registered user","Warning",JOptionPane.WARNING_MESSAGE);
			
			clearfield();
			
		}
		
		

	}
	
	public void clearfield() {
		usernametf.setText("");
		passwordtf.setText("");
		gendergp.clearSelection();;
		country.setSelectedIndex(0);
		rolegp.clearSelection();
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==backbtn) {
			setVisible(false);
			new Login();	
		}else if(e.getSource()==registerbtn) {
			validation();
			
			
		}
	}
	
}
