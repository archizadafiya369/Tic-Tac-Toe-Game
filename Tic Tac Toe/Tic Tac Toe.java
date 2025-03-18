import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class TicTacToe extends Frame implements ActionListener{
    JButton btn[] = new JButton[9];
    Container c;
    JPanel p1,p2;
    JLabel txt;
    int chance_flag = 0;
    boolean player1;
    Object[] n = {"Restart"};
    JFrame frame;
    TicTacToe(){
        p1=new JPanel();
        p2=new JPanel();

        setTitle("Tic Tac Toe");
        setSize(1000,900);
        setVisible(true);
        setBackground(new Color(150, 150, 150));
        setLayout(new BorderLayout());
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                setVisible(false);
                System.exit(0);
            }
        });

        p1.setLayout(new FlowLayout());
        txt=new JLabel("Start Game");
        txt.setFont(new Font("Times New Roman", Font.BOLD, 75));
        txt.setHorizontalAlignment(JLabel.CENTER);
        p1.add(txt);

        p2.setLayout(new GridLayout(3,3));
        for (int i = 0; i < 9; i++) {
            btn[i] = new JButton(" ");
            p2.add(btn[i]);
            btn[i].setFont(new Font("Times New Roman", Font.BOLD, 120));
            btn[i].setFocusable(false);
            btn[i].addActionListener(this);
        }
        add(p1,BorderLayout.NORTH);
        add(p2,BorderLayout.CENTER);

        startGame();
    }

    void startGame(){        
            txt.setText("Its O's Turn");       
    }

    public void matchCheck() {
        if ((btn[0].getText() == "X") && (btn[1].getText() == "X") && (btn[2].getText() == "X")) {
            xWins(0, 1, 2);
        }
        else if ((btn[0].getText() == "X") && (btn[4].getText() == "X") && (btn[8].getText() == "X")) {
            xWins(0, 4, 8);
        }
        else if ((btn[0].getText() == "X") && (btn[3].getText() == "X") && (btn[6].getText() == "X")) {
            xWins(0, 3, 6);
        }
        else if ((btn[1].getText() == "X") && (btn[4].getText() == "X") && (btn[7].getText() == "X")) {
            xWins(1, 4, 7);
        }
        else if ((btn[2].getText() == "X") && (btn[4].getText() == "X") && (btn[6].getText() == "X")) {
            xWins(2, 4, 6);
        }
        else if ((btn[2].getText() == "X") && (btn[5].getText() == "X") && (btn[8].getText() == "X")) {
            xWins(2, 5, 8);
        }
       else if ((btn[3].getText() == "X") && (btn[4].getText() == "X") && (btn[5].getText() == "X")) {
            xWins(3, 4, 5);
        }
       else if ((btn[6].getText() == "X") && (btn[7].getText() == "X") && (btn[8].getText() == "X")) {
            xWins(6, 7, 8);
        }
      
        else if ((btn[0].getText() == "O") && (btn[1].getText() == "O") && (btn[2].getText() == "O")) {
            oWins(0, 1, 2);
        }
        else if ((btn[0].getText() == "O") && (btn[3].getText() == "O") && (btn[6].getText() == "O")) {
            oWins(0, 3, 6);
        }
        else if ((btn[0].getText() == "O") && (btn[4].getText() == "O") && (btn[8].getText() == "O")) {
            oWins(0, 4, 8);
        }
        else if ((btn[1].getText() == "O") && (btn[4].getText() == "O") && (btn[7].getText() == "O")) {
            oWins(1, 4, 7);
        }
        else if ((btn[2].getText() == "O") && (btn[4].getText() == "O") && (btn[6].getText() == "O")) {
            oWins(2, 4, 6);
        }
        else if ((btn[2].getText() == "O") && (btn[5].getText() == "O") && (btn[8].getText() == "O")) {
            oWins(2, 5, 8);
        }
        else if ((btn[3].getText() == "O") && (btn[4].getText() == "O") && (btn[5].getText() == "O")) {
            oWins(3, 4, 5);
        } else if ((btn[6].getText() == "O") && (btn[7].getText() == "O") && (btn[8].getText() == "O")) {
            oWins(6, 7, 8);
        }
        else if(chance_flag==9) {
            txt.setText("Match Tie");
            gameOver("Match Tie");
        }
    }
    public void xWins(int x1, int x2, int x3) {
        btn[x1].setBackground(Color.yellow);
        btn[x2].setBackground(Color.yellow);
        btn[x3].setBackground(Color.yellow);
        for (int i = 0; i < 9; i++) {
            btn[i].setEnabled(false);
        }
        txt.setText("Player 2 (X) Won");
        gameOver("X Wins");
    }
    public void oWins(int x1, int x2, int x3) {
        btn[x1].setBackground(Color.yellow);
        btn[x2].setBackground(Color.yellow);
        btn[x3].setBackground(Color.yellow);
        for (int i = 0; i < 9; i++) {
            btn[i].setEnabled(false);
        }
        txt.setText("Player 1 (O) Won");
        gameOver("O Wins");
    }

    public void gameOver(String s){
        chance_flag=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/st", "root", "");
            Statement st = con.createStatement();
            String qry = "insert into gamedb(winner) values (' "+ s +" '); ";
            st.executeUpdate(qry);
        }catch(Exception e){ }       

        int restart=JOptionPane.showConfirmDialog(this,s," Do you Want to play again? ",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null);
        if(restart==JOptionPane.YES_OPTION){            
            new TicTacToe();
        }else if(restart==JOptionPane.NO_OPTION){
            displayLastWinner();
        }    
    }

    public void displayLastWinner(){
        try{
            System.out.println("inside display method");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/st", "root", "");
            Statement st = con.createStatement();
            String qry = "SELECT * FROM `gamedb`";
            ResultSet rs = st.executeQuery(qry);
            System.out.println("query executed successfully!!");
            
            JLabel title = new JLabel("Winner Record Table");
            JTextArea table = new JTextArea();
            String data = "Sr No. \t Winner";
            while(rs.next()){
                data += "\n "+rs.getInt(1)+"\t"+rs.getString(2);     
                table.setText(data);                
            }  
            System.out.println(data);
            frame = new JFrame();
            frame.setLayout(new BorderLayout());
            frame.add(title, BorderLayout.NORTH);
            frame.add(table, BorderLayout.CENTER);

            frame.setTitle("Displaying Winners Table");
            frame.setSize(500,500);
            frame.setVisible(true);
            System.out.println("frame is now visible");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }catch(Exception e){ }   
    }

    public void actionPerformed(ActionEvent e){
        for(int i=0;i<9;i++){
            if(e.getSource()==btn[i]){
                if((player1==true) && (btn[i].getText()==" ")){
                    System.out.println("its X turn");
                    btn[i].setText("X");
                    txt.setText("Its O's Turn");
                    player1=false;
                    chance_flag++;
                    matchCheck();
                }else if((player1==false) && (btn[i].getText()==" ")){
                    System.out.println("its O turn");
                    btn[i].setText("O");
                    txt.setText("Its X's Turn");
                    player1=true;
                    chance_flag++;
                    matchCheck();
                }                
            }
        }
    }
    public static void main(String args[]){
        new TicTacToe();
    }
}
