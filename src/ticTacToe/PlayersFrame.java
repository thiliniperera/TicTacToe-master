package ticTacToe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author A.H.F. Riyafa
 */
public class PlayersFrame extends JDialog{
    private JButton btnUser;
    private JButton btnComp;
    private JPanel buttonPanel;
    private JPanel userPanel;
    private String player1,player2;
    public PlayersFrame(JFrame frame) {
        super(frame);
        setLocationRelativeTo(frame);
    }
    
    public final void createAndShowGUI(){
        //set the title of the JDialog
        setTitle("New Game");
        
        //panel to carry the buttons
        buttonPanel=new JPanel(new GridLayout(0,1));
        add(buttonPanel);
        
        //panel to add players
        userPanel=new JPanel();
        populateUserPanel(userPanel);
        
        //creates the button for playing with another user
        btnUser=new JButton("With another user");
        
        btnUser.addActionListener(evt->{
            buttonPanel.removeAll();
            add(userPanel);
            this.revalidate();
        });
        buttonPanel.add(btnUser);
        //creates the button for playing with computer
        btnComp=new JButton("With Computer");
        btnComp.addActionListener(evt->{
            player1=System.getProperty("user.name");
            player2="Computer";
            dispose();
        }); 
        buttonPanel.add(btnComp);
        setSize(new Dimension(300,150));
        setResizable(false);
        setModal(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    private void populateUserPanel(JPanel panel) {
        
        panel.setLayout(new GridLayout(0,1));     
            
        
        //panel to obtain first user name
        JPanel panel1=new JPanel(new BorderLayout());
        JLabel label1=new JLabel("Player 1: ");
        JTextField text1=new JTextField();
        text1.requestFocus();
        panel1.add(label1,BorderLayout.WEST);
        panel1.add(text1);
        
        //panel to obtain Second user name
        JPanel panel2=new JPanel(new BorderLayout());
        JLabel label2=new JLabel("Player 2: ");
        JTextField text2=new JTextField();
        panel2.add(label2,BorderLayout.WEST);
        panel2.add(text2);
        
        //panel to obtain first user name
        JPanel panel3=new JPanel();
        JButton btnOk=new JButton("OK");
        btnOk.addActionListener(evt->{  
            if (text1.getText()!=null) {
                player1=text1.getText();
            }else{
                player1="Player 1";
            }
            
             if (text1.getText()!=null) {
                player2=text2.getText();
            }else{
                player2="Player 2";
            }
            dispose();
        });        
        panel3.add(btnOk);
        
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        
        
    }
    
    public static void main(String[] args) {
        new PlayersFrame(new JFrame());
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }   
}
