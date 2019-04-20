import java.util.*;

class Uczen implements Comparable<Uczen> {
    public String id;
    public String imie;
    ArrayList<Integer> oceny;

    Uczen(String id, String imie) {
        this.id = id;
        this.imie = imie;
        oceny =  new ArrayList<>();
    }

    public String toString() {
        StringBuilder s = new StringBuilder("  " + id + "        " + imie + "    ");
        for (Integer i : oceny) s.append(String.valueOf(i)).append(" ");
        return s.toString();
    }
    public int compareTo(Uczen u){
        return imie.toUpperCase().compareTo(u.imie.toUpperCase());
    }

    double srednia(){
        Integer s = 0;
        for(Integer i: oceny) {
            s += i;
        }
        return s.doubleValue()/oceny.size();
    }
    public ArrayList<Integer> getOceny(){
        return oceny;
    }
}

class CompX implements Comparator<Uczen>{
    public int compare(Uczen u, Uczen i){
        int i1 = u.srednia() > i.srednia() ? 1 : -1;
        return i1;
    }     
}

class WykazU {
    ArrayList<Uczen> wykaz = new ArrayList<>();

    void wstawUcznia(String id, String imie) {
        wykaz.add(new Uczen(id, imie));
    }

    void wstawOcene(String id, int ocena) {
        for (Uczen u : wykaz)
            if (u.id.equals(id)) {
                u.oceny.add(ocena);
            }
    }

    void wypisz(String id) {
        for (Uczen u : wykaz)
            if (u.id.equals(id)) {
                System.out.println(u);
            }
    }

    void wypisz() {
        System.out.println("  ID  ||   Imie   ||  Ocena ");
        for (Uczen u : wykaz) System.out.println(u);
    }

    void SortujA() {
        Collections.sort(wykaz);
    }

    void SortujB() {
        Collections.sort(wykaz, new CompX());
    }

    public float srednia() {
        float s = 0;
        float j = 0;
        for (Uczen u: wykaz) {
            for (Integer i : u.getOceny()) {
                s += i;
            }
            j += u.getOceny().size();
        }

        return s / j;
    }
}

class TestWykazU {
    public static void main(String[] a) {
        WykazU wu = new WykazU();

        wu.wstawUcznia("K", "Kazik");
        wu.wstawUcznia("K1", "Kazik");
        wu.wstawUcznia("N", "Nikodem");
        wu.wstawUcznia("J", "Jan   ");
        wu.wstawUcznia("W", "Wiesiek");

        wu.wstawOcene("K", 5);
        wu.wstawOcene("K", 4);
        wu.wstawOcene("K", 3);
        wu.wstawOcene("K", 5);
        wu.wstawOcene("K", 2);

        wu.wstawOcene("K1", 5);
        wu.wstawOcene("K1", 6);

        wu.wstawOcene("N", 4);
        wu.wstawOcene("N", 5);
        wu.wstawOcene("N", 4);

        wu.wstawOcene("J", 3);
        wu.wstawOcene("W", 5);
        wu.wypisz();

        wu.SortujA();
        wu.wypisz();

        wu.SortujB();
        wu.wypisz();

        System.out.println("średnia grupy: " + wu.srednia());
    }
}





