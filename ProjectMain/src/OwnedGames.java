import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class OwnedGames extends JFrame implements ActionListener,MouseListener{

	DefaultTableModel dtm;
	JPanel insidebottom,main,top,bottom,darkgrey,tabelpanel,gameidpanel,gameidtfpanel,gamenamepanel
			,gamenametfpanel,gamepricepanel,gamepricetfpanel,gamegenrepanel,gamegenretfpanel,howmuchpanel,howmuchspanel
			,checkboxlabelpanel,checkboxpanel,buygamespanel,backpanel;
	JTable tabel;
	JScrollPane scrollpane;
	JTableHeader headerr;
	

	JLabel gameidlabel,gamenamelabel,gamepricelabel,gamegenrelabel,
			howmanylabel,checkboxlabel,kosong,kosong1;
	JTextField gameidtf,gamenametf,gamepricetf,gamegenretf,ownedtf,spenttf;
	JSpinner qty;
	JCheckBox cb;
	JButton buygames,back;
	Login loginpage;
	
	int selectedIndex=-1;
	Connect con = Connect.getInstance();
	private Statement st;
	
	public OwnedGames() {
//		// TODO Auto-generated constructor stub
		
		setLayout(new BorderLayout());

		
		main = new JPanel(new GridLayout(2,1));
		main.setBackground(Color.DARK_GRAY);
		
//TOP
		top = new JPanel(new BorderLayout());
		// TABLE
		Vector <Object> header = new Vector();
		header.add("Game ID");
		header.add("Game Name");
		header.add("Genre");
		header.add("Quantity");
		header.add("Price");
	
		String user = loginpage.usernametf.getText();
		

		String query = String.format("SELECT gm.gameId,gm.name,gr.genreName,SUM(t.gameQuantity) AS quantity,gm.price FROM  user u JOIN transaction t ON u.userId = t.userId JOIN game gm ON t.gameId = gm.gameId JOIN genre gr ON gm.genreId = gr.genreId WHERE username = '%s' GROUP BY  gm.gameId,gm.name,gr.genreName,gm.price;",user);
															
		ResultSet res = con.executeQuery(query);
		
		Vector <Vector <Object>> data = new Vector();
		try {
			while(res.next()) {
				Vector <Object> game = new Vector();
				game.add(res.getString("gameId"));
				game.add(res.getString("name"));			
				game.add(res.getString("genreName"));
				game.add(res.getInt("quantity"));
				game.add(res.getInt("price"));
				data.add(game);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dtm=new DefaultTableModel(data, header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
			
		};

		tabel=new JTable(dtm);
		tabel.setBackground(Color.DARK_GRAY);
		tabel.setForeground(Color.white);
		
		tabel.getTableHeader().setReorderingAllowed(false);
		tabel.setRowHeight(30);
		scrollpane = new JScrollPane(tabel);
		scrollpane.setPreferredSize(new Dimension(887,320));
		scrollpane.setBackground(Color.black);
		
		
		headerr=tabel.getTableHeader();
		headerr.setBackground(Color.DARK_GRAY);
		headerr.setForeground(Color.white);
		
		
		tabel.setOpaque(true);
		scrollpane.setOpaque(true);
		
		scrollpane.getViewport().setBackground(Color.DARK_GRAY);
	
			
		top.add(scrollpane);

		
		
//Bottom
		
		bottom= new JPanel(new FlowLayout(FlowLayout.CENTER,0,30));
		bottom.setBackground(Color.DARK_GRAY);
		
		insidebottom = new JPanel(new GridLayout(7,2,10,10));
		insidebottom.setBackground(Color.DARK_GRAY);
		insidebottom.setPreferredSize(new Dimension (460,240));
		
		//Label
		gameidlabel=new JLabel("Game ID");
		gameidlabel.setForeground(Color.white);
		
		gamenamelabel=new JLabel("Game Name");
		gamenamelabel.setForeground(Color.white);
		
		gamepricelabel=new JLabel("Game Price");
		gamepricelabel.setForeground(Color.white);
		
		gamegenrelabel=new JLabel("Game Genre");
		gamegenrelabel.setForeground(Color.white);
		
		howmanylabel=new JLabel("Owned Quantity");
		howmanylabel.setForeground(Color.white);
		
		checkboxlabel=new JLabel("Total Spent on Games");
		checkboxlabel.setForeground(Color.white);
		
		kosong = new JLabel();
		kosong.setBackground(Color.DARK_GRAY);
		kosong1 = new JLabel();
		kosong1.setBackground(Color.DARK_GRAY);
		
		
		
		//Text Field
		gameidtf=new JTextField();
		gameidtf.setPreferredSize(new Dimension(250,25));
		gameidtf.setBackground(Color.DARK_GRAY);
		gameidtf.setForeground(Color.white);
		gameidtf.setEditable(false);
			
		gamenametf=new JTextField();
		gamenametf.setPreferredSize(new Dimension(250,25));
		gamenametf.setBackground(Color.DARK_GRAY);
		gamenametf.setForeground(Color.white);
		gamenametf.setEditable(false);
		
		gamepricetf=new JTextField();
		gamepricetf.setPreferredSize(new Dimension(250,25));
		gamepricetf.setBackground(Color.DARK_GRAY);
		gamepricetf.setForeground(Color.white);
		gamepricetf.setEditable(false);
		
		gamegenretf=new JTextField();
		gamegenretf.setPreferredSize(new Dimension(250,25));
		gamegenretf.setBackground(Color.DARK_GRAY);
		gamegenretf.setForeground(Color.white);
		gamegenretf.setEditable(false);
		
		ownedtf=new JTextField();
		ownedtf.setPreferredSize(new Dimension(250,25));
		ownedtf.setBackground(Color.DARK_GRAY);
		ownedtf.setForeground(Color.white);
		ownedtf.setEditable(false);
		
		spenttf=new JTextField();
		spenttf.setPreferredSize(new Dimension(250,25));
		spenttf.setBackground(Color.DARK_GRAY);
		spenttf.setForeground(Color.white);
		spenttf.setEditable(false);
		
		//Button
		backpanel = new JPanel(new GridLayout(1,3));
		backpanel.setBackground(Color.DARK_GRAY);
		
		back=new JButton("Back");
		back.setPreferredSize(new Dimension(70,20));
		back.setBackground(Color.DARK_GRAY);
		back.setForeground(Color.white);
//		back.setBorder(new EmptyBorder(-10, 0, 0, 170));
		backpanel.add(back);
		backpanel.add(kosong);
		backpanel.add(kosong1);
		
		
		insidebottom.add(gameidlabel);
		insidebottom.add(gameidtf);
		insidebottom.add(gamenamelabel);
		insidebottom.add(gamenametf);
		insidebottom.add(gamepricelabel);
		insidebottom.add(gamepricetf);
		insidebottom.add(gamegenrelabel);
		insidebottom.add(gamegenretf);
		insidebottom.add(howmanylabel);
		insidebottom.add(ownedtf);
		insidebottom.add(checkboxlabel);
		insidebottom.add(spenttf);
		insidebottom.add(backpanel);
		
		
		bottom.add(insidebottom);
		
		
//ADD
		main.add(top);	
		main.add(bottom);
		add(main);
		
		frame();
		
		tabel.addMouseListener(this);
		back.addActionListener(this);
	}

	public void frame() {
		//Set Size
		setSize(new Dimension (900,650));	
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
		if(e.getSource()==back) {
			this.dispose();
			new PlayerForm();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==tabel) {
			int row = tabel.getSelectedRow();
			selectedIndex = row;
			gameidtf.setText(dtm.getValueAt(selectedIndex, 0).toString());
			gamenametf.setText(dtm.getValueAt(selectedIndex, 1).toString());
			gamegenretf.setText(dtm.getValueAt(selectedIndex, 2).toString());
			ownedtf.setText(dtm.getValueAt(selectedIndex, 3).toString());
			gamepricetf.setText(dtm.getValueAt(selectedIndex, 4).toString());
			Integer price = Integer.parseInt(dtm.getValueAt(selectedIndex, 4).toString());
			Integer own = Integer.parseInt(dtm.getValueAt(selectedIndex, 3).toString());
			Integer spent = price*own;
			String parsespent = String.valueOf(spent);
			spenttf.setText(parsespent);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
