import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuListener;

public class PlayerForm extends JFrame implements ActionListener{

	JPanel gray,stim,main,center;
	JLabel stimLabel,kosong,kosong1;
	JMenuBar menubar;
	JMenu menu1,menu2;
	JMenuItem mitem1,mitem2,mitem3;
	
	public PlayerForm() {
		// TODO Auto-generated constructor stub

		setLayout(new BorderLayout());
		
		main = new JPanel(new GridLayout (3,1));
		main.setBackground(Color.LIGHT_GRAY);
		

		
		stim = new JPanel(new FlowLayout());
		stim.setBackground(Color.LIGHT_GRAY);
		kosong = new JLabel();
		kosong1 = new JLabel();
		
		stimLabel = new JLabel("Stim");
		stimLabel.setFont(new Font("Italic",Font.ITALIC,70));
		
		stim.add(stimLabel);
		main.add(kosong1);
		main.add(stim);
		main.add(kosong);

		add(main);
		
		
		JMenuBar menubar = new JMenuBar();
		menubar.setBackground(Color.DARK_GRAY);
		JMenu menu1 = new JMenu("Account");
		menu1.setForeground(Color.white);
		JMenu menu2 =new JMenu("Games");
		menu2.setForeground(Color.white);
		JMenuItem mitem1 =new JMenuItem ("Log Out");
		mitem1.setForeground(Color.white);
		mitem1.setBackground(Color.DARK_GRAY);
		JMenuItem mitem2 =new JMenuItem ("Buy Games");
		mitem2.setForeground(Color.white);
		mitem2.setBackground(Color.DARK_GRAY);
		JMenuItem mitem3 =new JMenuItem ("Owned Games");
		mitem3.setForeground(Color.white);
		mitem3.setBackground(Color.DARK_GRAY);
		
		menu1.add(mitem1);
		menu2.add(mitem2);
		menu2.add(mitem3);
		
		menubar.add(menu1);
		menubar.add(menu2);
		
		setJMenuBar(menubar);
		frame();
		
		mitem1.addActionListener(this);
		mitem2.addActionListener(this);
		mitem3.addActionListener(this);
	}

	public void frame() {
		//Set Size
		setSize(new Dimension (700,500));	
		//set kalo close, program di terminate
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//set frame di tengah
		setLocationRelativeTo(null);
		//Keluarin Frame
		setVisible(true);	
		//disable resizeable
		setResizable(false);
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Log Out")) {
			this.dispose();
			new Login();
		}else if(e.getActionCommand().equals("Buy Games")) {
			this.dispose();
			new BuyGames();
		}else if(e.getActionCommand().equals("Owned Games")) {
			this.dispose();
			new OwnedGames();
		}
		
	}


	
}
