package agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import OSPStat.Stat;
import OSPStat.WStat;
import simulation.*;
import managers.*;
import continualAssistants.*;
import generatory.DiskretneEmpiricke;
import generatory.PrvokEmpiricke;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import statistiky.PriemernaDlzkaRadu;
import statistiky.Priemer;
import zamestnanci.Kadernicka;

//meta! id="6"
public class AgentKadernicok extends Agent
{
    
    private SimQueue<MessageForm> radZakaznikovPredKadernickami;
    
    private Priemer statistikaRadKadernicky;
    
    private PriemernaDlzkaRadu statistikaDlzkaRadKadernicky;
    
    private Priemer statistikaDobaObsluhyKadernicky;
    
    private ArrayList<Kadernicka> volneKadernicky;
    private ArrayList<Kadernicka> pracujuceKadernicky;
    private Random pravdepodobnostVyberuUcesu;
    private Random jednoduchyUces;
    private DiskretneEmpiricke zlozityUces;
    private DiskretneEmpiricke svadobnyUces;
    
    private int pocetKadernicok;
    
	public AgentKadernicok(int id, Simulation mySim, Agent parent, int pocetPracovnikov)
	{
                super(id, mySim, parent);
                pocetKadernicok = pocetPracovnikov;
		init();
	}

	@Override
	public void prepareReplication()
	{
            super.prepareReplication();
            radZakaznikovPredKadernickami = new SimQueue<>(new WStat(mySim()));
            statistikaRadKadernicky = new Priemer();
            statistikaDobaObsluhyKadernicky = new Priemer();
            statistikaDlzkaRadKadernicky = new PriemernaDlzkaRadu();
            volneKadernicky = new ArrayList<>();
            pracujuceKadernicky = new ArrayList<>();
      
            // vlozenie volnych kadernicok
            for (int i = 1; i <= pocetKadernicok; i++) {
                Kadernicka kadernicka = new Kadernicka(i);
                this.volneKadernicky.add(kadernicka);
            }
            
        this.pravdepodobnostVyberuUcesu = new Random();
        // generator hodnoty, ktora predstavuje cas robenia jednoducheho ucesu
        this.jednoduchyUces = new Random();
        // generator hodnoty, ktora predstavuje cas robenia zloziteho ucesu
        PrvokEmpiricke p1 = new PrvokEmpiricke(30, 60, 0.4);
        PrvokEmpiricke p2 = new PrvokEmpiricke(61, 120, 0.6);
        ArrayList<PrvokEmpiricke> l = new ArrayList<>();
        l.add(p1);
        l.add(p2);
        this.zlozityUces = new DiskretneEmpiricke(l);
        // generator hodnoty, ktora predstavuje cas robenia svadobneho ucesu
        PrvokEmpiricke o1 = new PrvokEmpiricke(50, 60, 0.2);
        PrvokEmpiricke o2 = new PrvokEmpiricke(61, 100, 0.3);
        PrvokEmpiricke o3 = new PrvokEmpiricke(101, 150, 0.5);
        ArrayList<PrvokEmpiricke> m = new ArrayList<>();
        m.add(o1);
        m.add(o2);
        m.add(o3);
        this.svadobnyUces = new DiskretneEmpiricke(m);
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerKadernicok(Id.managerKadernicok, mySim(), this);
		new ObsluhaCesanie(Id.obsluhaCesanie, mySim(), this);
		addOwnMessage(Mc.urobUces);

	}
	//meta! tag="end"
        
        public ArrayList<Kadernicka> dajZoznamVolnychKadernicok() {
            return volneKadernicky;
        }
    
        public ArrayList<Kadernicka> dajZoznamPracujucichKadernicok() {
            return pracujuceKadernicky;
        }
    
        public Priemer dajStatistikuRadKadernicky() {
            return this.statistikaRadKadernicky;
        }
    
        public SimQueue<MessageForm> dajRadZakaznikovPredKadernickami() { 
            return radZakaznikovPredKadernickami; 
        }
    
        
        // hodnota z generatora
    public double dajPravdepodobnostVyberuUcesu() {
        return this.pravdepodobnostVyberuUcesu.nextDouble();
    }
    
    // hodnota z generatora
    public double dajCasJednoduchehoUcesu() {
        return (this.jednoduchyUces.nextInt(21) + 10) * 60;
    }
    
    // hodnota z generatora
    public double dajCasZlozitehoUcesu() {
        return this.zlozityUces.dajVygenerovanuHodnotu() * 60;
    }
    
    // hodnota z generatora
    public double dajCasSvadobnehoUcesu() {
        return this.svadobnyUces.dajVygenerovanuHodnotu() * 60;
    }
    
    public PriemernaDlzkaRadu dajStatistikuDlzkaRadKadernicky() {
        return this.statistikaDlzkaRadKadernicky;
    }
    
    public Priemer dajStatistikuDobaObsluhyKadernicky() {
        return this.statistikaDobaObsluhyKadernicky;
    }
    
    public ArrayList<Kadernicka> dajZoznamVsetkychKadernicokVypis() {
        ArrayList<Kadernicka> docasny = new ArrayList<>();
        docasny.addAll(volneKadernicky);
        docasny.addAll(pracujuceKadernicky);
        Collections.sort(docasny);
        return docasny;
    }
    
    public double dajPriemerneVytazenie(double aktualnySimulacnyCas) {
        double suma = 0;
        double pocet = dajZoznamVsetkychKadernicokVypis().size();
        for (Kadernicka r : dajZoznamVsetkychKadernicokVypis()) {
            suma = suma + r.vypocitajVytazenie(aktualnySimulacnyCas);
        }
        return suma / pocet;
    }
    
    public double dajPriemerneVytazenieDoCasu() {
        double suma = 0;
        double pocet = dajZoznamVsetkychKadernicokVypis().size();
        for (Kadernicka r : dajZoznamVsetkychKadernicokVypis()) {
            suma = suma + r.vypocitajVytazenieDo17();
        }
        return suma / pocet;
    }
}
