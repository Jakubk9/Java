import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

class Obliczenia {
    public int oblicz1(int n) {
        return n + 10;
    }

    public int oblicz2(int n) {
        return n - 1;
    }
}

class GIdoObl extends JFrame {
    JTextField
            status = new JTextField(20),
            wpwy = new JTextField(20),
            rezultat = new JTextField(20);

    Konto konto = new Konto();
    JButton
            obl1 = new JButton("Wpłata/Wypłata"),
            obl2 = new JButton("Odblokuj"),
            ob13 = new JButton("Cofnij");

    GIdoObl() {
        setTitle("Konto");
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(8, 2, 10, 10));
        cp.add(new JLabel("Stan:"));
        cp.add(status);
        cp.add(new JLabel("")); // odstep
        cp.add(new JLabel(""));
        obl1.addActionListener(new Obl1L());
        cp.add(obl1);
        cp.add(wpwy);
        cp.add(new JLabel("")); // odstep
        cp.add(new JLabel(""));
        cp.add(rezultat);
        obl2.addActionListener(new Obl2L());
        cp.add(obl2);
        cp.add(new JLabel("")); // odstep
        cp.add(new JLabel(""));
        cp.add(ob13);
        ob13.addActionListener(new Ob13L());
        cp.add(new JLabel("")); // odstep

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    int dajLiczbe(JTextField tf) throws NumberFormatException {
        return Integer.parseInt(tf.getText());
    }

    class Obl1L implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            obl1.setEnabled(false);
            wpwy.setEnabled(false);
        }
    }

    class Obl2L implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                try {
                    konto.operacja(dajLiczbe(wpwy));
                } catch (NumberFormatException a) {
                    rezultat.setText("Podaj Liczbę!!");
                    obl1.setEnabled(true);
                    wpwy.setText("");
                    wpwy.setEnabled(true);
                    return;
                }
                obl1.setEnabled(true);
                wpwy.setText("");
                wpwy.setEnabled(true);
                rezultat.setText("OK");
                status.setText(Integer.toString(konto.dajStan()));
            } catch (DebetException a1) {
                obl2.setEnabled(true);
                wpwy.setText("");
                wpwy.setEnabled(true);
                rezultat.setText("Brakuje " + a1.ile);
            }
        }
    }

    class Ob13L implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            konto.cofnij();
            status.setText(Integer.toString(konto.dajStan()));
        }
    }

    public static void main(String[] arg) {
        GIdoObl gi = new GIdoObl();
        gi.setSize(500, 500);
        gi.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                gi.konto.zapisz();
            }
        });

    }

    class DebetException extends Exception {
        int ile;

        public DebetException(int i) {
            ile = i;
        }
    }

    class Konto {
        private ArrayList<Integer> stan = new ArrayList<Integer>();

        Konto() {
            wczytaj();
            if (stan.size() == 0)
                stan.add(0);
        }

        public void operacja(int ile) throws DebetException {
            if (stan.get(stan.size() - 1) + ile >= 0)
                stan.add(stan.get(stan.size() - 1) + ile);
            else
                throw new DebetException((stan.get(stan.size() - 1) + ile * (-1)));
        }

        public int dajStan() {
            return stan.get(stan.size() - 1);
        }

        public int cofnij() {
            if (stan.size() > 1)
                stan.remove(stan.size() - 1);
            return stan.get(stan.size() - 1);
        }

        public void zapisz() {
            try {
                FileOutputStream f = new FileOutputStream("StanKonta");
                ObjectOutputStream os = new ObjectOutputStream(f);
                os.writeObject(stan);
                f.close();
            } catch (IOException e) {
            }
        }

        void wczytaj() {
            try {
                ObjectInputStream is = new ObjectInputStream(
                        new FileInputStream("StanKonta"));
                stan = (ArrayList<Integer>) is.readObject();
                is.close();
            } catch (IOException e) {
                stan.add(0);
            } catch (ClassNotFoundException e) {
            }
        }
    }
}
