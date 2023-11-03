package agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import OSPStat.Stat;
import OSPStat.WStat;
import simulation.*;
import managers.*;
import continualAssistants.*;
import generatory.Trojuholnikove;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import statistiky.PriemernaDlzkaRadu;
import statistiky.Priemer;
import zamestnanci.Kozmeticka;

//meta! id="7"
public class AgentKozmeticok extends Agent
{
    
    private SimQueue<MessageForm> radZakaznikovPredKozmetickami;
    
    private Priemer statistikaRadKozmeticky;
    
    private PriemernaDlzkaRadu statistikaDlzkaRadKozmeticky;
    
    private Priemer statistikaDobaObsluhyKozmeticky;
    
    private ArrayList<Kozmeticka> volneKozmeticky;
    private ArrayList<Kozmeticka> pracujuceKozmeticky;
    
    private Trojuholnikove cisteniePleti;
    private Random pravdepodobnostTypuLicenia;
    private Random jednoducheLicenie;
    private Random zloziteLicenie;
    
    private int pocetKozmeticok;
    
	public AgentKozmeticok(int id, Simulation mySim, Agent parent, int pocetPracovnikov)
	{
		super(id, mySim, parent);
                pocetKozmeticok = pocetPracovnikov;
		init();
	}

	@Override
	public void prepareReplication()
	{
            super.prepareReplication();
            radZakaznikovPredKozmetickami= new SimQueue<>(new WStat(mySim()));
            statistikaRadKozmeticky = new Priemer();
            statistikaDlzkaRadKozmeticky = new PriemernaDlzkaRadu();
            statistikaDobaObsluhyKozmeticky= new Priemer();
            volneKozmeticky = new ArrayList<>();
            pracujuceKozmeticky = new ArrayList<>();
      
            // vlozenie volnych kadernicok
            for (int i = 1; i <= pocetKozmeticok; i++) {
                Kozmeticka kozmeticka = new Kozmeticka(i);
                this.volneKozmeticky.add(kozmeticka);
            }
            
            this.pravdepodobnostTypuLicenia = new Random();
            this.jednoducheLicenie = new Random();
        // generator hodnoty, ktora predstavuje cas zloziteho licenia
                this.zloziteLicenie = new Random();
                this.cisteniePleti = new Trojuholnikove(360, 900, 540);
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerKozmeticok(Id.managerKozmeticok, mySim(), this);
		new ObsluhaCistenie(Id.obsluhaCistenie, mySim(), this);
		new ObsluhaLicenie(Id.obsluhaLicenie, mySim(), this);
		addOwnMessage(Mc.urobCistenie);
		addOwnMessage(Mc.urobLicenie);
	}
	//meta! tag="end"
        
        public ArrayList<Kozmeticka> dajZoznamVolnychKozmeticok() {
            return volneKozmeticky;
        }
    
        public ArrayList<Kozmeticka> dajZoznamPracujucichKozmeticok() {
            return pracujuceKozmeticky;
        }
    
        public SimQueue<MessageForm> dajRadZakaznikovPredKozmetickami() { 
            return radZakaznikovPredKozmetickami; 
        }
    
        public Priemer dajStatistikuRadKozmeticky() {
            return this.statistikaRadKozmeticky;
        }
        
        // hodnota z generatora
    public double dajPravdepodobnostTypuLicenia() {
        return this.pravdepodobnostTypuLicenia.nextDouble();
    }
    
    
    // hodnota z generatora
    public double dajCasJednoduchehoLicenia() {
        return (this.jednoducheLicenie.nextInt(16) + 10) * 60;
    }
    
    // hodnota z generatora
    public double dajCasZlozitehoLicenia() {
        return (this.zloziteLicenie.nextInt(81) + 20) * 60;
    }
    
    // hodnota z generatora
    public double dajCasCisteniaPleti() {
        return this.cisteniePleti.dajVygenerovanuHodnotu();
    }
    
    public PriemernaDlzkaRadu dajStatistikuDlzkaRadKozmeticky() {
        return this.statistikaDlzkaRadKozmeticky;
    }
    
    public Priemer dajStatistikuDobaObsluhyKozmeticky() {
        return this.statistikaDobaObsluhyKozmeticky;
    }
    
    public ArrayList<Kozmeticka> dajZoznamVsetkychKozmeticokVypis() {
        ArrayList<Kozmeticka> docasny = new ArrayList<>();
        docasny.addAll(volneKozmeticky);
        docasny.addAll(pracujuceKozmeticky);
        Collections.sort(docasny);
        return docasny;
    }
    
    public double dajPriemerneVytazenie(double aktualnySimulacnyCas) {
        double suma = 0;
        double pocet = dajZoznamVsetkychKozmeticokVypis().size();
        for (Kozmeticka r : dajZoznamVsetkychKozmeticokVypis()) {
            suma = suma + r.vypocitajVytazenie(aktualnySimulacnyCas);
        }
        return suma / pocet;
    }
    
    public double dajPriemerneVytazenieDoCasu() {
        double suma = 0;
        double pocet = dajZoznamVsetkychKozmeticokVypis().size();
        for (Kozmeticka r : dajZoznamVsetkychKozmeticokVypis()) {
            suma = suma + r.vypocitajVytazenieDo17();
        }
        return suma / pocet;
    }
}
