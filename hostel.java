import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class mainFrame extends JFrame{
  mainFrame()
  {
      Font mf = new Font("Consolas", Font.PLAIN, 35);
      Font f = new Font("Consolas", Font.PLAIN, 25);
      Font bf = new Font("Consolas", Font.PLAIN, 20);
      JLabel l=new JLabel("Hostel Management System");
      l.setBounds(175,100,500,100);
      l.setFont(mf);
      add(l);
      JLabel wl=new JLabel("Warden");
      wl.setBounds(150,300,100,30);
      wl.setFont(f);
      add(wl);
      JLabel ul=new JLabel("Student");
      ul.setBounds(500,300,100,30);
      ul.setFont(f);
      add(ul);
      JButton wlb=new JButton("Login");
      wlb.setBounds(150,370,120,30);
      wlb.setFont(bf);
      add(wlb);
      JButton ulb=new JButton("Login");
      ulb.setBounds(500,370,120,30);
      ulb.setFont(bf);
      add(ulb);
      wlb.setActionCommand("Wlogin");
      ulb.setActionCommand("Ulogin");
      mainFrameListener ml=new mainFrameListener(this);
      wlb.addActionListener(ml);
      ulb.addActionListener(ml);
      this.setSize(800,800);
      this.setLayout(null);
      this.setVisible(true);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
class mainFrameListener implements ActionListener{
  mainFrame x;
  mainFrameListener(mainFrame x)
  {
    this.x=x;
  }
  public void actionPerformed(ActionEvent e)
  {
       String a=e.getActionCommand();
       if(a.equals("Wlogin"))
       {
          new wardenLoginFrame();
          x.dispose();
       }
       else if(a.equals("Ulogin"))
       {
         new studentLoginFrame();
         x.dispose();
       }
  }
}


class studentLoginFrame extends JFrame
{
  JTextField id;
  JPasswordField pwd;
  JButton b,ba;
  studentLoginFrame()
  {
    Font mf = new Font("Consolas", Font.PLAIN, 35);
    Font f = new Font("Consolas", Font.PLAIN, 25);
    Font bf = new Font("Consolas", Font.PLAIN, 20);
    ba=new JButton("Back");
    ba.setBounds(0,0,100,30);
    ba.setFont(bf);
    ba.setActionCommand("back");
    add(ba);
    JLabel ml=new JLabel("Login");
    ml.setFont(mf);
    ml.setBounds(300,100,200,50);
    add(ml);
    JLabel uid=new JLabel("User ID");
    uid.setFont(f);
    uid.setBounds(100,250,110,30);
    add(uid);
    JLabel pas=new JLabel("Password");
    pas.setFont(f);
    pas.setBounds(100,350,110,30);
    add(pas);
    id=new JTextField(20);
    id.setBounds(320,250,150,30);
    add(id);
    pwd=new JPasswordField(20);
    pwd.setBounds(320,350,150,30);
    add(pwd);
    b=new JButton("Submit");
    b.setFont(bf);
    b.setBounds(300,450,120,30);
    b.setActionCommand("submit");
    add(b);
    studentLoginListener l=new studentLoginListener(this);
    b.addActionListener(l);
    ba.addActionListener(l);
    this.setSize(800,800);
    this.setLayout(null);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }
}
class studentLoginListener implements ActionListener
{
  studentLoginFrame x;
  boolean flag=false;
  studentLoginListener(studentLoginFrame x)
  {
    this.x=x;
  }
  public void getConnection()
  {
    String u_name=x.id.getText();
    String pass=new String(x.pwd.getPassword());
    Connection con=null;
    try{  
       Class.forName("com.mysql.cj.jdbc.Driver");
 
       String url= "jdbc:mysql://localhost:3306/hostel"; 
       String user = "root"; 
       String pwd = "Rammohan@2004";
       con = DriverManager.getConnection(url, user, pwd);
       String q="select * from studentLogin where id=?";
       PreparedStatement pst=con.prepareStatement(q);
       pst.setString(1,u_name);
       ResultSet rs=pst.executeQuery();
       if(rs.next())
       {
        String s=rs.getString("password");
          if(s.equals(pass))
          {
            global.id=u_name;
            flag=true;

          }
          

       }
    }
    catch(Exception ex){
  System.out.println(ex);
    } 
  }
  public void actionPerformed(ActionEvent e)
  {
    String s=e.getActionCommand();
    if(s.equals("back"))
    {
       new mainFrame();
       x.dispose();
    }
    else
    {
       getConnection();
        Graphics g=x.getGraphics();
        Font bf = new Font("Consolas", Font.PLAIN, 20);
        g.setFont(bf);
        if(flag)
        {
           
           new studentMainFrame();
           x.dispose();
        }
        else
        {
           g.drawString("Login failed",300,550);
           try{
             Thread.currentThread().sleep(2000);
           }
           catch(Exception ex)
           {
            System.out.println(ex);
           }
           new studentLoginFrame();
           x.dispose();
        }
      }
  }
}
class global {
  public static String id;
}
class studentMainFrame extends JFrame{
  JButton rh,vh,rd,rmd,fee,ba;
    studentMainFrame()
    {
         Font mf = new Font("Consolas", Font.PLAIN, 35);
         Font f = new Font("Consolas", Font.PLAIN, 25);
         Font bf = new Font("Consolas", Font.PLAIN, 20);
          ba=new JButton("Back");
         ba.setBounds(0,0,100,30);
         ba.setFont(bf);
         ba.setActionCommand("back");
         add(ba);
         String x=global.id;
         JLabel sid=new JLabel("Student ID:-"+x);
         sid.setBounds(200,50,400,50);
         sid.setFont(f);
         add(sid);
         rh=new JButton("Hostel Registration");
         rh.setFont(f);
         rh.setBounds(200,150,300,50);
         rh.setActionCommand("reg");
         add(rh);
         vh=new JButton("Vacate Hostel");
         vh.setFont(f);
         vh.setBounds(200,250,300,50);
         vh.setActionCommand("vacate");
         add(vh);
         rd=new JButton("Room Details");
         rd.setFont(f);
         rd.setBounds(200,350,300,50);
         rd.setActionCommand("room");
         add(rd);
         rmd=new JButton("Roommates Details");
         rmd.setFont(f);
         rmd.setBounds(200,450,300,50);
         rmd.setActionCommand("roommate");
         add(rmd);
         fee=new JButton("Fee Payment");
         fee.setFont(f);
         fee.setBounds(200,550,300,50);
         fee.setActionCommand("fee");
         add(fee);
         studentMainListener l=new studentMainListener(this);
         rh.addActionListener(l);
         vh.addActionListener(l);
         rmd.addActionListener(l);
         rd.addActionListener(l);
         fee.addActionListener(l);
         ba.addActionListener(l);
         this.setSize(800,800);
         this.setLayout(null);
         this.setVisible(true);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class studentMainListener implements ActionListener
{
  studentMainFrame x;
  studentMainListener(studentMainFrame x)
  {
    this.x=x;
  }
  public void actionPerformed(ActionEvent e)
  {
     String s=e.getActionCommand();
     Connection con=null;
      try{  
         Class.forName("com.mysql.cj.jdbc.Driver");
   
         String url= "jdbc:mysql://localhost:3306/hostel"; 
         String user = "root"; 
         String pwd = "Rammohan@2004";
         con = DriverManager.getConnection(url, user, pwd);
        if(s.equals("back"))
        {
            new mainFrame();
            x.dispose();
        }
        else if(s.equals("reg"))
        {
            String q="select * from studentDetails where id=?";
            PreparedStatement pst=con.prepareStatement(q);
            pst.setString(1,global.id);
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                new alreadyRegisterFrame();
                x.dispose();
            }
            else
            {
              new registerFrame();
              x.dispose();
            }
          } 
        else if(s.equals("vacate"))
        {
         
            String q="select * from studentDetails where id=?";
            PreparedStatement pst=con.prepareStatement(q);
            pst.setString(1,global.id);
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                new vacateHostelFrame();
                x.dispose();
            }
            else
            {
              new notRegisterFrame();
              x.dispose();
            }

        }
        else if(s.equals("room"))
        {
          new roomDetailsFrame();
          x.dispose();
        }
        else if(s.equals("roommate"))
        {
            String q="select * from studentDetails where id=?";
            PreparedStatement pst=con.prepareStatement(q);
            pst.setString(1,global.id);
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                  String rt=rs.getString("roomType");
                  String rn=rs.getString("roomNo");
                  new roommateFrame(rt, rn,0);
                  x.dispose();
            }
            else
            {
                new notRegisterFrame();
                x.dispose();
            }
        }
        else if(s.equals("fee"))
        {
            String q="select * from studentDetails where id=?";
            PreparedStatement pst=con.prepareStatement(q);
            pst.setString(1,global.id);
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                String fee=rs.getString("fee");
                new feeFrame(fee);
                x.dispose();
            }
            else
            {
              new notRegisterFrame();
              x.dispose();
            }
        }
    }
    catch(Exception ex)
    {
      System.out.println(ex);
    }
  }
  
}
class vacateHostelFrame extends JFrame{
  vacateHostelFrame()
  {
      Font mf = new Font("Consolas", Font.PLAIN, 30);
      Font f = new Font("Consolas", Font.PLAIN, 25);
      Font bf = new Font("Arial", Font.PLAIN, 20);
      JButton ba=new JButton("Back");
      ba.setBounds(0,0,100,30);
      ba.setFont(bf);
      ba.setActionCommand("back");
      add(ba);
      JButton v=new JButton("Vacate Hostel");
      v.setFont(f);
      v.setBounds(200,300,300,50);
      v.setActionCommand("vacate");
      add(v);
      vacateHostelListener l=new vacateHostelListener(this);
      ba.addActionListener(l);
      v.addActionListener(l);
      this.setSize(800,800);
      this.setLayout(null);
      this.setVisible(true);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
class vacateHostelListener implements ActionListener{
  boolean flag=false,flag1=false;
  vacateHostelFrame x;
  vacateHostelListener(vacateHostelFrame x)
  {
     this.x=x;
  }
  public void getConnection()
  {
    Connection con=null;
    try{  
       Class.forName("com.mysql.cj.jdbc.Driver");
 
       String url= "jdbc:mysql://localhost:3306/hostel"; 
       String user = "root"; 
       String pwd = "Rammohan@2004";
       con = DriverManager.getConnection(url, user, pwd);
       String q1="select * from studentDetails where id=?";
       PreparedStatement pst1=con.prepareStatement(q1);
       pst1.setString(1,global.id);
       ResultSet rs=pst1.executeQuery();
       String trt=" ",tbn=" ",trn=" ";
       if(rs.next())
       {
          trn=rs.getString("roomNo");
        trt=rs.getString("roomType");
         tbn=rs.getString("bedNo");
       }
       
       String temp=" ";
       if(trt.equals("Two Sharing"))
           {
            
              if(tbn.equals("Bed 1"))
              {
                
               
                String q2="update twoSharing set bedOne=? where roomNo=?";
                PreparedStatement pst2=con.prepareStatement(q2);
                pst2.setString(1,temp);
                pst2.setString(2,trn);
                pst2.executeUpdate();
                flag1=true;
              }
              else if(tbn.equals("Bed 2"))
              {
                
                String q2="update twoSharing set bedTwo=? where roomNo=?";
                PreparedStatement pst2=con.prepareStatement(q2);
                pst2.setString(1,temp);
                pst2.setString(2,trn);
                pst2.executeUpdate();
                flag1=true;
              }
              

           }
           else
           {
           
                if(tbn.equals("Bed 1"))
                {
                  
                  String q2="update fourSharing set bedOne=? where roomNo=?";
                  PreparedStatement pst2=con.prepareStatement(q2);
                  pst2.setString(1,temp);
                  pst2.setString(2,trn);
                  pst2.executeUpdate();
                  flag1=true;
                }
                else if(tbn.equals("Bed 2"))
                {
                  
                  String q2="update fourSharing set bedTwo=? where roomNo=?";
                  PreparedStatement pst2=con.prepareStatement(q2);
                  pst2.setString(1,temp);
                  pst2.setString(2,trn);
                  pst2.executeUpdate();
                  flag1=true;
                }
                else if(tbn.equals("Bed 3"))
                {
                  
                  String q2="update fourSharing set bedThree=? where roomNo=?";
                  PreparedStatement pst2=con.prepareStatement(q2);
                  pst2.setString(1,temp);
                  pst2.setString(2,trn);
                  pst2.executeUpdate();
                  flag1=true;
                }
                else if(tbn.equals("Bed 4"))
                {
                  System.out.println("four sharing bed 4");
                  String q2="update fourSharing set bedFour=? where roomNo=?";
                  PreparedStatement pst2=con.prepareStatement(q2);
                  pst2.setString(1,temp);
                  pst2.setString(2,trn);
                  pst2.executeUpdate();
                  flag1=true;
                }
              
           }
           if(flag1)
           {
            String q="delete from studentDetails where id=?";
            PreparedStatement pst=con.prepareStatement(q);
            pst.setString(1,global.id);
            pst.executeUpdate();
           flag=true; 

           }
            
    }
    catch(Exception e)
    {
      System.out.println(e);
    }

  }
  public void actionPerformed(ActionEvent e)
  {
     String s=e.getActionCommand();
     if(s.equals("back"))
     {
       new studentMainFrame();
       x.dispose();
     }
     else
     {
         getConnection();
         Graphics g=x.getGraphics();
         Font f = new Font("Consolas", Font.PLAIN, 25);
         g.setFont(f);
         if(flag && flag1)
         {
            
             g.drawString("Hostel Vacated Sucessfully", 200, 500);
         }
         else
         {
             g.drawString("Hostel Vacate Failed", 200, 500);
         }
     }
  }
}
class notRegisterFrame extends JFrame{
  notRegisterFrame()
  {
     Font mf = new Font("Consolas", Font.PLAIN, 30);
     Font f = new Font("Consolas", Font.PLAIN, 25);
     Font bf = new Font("Arial", Font.PLAIN, 20);
     JButton ba=new JButton("Back");
     ba.setBounds(0,0,100,30);
      ba.setFont(bf);
     add(ba);
     JLabel r=new JLabel("Hostel Not Registered");
     r.setFont(f);
     r.setBounds(100,300,500,50);
     add(r);
     notRegisterListener l=new notRegisterListener(this);
     ba.addActionListener(l);
     this.setSize(800,800);
     this.setLayout(null);
     this.setVisible(true);
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
  }
}

class notRegisterListener implements ActionListener
{
  notRegisterFrame x;
  notRegisterListener(notRegisterFrame x)
  {
    this.x=x;
  }
   public void actionPerformed(ActionEvent e)
   {
      new studentMainFrame();
      x.dispose();
   }
}
class alreadyRegisterFrame extends JFrame{
   alreadyRegisterFrame()
   {
      Font mf = new Font("Consolas", Font.PLAIN, 30);
      Font f = new Font("Consolas", Font.PLAIN, 25);
      Font bf = new Font("Arial", Font.PLAIN, 20);
      JButton ba=new JButton("Back");
      ba.setBounds(0,0,100,30);
      ba.setFont(bf);
      add(ba);
      JLabel r=new JLabel("Hostel Already Registered");
      r.setFont(f);
      r.setBounds(100,300,500,50);
      add(r);
      alreadyRegisterListener l=new alreadyRegisterListener(this);
      ba.addActionListener(l);
      this.setSize(800,800);
      this.setLayout(null);
      this.setVisible(true);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
   }
}
class alreadyRegisterListener implements ActionListener
{
    alreadyRegisterFrame x;
     alreadyRegisterListener(alreadyRegisterFrame x)
     {
      this.x=x;
     }
    public void actionPerformed(ActionEvent e)
    {
       new studentMainFrame();
       x.dispose();
    }
}
class registerFrame extends JFrame{
    JComboBox<String> rtc,rnc,bnc;
    JTextField t1,t2,pnt,adt;
    ButtonGroup cbg;
    registerFrame()
    {
        
       
        Font mf = new Font("Consolas", Font.PLAIN, 30);
        Font f = new Font("Consolas", Font.PLAIN, 18);
        Font rf = new Font("Arial", Font.PLAIN, 10);
        Font bf = new Font("Arial", Font.PLAIN, 20);
        JButton ba=new JButton("Back");
        ba.setBounds(0,0,100,30);
        ba.setFont(bf);
        ba.setActionCommand("back");
        add(ba);
        JLabel l=new JLabel("Hostel Registeration");
        JLabel nl=new JLabel("Name");
        JLabel il=new JLabel("ID");
        l.setFont(mf);
        nl.setFont(f);
        il.setFont(f);
        l.setBounds(225,50,350,50);
        nl.setBounds(50,150,100,30);
        il.setBounds(50,190,100,30);
        add(l);
        add(nl);
        add(il);
        JLabel fe=new JLabel("Fees:-");
        JLabel ts=new JLabel("Two Sharing  :- 150000");
        JLabel fs=new JLabel("Four Sharing :- 100000");
        fe.setFont(f);
        ts.setFont(f);
        fs.setFont(f);
        fe.setBounds(500,270,300,30);
        ts.setBounds(500,310,300,30);
        fs.setBounds(500,350,300,30);
        add(fe);
        add(ts);
        add(fs);
        t1=new JTextField(20);
        t2=new JTextField(20);
        t1.setBounds(250,150,200,30);
        t2.setBounds(250,190,200,30);
        add(t1);
        add(t2);
       
        JLabel gl=new JLabel("Gender");
        gl.setFont(f);
        gl.setBounds(50,230,100,30);
        add(gl);
       
        cbg = new ButtonGroup();
        JRadioButton gm = new JRadioButton ("Male");
        JRadioButton gf = new JRadioButton ("Female");
        gm.setBounds(250,230,100,30);
        gf.setBounds(360,230,100,30);
        gm.setActionCommand("Male");
        gf.setActionCommand("Female");
        cbg.add(gm);
        cbg.add(gf);
        add(gm);
        add(gf);
      
        JLabel rt=new JLabel("Room Type");
        rt.setFont(f);
        rt.setBounds(50,270,150,30);
        add(rt);
        String type[]={"Two Sharing","Four Sharing"};	
        rtc= new JComboBox<String>(type);
        rtc.setBounds(250,270,200,30);
        add(rtc);
        
        roomListner rl=new roomListner(this);
        rtc.addItemListener(rl);
        
        JLabel rn=new JLabel ("Room No");
        rn.setFont(f);
        rn.setBounds(50,310,100,30);
        add(rn);
       
        rnc=new JComboBox<String>();
        rnc.setBounds(250,310,100,30);
        add(rnc);
        
        roomNoListener rnl=new roomNoListener(this);
        rnc.addItemListener(rnl);
       
        JLabel bnl=new JLabel("Bed No");
        bnl.setBounds(50,350,100,30);
        bnl.setFont(f);
        add(bnl);
        
        bnc=new JComboBox<String>();
        bnc.setBounds(250,350,100,30);
        add(bnc);
       
        JLabel pn=new JLabel("Phone No");
        pn.setBounds(50,390,200,30);
        pn.setFont(f);
        add(pn);
       
         pnt=new JTextField(20);
        pnt.setBounds(250,390,200,30);
        add(pnt);
       
        JLabel adl=new JLabel("Address");
        adl.setBounds(50,430,100,30);
        adl.setFont(f);
        add(adl);
        
        adt=new JTextField(20);
        adt.setBounds(250,430,200,30);
        add(adt);
       
        JButton b=new JButton("Submit");
        b.setBounds(300,500,120,30);
        b.setFont(f);
        submitListener l1= new submitListener(this);
        b.setActionCommand("submit");
        b.addActionListener(l1);
        add(b);
        ba.addActionListener(l1);
        this.setLayout(new FlowLayout());
        this.setSize(800,800);
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class roomListner implements ItemListener
{
    registerFrame x;
    boolean flag=false;
    roomListner(registerFrame x)
    {
        this.x=x;
    }
    public void getConnection()
    {
        Connection con=null;
        try{  
           Class.forName("com.mysql.cj.jdbc.Driver");
     
           String url= "jdbc:mysql://localhost:3306/hostel"; 
           String user = "root"; 
           String pwd = "Rammohan@2004";
           con = DriverManager.getConnection(url, user, pwd);
           String s=(String)x.rtc.getSelectedItem();
           if(s=="Two Sharing")
           {
              x.rnc.removeAllItems();
              String q="select * from twoSharing where bedOne IS NULL OR bedOne=' ' OR bedTwo IS NULL OR bedTwo=' ' ";
              PreparedStatement  pst = con.prepareStatement(q);
              ResultSet rs = pst.executeQuery(q);
              while(rs.next())
              {
                String temp=rs.getString("roomNo");
                x.rnc.addItem(temp);
              }
           }
           else  if(s=="Four Sharing")
           {
            x.rnc.removeAllItems();
            String q="select * from FourSharing where bedOne IS NULL OR bedOne=' ' OR bedTwo IS NULL OR bedTwo=' ' OR bedThree IS NULL OR bedThree=' ' OR bedFour IS NULL OR bedFour=' ' ";
            PreparedStatement  pst = con.prepareStatement(q);
            ResultSet rs = pst.executeQuery(q);
            while(rs.next())
            {
              String temp=rs.getString("roomNo");
              x.rnc.addItem(temp);
            }
           }
           
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }
    public void itemStateChanged(ItemEvent ie)
    {
       getConnection();
    }
}
class roomNoListener implements ItemListener
{
    registerFrame x;
    roomNoListener(registerFrame x)
    {
        this.x=x;
    }
   
    public void getConnection()
    {
        Connection con=null;
        try{  
           Class.forName("com.mysql.cj.jdbc.Driver");
     
           String url= "jdbc:mysql://localhost:3306/hostel"; 
           String user = "root"; 
           String pwd = "Rammohan@2004";
           con = DriverManager.getConnection(url, user, pwd);

           String st=(String)x.rtc.getSelectedItem();
           String s=(String)x.rnc.getSelectedItem();
           if(st=="Two Sharing")
           {
            x.bnc.removeAllItems();
            String ps="select * from twoSharing where roomNo=? ";
            PreparedStatement pst=con.prepareStatement(ps);
            pst.setString(1,s);
            ResultSet rs=pst.executeQuery();
           if(rs.next())
           {
                String temps;
                char c;
                temps=rs.getString("bedOne");
                c=temps.charAt(0);
                if(c==' ' || temps==null)
                {
                        x.bnc.addItem("Bed 1");
                }
                temps=rs.getString("bedTwo");
                c=temps.charAt(0);
                if(c==' ' || temps==null)
                {
                            x.bnc.addItem("Bed 2");
                }
              
           }
             
              
           }
           else  if(st=="Four Sharing")
           {
                x.bnc.removeAllItems();
                String ps="select * from fourSharing where roomNo=? ";
                PreparedStatement pst=con.prepareStatement(ps);
                pst.setString(1,s);
                ResultSet rs=pst.executeQuery();
                if(rs.next())
                {
                    String temps;
                    char c;
                    temps=rs.getString("bedOne");
                    c=temps.charAt(0);
                    if(c==' ' || temps==null)
                    {
                        x.bnc.addItem("Bed 1");
                    }
                    temps=rs.getString("bedTwo");
                    c=temps.charAt(0);
                    if(c==' ' || temps==null)
                    {
                                x.bnc.addItem("Bed 2");
                    }
                    temps=rs.getString("bedThree");
                    c=temps.charAt(0);
                    if(c==' ' || temps==null)
                    {
                            x.bnc.addItem("Bed 3");
                    }
                    temps=rs.getString("bedFour");
                    c=temps.charAt(0);
                    if(c==' ' || temps==null)
                    {
                                x.bnc.addItem("Bed 4");
                    }
                
            }
           
           }
           
           
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }
    public void itemStateChanged(ItemEvent e)
    {
        getConnection();
    }
}
class submitListener implements ActionListener
{
    registerFrame x;
    boolean flag=false;
    submitListener(registerFrame x)
    {
        this.x=x;
    }
    public void getConnection()
    {
        Connection con=null;
        try{  
           Class.forName("com.mysql.cj.jdbc.Driver");
     
           String url= "jdbc:mysql://localhost:3306/hostel"; 
           String user = "root"; 
           String pwd = "Rammohan@2004";
           con = DriverManager.getConnection(url, user, pwd);
           String tn=x.t1.getText();
           String tid=x.t2.getText();
           String tg=x.cbg.getSelection().getActionCommand();
           String trt=(String)x.rtc.getSelectedItem();
           String trn=(String)x.rnc.getSelectedItem();
           String tbn=(String)x.bnc.getSelectedItem();
           String tpn=x.pnt.getText();
           String tad=x.adt.getText();
           String fee;
           String q="insert into studentDetails values(?,?,?,?,?,?,?,?,?)";
           PreparedStatement pst=con.prepareStatement(q);
           pst.setString(1,tid);
           pst.setString(2,tn);
           pst.setString(3,tg);
           pst.setString(4,trt);
           pst.setString(5,trn);
           pst.setString(6,tbn);
           pst.setString(7,tpn);
           pst.setString(8,tad);
           if(trt=="Two Sharing")
           {
               fee="150000";
           }
           else
           {
               fee="100000";
           }
           pst.setString(9,fee);
           pst.executeUpdate();
           if(trt=="Two Sharing")
           {
              if(tbn=="Bed 1")
              {
                String q1="update twoSharing set bedOne=? where roomNo=?";
                PreparedStatement pst1=con.prepareStatement(q1);
                pst1.setString(1,tid);
                pst1.setString(2,trn);
                pst1.executeUpdate();
              }
              else
              {
                String q1="update twoSharing set bedTwo=? where roomNo=?";
                PreparedStatement pst1=con.prepareStatement(q1);
              pst1.setString(1,tid);
              pst1.setString(2,trn);
              pst1.executeUpdate();
              }
              

           }
           else
           {
                if(tbn=="Bed 1")
                {
                    String q1="update fourSharing set bedOne=? where roomNo=?";
                    PreparedStatement pst1=con.prepareStatement(q1);
                    pst1.setString(1,tid);
                    pst1.setString(2,trn);
                    pst1.executeUpdate();
                }
                else if(tbn=="Bed 2")
                {
                    String q1="update fourSharing set bedTwo=? where roomNo=?";
                    PreparedStatement pst1=con.prepareStatement(q1);
                    pst1.setString(1,tid);
                    pst1.setString(2,trn);
                    pst1.executeUpdate();
                }
                else if(tbn=="Bed 3")
                {
                    String q1="update fourSharing set bedThree=? where roomNo=?";
                    PreparedStatement pst1=con.prepareStatement(q1);
                    pst1.setString(1,tid);
                    pst1.setString(2,trn);
                    pst1.executeUpdate();
                }
                else 
                {
                    String q1="update fourSharing set bedFour=? where roomNo=?";
                    PreparedStatement pst1=con.prepareStatement(q1);
                    pst1.setString(1,tid);
                    pst1.setString(2,trn);
                    pst1.executeUpdate();
                }
              
           }
           
           flag=true;
        
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void actionPerformed(ActionEvent e)
    {
          String s=e.getActionCommand();
          if(s.equals("back"))
          {
            new studentMainFrame();
            x.dispose();
          }
          else
          {
            getConnection();
            Graphics g=x.getGraphics();
            Font f = new Font("Consolas", Font.PLAIN, 18);
            g.setFont(f);
            if(flag)
            {
                g.drawString("Hostel Registered Sucessfully", 250, 600);
            }
            else
            {
              g.drawString("Hostel Registration Failed ", 250, 600);
              try{
                Thread.currentThread().sleep(2000);
              }
              catch(Exception m)
              {
                System.out.println(m);
              }
              new registerFrame();
              x.dispose();
            }
          
          
        }
    }
}
class roomDetailsFrame extends JFrame{
   roomDetailsFrame()
   {
      Font mf = new Font("Consolas", Font.PLAIN, 30);
      Font f = new Font("Consolas", Font.PLAIN, 25);
      Font rf = new Font("Arial", Font.PLAIN, 10);
      Font bf = new Font("Arial", Font.PLAIN, 20);
      JButton ba=new JButton("Back");
      ba.setBounds(0,0,100,30);
      ba.setFont(bf);
      add(ba);
      
      
      Connection con=null;
        try{  
           Class.forName("com.mysql.cj.jdbc.Driver");
     
           String url= "jdbc:mysql://localhost:3306/hostel"; 
           String user = "root"; 
           String pwd = "Rammohan@2004";
           con = DriverManager.getConnection(url, user, pwd);
           String q="select * from studentDetails where id=?";
           PreparedStatement pst=con.prepareStatement(q);
           pst.setString(1,global.id);
           ResultSet rs=pst.executeQuery();
           String rt,rn,bn;
           if(rs.next())
           {
               rt=rs.getString("roomType");
               rn=rs.getString("roomNo");
               bn=rs.getString("bedNo");
               JLabel ml=new JLabel("Room Details");
               ml.setFont(mf);
               ml.setBounds(200,100,300,50);
              add(ml);
               JLabel rtl=new JLabel("Room Type :-  "+rt);
               rtl.setBounds(150,200,400,50);
               rtl.setFont(f);
               add(rtl);
               JLabel rnl=new JLabel("Room No   :-  "+rn);
               rnl.setBounds(150,250,300,50);
               rnl.setFont(f);
               add(rnl);
               JLabel bnl=new JLabel("Bed No    :-  "+bn);
               bnl.setBounds(150,300,300,50);
               bnl.setFont(f);
               add(bnl);
           }
           else{
               JLabel nr=new JLabel("Hostel Not Registered");
               nr.setFont(f);
               nr.setBounds(200,300,300,50);
               add(nr);
           }
        }
        catch(Exception ex)
        {
          System.out.println(ex);
        }
      
      roomDetailsListener l=new roomDetailsListener(this);
      ba.addActionListener(l);
      this.setSize(800,800);
      this.setLayout(null);
      this.setVisible(true);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
   }
}
class roomDetailsListener implements ActionListener
{
  roomDetailsFrame x;
  roomDetailsListener(roomDetailsFrame x)
  {
    this.x=x;
  }
  public void actionPerformed(ActionEvent e)
  {
     new studentMainFrame();
     x.dispose();
  }
}
class roommateFrame extends JFrame{
  roommateFrame(String rt,String rn,int z)
  {
      Font mf = new Font("Consolas", Font.PLAIN, 30);
      Font f = new Font("Consolas", Font.PLAIN, 22);
      Font rf = new Font("Consolas", Font.PLAIN, 17);
      Font bf = new Font("Consolas", Font.PLAIN, 18);
      if(z==0)
      {
        JButton ba1=new JButton("Back");
        ba1.setBounds(0,0,100,30);
        ba1.setFont(bf);
        ba1.setActionCommand("back");
        add(ba1);
        roommateListener l=new roommateListener(this);
        ba1.addActionListener(l);
        JLabel rmd=new JLabel("Roommate Details");
        rmd.setFont(mf);
        rmd.setBounds(200,10,300,50);
        add(rmd);
      }
      else
      {
        JButton ba2=new JButton("Back");
        ba2.setBounds(0,0,100,30);
        ba2.setFont(bf);
        ba2.setActionCommand("back");
        studentInRoomListener2 l=new studentInRoomListener2(this);
        add(ba2);
        ba2.addActionListener(l);
      }
      JLabel rtd=new JLabel("Room Type :-  "+rt);
      rtd.setFont(f);
      rtd.setBounds(50,70,400,50);
      add(rtd);
      JLabel rnd=new JLabel("Room No   :-  "+rn);
      rnd.setFont(f);
      rnd.setBounds(50,120,400,50);
      add(rnd);
      Connection con=null;
        try{  
           Class.forName("com.mysql.cj.jdbc.Driver");
     
           String url= "jdbc:mysql://localhost:3306/hostel"; 
           String user = "root"; 
           String pwd = "Rammohan@2004";
           con = DriverManager.getConnection(url, user, pwd);
           if(rt.equals("Two Sharing"))
           {
              String q="select * from twoSharing where roomNo=?";
              PreparedStatement pst=con.prepareStatement(q);
              pst.setString(1,rn);
              ResultSet rs=pst.executeQuery();
              if(rs.next())
              {
                  String b1=rs.getString("bedOne");
                  JLabel lb1=new JLabel("Bed 1");
                  lb1.setFont(bf);
                  lb1.setBounds(50,250,100,30);
                  add(lb1);
                  if(b1.equals(" "))
                  {
                     JLabel x=new JLabel("Not Booked");
                     x.setFont(rf);
                     x.setBounds(50,300,300,30);
                     add(x);
                  }
                  else
                  {
                      String q1="select * from studentDetails where id=?";
                      PreparedStatement pst1=con.prepareStatement(q1);
                      pst1.setString(1,b1);
                      ResultSet rs1=pst1.executeQuery();
                      if(rs1.next())
                      {
                         String b1na=rs1.getString("name");
                         String b1id=rs1.getString("id");
                         String b1pn=rs1.getString("phoneNo");
                         JLabel bln=new JLabel("Name  :- "+b1na);
                         JLabel bli=new JLabel("ID    :- "+b1id);
                         JLabel blp=new JLabel("Phone :- "+b1pn);
                         bln.setFont(rf);
                         bli.setFont(rf);
                         blp.setFont(rf);
                         bln.setBounds(50,300,300,30);
                         bli.setBounds(50,350,300,30);
                         blp.setBounds(50,400,300,30);
                         add(bln);
                         add(bli);
                         add(blp);

                      }
                  }
                  String b2=rs.getString("bedTwo");
                  JLabel lb2=new JLabel("Bed 2");
                  lb2.setFont(bf);
                  lb2.setBounds(450,250,100,30);
                  add(lb2);
                  if(b2.equals(" "))
                  {
                     JLabel x=new JLabel("Not Booked");
                     x.setFont(rf);
                     x.setBounds(450,300,300,30);
                     add(x);
                  }
                  else
                  {
                      String q1="select * from studentDetails where id=?";
                      PreparedStatement pst1=con.prepareStatement(q1);
                      pst1.setString(1,b2);
                      ResultSet rs1=pst1.executeQuery();
                      if(rs1.next())
                      {
                         String b1na=rs1.getString("name");
                         String b1id=rs1.getString("id");
                         String b1pn=rs1.getString("phoneNo");
                         JLabel bln=new JLabel("Name  :- "+b1na);
                         JLabel bli=new JLabel("ID    :- "+b1id);
                         JLabel blp=new JLabel("Phone :- "+b1pn);
                         bln.setFont(rf);
                         bli.setFont(rf);
                         blp.setFont(rf);
                         bln.setBounds(450,300,300,30);
                         bli.setBounds(450,350,300,30);
                         blp.setBounds(450,400,300,30);
                         add(bln);
                         add(bli);
                         add(blp);

                      }
                  }
              }
           }
           else
           {
              String q="select * from fourSharing where roomNo=?";
              PreparedStatement pst=con.prepareStatement(q);
              pst.setString(1,rn);
              ResultSet rs=pst.executeQuery();
              if(rs.next())
              {
                  String b1=rs.getString("bedOne");
                  JLabel lb1=new JLabel("Bed 1");
                  lb1.setFont(bf);
                  lb1.setBounds(50,250,100,30);
                  add(lb1);
                  if(b1.equals(" "))
                  {
                     JLabel x=new JLabel("Not Booked");
                     x.setFont(rf);
                     x.setBounds(50,300,300,30);
                     add(x);
                  }
                  else
                  {
                      String q1="select * from studentDetails where id=?";
                      PreparedStatement pst1=con.prepareStatement(q1);
                      pst1.setString(1,b1);
                      ResultSet rs1=pst1.executeQuery();
                      if(rs1.next())
                      {
                         String b1na=rs1.getString("name");
                         String b1id=rs1.getString("id");
                         String b1pn=rs1.getString("phoneNo");
                         JLabel bln=new JLabel("Name  :- "+b1na);
                         JLabel bli=new JLabel("ID    :- "+b1id);
                         JLabel blp=new JLabel("Phone :- "+b1pn);
                         bln.setFont(rf);
                         bli.setFont(rf);
                         blp.setFont(rf);
                         bln.setBounds(50,300,300,30);
                         bli.setBounds(50,350,300,30);
                         blp.setBounds(50,400,300,30);
                         add(bln);
                         add(bli);
                         add(blp);

                      }
                  }
                  String b2=rs.getString("bedTwo");
                  JLabel lb2=new JLabel("Bed 2");
                  lb2.setFont(bf);
                  lb2.setBounds(450,250,100,30);
                  add(lb2);
                  if(b2.equals(" "))
                  {
                     JLabel x=new JLabel("Not Booked");
                     x.setFont(rf);
                     x.setBounds(450,300,300,30);
                     add(x);
                  }
                  else
                  {
                      String q1="select * from studentDetails where id=?";
                      PreparedStatement pst1=con.prepareStatement(q1);
                      pst1.setString(1,b2);
                      ResultSet rs1=pst1.executeQuery();
                      if(rs1.next())
                      {
                         String b1na=rs1.getString("name");
                         String b1id=rs1.getString("id");
                         String b1pn=rs1.getString("phoneNo");
                         JLabel bln=new JLabel("Name  :- "+b1na);
                         JLabel bli=new JLabel("ID    :- "+b1id);
                         JLabel blp=new JLabel("Phone :- "+b1pn);
                         bln.setFont(rf);
                         bli.setFont(rf);
                         blp.setFont(rf);
                         bln.setBounds(450,300,300,30);
                         bli.setBounds(450,350,300,30);
                         blp.setBounds(450,400,300,30);
                         add(bln);
                         add(bli);
                         add(blp);

                      }
                  }
                  String b3=rs.getString("bedThree");
                  JLabel lb3=new JLabel("Bed 3");
                  lb3.setFont(bf);
                  lb3.setBounds(50,500,100,30);
                  add(lb3);
                  if(b3.equals(" "))
                  {
                     JLabel x=new JLabel("Not Booked");
                     x.setFont(rf);
                     x.setBounds(50,550,300,30);
                     add(x);
                  }
                  else
                  {
                      String q1="select * from studentDetails where id=?";
                      PreparedStatement pst1=con.prepareStatement(q1);
                      pst1.setString(1,b3);
                      ResultSet rs1=pst1.executeQuery();
                      if(rs1.next())
                      {
                         String b1na=rs1.getString("name");
                         String b1id=rs1.getString("id");
                         String b1pn=rs1.getString("phoneNo");
                         JLabel bln=new JLabel("Name  :- "+b1na);
                         JLabel bli=new JLabel("ID    :- "+b1id);
                         JLabel blp=new JLabel("Phone :- "+b1pn);
                         bln.setFont(rf);
                         bli.setFont(rf);
                         blp.setFont(rf);
                         bln.setBounds(50,550,300,30);
                         bli.setBounds(50,600,300,30);
                         blp.setBounds(50,650,300,30);
                         add(bln);
                         add(bli);
                         add(blp);

                      }
                  }
                  String b4=rs.getString("bedFour");
                  JLabel lb4=new JLabel("Bed 4");
                  lb4.setFont(bf);
                  lb4.setBounds(450,500,100,30);
                  add(lb4);
                  if(b4.equals(" "))
                  {
                     JLabel x=new JLabel("Not Booked");
                     x.setFont(rf);
                     x.setBounds(450,550,300,30);
                     add(x);
                  }
                  else
                  {
                      String q1="select * from studentDetails where id=?";
                      PreparedStatement pst1=con.prepareStatement(q1);
                      pst1.setString(1,b4);
                      ResultSet rs1=pst1.executeQuery();
                      if(rs1.next())
                      {
                         String b1na=rs1.getString("name");
                         String b1id=rs1.getString("id");
                         String b1pn=rs1.getString("phoneNo");
                         JLabel bln=new JLabel("Name  :- "+b1na);
                         JLabel bli=new JLabel("ID    :- "+b1id);
                         JLabel blp=new JLabel("Phone :- "+b1pn);
                         bln.setFont(rf);
                         bli.setFont(rf);
                         blp.setFont(rf);
                         bln.setBounds(450,550,300,30);
                         bli.setBounds(450,600,300,30);
                         blp.setBounds(450,650,300,30);
                         add(bln);
                         add(bli);
                         add(blp);

                      }
                  }
              }
           }
           
        }
        catch(Exception ex)
        {
          System.out.println(ex);
        }
        
      this.setSize(800,800);
      this.setLayout(null);
      this.setVisible(true);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }
}
class roommateListener implements ActionListener
{
  roommateFrame x;
  roommateListener(roommateFrame x)
  {
    this.x=x;
  }
   public void actionPerformed(ActionEvent e)
   {
      new studentMainFrame();
      x.dispose();
   }
}
class feeFrame extends JFrame{
  JTextField at;
   feeFrame(String fee)
   {
      Font mf = new Font("Consolas", Font.PLAIN, 30);
      Font f = new Font("Consolas", Font.PLAIN, 22);
      Font rf = new Font("Consolas", Font.PLAIN, 17);
      Font bf = new Font("Consolas", Font.PLAIN, 18);
      JButton ba=new JButton("Back");
      ba.setBounds(0,0,100,30);
      ba.setFont(bf);
      ba.setActionCommand("back");
      add(ba);      
      if(fee.equals("0"))
      {
         JLabel nod=new JLabel("Fee paid");
         nod.setFont(mf);
         nod.setBounds(200,50,300,50);
         add(nod);
      }
      else
      {
        JLabel m=new JLabel("Fee Payment");
        m.setFont(mf);
        m.setBounds(200,50,300,50);
        add(m);
        JLabel d=new JLabel("Fee Due      :-  "+fee);
        d.setFont(f);
        d.setBounds(100,150,300,30);
        add(d);
        JLabel a=new JLabel("Enter amount :-  ");
        a.setBounds(100,200,300,30);
        a.setFont(f);
        add(a);
         at=new JTextField(10);
        at.setBounds(300,200,100,30);
        add(at);
        JButton pay=new JButton("Pay");
        pay.setFont(f);
        pay.setBounds(250,290,100,30);
        pay.setActionCommand("pay");
        add(pay);
        feeListener l=new feeListener(this, fee);
        pay.addActionListener(l);
        
      }
      feeListener l=new feeListener(this, fee);
      ba.addActionListener(l);
      this.setSize(800,800);
      this.setLayout(null);    
      this.setVisible(true);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }
}
class feeListener implements ActionListener
{
  feeFrame x;
  String fee;
  boolean flag=false;
  feeListener(feeFrame x,String fee)
  {
         this.x=x;
         this.fee=fee;
  }
  public void getConnection()
  {
    Connection con=null;
    try{  
      Class.forName("com.mysql.cj.jdbc.Driver");

      String url= "jdbc:mysql://localhost:3306/hostel"; 
      String user = "root"; 
      String pwd = "Rammohan@2004";
      con = DriverManager.getConnection(url, user, pwd);
      String amt=x.at.getText();
      int feed=Integer.valueOf(fee);
      int feep=Integer.valueOf(amt);
      if(feep<=feed)
      {
        int td=feed-feep;
        String tamt=String.valueOf(td);
        String q="update studentDetails set fee=?";
        PreparedStatement pst=con.prepareStatement(q);
        pst.setString(1,tamt);
        pst.executeUpdate();
        flag=true;
      }
    }
    catch(Exception ex)
    {
      System.out.println(ex);
    }
     
  }
  public void actionPerformed(ActionEvent e)
  {
      String s=e.getActionCommand();
      if(s.equals("back"))
      {
         new studentMainFrame();
         x.dispose();
      }
      else
      {
          getConnection();  
          Graphics g=x.getGraphics();
          Font f = new Font("Consolas", Font.PLAIN, 22);
          g.setFont(f);
          if(flag)
          {
              g.drawString("Fee Paid successfully",200,420);
          }
          else
          {
              g.drawString("Payment Failed",230,420);
              try{
                Thread.currentThread().sleep(2000);
              }
              catch(Exception ex)
              {
                System.out.println(ex);
              }
              new feeFrame(fee);
              x.dispose();
          }
      }
  }

}
class wardenLoginFrame extends JFrame{
  JTextField id;
    JPasswordField pwd;
    JButton b,ba;
  wardenLoginFrame()
  {
      Font mf = new Font("Consolas", Font.PLAIN, 35);
      Font f = new Font("Consolas", Font.PLAIN, 25);
      Font bf = new Font("Consolas", Font.PLAIN, 20);
      ba=new JButton("Back");
      ba.setBounds(0,0,100,30);
      ba.setFont(bf);
      ba.setActionCommand("back");
      add(ba);
      JLabel ml=new JLabel("Login");
      ml.setFont(mf);
      ml.setBounds(300,100,200,50);
      add(ml);
      JLabel uid=new JLabel("User Name");
      uid.setFont(f);
      uid.setBounds(100,250,150,30);
      add(uid);
      JLabel pas=new JLabel("Password");
      pas.setFont(f);
      pas.setBounds(100,350,110,30);
      add(pas);
      id=new JTextField(20);
      id.setBounds(320,250,150,30);
      add(id);
      pwd=new JPasswordField(20);
      pwd.setBounds(320,350,150,30);
      add(pwd);
      b=new JButton("Submit");
      b.setFont(bf);
      b.setBounds(300,450,120,30);
      b.setActionCommand("submit");
      add(b);
      wardenLoginListener l=new wardenLoginListener(this);
      b.addActionListener(l);
      ba.addActionListener(l);
      this.setSize(800,800);
      this.setLayout(null);
      this.setVisible(true);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
    }
  }
class wardenLoginListener implements ActionListener
{
  wardenLoginFrame x;
  boolean flag=false;
  wardenLoginListener(wardenLoginFrame x)
  {
    this.x=x;
  }
  public void getconnection()
  {
      String u_name=x.id.getText();
      String pass=new String(x.pwd.getPassword());
      Connection con=null;
      try{  
        Class.forName("com.mysql.cj.jdbc.Driver");
  
        String url= "jdbc:mysql://localhost:3306/hostel"; 
        String user = u_name; 
        String pwd = pass;
        con = DriverManager.getConnection(url, user, pwd);
        flag=true;

      }
      catch(Exception ex)
      {
        System.out.println(ex);
      }
  }
  public void actionPerformed(ActionEvent e)
  {
    String s=e.getActionCommand();
    if(s.equals("back"))
    {
       new mainFrame();
       x.dispose();
    }
    else
    {
      getconnection();
       Graphics g=x.getGraphics();
       Font bf = new Font("Consolas", Font.PLAIN, 20);
       g.setFont(bf);
       if(flag)
       {
           new wardenMainFrame();
           x.dispose();
       }
       else
       {
           g.drawString("Login failed",300,550);
           try{
             Thread.currentThread().sleep(2000);
           }
           catch(Exception ex)
           {
            System.out.println(ex);
           }
           new wardenLoginFrame();
           x.dispose();

       }
    }
  }
}
class wardenMainFrame extends JFrame
{
      JButton ba,sr,ar,dr,sd,rd;
       wardenMainFrame()
       {
        Font mf = new Font("Consolas", Font.PLAIN, 35);
        Font f = new Font("Consolas", Font.PLAIN, 25);
        Font bf = new Font("Consolas", Font.PLAIN, 20);
         ba=new JButton("Back");
        ba.setBounds(0,0,100,30);
        ba.setFont(bf);
        ba.setActionCommand("back");
        add(ba);
        sr=new JButton("Student Registration");
        sr.setFont(f);
        sr.setBounds(200,150,310,50);
        sr.setActionCommand("reg");
        add(sr);
        ar=new JButton("Add Rooms");
        ar.setFont(f);
        ar.setBounds(200,250,310,50);
        ar.setActionCommand("add");
        add(ar);
        dr=new JButton("Delete Rooms");
        dr.setFont(f);
        dr.setBounds(200,350,310,50);
        dr.setActionCommand("delete");
        add(dr);
        rd=new JButton("Room Details");
        rd.setFont(f);
        rd.setBounds(200,450,310,50);
        rd.setActionCommand("room");
        add(rd);
        sd=new JButton("Student Details");
        sd.setFont(f);
        sd.setBounds(200,550,310,50);
        sd.setActionCommand("details");
        add(sd);
        wardenMainListener l=new wardenMainListener(this);
        ba.addActionListener(l);
        sr.addActionListener(l);
        ar.addActionListener(l);
        dr.addActionListener(l);
        rd.addActionListener(l);
        sd.addActionListener(l);
        this.setSize(800,800);
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
       }
}
class wardenMainListener implements ActionListener
{
  wardenMainFrame x;
  wardenMainListener(wardenMainFrame x)
  {
    this.x=x;
  }
  public void actionPerformed(ActionEvent e)
  {
       String s=e.getActionCommand();
        if(s.equals("back"))
        {
           new mainFrame();
           x.dispose();
        }
        else if(s.equals("reg"))
        {
           new studentSignUpFrame();
           x.dispose();
        }
        else if(s.equals("details"))
        {
            new studentsInRoomFrame();
            x.dispose();
        }
        else if(s.equals("add"))
        {
          new addRoomsFrame();
          x.dispose();
        }
        else if(s.equals("delete"))
        {
          new deleteRoomsFrame();
          x.dispose();
        }
        else if(s.equals("room"))
        {
           new totalRoomDetailsFrame();
           x.dispose();
        }
  }
}
class studentSignUpFrame extends JFrame{
  JTextField id;
  JPasswordField pwd;
  JButton b,ba;
  studentSignUpFrame()
  {
    Font mf = new Font("Consolas", Font.PLAIN, 35);
    Font f = new Font("Consolas", Font.PLAIN, 25);
    Font bf = new Font("Consolas", Font.PLAIN, 20);
    ba=new JButton("Back");
    ba.setBounds(0,0,100,30);
    ba.setFont(bf);
    ba.setActionCommand("back");
    add(ba);
    JLabel ml=new JLabel("Student Registeration");
    ml.setFont(mf);
    ml.setBounds(200,100,450,50);
    add(ml);
    JLabel uid=new JLabel("User ID");
    uid.setFont(f);
    uid.setBounds(100,250,110,30);
    add(uid);
    JLabel pas=new JLabel("Password");
    pas.setFont(f);
    pas.setBounds(100,350,110,30);
    add(pas);
    id=new JTextField(20);
    id.setBounds(320,250,150,30);
    add(id);
    pwd=new JPasswordField(20);
    pwd.setBounds(320,350,150,30);
    add(pwd);
    b=new JButton("Submit");
    b.setFont(bf);
    b.setBounds(300,450,120,30);
    b.setActionCommand("submit");
    add(b);
   studentSignUpListener l=new studentSignUpListener(this);
    b.addActionListener(l);
    ba.addActionListener(l);
    this.setSize(800,800);
    this.setLayout(null);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

}
class studentSignUpListener implements ActionListener
{
  studentSignUpFrame x;
  boolean flag=false;
  studentSignUpListener(studentSignUpFrame x)
  {
    this.x=x;
  }
  public void getConnection()
  {
    String u_name=x.id.getText();
    String pass=new String(x.pwd.getPassword());
    Connection con=null;
    try{  
       Class.forName("com.mysql.cj.jdbc.Driver");
 
       String url= "jdbc:mysql://localhost:3306/hostel"; 
       String user = "root"; 
       String pwd = "Rammohan@2004";
       con = DriverManager.getConnection(url, user, pwd);
       String q="insert into studentLogin values(?,?)";
       PreparedStatement pst=con.prepareStatement(q);
       pst.setString(1,u_name);
       pst.setString(2,pass);
       pst.executeUpdate();
       flag=true;
    }
    catch(Exception ex){
  System.out.println(ex);
    } 
  }
  public void actionPerformed(ActionEvent e)
  {
        String s=e.getActionCommand() ;
        if(s.equals("back"))
        {
           new wardenMainFrame();
           x.dispose();
        }
        else
        {
          getConnection();
          Graphics g=x.getGraphics();
          Font bf = new Font("Consolas", Font.PLAIN, 20);
          g.setFont(bf);
          if(flag)
          {
            g.drawString("Registered sucessfully",300,550);
            try{
              Thread.currentThread().sleep(2000);
            }
            catch(Exception ex)
            {
            System.out.println(ex);
            }
            new wardenMainFrame();
            x.dispose();
          }
          else{
            g.drawString("Registeration Failed",300,550);
            try{
              Thread.currentThread().sleep(2000);
            }
            catch(Exception ex)
            {
            System.out.println(ex);
            }
            new studentSignUpFrame();
            x.dispose();
        }
      }
  }
}
class studentsInRoomFrame extends JFrame{
  JComboBox<String> rtc,rnc;
   studentsInRoomFrame()
   {
      Font mf = new Font("Consolas", Font.PLAIN, 30);
      Font f = new Font("Consolas", Font.PLAIN, 25);
      Font bf = new Font("Consolas", Font.PLAIN, 20);
      JButton ba=new JButton("Back");
      ba.setBounds(0,0,100,30);
      ba.setFont(bf);
      ba.setActionCommand("back");
      add(ba);
      JLabel ml=new JLabel("Students In a Particular Room");
      ml.setFont(mf);
      ml.setBounds(170,50,600,50);
      add(ml);
      JLabel rt=new JLabel("Room Type");
      rt.setFont(f);
      rt.setBounds(100,150,150,30);
      add(rt);
      JLabel rn=new JLabel("Room No");
      rn.setFont(f);
      rn.setBounds(100,200,150,30);
      add(rn);
      String type[]={"Two Sharing","Four Sharing"};	
      rtc= new JComboBox<String>(type);
      rtc.setBounds(300,150,150,30);
      add(rtc);
      studentInRoomTypeListner tl=new studentInRoomTypeListner(this);
      rtc.addItemListener(tl);
      rnc=new JComboBox<String>();
      rnc.setBounds(300,200,150,30);
      add(rnc);
      JButton s=new JButton("submit");
      s.setFont(f);
      s.setBounds(220,300,120,30);
      s.setActionCommand("submit");
      add(s);
      studentsInRoomListener l=new studentsInRoomListener(this);
      s.addActionListener(l);
      ba.addActionListener(l);
      this.setSize(800,800);
      this.setLayout(null);
      this.setVisible(true);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }
}
class studentInRoomTypeListner implements ItemListener
{
    studentsInRoomFrame x;
    boolean flag=false;
    studentInRoomTypeListner(studentsInRoomFrame x)
    {
        this.x=x;
    }
    public void getConnection()
    {
        Connection con=null;
        try{  
           Class.forName("com.mysql.cj.jdbc.Driver");
     
           String url= "jdbc:mysql://localhost:3306/hostel"; 
           String user = "root"; 
           String pwd = "Rammohan@2004";
           con = DriverManager.getConnection(url, user, pwd);
           String s=(String)x.rtc.getSelectedItem();
           if(s=="Two Sharing")
           {
              x.rnc.removeAllItems();
              String q="select * from twoSharing ";
              PreparedStatement  pst = con.prepareStatement(q);
              ResultSet rs = pst.executeQuery(q);
              while(rs.next())
              {
                String temp=rs.getString("roomNo");
                x.rnc.addItem(temp);
              }
           }
           else  if(s=="Four Sharing")
           {
            x.rnc.removeAllItems();
            String q="select * from FourSharing  ";
            PreparedStatement  pst = con.prepareStatement(q);
            ResultSet rs = pst.executeQuery(q);
            while(rs.next())
            {
              String temp=rs.getString("roomNo");
              x.rnc.addItem(temp);
            }
           }
           
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }
    public void itemStateChanged(ItemEvent ie)
    {
       getConnection();
    }
}
class studentsInRoomListener implements ActionListener
{
  studentsInRoomFrame x;
  studentsInRoomListener(studentsInRoomFrame x)
  {
         this.x=x;
  }
  public void actionPerformed(ActionEvent e)
  {
       String s=e.getActionCommand();
       if(s.equals("back"))
       {
          new wardenMainFrame();
          x.dispose();
       }
       else
       {
          String rt=(String)x.rtc.getSelectedItem();
          String rn=(String)x.rnc.getSelectedItem();
          new roommateFrame(rt, rn,1);
          x.dispose();
       }
  }
}
class studentInRoomListener2 implements ActionListener
{
  roommateFrame x;
  studentInRoomListener2(roommateFrame x)
  {
    this.x=x;
  }
   public void actionPerformed(ActionEvent e)
   {
      new wardenMainFrame();
      x.dispose();
   }
}
class addRoomsFrame extends JFrame{
  JComboBox<String> rtc;
  JTextField rnc;
  addRoomsFrame()
  {
      Font mf = new Font("Consolas", Font.PLAIN, 30);
      Font f = new Font("Consolas", Font.PLAIN, 25);
      Font bf = new Font("Consolas", Font.PLAIN, 20);
      JButton ba=new JButton("Back");
      ba.setBounds(0,0,100,30);
      ba.setFont(bf);
      ba.setActionCommand("back");
      add(ba);
      JLabel ml=new JLabel("Adding a Room");
      ml.setFont(mf);
      ml.setBounds(170,50,600,50);
      add(ml);
      JLabel rt=new JLabel("Room Type");
      rt.setFont(f);
      rt.setBounds(100,150,150,30);
      add(rt);
      String type[]={"Two Sharing","Four Sharing"};	
      rtc= new JComboBox<String>(type);
      rtc.setBounds(300,150,150,30);
      add(rtc);
      JLabel rn=new JLabel("Room No");
      rn.setFont(f);
      rn.setBounds(100,200,150,30);
      add(rn);
       rnc=new JTextField(10);
      rnc.setBounds(300,200,150,30);
      add(rnc);
      JButton s=new JButton("submit");
      s.setFont(f);
      s.setBounds(220,300,120,30);
      s.setActionCommand("submit");
      add(s);
      addRoomListener l=new addRoomListener(this);
      ba.addActionListener(l);
      s.addActionListener(l);
      this.setSize(800,800);
      this.setLayout(null);
      this.setVisible(true);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
class addRoomListener implements ActionListener
{
  addRoomsFrame x;
  boolean flag=false;
  addRoomListener(addRoomsFrame x)
  {
     this.x=x;
  }
  public void getconnection()
  {
    Connection con=null;
    try{  
      Class.forName("com.mysql.cj.jdbc.Driver");

      String url= "jdbc:mysql://localhost:3306/hostel"; 
      String user = "root"; 
      String pwd = "Rammohan@2004";
      con = DriverManager.getConnection(url, user, pwd);
      String rt=(String)x.rtc.getSelectedItem();
      String rn=x.rnc.getText();
      if(rt.equals("Two Sharing"))
      {
           String q="insert into twoSharing values(?,?,?)";
           PreparedStatement pst=con.prepareStatement(q);
           pst.setString(1,rn);
           pst.setString(2," ");
           pst.setString(3," ");
           pst.executeUpdate();
           flag=true;
      }
      else
      {
        String q="insert into fourSharing values(?,?,?,?,?)";
        PreparedStatement pst=con.prepareStatement(q);
        pst.setString(1,rn);
        pst.setString(2," ");
        pst.setString(3," ");
        pst.setString(4," ");
        pst.setString(5," ");
        pst.executeUpdate();
        flag=true;
      }
    }
    catch(Exception ex)
    {
      System.out.println(ex);
    }
  }
  public void actionPerformed(ActionEvent e)
  {
      String s=e.getActionCommand();
      if(s.equals("back"))
      {
        new wardenMainFrame();
        x.dispose();
      }
      else
      {
          Graphics g=x.getGraphics();
          Font f = new Font("Consolas", Font.PLAIN, 25);
          g.setFont(f);
           getconnection();
           if(flag)
           {
                g.drawString("Room Added Successfully",180,400);
           }
           else{
                g.drawString("Failed ",180,400);
                try{
                  Thread.currentThread().sleep(2000);
                }
                catch(Exception ex)
                {
                  System.out.println(ex);
                }
                new addRoomsFrame();
                x.dispose();
           }
      }
  }
}
class deleteRoomsFrame extends JFrame{
  JComboBox<String> rtc,rnc;
  deleteRoomsFrame()
  {
      Font mf = new Font("Consolas", Font.PLAIN, 30);
      Font f = new Font("Consolas", Font.PLAIN, 25);
      Font bf = new Font("Consolas", Font.PLAIN, 20);
      JButton ba=new JButton("Back");
      ba.setBounds(0,0,100,30);
      ba.setFont(bf);
      ba.setActionCommand("back");
      add(ba);
      JLabel ml=new JLabel("Deleting a Room");
      ml.setFont(mf);
      ml.setBounds(170,50,600,50);
      add(ml);
      JLabel rt=new JLabel("Room Type");
      rt.setFont(f);
      rt.setBounds(100,150,150,30);
      add(rt);
      String type[]={"Two Sharing","Four Sharing"};	
      rtc= new JComboBox<String>(type);
      rtc.setBounds(300,150,150,30);
      add(rtc);
      JLabel rn=new JLabel("Room No");
      rn.setFont(f);
      rn.setBounds(100,200,150,30);
      add(rn);
       rnc=new JComboBox<>();
      rnc.setBounds(300,200,150,30);
      add(rnc);
      JButton s=new JButton("submit");
      s.setFont(f);
      s.setBounds(220,300,120,30);
      s.setActionCommand("submit");
      add(s);
      deleteRoomTypeListener tl=new deleteRoomTypeListener(this);
      rtc.addItemListener(tl);
      deleteRoomListener l=new deleteRoomListener(this);
      ba.addActionListener(l);
      s.addActionListener(l);
      this.setSize(800,800);
      this.setLayout(null);
      this.setVisible(true);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
class deleteRoomTypeListener implements ItemListener
{
   deleteRoomsFrame x;
   deleteRoomTypeListener(deleteRoomsFrame x)
   {
    this.x=x;
   }
   public void itemStateChanged(ItemEvent e)
   {
    Connection con=null;
    try{  
      Class.forName("com.mysql.cj.jdbc.Driver");

      String url= "jdbc:mysql://localhost:3306/hostel"; 
      String user = "root"; 
      String pwd = "Rammohan@2004";
      con = DriverManager.getConnection(url, user, pwd);
      String s=(String)x.rtc.getSelectedItem();
      if(s.equals("Two Sharing"))
           {
              x.rnc.removeAllItems();
              String q="select * from twoSharing where  bedOne=' '  and bedTwo=' ' ";
              PreparedStatement  pst = con.prepareStatement(q);
              ResultSet rs = pst.executeQuery(q);
              while(rs.next())
              {
                String temp=rs.getString("roomNo");
                x.rnc.addItem(temp);
              }
           }
           else  if(s.equals("Four Sharing"))
           {
            x.rnc.removeAllItems();
            String q="select * from FourSharing where bedOne=' ' and  bedTwo=' ' and  bedThree=' ' and  bedFour=' ' ";
            PreparedStatement  pst = con.prepareStatement(q);
            ResultSet rs = pst.executeQuery(q);
            while(rs.next())
            {
              String temp=rs.getString("roomNo");
              x.rnc.addItem(temp);
            }
           }
           
      
    }
    catch(Exception ex)
    {
      System.out.println(ex);
    }
   }
}
class deleteRoomListener implements ActionListener
{
  deleteRoomsFrame x;
  boolean flag=false;
  deleteRoomListener(deleteRoomsFrame x)
  {
     this.x=x;
  }
  public void getconnection()
  {
    Connection con=null;
    try{  
      Class.forName("com.mysql.cj.jdbc.Driver");

      String url= "jdbc:mysql://localhost:3306/hostel"; 
      String user = "root"; 
      String pwd = "Rammohan@2004";
      con = DriverManager.getConnection(url, user, pwd);
      String rt=(String)x.rtc.getSelectedItem();
      String rn=(String)x.rnc.getSelectedItem();
      if(rt.equals("Two Sharing"))
      {
           String q="delete from twoSharing where roomNo=?";
           PreparedStatement pst=con.prepareStatement(q);
           pst.setString(1,rn);
           pst.executeUpdate();
           flag=true;
      }
      else
      {
        String q="delete from fourSharing where roomNo=?";
        PreparedStatement pst=con.prepareStatement(q);
        pst.setString(1,rn);
        pst.executeUpdate();
        flag=true;
      }
    }
    catch(Exception ex)
    {
      System.out.println(ex);
    }
  }
  public void actionPerformed(ActionEvent e)
  {
      String s=e.getActionCommand();
      if(s.equals("back"))
      {
        new wardenMainFrame();
        x.dispose();
      }
      else
      {
          Graphics g=x.getGraphics();
          Font f = new Font("Consolas", Font.PLAIN, 25);
          g.setFont(f);
           getconnection();
           if(flag)
           {
                g.drawString("Room Deleted Successfully",180,400);
           }
           else{
                g.drawString("Failed ",180,400);
                try{
                  Thread.currentThread().sleep(2000);
                }
                catch(Exception ex)
                {
                  System.out.println(ex);
                }
                new deleteRoomsFrame();
                x.dispose();
           }
      }
  }
}
class totalRoomDetailsFrame extends JFrame{
   totalRoomDetailsFrame()
   {
      this.setSize(800,800);
      this.setLayout(null);
      this.setVisible(true);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Font mf = new Font("Consolas", Font.PLAIN, 15);
      Font f = new Font("Consolas", Font.PLAIN, 25);
      Font bf = new Font("Consolas", Font.PLAIN, 20);
      JButton ba=new JButton("Back");
      ba.setBounds(0,0,100,30);
      ba.setFont(bf);
      ba.setActionCommand("back");
      add(ba);
      JLabel tl=new JLabel("Two Sharing");
      tl.setFont(f);
      tl.setBounds(120,50,200,30);
      add(tl);
      JLabel fl=new JLabel("Four Sharing");
      fl.setFont(f);
      fl.setBounds(420,50,200,30);
      add(fl);
      
      Connection con=null;
      try{  
        Class.forName("com.mysql.cj.jdbc.Driver");
  
        String url= "jdbc:mysql://localhost:3306/hostel"; 
        String user = "root"; 
        String pwd = "Rammohan@2004";
        con = DriverManager.getConnection(url, user, pwd);
        String q1="select * from twoSharing";
        PreparedStatement pst1=con.prepareStatement(q1);
        ResultSet rs1=pst1.executeQuery();
        int i=0;
        while(rs1.next())
        {
            String s=rs1.getString("roomNo");
            JLabel rn=new JLabel(s);
            rn.setFont(mf);
            rn.setBounds(120,150+i,100,30);
            add(rn);
            i=i+30;
        }
        String q2="select * from fourSharing";
        PreparedStatement pst2=con.prepareStatement(q2);
        ResultSet rs2=pst2.executeQuery();
         i=0;
        while(rs2.next())
        {
            String s=rs2.getString("roomNo");
            JLabel rn=new JLabel(s);
            rn.setFont(mf);
            rn.setBounds(420,150+i,100,30);
            add(rn);
            i=i+30;
        }
      }
      catch(Exception ex)
      {
        System.out.println(ex);
      }
      totalRoomDetailsListener l=new totalRoomDetailsListener(this);
      ba.addActionListener(l);
      
   }
}
class totalRoomDetailsListener implements ActionListener
{
  totalRoomDetailsFrame x;
   totalRoomDetailsListener(totalRoomDetailsFrame x)
   {
      this.x=x;
   }
   public void actionPerformed(ActionEvent e)
   {
     new wardenMainFrame();
     x.dispose();
   }
}
public class hostel {
    public static void main(String args[] )
    {
         new mainFrame();
        
    }
}