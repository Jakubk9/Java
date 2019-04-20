class Ulamek
{
    private int licznik;
    private int mianownik;

    public Ulamek(int licznik, int mianownik){
        this.licznik = licznik;
        this.mianownik = mianownik;
    }

    public static Ulamek razy(Ulamek u, Ulamek v) {
        return new Ulamek(u.licznik*v.licznik, v.mianownik*u.mianownik);
    }

    public void mnozPrzez(Ulamek i){
        this.licznik = this.licznik * i.licznik;
        this.mianownik = this.mianownik * i.mianownik;
    }

    public void mnozPrzez(int i){
        this.licznik = this.licznik * i;
    }

    public int getLicznik() {
        return licznik;
    }

    public void setLicznik(int l) {
        licznik = l;
    }

    public int getMianownik() {
        return mianownik;
    }

    public void setMianownik(int m) {
        mianownik = m;
    }

    public String toString(){
        return this.licznik + "/" + this.mianownik;
    }



}


class LiczbaU {
    private int calosci;
    private Ulamek czescU;

    LiczbaU(int calosci, Ulamek czescU){
        this.calosci = calosci+czescU.getLicznik()/czescU.getMianownik();
        this.czescU = new Ulamek(czescU.getLicznik()%czescU.getMianownik(), czescU.getMianownik()); //licznik < mianownik
    };

    void mnozPrzez(LiczbaU l){
        this.calosci = ((this.calosci*czescU.getMianownik()+czescU.getLicznik())*(l.calosci*l.czescU.getMianownik()+l.czescU.getLicznik()))/(this.czescU.getMianownik()*l.czescU.getMianownik());
        this.czescU.setLicznik(((this.calosci*czescU.getMianownik()+czescU.getLicznik())*(l.calosci*l.czescU.getMianownik()+l.czescU.getLicznik()))/(this.czescU.getMianownik()*l.czescU.getMianownik()));
        this.czescU.setMianownik(this.czescU.getMianownik()*l.czescU.getMianownik());
    }
    void mnozPrzez(int i){
        this.calosci = this.calosci*i+(this.czescU.getLicznik()*i)/this.czescU.getMianownik();
        this.czescU.setLicznik((this.czescU.getLicznik()*i)%this.czescU.getMianownik());
    }
    void mnozPrzez(Ulamek u){
        this.calosci = ((this.calosci*this.czescU.getMianownik()+this.czescU.getLicznik())*u.getLicznik())/(this.czescU.getMianownik()*u.getMianownik());
        this.czescU.setLicznik(((this.calosci*this.czescU.getMianownik()+this.czescU.getLicznik())*u.getLicznik())%(this.czescU.getMianownik()*u.getMianownik()));
        this.czescU.setMianownik(this.czescU.getMianownik()*u.getMianownik());
    }
    public String toString(){
        return this.calosci + " " + this.czescU;
    }
}

class UlamekZP extends Ulamek{
    private int starylicznik;
    private int starymianownik;

    UlamekZP(int l, int m){
        super(l, m);
        starylicznik = 0;
        starymianownik = 0;
    }

    public int getStary_Licznik() {
        return starylicznik;
    }

    public int getStary_Mianownik() {
        return starymianownik;
    }

    public void mnozPrzez(int i){
        this.starylicznik = getLicznik();
        this.starymianownik = getMianownik();
        super.mnozPrzez(i);
    }

    public boolean sprawdz(){
        if(starymianownik == 0){
            return false;
        }
        else
            return true;
    }

    public void cofnij(){
        setLicznik(starylicznik);
        setMianownik(starymianownik);
    }
}

class TestUlamek
{
    static public void main(String[] args){
        Ulamek u1 = new Ulamek(5,32);
        Ulamek u2 = new Ulamek(3,4);
        Ulamek u3 = new Ulamek(6,11);

        System.out.println("~~~~~~~~");
        System.out.println(u1);
        System.out.println(u1.sprawdz:());
        u1.mnozPrzez(10);
        System.out.println(u1);
        System.out.println(u1.sprawdz());
        u1.cofnij();
        System.out.println(u1);
    }
