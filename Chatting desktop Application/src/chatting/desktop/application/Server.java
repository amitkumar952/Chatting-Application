//java Swing use karenge jiski help se desktop based Application bna sakte hai
// Socket programming ka use karenge massage transfer kerne ke liye
package chatting.desktop.application;
import javax.swing.*; //swing aata hai java ke extendses package mein to javax import kerna padega
import javax.swing.border.*;
import java.awt.*; //color
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;


public class Server  implements ActionListener {
    
    JTextField text;
    JPanel a1;
    static Box vertical = Box.createVerticalBox(); //MAssages ko ek ke bad ek ko vertical print ker rha hai jo createverticalBox ki bajeh se bho rha hai
    
    static JFrame f = new JFrame();
    static DataOutputStream dout;
    
    
    Server(){   //object banate hi cunstractor call hota hai
        
        f.setLayout(null); // null isliye kiya kyoki mein koi inbuilt layout ka use nhi karonga  mein apne se layout use karonga
        
        JPanel p1 = new JPanel();//Panel ka matlab fram ke uper koi bhi cheej ko divide kerna hai or or koi bhi cheej lagani hai 
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null);
        f.add(p1); //add function ka use kerke app fram ke uper add ker sakte ho
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        p1.add(back);
        
        back.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0); // Pore fram ko band kerne ke liye
               
            }
        });
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));
        Image i5 = i4.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40, 10, 50, 50);
        p1.add(profile);
        
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(300, 20, 30, 30);
        p1.add(video);
        
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i11 = i10.getImage().getScaledInstance(35, 30,Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12);
        phone.setBounds(360, 20, 30, 30);
        p1.add(phone);
        
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10, 25,Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel morevert = new JLabel(i15);
        morevert.setBounds(420, 20, 10, 25);
        p1.add(morevert);
        
        JLabel name = new JLabel("Amit Gangwar");
        name.setBounds(110, 15, 100, 18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 13));
        p1.add(name);
        
        
        JLabel status = new JLabel("Active Now");
        status.setBounds(110, 35, 100, 18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 10));
        p1.add(status);
        
        
        a1 = new JPanel();
        a1.setBounds(5, 75, 440, 570);
        f.add(a1);
        
        text = new JTextField();      //JTextField class se aap textField bna sakte jisper user text likh sikhe
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(text);
        
        JButton send  = new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(send);
        
        f.setSize(450, 700);
        f.setLocation(200, 50);
        f.setUndecorated(true); // frame ke header ko hatane ke liye
        f.getContentPane(). setBackground(Color.WHITE);    //pore frame ko uthane ke liye getContentPane ka use kerte hai
        
        
        f.setVisible(true); //visibility by default hidden hoti hai to isliye setVisible(true) function ka use kerte hai
        
    }
    public void actionPerformed(ActionEvent ae){
        try{
        String out = text.getText(); // getTExt funtion se aap bo nikal sakte ho jo us text field mein likha hua hai
        
//        JLabel output = new JLabel(out);
        
        JPanel p2 = formatLabel(out);
       
        
        a1.setLayout(new BorderLayout());
        
        
        JPanel right = new JPanel(new BorderLayout());
        right.add(p2, BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        
        a1.add(vertical, BorderLayout.PAGE_START);
        
        
        dout.writeUTF(out);
        text.setText("");
        /*repaint();
        invalidate();
        validate();   => all function use the reload ya repaint the page*/ 
 
        f.repaint();
        f.invalidate();
        f.validate();
    
    }catch(Exception e){
        e.printStackTrace();
    }
        
    }
    public static JPanel formatLabel(String  out){
        JPanel panel = new JPanel();
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel output = new JLabel("<html><p style = \"width: 150px\">" + out + "</p></html>"); // java mein app html ka bhi use ker sakte ho
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true); // setOpaque true nhi karoge tabtak display per color dispaly nhi hoga
        output.setBorder(new EmptyBorder(15, 15, 15, 50));
        panel.add(output);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        
        panel.add(time);
        
        return panel;
        
    }
        
    
    public static void main(String[] args){
        new Server(); // esa likhte hai object ko to bo Annumous object bola jata hai
        
        
        try{
            ServerSocket skt = new ServerSocket(6001);
            while(true){
            Socket s = skt.accept();
            DataInputStream din = new DataInputStream(s.getInputStream()); // iski help se massage read ker sakte ho
            dout = new DataOutputStream(s.getOutputStream()); // iski help se massage  send ker sakte ho
            
            
            while(true){
                String msg = din.readUTF();
                JPanel panel = formatLabel(msg);
                
                JPanel left = new JPanel(new BorderLayout());
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);
                f.validate();
                
                
            }
        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
