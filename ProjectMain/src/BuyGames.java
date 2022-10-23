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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class BuyGames extends JFrame implements ActionListener,MouseListener{
	
	DefaultTableModel dtm;
	JPanel insidebottom,main,top,bottom,darkgrey,tabelpanel,gameidpanel,gameidtfpanel,gamenamepanel
			,gamenametfpanel,gamepricepanel,gamepricetfpanel,gamegenrepanel,gamegenretfpanel,howmuchpanel,howmuchspanel
			,checkboxlabelpanel,checkboxpanel,buygamespanel,backpanel;
	JTable tabel;
	JScrollPane scrollpane;
	JTableHeader headerr;
	
//awdsa
	
	JLabel gameidlabel,gamenamelabel,gamepricelabel,gamegenrelabel,
			howmanylabel,checkboxlabel,kosong,kosong1;
	JTextField gameidtf,gamenametf,gamepricetf,gamegenretf;
	JSpinner qty;
	JCheckBox cb;
	JButton buygames,back;
	Login loginpage;
	
	int selectedIndex=-1;
	Connect con = Connect.getInstance();
	private Statement st;

	public BuyGames() {
//		// TODO Auto-generated constructor stub
		
		setLayout(new BorderLayout());
		// TODO Auto-generated constructor stub
		
		main = new JPanel(new GridLayout(2,1));
		main.setBackground(Color.DARK_GRAY);
		
//TOP
		top = new JPanel(new BorderLayout());
		// TABLE
		Vector <Object> header = new Vector();
		header.add("Game ID");
		header.add("Game Name");
		header.add("Game Price");
		header.add("Genre");
		header.add("Quantity");
	
		
		
		String query = String.format("SELECT * FROM game gm JOIN genre gr ON gm.genreId = gr.genreId WHERE quantity > 0");
		ResultSet res = con.executeQuery(query);
		
		Vector <Vector <Object>> data = new Vector();
		try {
			while(res.next()) {
				Vector <Object> game = new Vector();
				game.add(res.getString("gameid"));
				game.add(res.getString("name"));			
				game.add(res.getInt("price"));
				game.add(res.getString("genreName"));
				game.add(res.getInt("quantity"));
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
		
		howmanylabel=new JLabel("How many do you want to buy?");
		howmanylabel.setForeground(Color.white);
		
		checkboxlabel=new JLabel("Once bought game cannot be returned!");
		checkboxlabel.setForeground(Color.white);
		
		kosong = new JLabel("");
		kosong1 = new JLabel("");
		
		//Text Field

		gameidtf=new JTextField();
		gameidtf.setEditable(false);
		gameidtf.setBackground(Color.DARK_GRAY);
		gameidtf.setForeground(Color.white);

		gamenametf=new JTextField();
		gamenametf.setEditable(false);
		gamenametf.setBackground(Color.DARK_GRAY);
		gamenametf.setForeground(Color.white);

		gamepricetf=new JTextField();
		gamepricetf.setEditable(false);
		gamepricetf.setPreferredSize(new Dimension(250,25));
		gamepricetf.setBackground(Color.DARK_GRAY);
		gamepricetf.setForeground(Color.white);
		
		gamegenretf=new JTextField();
		gamegenretf.setEditable(false);
		gamegenretf.setPreferredSize(new Dimension(250,25));
		gamegenretf.setBackground(Color.DARK_GRAY);
		gamegenretf.setForeground(Color.white);
		
		//Spinner
		qty= new JSpinner();
		qty.setPreferredSize(new Dimension(250,25));

		//CheckBox
		cb=new JCheckBox();
		cb.setBackground(Color.DARK_GRAY);
		
//		//Button
		backpanel = new JPanel();
		backpanel.setBackground(Color.DARK_GRAY);
		back=new JButton("Back");
		back.setPreferredSize(new Dimension(80,20));		
		back.setBackground(Color.DARK_GRAY);
		back.setForeground(Color.white);
		backpanel.add(back);
		
		buygamespanel = new JPanel();
		buygamespanel.setBackground(Color.DARK_GRAY);
		buygames=new JButton("Buy Games");
		buygames.setPreferredSize(new Dimension(100,20));
		buygames.setBackground(Color.DARK_GRAY);
		buygames.setForeground(Color.white);
		buygamespanel.add(buygames);
		
		//ADD to insidebottom
		
		insidebottom.add(gameidlabel);
		insidebottom.add(gameidtf);
		insidebottom.add(gamenamelabel);
		insidebottom.add(gamenametf);
		insidebottom.add(gamepricelabel);
		insidebottom.add(gamepricetf);
		insidebottom.add(gamegenrelabel);
		insidebottom.add(gamegenretf);
		insidebottom.add(howmanylabel);
		insidebottom.add(qty);
		insidebottom.add(checkboxlabel);
		insidebottom.add(cb);
		insidebottom.add(backpanel);
		insidebottom.add(buygamespanel);
		
		
	
		//ADD to bottom panel
		bottom.add(insidebottom);

		
//ADD
		main.add(top);
		main.add(bottom);
		add(main);
		
		frame();
		
//ActionListener & MouseListener	
		tabel.addMouseListener(this);
		back.addActionListener(this);
		buygames.addActionListener(this);
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==tabel) {
			int row = tabel.getSelectedRow();
			selectedIndex = row;
			Integer quantity = Integer.parseInt(dtm.getValueAt(row, 4).toString());
			if(quantity == 0) {
				JOptionPane.showMessageDialog(null, "Sorry game already sold out","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			gameidtf.setText(dtm.getValueAt(row, 0).toString());
			gamenametf.setText(dtm.getValueAt(row, 1).toString());
			gamepricetf.setText(dtm.getValueAt(row, 2).toString());
			gamegenretf.setText(dtm.getValueAt(row, 3).toString());
			

		}
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==back) {
			this.dispose();
			new PlayerForm();
		}else if(e.getSource()==buygames) {
			
			if(selectedIndex == -1) {
				JOptionPane.showMessageDialog(null, "Select a game first!","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}
			int row = tabel.getSelectedRow();
			String gameid = gameidtf.getText();
			String username = loginpage.usernametf.getText();
			String password = loginpage.passwordtf.getText();	
			Integer quantity = Integer.parseInt(qty.getValue().toString());
			Integer stock = Integer.parseInt(dtm.getValueAt(row, 4).toString());
			
			 if (stock == 0) {
				JOptionPane.showMessageDialog(null, "Sorry game already sold out","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			
			if(quantity < 1 || quantity > stock) {
				JOptionPane.showMessageDialog(null, "Game quantity cannot be less than 0 or more than stock","Warning",JOptionPane.WARNING_MESSAGE);
			}else if(cb.isSelected() == false) {
				JOptionPane.showMessageDialog(null, "Checkbox must be checked!","Warning",JOptionPane.WARNING_MESSAGE);
			}else {
				Integer id = 0;
				
				String transactionid = String.format("SELECT MAX(transactionId) AS 'total' FROM transaction");
				ResultSet resid = con.executeQuery(transactionid);
				
				try {
					if(resid.next()) {
						id = Integer.parseInt(resid.getString("total"));
						id++;
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				String user = String.format("SELECT * FROM user WHERE username ='%s'"+"AND password = '%s'",username,password);
				ResultSet res = con.executeQuery(user);
				
				try {
					if(res.next()) {
					
						
						Integer userid = Integer.parseInt(res.getString("userId"));
						String insert = String.format("INSERT INTO transaction VALUES ('" + id +"', '" + userid + "','" + gameid + "','" + quantity + "')");
						con.executeUpdate(insert);
						
						
						
						int afterbuy = stock - quantity;
						
						String update = String.format("UPDATE game SET quantity = '" + afterbuy + "'" + "WHERE gameId = '%s'",gameid );
						con.executeUpdate(update); 
						
						
						
						
						dtm.setValueAt(afterbuy, selectedIndex, 4);
						int cek = (int) dtm.getValueAt(selectedIndex, 4);
						
						if(cek == 0) {
							dtm.removeRow(selectedIndex);
						}
						JOptionPane.showMessageDialog(null, "Game Bought","Warning",JOptionPane.WARNING_MESSAGE);
						
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
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
