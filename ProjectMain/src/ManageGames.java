import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class ManageGames extends JFrame implements ActionListener,MouseListener{

	DefaultTableModel dtm;
	JPanel main,top,bottom,bottomleft,bottomright,bottomleftcenter,blc1,blc2,brc1,brc2,bottomrightcenter,darkgrey,tabelpanel,gameidpanel,gameidtfpanel,gamenamepanel
			,gamenametfpanel,gamepricepanel,gamepricetfpanel,gamegenrepanel,gamegenretfpanel,howmuchpanel,howmuchspanel
			,checkboxlabelpanel,checkboxpanel,buygamespanel,backpanel,updatepanel,newgamepanel,newgametfpanel,newgamepricepanel,newgamepricetfpanel
			,newgamegenrepanel,newgamegenrecbpanel,newgamequantitypanel,newgamequantityspanel,insertpanel;
	JTable tabel;
	JScrollPane scrollpane;
	JTableHeader headerr;
	
	
	JLabel gameidlabel,gamenamelabel,gamepricelabel,gamegenrelabel,kosong,kosong1,kosong2,kosong3,kosong4,
			howmanylabel,checkboxlabel,newgamelabel,newgamepricelabel,newgamegenrelabel,newgamequantitylabel;
	JTextField gameidtf,gamenametf,gamepricetf,gamegenretf,namegametf,newgamepricetf;
	JSpinner qty,newqty;
	JButton buygames,back,update,insert;
	JComboBox<String> cb,newgamegenre;
	
	int selectedIndex=-1;
	Connect con = Connect.getInstance();
	private Statement st;
	
	public ManageGames() {
		
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
	
		
		String query = String.format("SELECT * FROM game gm JOIN genre gr ON gm.genreId = gr.genreId");
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
		scrollpane.setPreferredSize(new Dimension(984,320));
		scrollpane.setBackground(Color.black);
		
		headerr=tabel.getTableHeader();
		headerr.setBackground(Color.DARK_GRAY);
		headerr.setForeground(Color.white);
		
		
		
		tabel.setOpaque(true);
		scrollpane.setOpaque(true);
		
		scrollpane.getViewport().setBackground(Color.DARK_GRAY);
		
		top.add(scrollpane);
		
//Bottom
		
		bottom = new JPanel(new GridLayout(1,2));
		bottom.setBackground(Color.DARK_GRAY);
	
		//Bottom Left
		bottomleft = new JPanel(new FlowLayout(FlowLayout.CENTER,0,40));
		bottomleft.setBackground(Color.DARK_GRAY);
		
		bottomleftcenter = new JPanel(new BorderLayout());
		bottomleftcenter.setPreferredSize(new Dimension (350,200));
		bottomleftcenter.setBackground(Color.DARK_GRAY);
		
		blc1 = new JPanel(new GridLayout(6,1,0,5));
		blc1.setPreferredSize(new Dimension (100,200));
		blc1.setBackground(Color.DARK_GRAY);
		
		blc2 = new JPanel(new GridLayout(6,1,0,7));
		blc2.setPreferredSize(new Dimension (250,200));
		blc2.setBackground(Color.DARK_GRAY);
		
		
		
		//Bottom Right
		bottomright = new JPanel(new FlowLayout(FlowLayout.CENTER,0,25));
		bottomright.setBackground(Color.DARK_GRAY);
		
		bottomrightcenter = new JPanel(new BorderLayout());
		bottomrightcenter.setPreferredSize(new Dimension (350,150));
		bottomrightcenter.setBackground(Color.DARK_GRAY);
		
		brc1 = new JPanel(new GridLayout(5,1));
		brc1.setPreferredSize(new Dimension (150,150));
		brc1.setBackground(Color.DARK_GRAY);
		
		brc2 = new JPanel(new GridLayout(5,1,0,7));
		brc2.setPreferredSize(new Dimension(200,150));
		brc2.setBackground(Color.DARK_GRAY);
	
		insertpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
		insertpanel.setPreferredSize(new Dimension(350, 30));
		insertpanel.setBackground(Color.DARK_GRAY);
		//Label
		gameidlabel=new JLabel("Game ID");
		gameidlabel.setForeground(Color.white);
		
		gamenamelabel=new JLabel("Game Name");
		gamenamelabel.setForeground(Color.white);
		
		gamepricelabel=new JLabel("Game Price");
		gamepricelabel.setForeground(Color.white);
		
		gamegenrelabel=new JLabel("Game Genre");
		gamegenrelabel.setForeground(Color.white);
		
		howmanylabel=new JLabel("Game Quantity");
		howmanylabel.setForeground(Color.white);
		
		checkboxlabel=new JLabel("Once bought game cannot be returned!");
		checkboxlabel.setForeground(Color.white);
		
		newgamelabel=new JLabel("New Game Name");
		newgamelabel.setForeground(Color.white);
		
		newgamepricelabel=new JLabel("New Game Price");
		newgamepricelabel.setForeground(Color.white);
		
		newgamegenrelabel=new JLabel("New Game Genre");
		newgamegenrelabel.setForeground(Color.white);
		
		newgamequantitylabel=new JLabel("New Game Quantity");
		newgamequantitylabel.setForeground(Color.white);
		
		JLabel kosong = new JLabel();
		JLabel kosong1 = new JLabel();
		
		//Text Field
		gameidtf=new JTextField();
		gameidtf.setEditable(false);
		gameidtf.setPreferredSize(new Dimension(250,25));
		gameidtf.setBackground(Color.DARK_GRAY);
		gameidtf.setForeground(Color.white);
			
		gamenametf=new JTextField();
		gamenametf.setPreferredSize(new Dimension(250,25));
		gamenametf.setBackground(Color.DARK_GRAY);
		gamenametf.setForeground(Color.white);
		
		gamepricetf=new JTextField();
		gamepricetf.setPreferredSize(new Dimension(250,25));
		gamepricetf.setBackground(Color.DARK_GRAY);
		gamepricetf.setForeground(Color.white);
		
		gamegenretf=new JTextField();
		gamegenretf.setPreferredSize(new Dimension(250,25));
		gamegenretf.setBackground(Color.DARK_GRAY);
		gamegenretf.setForeground(Color.white);
		
		namegametf=new JTextField();
		namegametf.setPreferredSize(new Dimension(200,20));
		namegametf.setBackground(Color.DARK_GRAY);
		namegametf.setForeground(Color.white);
		
		newgamepricetf=new JTextField();
		newgamepricetf.setPreferredSize(new Dimension(200,20));
		newgamepricetf.setBackground(Color.DARK_GRAY);
		newgamepricetf.setForeground(Color.white);
		
	
		
		
		//Button
		backpanel = new JPanel();
		backpanel.setBackground(Color.DARK_GRAY);
		back=new JButton("Back");
		back.setPreferredSize(new Dimension(70,20));
		back.setBackground(Color.DARK_GRAY);
		back.setForeground(Color.white);
		backpanel.add(back);
		
		buygamespanel = new JPanel(new FlowLayout(FlowLayout.CENTER,40,4));
		buygamespanel.setBackground(Color.DARK_GRAY);
		buygames=new JButton("Delete");
		buygames.setPreferredSize(new Dimension(80,20));
		buygames.setBackground(Color.DARK_GRAY);
		buygames.setForeground(Color.white);
		buygamespanel.add(buygames);
		
		updatepanel = new JPanel();
		update=new JButton("Update");
		update.setPreferredSize(new Dimension(80,20));
		update.setBackground(Color.DARK_GRAY);
		update.setForeground(Color.white);
		buygamespanel.add(update);
		
		insert=new JButton("Insert");
		insert.setPreferredSize(new Dimension(80,20));
		insert.setBackground(Color.DARK_GRAY);
		insert.setForeground(Color.white);
		
		//Spinner
		qty= new JSpinner();
		qty.setPreferredSize(new Dimension(250,25));
		
		newqty= new JSpinner();
		newqty.setPreferredSize(new Dimension(200,20));
		
		//Combo Box
		String querycb = String.format("SELECT * FROM genre");
		ResultSet resultcb = con.executeQuery(querycb);

		
		cb=new JComboBox<String>();
		cb.setPreferredSize(new Dimension (250,25));
		cb.setBackground(Color.DARK_GRAY);
		cb.setForeground(Color.white);
		
		newgamegenre=new JComboBox<String>();
		newgamegenre.setPreferredSize(new Dimension (200,20));
		newgamegenre.setBackground(Color.DARK_GRAY);
		newgamegenre.setForeground(Color.white);
		
		try {
			while(resultcb.next()) {
				String a =resultcb.getString("genreName");
				cb.addItem(a);
				newgamegenre.addItem(a);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		// ADD BOTTOM LEFT
		blc1.add(gameidlabel);
		blc2.add(gameidtf);
		blc1.add(gamenamelabel);
		blc2.add(gamenametf);
		blc1.add(gamepricelabel);
		blc2.add(gamepricetf);
		blc1.add(gamegenrelabel);
		blc2.add(cb);
		blc1.add(howmanylabel);
		blc2.add(qty);
		blc1.add(backpanel);
		blc2.add(buygamespanel);
		bottomleftcenter.add(blc1,BorderLayout.WEST);
		bottomleftcenter.add(blc2,BorderLayout.EAST);
		
		bottomleft.add(bottomleftcenter);
		
		// ADD Bottom Right
		brc1.add(kosong);
		brc2.add(kosong1);
		brc1.add(newgamelabel);
		brc2.add(namegametf);
		brc1.add(newgamepricelabel);
		brc2.add(newgamepricetf);
		brc1.add(newgamegenrelabel);
		brc2.add(newgamegenre);
		brc1.add(newgamequantitylabel);
		brc2.add(newqty);
		
		
		bottomrightcenter.add(brc1,BorderLayout.WEST);
		bottomrightcenter.add(brc2,BorderLayout.EAST);
		
		insertpanel.add(insert);
		bottomright.add(bottomrightcenter);
		bottomright.add(insertpanel);
		// ADD BOTTOM
		bottom.add(bottomleft);
		bottom.add(bottomright);
		
		main.add(top);
		main.add(bottom);
		add(main);
		
		frame();
		
		back.addActionListener(this);
		tabel.addMouseListener(this);
		buygames.addActionListener(this);
		insert.addActionListener(this);
		update.addActionListener(this);
	}

	public void frame() {
		//Set Size
		setSize(new Dimension (1000,650));	
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
			new DeveloperForm();
		}else if (e.getSource()==buygames) {
			if(selectedIndex==-1) {
				JOptionPane.showMessageDialog(null, "Select a game first!","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}else {
				int yakin= JOptionPane.showConfirmDialog(null, "Are you sure want to delete?","Warning",JOptionPane.WARNING_MESSAGE);
				if(yakin == JOptionPane.YES_OPTION) {
					String gameid = gameidtf.getText();
					//delete database
					String querydelete= String.format("DELETE FROM game WHERE gameid = '%s'",gameid);
					con.executeUpdate(querydelete);
					//delete tabel
					dtm.removeRow(selectedIndex);
					JOptionPane.showMessageDialog(null, "Delete Success","Warning",JOptionPane.WARNING_MESSAGE);
					clearfield1();
				}
			}
		}else if (e.getSource()==insert) {
			String newgame = namegametf.getText();
			String newgameprice = newgamepricetf.getText();
			String newgenre = newgamegenre.getSelectedItem().toString();
			Integer newquantity = (Integer) newqty.getValue();
			
			String query = String.format("SELECT * FROM game WHERE name = '%s'",newgame);
			ResultSet res = con.executeQuery(query);
			
			if(newgame.equals("")||newgameprice.equals("")||newgenre.equals("Select a Genre")||newquantity == 0) {
				JOptionPane.showMessageDialog(null, "All field must be input","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}else if (newgame.length() < 5 || newgame.length() > 30) {
				JOptionPane.showMessageDialog(null, "New Game Name must consist of 5 - 30 characters","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			} else
				try {
					if (res.next()) {
						JOptionPane.showMessageDialog(null, "New Game Name already exist","Warning",JOptionPane.WARNING_MESSAGE);
						return;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			//price textfield numeric
			Integer numeric;
			try {
				numeric = Integer.parseInt(newgameprice);
				
			} catch (Exception e2) {
				// TODO: handle exception
				numeric = -1;
				JOptionPane.showMessageDialog(null, "New Game Price must be numeric","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			
			int price = Integer.parseInt(newgamepricetf.getText().toString());
		
			if(price < 0) {
				JOptionPane.showMessageDialog(null, "New Game Price must be bigger than 0","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}else if(newgenre.equals("Select a Genre")) {
				JOptionPane.showMessageDialog(null, "New Game Genre must be selected","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}else if(newquantity < 0) {
				JOptionPane.showMessageDialog(null, "New Game Quantity must be more than 0","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			

			
			String query1 = String.format("SELECT MAX(SUBSTRING(gameId,5,3)) AS 'total' FROM game");
			ResultSet res1 = con.executeQuery(query1);

			
			Integer no = 0;
			String game = "";
			
			
			try {
				if(res1.next()) {
					no = Integer.parseInt(res1.getString("total")) ;
					no++;
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			if(no < 10) {
				game = "GAME00" +no;
			}else if(no > 9) {
				game = "GAME0" +no;
			}else if(no > 99) {
				game = "GAME" +no;
			}
			

			//insert into database
			String genreid="";
			String querygenre = String.format("SELECT * FROM genre");
			ResultSet res2 = con.executeQuery(querygenre);
			
			try {
				while(res2.next()) {
					if(newgenre.equals(res2.getString("genreName"))) {
						genreid = res2.getString("genreId");
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			query1 ="INSERT INTO `game`(`gameid`, `name`, `price`, `genreid`, `quantity`) VALUES ('" + game + "','" + newgame + "','" + newgameprice + "','" + genreid + "','" + newquantity +"')";
			con.executeUpdate(query1);
			
			//insert new games into table
			dtm.addRow(new Object [] {game,newgame,newgameprice,newgenre,newquantity});
			JOptionPane.showMessageDialog(null, "Insert Success","Warning",JOptionPane.WARNING_MESSAGE);
			clearfield();
			
			
		}else if(e.getSource()==update) {
			String gameid=gameidtf.getText();
			String game = gamenametf.getText();
			String gameprice = gamepricetf.getText();
			String gamegenre = cb.getSelectedItem().toString();
			Integer gamequantity = (Integer) qty.getValue();
			
			String tesduplicate = String.format("SELECT * FROM game WHERE name = '%s'",game);
			ResultSet resduplicate = con.executeQuery(tesduplicate);
			
			if(selectedIndex==-1) {
				JOptionPane.showMessageDialog(null, "Select a game first!","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}else if(game.equals("")||gameprice.equals("")||gamegenre.equals("Select a Genre")||gamequantity == 0) {
				JOptionPane.showMessageDialog(null, "All field must be input","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}else if(game.length() < 5 || game.length() > 30){
				JOptionPane.showMessageDialog(null, "Game Name must consist of 5 - 30 characters","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}
			try {
				if (resduplicate.next()) {
					JOptionPane.showMessageDialog(null, "Game Name already exist","Warning",JOptionPane.WARNING_MESSAGE);
					return;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Integer numeric;
			try {
				numeric = Integer.parseInt(gameprice);
				
			} catch (Exception e2) {
				// TODO: handle exception
				numeric = -1;
				JOptionPane.showMessageDialog(null, "Game Price must be numeric","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			
			int price = Integer.parseInt(gamepricetf.getText().toString());
		
			if(price < 0) {
				JOptionPane.showMessageDialog(null, "Price must be > 0","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}else if(gamegenre.equals("Select a Genre")) {
				JOptionPane.showMessageDialog(null, "Game Genre must be selected","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}else if(gamequantity < 0) {
				JOptionPane.showMessageDialog(null, "Quantity must be > 0","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			String genreid="";
			String querygenre1 = String.format("SELECT * FROM genre");
			ResultSet res3 = con.executeQuery(querygenre1);
			
			try {
				while(res3.next()) {
					if(gamegenre.equals(res3.getString("genreName"))) {
						genreid = res3.getString("genreId");
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String update = String.format("UPDATE `game` SET `name`='" + game + "',`price`='" + gameprice + "',`genreId`='" + genreid + "',`quantity`='" + gamequantity + "'"
					+ "WHERE gameId = '%s'",gameid);
			con.executeUpdate(update); 
			
			dtm.setValueAt(game, selectedIndex, 1);
			dtm.setValueAt(gameprice, selectedIndex, 2);
			dtm.setValueAt(genreid, selectedIndex, 3);
			dtm.setValueAt(gamequantity, selectedIndex, 4); 
			JOptionPane.showMessageDialog(null, "Update Success","Warning",JOptionPane.WARNING_MESSAGE);
			
		
		}
	}

	public void clearfield() {
		namegametf.setText("");
		newgamepricetf.setText("");
		newgamegenre.setSelectedItem(0);
		newqty.setValue(0);
	}
	
	public void clearfield1() {
		gameidtf.setText("");
		gamenametf.setText("");
		gamepricetf.setText("");
		cb.setSelectedItem(0);
		qty.setValue(0);
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==tabel) {
			int row = tabel.getSelectedRow();
			selectedIndex=row;
			gameidtf.setText(dtm.getValueAt(row, 0).toString());
			gamenametf.setText(dtm.getValueAt(row, 1).toString());
			gamepricetf.setText(dtm.getValueAt(row, 2).toString());
			cb.setSelectedItem(dtm.getValueAt(row, 3).toString());
			String s = dtm.getValueAt(row, 4).toString();
			int i = Integer.parseInt(s);
			qty.setValue(i);
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
