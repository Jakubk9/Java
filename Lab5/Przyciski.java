import java.awt.* ;
import java.awt.event.* ;
import javax.swing.*;

class Przyciski extends JFrame{
    JTextField t = new JTextField(20);
    JButton b1 = new JButton("przycisk 1") ;
    JButton b2 = new JButton("przycisk 2") ;
    JButton b3 = new JButton("przycisk 3") ;
    JButton b4 = new JButton("przycisk 4") ;
    JButton b5 = new JButton("RESET") ;

    Integer kolory = 0;
    Integer ile = 0;

    Przyciski(){
        setTitle("Przyciski");
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout()) ;
        cp.add(b1) ;
        cp.add(b2) ;
        cp.add(b3);
        cp.add(b4);
        cp.add(b5);
        cp.add(t) ;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,300) ;
        setVisible(true) ;
        b1.addActionListener( new B1());
        b2.addActionListener( new B2());
        b3.addActionListener( new B3());
        b4.addActionListener( new B4());
        b5.addActionListener( new B5());
        b1.setBackground(Color.blue);
        b2.setBackground(Color.blue);
        b3.setBackground(Color.blue);
        b4.setBackground(Color.blue);
//        b5.setSize(200,100);
    }

    class B1 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (b1.getBackground().equals(Color.blue)) {
                if (kolory < 3){
                b1.setBackground(Color.yellow);
                kolory++;
                ile++;
                t.setText(ile.toString());
                }
            } else { b1.setBackground(Color.blue);
            kolory--;
            ile++;
            }
            t.setText(ile.toString());
        }
    }

    class B2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (b2.getBackground().equals(Color.blue)) {
                if (kolory < 3){
                    b2.setBackground(Color.yellow);
                    kolory++;
                    ile++;
                    t.setText(ile.toString());
                }
            } else { b2.setBackground(Color.blue);
            kolory--;
            ile++;
            }
            t.setText(ile.toString());
        }
    }

    class B3 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (b3.getBackground().equals(Color.blue)) {
                if (kolory < 3){
                    b3.setBackground(Color.yellow);
                    kolory++;
                    ile++;
                    t.setText(ile.toString());
                }
            } else { b3.setBackground(Color.blue);
            kolory--;
            ile++;
            }
            t.setText(ile.toString());
        }
    }

    class B4 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (b4.getBackground().equals(Color.blue)) {
                if (kolory < 3){
                    b4.setBackground(Color.yellow);
                    kolory++;
                    ile++;
                    t.setText(ile.toString());
                }
            } else { b4.setBackground(Color.blue);
            kolory--;
            ile++;
            }
            t.setText(ile.toString());
        }
    }

    class B5 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ile = 0;
            t.setText(ile.toString());
        }
    }



    public static void main(String[] arg){
        JFrame f = new Przyciski() ;
        //JFrame f1 = new Przyciski() ;
    }
}





