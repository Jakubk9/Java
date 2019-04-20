import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


class Zadanie1 {
    final int ogr = 200;            // ograniczenie na ilosc danych
    String[] tab = new String[ogr]; // tablica napisow
    int ile;                        // ilosc danych
    int kropki = 0;         // dlugosc najdluzszego stringa


    void czytaj(String plikWe) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(plikWe));
        String linia;
        ile = 0;
        while ((linia = br.readLine()) != null)
            if (linia.length() > 0)
                tab[ile++] = linia;
        br.close();
    }



    void zamienIszukaj() {
        int i;
        kropki = tab[0].length();
        for (i = 0; i < ile; i++) {
            int a = Integer.parseInt(tab[i]);
            tab[i] = Integer.toOctalString(a);
            if (tab[i].length() > kropki)
                kropki = tab[i].length();
        }
    }


    void pisz() {
        int i, j;
        for (i = 0; i < ile; i++) {
            for (j = 0; j < (kropki - tab[i].length()); j++)
                System.out.print(".");
            System.out.println(tab[i]);
        }
    }


    public static void main(String[] args) throws Exception {
        Zadanie1 cz = new Zadanie1();
        if (args.length >= 1) {
            cz.czytaj(args[0]);
            cz.zamienIszukaj();
            cz.pisz();
        } else
            System.err.println("Uzycie: Czytanie plik_wejsciowy");
    }
}