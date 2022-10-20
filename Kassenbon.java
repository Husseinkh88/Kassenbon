import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.SystemColor;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.print.PrinterJob;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.*;
public class Kassenbon {
private JFrame frame;
private  JPanel panel;
private  JTextField textCode;
private  JTextField textName;
private  JTextField textPreis;
private   JSpinner spinner;
private  JTextField textSumme;
private  JButton btnLerren;
private  JButton btnAdd;
private  JTextField Gesamtsumme;
private  JTextField textBezahlt;
private  JTextField textGesamtsumme;
private  JTextField textRueckgeld;
     
public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kassenbon window = new Kassenbon();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
public Kassenbon() {
    
		 initialize();
		 connectTest();
		
	} 
 private  void initialize() {

		frame = new JFrame("Kassenbon");
		frame.getContentPane().setBackground(SystemColor.control);
		frame.setBounds(0, 0, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
     JPanel panel = new JPanel();
     panel.setBounds(10, 26,210, 200);
     panel.setLayout(null);
     frame.getContentPane().add(panel);

	 JLabel lblNewLabel = new JLabel("ProductCode");
	 lblNewLabel.setBounds(13, 8, 90, 14);
	 panel.add(lblNewLabel);
		    
     JLabel lblNewLabel_1 = new JLabel("Produktname");
	 lblNewLabel_1.setBounds(13, 33, 90, 14);
	 panel.add(lblNewLabel_1);

	JLabel lblNewLabel_2 = new JLabel("Preis");
	lblNewLabel_2.setBounds(13, 58, 90, 14);
	panel.add(lblNewLabel_2);
		
	
		
	JLabel lblNewLabel_3 = new JLabel("Quani\u00e4t");
	lblNewLabel_3.setBounds(15, 83, 88, 14);
	panel.add(lblNewLabel_3);
		
	
	
		
    JLabel lblNewLabel_4 = new JLabel("Summe");
	lblNewLabel_4.setBounds(13, 111, 90, 14);
	panel.add(lblNewLabel_4);
   

   textCode = new JTextField();
   textCode.setBounds(113, 8, 90, 20);
   panel.add(textCode);
   textCode.setColumns(10);
   
    textName = new JTextField();
    textName.setBounds(113, 33, 90, 20);
	textName.setColumns(10);
   textName.setEditable(false);
   panel.add(textName);

	textPreis = new JTextField();
	textPreis.setBounds(113, 58,90, 20);
	textPreis.setColumns(10);
   textPreis.setEditable(false); 
   panel.add(textPreis);

    spinner = new JSpinner();
    spinner.setModel(new SpinnerNumberModel(0,0,null,1));
	 spinner.setBounds(170, 83, 30, 20);
	 panel.add(spinner);

   textSumme = new JTextField();
   textSumme.setEditable(false);
	textSumme.setBounds(113, 111,90, 20);
	panel.add(textSumme);



    JButton btnLerren = new JButton("Lereen");
    btnLerren.setBounds(13,143,90, 20);
	panel.add(btnLerren);

    JButton btnAdd = new JButton("Add");
    btnAdd.setBounds(113,143,90, 20);
    panel.add(btnAdd);
    

     JLabel Gesamtsumme = new JLabel("Gesamtsumme");
	  Gesamtsumme.setBounds(445, 34, 90, 20);
	  frame.add(Gesamtsumme);

	  JTextField textGesamtsumme = new JTextField();
	  textGesamtsumme.setEditable(false);
	  textGesamtsumme.setBounds(545,34,90, 20);
	  frame.add(textGesamtsumme);

	  JLabel Bezahlt = new JLabel("Bezahlt");
	  Bezahlt.setBounds(445, 80, 90, 20);
	  frame.add(Bezahlt);

	  JTextField textBezahlt = new JTextField();
	  textBezahlt.setBounds(545,80,90, 20);
	  textBezahlt.setEditable(false);
	  frame.add(textBezahlt);


	  JLabel Rueckgeld = new JLabel("R\u00fcckgeld");
	  Rueckgeld.setBounds(445, 110, 90, 20);
	  frame.add(Rueckgeld);

	  JTextField textRueckgeld = new JTextField();
	  textRueckgeld.setEditable(false);
	  textRueckgeld.setBounds(545,110,90, 20);
	  frame.add(textRueckgeld);

	  JButton btnKassenbon = new JButton("Kassenbon");
     btnKassenbon.setBounds(545,140,100, 20);
     frame.add( btnKassenbon);
     
     JScrollPane scrollPanneArea = new JScrollPane();
	  scrollPanneArea.setBounds(445,190,200,230);
	  frame.getContentPane().add(scrollPanneArea);

	  JTextArea textarea = new JTextArea();
	  textarea.setEditable(false);
	scrollPanneArea.setViewportView(textarea);

	 JButton btnPrint = new JButton("Print");
     btnPrint.setBounds(545,430,90, 20);
     frame.add(btnPrint);

     JScrollPane scrollPanne = new JScrollPane();
	  scrollPanne.setBounds(10, 230, 320, 190);
	  frame.getContentPane().add(scrollPanne);

     JTable jt=new JTable();
     jt.setBackground(new Color(176,196,222));
     DefaultTableModel model = new  DefaultTableModel();
     Object[] column={"Produktname","Preis","Quani\u00e4t","Summe"};
     final Object[] row = new Object[4]; // final meanes that you can't change the object's reference to point to another reference or 
                                         // or another object .whereas immutable meanes that the value can't be change.
     model.setColumnIdentifiers(column);

     jt.setModel(model);
     scrollPanne.setViewportView(jt);

     JButton btnDelet = new JButton("L\u00f6chen");
     btnDelet.setBounds(10,430,90, 20);
     frame.add(btnDelet);

    
	/*
	 
    *  Create Function und Eevent
   */
  
  //............................................................................/

    textCode.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
            String code =	textCode.getText();
				try {
				      pstmt = con.prepareStatement("select name,preis from kassen where code = ?");
				      pstmt.setString(1,code);
				      rs = pstmt.executeQuery();
			   	if(rs.next()== true) {
				      String name =  rs.getString(1);
				      String preis =  rs.getString(2);
				      textName.setText(name);
				       textPreis.setText(preis);
				 }else {
					    textName.setText("");
					    textPreis.setText(""); }
				   }catch(SQLException ex) {
					ex.printStackTrace();}
				  }});
    //........................................................................----------------...../
         btnLerren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textCode.setText("");
				textName.setText("");
				textPreis.setText("");
				textSumme.setText("");
				spinner.setModel(new SpinnerNumberModel(0,0,null,1));
			}});

	 //..........................................................................	..................//
		   btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        if(textCode.getText().equals("") || textSumme.getText().equals("")){

       JOptionPane.showMessageDialog(null,"Pleas fill compltet Information");
				 }
      
           else{
				row[0] = textName.getText();
				row[1] = textPreis.getText();
				row[2] = spinner.getValue().toString();
				row[3] = textSumme.getText();
				model.addRow(row);
             int sum = 0;
                    
        for(int j = 0; j<jt.getRowCount(); j++)
            {
            sum = sum + Integer.parseInt(jt.getValueAt(j, 3).toString());
             }
            textGesamtsumme.setText(String.valueOf(sum));
            textCode.setText("");
				textName.setText("");
				textPreis.setText("");
				spinner.setModel(new SpinnerNumberModel(0,0,null,1));
				textSumme.setText("");
				 textBezahlt.setEditable(true);
         }}}

		);
        
  
   	
	 //..........................................................................	..................//
		     btnDelet.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
			   int selected = jt.getSelectedRow();	
			   int valueOfselected = Integer.parseInt(jt.getValueAt(selected, 3).toString());
            int valueOfGesamt =Integer.parseInt(textGesamtsumme.getText());
			   if (selected >= 0 ) {
			   	model.removeRow(selected);
              textGesamtsumme.setText(String.valueOf(valueOfGesamt-valueOfselected));
           
           textRueckgeld.setText("");
           textBezahlt.setText("");
          ;
			  JOptionPane.showMessageDialog(null,"Erfolgreisch gelöcht"); 	
			   }else{
           JOptionPane.showMessageDialog(null,"Bitte wählen aus");
			   }
			  
			}});
  	 //..........................................................................	..................//
     
     spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
        int qty = Integer.parseInt(spinner.getValue().toString());
        int price = Integer.parseInt(textPreis.getText());
        int tot = qty * price;
        
        textSumme.setText(String.valueOf(tot));
       
	}});
        //-----------------------------------------------------------------------------------------------
   textBezahlt.addKeyListener(new KeyAdapter() {


            public void keyReleased(KeyEvent e) {
            int soll =Integer.parseInt(textGesamtsumme.getText());
				 int pay =Integer.parseInt(textBezahlt.getText());
				      int balanc = pay - soll;

				    textRueckgeld.setText(String.valueOf(balanc)); 
				  
				  }});
    //-----------------------------------------------------------------------------------------------------------------//
    btnKassenbon.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
    
     if (textBezahlt.getText().equals("")) {
     	 JOptionPane.showMessageDialog(null,"Bitte Bezahlen Sie");
     }
   else{

    textarea.setText("               Order             \n"+
     	           
     	               "-----------------------------------------------\n"+
     	               "              IHREBESTELLUNG           \n"+
     	               " -----------------------------------------------\n"
        );
 
      for (int i=0;i< jt.getRowCount();i++ ) {
      	 
      	 String pname =  jt.getValueAt(i, 0).toString();
      	 String price =  jt.getValueAt(i, 1).toString();
      	 String quani =  jt.getValueAt(i, 2).toString();
      	 String summe =  jt.getValueAt(i, 3).toString();
  textarea.setText(textarea.getText() +"      "+pname+ "  :   " +price+  "  *   " +quani+ "  =  "+summe+"\n");

      }

   textarea.setText(textarea.getText() +"                        Gesamtsumme :"+textGesamtsumme.getText()+"\n "
   	                                   +"                        Bezahlt : " +textBezahlt.getText()+"\n "
   	                                   +"                        R\u00fcckgeld :  "+textRueckgeld.getText()+ "  ");

   
   
      
      ((DefaultTableModel)jt.getModel()).setNumRows(0);
      textGesamtsumme.setText("");
      textRueckgeld.setText("");
      textBezahlt.setText("");
      textBezahlt.setEditable(false);
    	}}});

  //-----------------------------------------------------------------------------------------------------------------//    
 btnPrint.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
  print(textarea.getText());
}});

//-----------------------------------------------------------------------------------------------------------------//    

}

    Connection con ;
	PreparedStatement pstmt;
    ResultSet rs;
    public  void connectTest() {

	 try {
	 //Class.forName("com.mysql.cj.jdbc.Driver");
	  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Kassenbon", "root", "");
	 System.out.println("Verbindung wurde herstellt");
	 }catch(SQLException ex) {
	 System.err.println("Verbindung konnte nicht herstellt werden");
	 System.err.println(ex);
	}
	}


	public void print(String text){
    PrintJob prjob = frame.getToolkit().getPrintJob(frame, "Drucken", null );
	 if (prjob != null) {
	 	Graphics g = prjob.getGraphics();
	 	int fontSize = 20;
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
	 	g.drawString(text,40,40);
	 	g.dispose();
	 	prjob.end();
			}


	}




    	

}