import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ManageGenre extends JFrame implements ActionListener,MouseListener{

	JPanel blc1,blc2,brc1,brc2,top,main,bottom,tabelpanel,insidebottomleft,insidebottomright,bottomleft,bottomright,darkgrey,genreidpanel,genreidtfpanel,genrenamepanel,genrenametfpanel,newgenrepanel,newgenretfpanel,backpanel
	,insertpanel,updatepanel,deletepanel;
	JLabel genreidlabel,genrenamelabel,newgenrenamelabel,kosong,kosong1,kosong2,kosong3;
	JTextField genreidtf,genrenametf,newgenrenametf;
	JButton back,insert,update,delete;
	DefaultTableModel dtm;
	JTable tabel;
	JScrollPane scrollpane;
	JTableHeader headerr;
	
	int selectedIndex=-1;
	Connect con = Connect.getInstance();
	private Statement st;
	
	public ManageGenre() {
		// TODO Auto-generated constructor stub				
		setLayout(new BorderLayout());
		
		main = new JPanel(new GridLayout(2,1));
		main.setBackground(Color.DARK_GRAY);
		
// TOP
		top = new JPanel(new BorderLayout());
		
		// TABLE
		Vector <Object> header = new Vector();
		header.add("Genre ID");
		header.add("Genre Name");
		
		String kosong5 ="";
			
		String query = String.format("SELECT * FROM genre WHERE (genreId != '%s')",kosong5);
		ResultSet res = con.executeQuery(query);
		
		Vector <Vector <Object>> data = new Vector();
		try {
			while(res.next()) {
				Vector <Object> genre = new Vector();
				genre.add(res.getString("genreId"));
				genre.add(res.getString("genreName"));			
				data.add(genre);
				
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
		scrollpane.setPreferredSize(new Dimension(960,310));
		scrollpane.setBackground(Color.black);

				
		headerr=tabel.getTableHeader();
		headerr.setBackground(Color.DARK_GRAY);
		headerr.setForeground(Color.white);
				
				
		tabel.setOpaque(true);
		scrollpane.setOpaque(true);
				
		scrollpane.getViewport().setBackground(Color.DARK_GRAY);
		
		top.add(scrollpane);

//Bottom
		bottom= new JPanel(new GridLayout(1,2));
		bottom.setBackground(Color.DARK_GRAY);
		
		//Bottom left
		bottomleft = new JPanel(new FlowLayout(FlowLayout.CENTER,0,40));
		bottomleft.setBackground(Color.DARK_GRAY);
		
		insidebottomleft = new JPanel(new BorderLayout());
		insidebottomleft.setBackground(Color.black);
		insidebottomleft.setPreferredSize(new Dimension(400,200));
		
		blc1 = new JPanel(new GridLayout(4,1,0,0));
		blc1.setPreferredSize(new Dimension (100,200));
		blc1.setBackground(Color.DARK_GRAY);
		
		blc2 = new JPanel(new GridLayout(4,1,0,0));
		blc2.setPreferredSize(new Dimension (300,200));
		blc2.setBackground(Color.DARK_GRAY);
		
		//Bottom Right
		bottomright = new JPanel(new FlowLayout(FlowLayout.CENTER,0,10));
		bottomright.setBackground(Color.DARK_GRAY);
		
		
		insidebottomright = new JPanel(new BorderLayout());
		insidebottomright.setBackground(Color.black);
		insidebottomright.setPreferredSize(new Dimension(450,40));
		
		brc1 = new JPanel(new GridLayout(1,1));
		brc1.setPreferredSize(new Dimension (150,40));
		brc1.setBackground(Color.darkGray);
		
		brc2 = new JPanel(new GridLayout(1,1));
		brc2.setPreferredSize(new Dimension(300,40));
		brc2.setBackground(Color.DARK_GRAY);
		
		darkgrey = new JPanel();
		darkgrey.setPreferredSize(new Dimension (450,100));
		darkgrey.setBackground(Color.DARK_GRAY);
		
		insertpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
		insertpanel.setPreferredSize(new Dimension(450, 30));
		insertpanel.setBackground(Color.DARK_GRAY);
		//LABEL
		genreidlabel=new JLabel("Genre ID");
		genreidlabel.setForeground(Color.white);
		genreidlabel.setFont(new Font("Plain",Font.PLAIN,15));
		
		genrenamelabel=new JLabel("Genre Name");
		genrenamelabel.setForeground(Color.white);
		genrenamelabel.setFont(new Font("Plain",Font.PLAIN,15));

		newgenrenamelabel=new JLabel("New Genre Name :");
		newgenrenamelabel.setForeground(Color.white);
		newgenrenamelabel.setFont(new Font("Plain",Font.PLAIN,15));
		
		kosong = new JLabel();
		kosong1 = new JLabel();
		
		//Text Field
		genreidtfpanel = new JPanel(new FlowLayout (FlowLayout.CENTER,0,12));
		genreidtfpanel.setBackground(Color.DARK_GRAY);
		genreidtf=new JTextField();
		genreidtf.setEditable(false);
		genreidtf.setPreferredSize(new Dimension(300,30));
		genreidtf.setBackground(Color.DARK_GRAY);
		genreidtf.setForeground(Color.white);
		genreidtfpanel.add(genreidtf);
		
		genrenametfpanel = new JPanel(new FlowLayout (FlowLayout.CENTER,0,12));
		genrenametfpanel.setBackground(Color.DARK_GRAY);
		genrenametf=new JTextField();
		genrenametf.setPreferredSize(new Dimension(300,30));
		genrenametf.setBackground(Color.DARK_GRAY);
		genrenametf.setForeground(Color.white);
		genrenametfpanel.add(genrenametf);
		
		newgenretfpanel = new JPanel(new FlowLayout (FlowLayout.CENTER,0,8));
		newgenretfpanel.setBackground(Color.darkGray);
		newgenrenametf=new JTextField();
		newgenrenametf.setPreferredSize(new Dimension(300,30));
		newgenrenametf.setBackground(Color.DARK_GRAY);
		newgenrenametf.setForeground(Color.white);
		newgenretfpanel.add(newgenrenametf);
		
		//Button
		backpanel = new JPanel();
		backpanel.setBackground(Color.DARK_GRAY);
		back=new JButton("Back");
		back.setPreferredSize(new Dimension(90,30));
		back.setBackground(Color.DARK_GRAY);
		back.setForeground(Color.white);
		backpanel.add(back);
		
		deletepanel = new JPanel(new FlowLayout(FlowLayout.CENTER,40,5));
		deletepanel.setBackground(Color.DARK_GRAY);
		delete=new JButton("Delete");
		delete.setPreferredSize(new Dimension(90,30));
		delete.setBackground(Color.DARK_GRAY);
		delete.setForeground(Color.white);
		deletepanel.add(delete);
		
		update=new JButton("Update");
		update.setPreferredSize(new Dimension(90,30));
		update.setBackground(Color.DARK_GRAY);
		update.setForeground(Color.white);
		deletepanel.add(update);
		
	

		insert=new JButton("Insert");
		insert.setPreferredSize(new Dimension(90,30));
		insert.setBackground(Color.DARK_GRAY);
		insert.setForeground(Color.white);
		
		//add bottom left
		blc1.add(kosong);
		blc2.add(kosong1);
		blc1.add(genreidlabel);
		blc2.add(genreidtfpanel);
		blc1.add(genrenamelabel);
		blc2.add(genrenametfpanel);
		blc1.add(backpanel);
		blc2.add(deletepanel);
		
		insidebottomleft.add(blc1,BorderLayout.WEST);
		insidebottomleft.add(blc2,BorderLayout.EAST);
		bottomleft.add(insidebottomleft);
		
		//add bottom right
		brc1.add(newgenrenamelabel);
		brc2.add(newgenretfpanel);
		
		insertpanel.add(insert);
		insidebottomright.add(brc1,BorderLayout.WEST);
		insidebottomright.add(brc2,BorderLayout.EAST);
		bottomright.add(darkgrey);
		bottomright.add(insidebottomright);
		bottomright.add(insertpanel);
		//add bottom
		bottom.add(bottomleft);
		bottom.add(bottomright);
//ADD to main
		main.add(top);
		main.add(bottom);
//ADD 
		
		add(main);
				
		frame();
		
		back.addActionListener(this);
		tabel.addMouseListener(this);
		delete.addActionListener(this);
		update.addActionListener(this);
		insert.addActionListener(this);
	}

	public void frame() {
		//Set Size
		setSize(new Dimension (977,650));	
		//set kalo close, program di terminate
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//set frame di tengah
		setLocationRelativeTo(null);
		//Keluarin Frame
		setVisible(true);
		//disable resizeable
		setResizable(false);
	}
	
	public void clearfield() {
		genrenametf.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==back) {
			this.dispose();
			new DeveloperForm();
		}else if(e.getSource()==insert) {
			String newgenrename = newgenrenametf.getText();
			if(newgenrename.equals("")) {
				JOptionPane.showMessageDialog(null, "Please enter a name","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}else {
				String query = String.format("SELECT * FROM genre WHERE genreName = '%s'",newgenrename);
				ResultSet res = con.executeQuery(query);
				
				try {
					if (res.next()) {
						JOptionPane.showMessageDialog(null, "New Game Name must not be duplicate.","Warning",JOptionPane.WARNING_MESSAGE);
						return;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				// GENRERATE ID
				String query1 = String.format("SELECT MAX(SUBSTRING(genreId,4,3)) AS 'total' FROM genre");
				ResultSet res1 = con.executeQuery(query1);

				
				Integer no = 0;
				String genre = "";
				
				
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
					genre = "GEN00" +no;
				}else if(no > 9) {
					genre = "GEN0" +no;
				}else if(no > 99) {
					genre = "GEN" +no;
				}
				
				//Insert into database
				query1 ="INSERT INTO `genre`VALUES ('" + genre + "','" + newgenrename + "')";
				con.executeUpdate(query1);
				
				//Insert into table
				dtm.addRow(new Object [] {genre,newgenrename});
				JOptionPane.showMessageDialog(null, "Insert Success","Warning",JOptionPane.WARNING_MESSAGE);
				clearfield();
			}
		}else if (e.getSource()==delete) {
			if(selectedIndex==-1) {
				JOptionPane.showMessageDialog(null, "Please Select a Genre","Warning",JOptionPane.WARNING_MESSAGE);
			}else {
				int yakin= JOptionPane.showConfirmDialog(null, "Are you sure want to delete?","Warning",JOptionPane.WARNING_MESSAGE);
				if(yakin == JOptionPane.YES_OPTION) {
					String genreid = genreidtf.getText();
					//delete database
					String querydelete= String.format("DELETE FROM genre WHERE genreid = '%s'",genreid);
					con.executeUpdate(querydelete);
					//delete tabel
					dtm.removeRow(selectedIndex);
					JOptionPane.showMessageDialog(null, "Delete Success","Warning",JOptionPane.WARNING_MESSAGE);
					genreidtf.setText("");
					clearfield();
				}
			}
		}else if (e.getSource()==update) {
			if(selectedIndex==-1) {
				JOptionPane.showMessageDialog(null, "Please Select a Genre","Warning",JOptionPane.WARNING_MESSAGE);
			}else {
				String genrename = genrenametf.getText();
				
				String tesduplicate = String.format("SELECT * FROM genre WHERE genreName = '%s'",genrename);
				ResultSet resduplicate = con.executeQuery(tesduplicate);
				
				try {
					if(resduplicate.next()) {
						JOptionPane.showMessageDialog(null, "Genre already exist","Warning",JOptionPane.WARNING_MESSAGE);
						return;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(genrename.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a name!","Warning",JOptionPane.WARNING_MESSAGE);
				}else {
					String genreid = genreidtf.getText();
					String update = String.format("UPDATE `genre` SET `genreName`='" + genrename + "'"
							+ "WHERE genreId = '%s'",genreid);
					con.executeUpdate(update); 
					
					dtm.setValueAt(genrename, selectedIndex, 1);
					JOptionPane.showMessageDialog(null, "Update Success","Warning",JOptionPane.WARNING_MESSAGE);
					genreidtf.setText("");
					clearfield();
				}
			}
		}
	
		
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==tabel) {
			int row = tabel.getSelectedRow();
			selectedIndex = row;
			genreidtf.setText(dtm.getValueAt(row, 0).toString());
			genrenametf.setText(dtm.getValueAt(row, 1).toString());
			
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
