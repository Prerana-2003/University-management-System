package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class TeacherLeave extends JFrame implements ActionListener {

    Choice cEmpId,ctime;
    JDateChooser dcdate;
    JButton submit,cancel;
    
       TeacherLeave() 
       {
         setSize(500, 550);
          setLocation(500, 100);
          setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Apply Leave (Teacher)");
        heading.setBounds(40, 50, 300, 30);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        add(heading);
        
        JLabel lblEmpId= new JLabel("Search by Employee Id");
        lblEmpId.setBounds(60, 100, 200, 30);
        add(lblEmpId);
        
        cEmpId = new Choice();
        cEmpId.setBounds(60, 130, 200, 20);
        add(cEmpId);

       try
       {
         Conn c =new Conn();
         ResultSet rs=c.s.executeQuery("select*from Teacher");
         while(rs.next())
         {
           cEmpId.add(rs.getString("EmpId"));
         }
                 
       }catch(Exception e)
        {
          e.printStackTrace();
        }
       
       JLabel lbldate = new JLabel("Date");
        lbldate.setBounds(60, 180, 200, 20);
        lbldate.setFont(new Font("Tahoma",Font.BOLD,20));
        add(lbldate);
        
        dcdate =new JDateChooser();
        dcdate.setBounds(60,210,200,30);
         add(dcdate);
         
         JLabel lbltime = new JLabel("Time Duration");
        lbltime.setBounds(60, 260, 200, 30);
        add(lbltime);
        
        ctime = new Choice();
        ctime.setBounds(60, 290, 200, 20);
        ctime.add("Full Day");
        ctime.add("Half Day");
        add(ctime);
        
        submit = new JButton("Submit");
        submit.setBounds(60, 350, 100, 25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        
        add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(200, 350, 100, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }
       
    public void actionPerformed(ActionEvent ae)
    {
      if(ae.getSource()==submit)
      {
         String EmpId=cEmpId.getSelectedItem();
         String date= ((JTextField)dcdate.getDateEditor().getUiComponent()).getText();
         
         
            String duration = ctime.getSelectedItem();
            
            String query = "insert into teacherleave values('"+EmpId+"', '"+date+"', '"+duration+"')";
            
            try {
                Conn c = new Conn();
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Leave Confirmed");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }



    public static void main(String args[]) {
        new TeacherLeave();
    }
}
