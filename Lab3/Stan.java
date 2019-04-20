import  java.io.*;

class Stan{
    private int x ; // pozycja biezaca
    private int y ;
    private int popX ;  // pozycja poprzednia
    private int popY ;
    void wPrawo(){ popX = x; popY=y; x++;  }
    void wLewo(){ popX = x;  popY=y; x--;  }
    void wGore(){ popY = y; popX=x;  y++;  }
    void wDol(){ popY = y;  popX=x; y--;   }
    private int punkty ;  // punktacja
    boolean koniec = false ;  // koniec = true gdy osiagnieto
    // pole wyjsciowe i gracz chce skonczyc
    int polaSciemnione;
    int x() { return x ; }
    int y() { return y ; }
    void wroc() { x=popX ; y = popY ; } // powrot na poprzednia pozycje
    String opis() { return "("+x+","+y+")  "+punkty+"punktow\n" ;}
    // podaje: x,y, punkty
    // UZUPELNIC EWENTUALNIE O WIECEJ POTRZEBNYCH METOD
    void dodajPunkt(int i){ this.punkty +=i; }
    void premia(int i) {this.punkty = i*punkty;}
    Stan(int xPocz, int yPocz, int pPocz){
        x = xPocz ; y = yPocz ; punkty=pPocz;
    }
}

abstract class Pole{
    static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
    static char czytajZnak(){
        //  czyta jeden znak z klawiatury, pomija znak konca linii
        int c = '\0';
        try{
            String linia = br.readLine();   //czytanie 1 linijki z klawiatury
            if (linia.length()>0) c = linia.charAt(0) ; // pobranie jednego znaku
        }catch (IOException e){}
        return (char)c;
    }

    abstract String komentarz() ;
    // daje napis zawierajacy komentarz charakterystyczny dla danego pola

    void ruch(Stan s){
        // "ruch" modyfikuje stan s zgodnie z regulami danego pola
        System.out.print(komentarz()) ;
        if (s.polaSciemnione == 0)
            System.out.println(s.opis());
        else
            s.polaSciemnione--;
        System.out.print(" jaki ruch? [g,d,l,p] ") ;
        switch (czytajZnak()) {
            case 'g' : s.wGore(); break;
            case 'd' : s.wDol(); break;
            case 'l' : s.wLewo(); break;
            case 'p' : s.wPrawo(); break;
        }
    }
}

class Sciana extends Pole{
    //wypisuje komentarz i wraca na poprzednie miejsce, odejmuje jeden punkt
    String komentarz(){ return "sciana! "; }
    void ruch(Stan s){
        s.wroc();
        super.ruch(s);
        //zmniejsz punkty
        s.dodajPunkt(-1);
    }
}

class ZwyklePole extends Pole{
    // jak Pole, ponadto powinna odejmowac jeden punkt
    String komentarz() { return " zwykle pole" ; }
    void ruch(Stan s){
        super.ruch(s);
        s.dodajPunkt(-1);
    }

}


class Wyjscie extends Pole{
    // oferuje mozliwosc zakonczenia gry, a jezeli nie konczymy, to tak jak Pole
    String komentarz(){ return "WYJŚCIE";}
    void ruch(Stan s){
        System.out.println("Koniec (y) / Graj dalej (n)");
        switch(czytajZnak()){
            case 'y' : s.koniec = true; return;
            case 'n' : s.wroc(); break;
        }
    }
}

class Sciemnij extends Pole{
    String komentarz(){ return "Pole Ściemnione";}
    void ruch(Stan s){
        s.polaSciemnione += 3;
        super.ruch(s);
        s.dodajPunkt(1);
    }
}


class PolePremia extends Pole{
    String komentarz(){ return "Pole Premia!!!";}
    void ruch(Stan s){
        s.dodajPunkt(1);
        super.ruch(s);
    }
}

class PodwojnePunkty extends Pole{
    String komentarz(){ return "Pole Podwójne Punkty";}
    void ruch(Stan s){
        s.premia(2);
        super.ruch(s);
    }
}

class Gra {
    public static void main(String[] a) {
        // inicjalizacja "jaskini"
        int i, j;
        int rozmiar = 10;
        Pole[][] jaskinia = new Pole[rozmiar][rozmiar];
        for (i = 0; i < rozmiar; i++)
            for (j = 0; j < rozmiar; j++) {
                if (i == 0 || i == rozmiar - 1 || j == 0 || j == rozmiar - 1)
                    jaskinia[i][j] = new Sciana();
                else
                    jaskinia[i][j] = new ZwyklePole();
            }
        jaskinia[2][3] = new PolePremia();
        jaskinia[3][1] = new Sciemnij();
        jaskinia[3][7] = new Wyjscie();
        jaskinia[4][4] = new PodwojnePunkty();
        // gra wlasciwa
        Stan s = new Stan(3, 3, 10);
        while (!s.koniec) {
            (jaskinia[s.x()][s.y()]).ruch(s);
        }
    }
}