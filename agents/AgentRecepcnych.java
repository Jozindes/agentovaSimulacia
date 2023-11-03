package agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import OSPStat.Stat;
import OSPStat.WStat;
import simulation.*;
import managers.*;
import continualAssistants.*;
import generatory.RovnomerneSpojite;
import java.util.ArrayList;
import java.util.Collections;
import statistiky.PriemernaDlzkaRadu;
import statistiky.Priemer;
import zamestnanci.Recepcna;

//meta! id="5"
public class AgentRecepcnych extends Agent {
    
    private SimQueue<MessageForm> radZakaznikovPredRecepcnymiObjednavka;
    private SimQueue<MessageForm> radZakaznikovPredRecepcnymiPlatba;
    
    private Priemer statistikaRadRecepcneObjednavka;
    private Priemer statistikaRadRecepcnePlatba;
    
    private PriemernaDlzkaRadu statistikaDlzkaRadRecepcneObjednavka;
    private PriemernaDlzkaRadu statistikaDlzkaRadRecepcnePlatba;
    
    private ArrayList<Recepcna> volneRecepcne;
    private ArrayList<Recepcna> pracujuceRecepcne;
    
    private RovnomerneSpojite generovanieVybavovaniaObjednavky;
    private RovnomerneSpojite generovanieVybavovaniaPlatby;
    
    private Priemer statistikaDobaObsluhyRecepcne;
    
    private double kolkoLudiSomPoslalPrec;
    
    private int pocetRecepcnych;
    
    public AgentRecepcnych(int id, Simulation mySim, Agent parent, int pocetPracovnikov) {
        super(id, mySim, parent);
        pocetRecepcnych = pocetPracovnikov;
	init();
    }

    @Override
    public void prepareReplication() {
	super.prepareReplication();
	radZakaznikovPredRecepcnymiObjednavka = new SimQueue<>(new WStat(mySim()));
        radZakaznikovPredRecepcnymiPlatba = new SimQueue<>(new WStat(mySim()));
        
        statistikaRadRecepcneObjednavka = new Priemer();
        statistikaRadRecepcnePlatba = new Priemer();
        
        statistikaDlzkaRadRecepcneObjednavka = new PriemernaDlzkaRadu();
        statistikaDlzkaRadRecepcnePlatba = new PriemernaDlzkaRadu();
        
        volneRecepcne = new ArrayList<>();
        pracujuceRecepcne = new ArrayList<>();
        generovanieVybavovaniaObjednavky = new RovnomerneSpojite(80, 320);
        generovanieVybavovaniaPlatby = new RovnomerneSpojite(130, 230);
        
        statistikaDobaObsluhyRecepcne = new Priemer();
        
        // vlozenie volnych recepcnych
        for (int i = 1; i <= pocetRecepcnych; i++) {
            Recepcna nova = new Recepcna(i);
            this.volneRecepcne.add(nova);
        } 
        
        kolkoLudiSomPoslalPrec = 0;
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private void init() {
	new ManagerRecepcnych(Id.managerRecepcnych, mySim(), this);
	new ObsluhaPlatba(Id.obsluhaPlatba, mySim(), this);
	new ObsluhaObjednavka(Id.obsluhaObjednavka, mySim(), this);
	addOwnMessage(Mc.spracujPlatbu);
	addOwnMessage(Mc.spracujObjednavku);
	addOwnMessage(Mc.dajPocetZakaznikovVRadoch);
        addOwnMessage(Mc.zmenaPoctuLudiRadyKadernickyKozmeticky);
    }
    //meta! tag="end"
    
    public ArrayList<Recepcna> dajZoznamVolnychRecepcnych() {
        return volneRecepcne;
    }
    
    public ArrayList<Recepcna> dajZoznamPracujucichRecepcnych() {
        return pracujuceRecepcne;
    }
           
    public SimQueue<MessageForm> dajRadZakaznikovPredRecepcnymiObjednavka() { 
        return radZakaznikovPredRecepcnymiObjednavka; 
    }
    
    // statistiky
    public Priemer dajStatistikuRadRecepcneObjednavka() {
        return this.statistikaRadRecepcneObjednavka;
    }
    
    public Priemer dajStatistikuRadRecepcnePlatba() {
        return this.statistikaRadRecepcnePlatba;
    }
    
    public PriemernaDlzkaRadu dajStatistikuDlzkaRadRecepcneObjednavka() {
        return this.statistikaDlzkaRadRecepcneObjednavka;
    }
    
    public PriemernaDlzkaRadu dajStatistikuDlzkaRadRecepcnePlatba() {
        return this.statistikaDlzkaRadRecepcnePlatba;
    }
    
    // statistiky
        
    public SimQueue<MessageForm> dajRadZakaznikovPredRecepcnymiPlatba() { 
        return radZakaznikovPredRecepcnymiPlatba; 
    }
       
    public double dajCasVybavovaniaObjednavky() {
        return generovanieVybavovaniaObjednavky.dajVygenerovanuHodnotu();
    }
    
    public double dajCasVybavovaniaPlatby() {
        return generovanieVybavovaniaPlatby.dajVygenerovanuHodnotu();
    }
    
    public ArrayList<Recepcna> dajZoznamVsetkychRecepcnychVypis() {
        ArrayList<Recepcna> docasny = new ArrayList<>();
        docasny.addAll(volneRecepcne);
        docasny.addAll(pracujuceRecepcne);
        Collections.sort(docasny);
        return docasny;
    }
    
    public void vyprazdnitRadRecepcneObjednavka() {
        while (radZakaznikovPredRecepcnymiObjednavka.size() > 0) {
            MessageForm v = radZakaznikovPredRecepcnymiObjednavka.removeLast();
            v.setCode(Mc.obsluzZakaznika);
            ((MyMessage)v).dajObsluhovanehoZakaznika().nastavKtoryRadIdeSkusit(((MyMessage)v).dajObsluhovanehoZakaznika().dajCisloRaduParkovisko());
            //System.out.println("Zakaznik s ID: " + ((MyMessage)v).dajObsluhovanehoZakaznika().dajID() + " zaplatil a ide na parkovisko do radu" + ((MyMessage)v).dajObsluhovanehoZakaznika().dajKtoryRadIdeSkusit());
            manager().response(v);
        }
    }
    
    public double dajPriemerneVytazenie(double aktualnySimulacnyCas) {
        double suma = 0;
        double pocet = dajZoznamVsetkychRecepcnychVypis().size();
        for (Recepcna r : dajZoznamVsetkychRecepcnychVypis()) {
            suma = suma + r.vypocitajVytazenie(aktualnySimulacnyCas);
        }
        return suma / pocet;
    }
    
    public double dajPriemerneVytazenieDoCasu() {
        double suma = 0;
        double pocet = dajZoznamVsetkychRecepcnychVypis().size();
        for (Recepcna r : dajZoznamVsetkychRecepcnychVypis()) {
            suma = suma + r.vypocitajVytazenieDo17();
        }
        return suma / pocet;
    }
    
    public Priemer dajStatistikuDobaObsluhyRecepcne() {
        return this.statistikaDobaObsluhyRecepcne;
    }
    
    public void nastavKolkoLudiSomPoslalPrec(double kolko) {
        this.kolkoLudiSomPoslalPrec = kolko;
    }
    
    public double dajKolkoLudiSomPoslalPrec() {
        return this.kolkoLudiSomPoslalPrec;
    }
}
