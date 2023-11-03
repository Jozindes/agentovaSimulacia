package simulation;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jožko
 */
public class Zakaznik {
    
    private int id;
    private final int akoPrisiel;
    private double kedyPrisiel;
    
    private boolean upravaUcesu = false;
    private boolean cisteniePleti = false;
    private boolean licenie = false;
    private boolean uzBolNaCisteni = false;
    
    private double zaciatokCakaniaRadRecepcneObjednavka;
    private double koniecCakaniaRadRecepcneObjednavka;
    private double zaciatokCakaniaRadRecepcnePlatba;
    private double koniecCakaniaRadRecepcnePlatba;
    private double zaciatokCakaniaRadKadernicky;
    private double koniecCakaniaRadKadernicky;
    private double zaciatokCakaniaRadKozmeticky;
    private double koniecCakaniaRadKozmeticky;
    
    private int naKtoromUsekuJeTeraz;
    private double kedyVstupilNaTentoUsek;
    private boolean zaparkoval;
    private int priKtoromParkovacomMiesteJe;
    private int posledneVolneMiesto;
    private int ktoryRadIdeSkusit;
    
    private double rychlostNaUseku;
    
    private int kolkokratPresielRad1;
    private int kolkokratPresielRad2;
    private int kolkokratPresielRad3;
    
    private int spokojnost;
    
    private String sposobPohybu;
    
    private boolean jeUzVnutri;
    private boolean potrebujeOdparkovat;
    private int cisloRaduParkovisko;
    private int cisloMiestaParkovisko;
    
    private boolean chceIstPrecZoSystemu;
    
    private double rychlostPeso;
    
    public Zakaznik(int id, int akoPrisiel, double kedyPrisiel, double hodnotaZaujemOSluzby, double hodnotaZaujemOCisteniePleti) {
        this.id = id;
        this.akoPrisiel = akoPrisiel;
        this.kedyPrisiel = kedyPrisiel;
        double vygenerovanaHodnotaZaujemOSluzby = hodnotaZaujemOSluzby;
        if (vygenerovanaHodnotaZaujemOSluzby < 0.2) {
            this.upravaUcesu = true;
        } else if (vygenerovanaHodnotaZaujemOSluzby >= 0.2 && vygenerovanaHodnotaZaujemOSluzby < 0.35) {
            this.licenie = true;
            double vygenerovanaHodnotaZaujemOCisteniePleti = hodnotaZaujemOCisteniePleti;
            if (vygenerovanaHodnotaZaujemOCisteniePleti < 0.35) {
                this.cisteniePleti = true;
            }
        } else {
            this.upravaUcesu = true;
            this.licenie = true;
            double vygenerovanaHodnotaZaujemOCisteniePleti = hodnotaZaujemOCisteniePleti;
            if (vygenerovanaHodnotaZaujemOCisteniePleti < 0.35) {
                this.cisteniePleti = true;
            }
        }       
        this.naKtoromUsekuJeTeraz = 0;
        this.kedyVstupilNaTentoUsek = 0;
        this.zaparkoval = false;
        this.priKtoromParkovacomMiesteJe = -1;
        this.posledneVolneMiesto = -1;
        this.ktoryRadIdeSkusit = 0;
        
        this.kolkokratPresielRad1 = 0;
        this.kolkokratPresielRad2 = 0;
        this.kolkokratPresielRad3 = 0;
        
        this.sposobPohybu = "";
        
        this.spokojnost = 0;
        
        this.jeUzVnutri = false;
        this.potrebujeOdparkovat = false;
        this.cisloRaduParkovisko = -1;
        this.cisloMiestaParkovisko = -1;
        
        this.chceIstPrecZoSystemu = false;
        
        this.rychlostPeso = 0;
    }
    
    // vrati hodnotu podla toho, ako prisiel zakaznik do systemu (1-peso, 2-autom)
    public int dajAkoPrisiel() {
        return this.akoPrisiel;
    }
    
    public double dajKedyPrisiel() {
        return this.kedyPrisiel;
    }
    
    public boolean chceUpravitUces() {
        return this.upravaUcesu;
    }

    public boolean chceCisteniePleti() {
        return this.cisteniePleti;
    }
    
    public boolean chceLicenie() {
        return this.licenie;
    }
    
    public void nastavBolUzNaCisteni(boolean stav) {
        this.uzBolNaCisteni = stav;
    }
    
    public boolean bolUzNaCisteni() {
        return this.uzBolNaCisteni;
    }
    
    // recepcne objednavka
    public void nastavZaciatokCakaniaRadRecepcneObjednavka(double casZaciatku) {
        this.zaciatokCakaniaRadRecepcneObjednavka = casZaciatku;
    }
    
    public void nastavKoniecCakaniaRadRecepcneObjednavka(double casKonca) {
        this.koniecCakaniaRadRecepcneObjednavka = casKonca;
    }
    
    public double dajZaciatokCakaniaRadRecepcneObjednavka() {
        return this.zaciatokCakaniaRadRecepcneObjednavka;
    }
    
    public double dajKoniecCakaniaRadRecepcneObjednavka() {
        return this.koniecCakaniaRadRecepcneObjednavka;
    }
    // recepcne objednavka
    
    // recepcne platba
    public void nastavZaciatokCakaniaRadRecepcnePlatba(double casZaciatku) {
        this.zaciatokCakaniaRadRecepcnePlatba = casZaciatku;
    }
    
    public void nastavKoniecCakaniaRadRecepcnePlatba(double casKonca) {
        this.koniecCakaniaRadRecepcnePlatba = casKonca;
    }
    
    public double dajZaciatokCakaniaRadRecepcnePlatba() {
        return this.zaciatokCakaniaRadRecepcnePlatba;
    }
    
    public double dajKoniecCakaniaRadRecepcnePlatba() {
        return this.koniecCakaniaRadRecepcnePlatba;
    }
    // recepcne platba
    
    // kadernicky
    public void nastavZaciatokCakaniaRadKadernicky(double casZaciatku) {
        this.zaciatokCakaniaRadKadernicky = casZaciatku;
    }
    
    public void nastavKoniecCakaniaRadKadernicky(double casKonca) {
        this.koniecCakaniaRadKadernicky = casKonca;
    }
    
    public double dajZaciatokCakaniaRadKadernicky() {
        return this.zaciatokCakaniaRadKadernicky;
    }
    
    public double dajKoniecCakaniaRadKadernicky() {
        return this.koniecCakaniaRadKadernicky;
    }
    // kadernicky
    
    // kozmeticky
    public void nastavZaciatokCakaniaRadKozmeticky(double casZaciatku) {
        this.zaciatokCakaniaRadKozmeticky = casZaciatku;
    }
    
    public void nastavKoniecCakaniaRadKozmeticky(double casKonca) {
        this.koniecCakaniaRadKozmeticky = casKonca;
    }
    
    public double dajZaciatokCakaniaRadKozmeticky() {
        return this.zaciatokCakaniaRadKozmeticky;
    }
    
    public double dajKoniecCakaniaRadKozmeticky() {
        return this.koniecCakaniaRadKozmeticky;
    }
    // kozmeticky
    
    // pohyb
    public void nastavUsek(int usek) {
        this.naKtoromUsekuJeTeraz = usek;
    }
    
    public void nastavCasVstupuNaUsek(double kedy) {
        this.kedyVstupilNaTentoUsek = kedy;
    }
    
    public int dajUsek() {
        return this.naKtoromUsekuJeTeraz;
    }
    
    public double dajCasVstupuNaUsek() {
        return this.kedyVstupilNaTentoUsek;
    }
    
    public void nastavZaparkoval(boolean stav) {
        this.zaparkoval = stav;
    }
    
    public boolean dajZaparkoval() {
        return this.zaparkoval;
    }
    
    public void nastavPriKtoromParkovacomMiesteJe(int miesto) {
        this.priKtoromParkovacomMiesteJe = miesto;
    }
    
    public int dajPriKtoromParkovacomMiesteJe() {
        return this.priKtoromParkovacomMiesteJe;
    }
    
    public void nastavPosledneVolneMiesto(int miesto) {
        this.posledneVolneMiesto = miesto;
    }
    
    public int dajPosledneVolneMiesto() {
        return this.posledneVolneMiesto;
    }
    
    public void nastavKtoryRadIdeSkusit(int rad) {
        this.ktoryRadIdeSkusit = rad;
    }
    
    public int dajKtoryRadIdeSkusit() {
        return this.ktoryRadIdeSkusit;
    }
    
    public void nastavRychlostNaUseku(double rychlost) {
        this.rychlostNaUseku = rychlost;
    }
    
    public double dajRychlostNaUseku() {
        return this.rychlostNaUseku;
    }
    
    public double dajPresneMiestoNaUseku(double aktualnySimulacnyCas) {
        double kolkoSomNaUseku = aktualnySimulacnyCas - this.kedyVstupilNaTentoUsek;
        return kolkoSomNaUseku * this.rychlostNaUseku;
    }
    
    public int dajID() {
        return this.id;
    }
    
    public int dajKolkokratPresielRad1() {
        return this.kolkokratPresielRad1;
    }
    public int dajKolkokratPresielRad2() {
        return this.kolkokratPresielRad2;
    }
    public int dajKolkokratPresielRad3() {
        return this.kolkokratPresielRad3;
    }
    
    public void zvysKolkokratPresielRad1() {
        this.kolkokratPresielRad1++;
    }
    public void zvysKolkokratPresielRad2() {
        this.kolkokratPresielRad2++;
    }
    public void zvysKolkokratPresielRad3() {
        this.kolkokratPresielRad3++;
    }
    
    public String dajSposobPohybu() {
        return this.sposobPohybu;
    }
    
    public void nastavSposobPohybu(String sposob) {
        this.sposobPohybu = sposob;
    }
    
    public int dajSpokojnost() {
        return this.spokojnost;
    }
    
    public void zvysSpokojnost(int kolko) {
        this.spokojnost = this.spokojnost + kolko;
    }
    
    public void nastavCiJeUzVnutri(boolean je) {
        this.jeUzVnutri = je;
    }
    
    public boolean dajCiJeVtutri() {
        return this.jeUzVnutri;
    }
    
    public void nastavPotrebujeOdparkovat(boolean stav) {
        this.potrebujeOdparkovat = stav;
    }
    
    public boolean dajCiPotrebujeOdparkovat() {
        return this.potrebujeOdparkovat;
    }
    
    public int dajCisloRaduParkovisko() {
        return this.cisloRaduParkovisko;
    }
    
    public int dajCisloMiestaParkovisko() {
        return this.cisloMiestaParkovisko;
    }
    
    public void nastavCisloRaduParkovisko(int cislo) {
        this.cisloRaduParkovisko = cislo;
    }
    
    public void nastavCisloMiestaParkovisko(int cislo) {
        this.cisloMiestaParkovisko = cislo;
    }
    
    public void nastavChceIstPrecZoSystemu(boolean stav) {
        this.chceIstPrecZoSystemu = stav;
    }
    
    public boolean dajChceIstPrecZoSystemu() {
        return this.chceIstPrecZoSystemu;
    }
    
    public double dajRychlostPeso() {
        return this.rychlostPeso;
    }
    
    public void nastavRychlostPeso(double rychlost) {
        this.rychlostPeso = rychlost;
    }
}
