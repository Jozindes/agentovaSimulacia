package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;
import generatory.Exponencialne;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import statistiky.Priemer;

//meta! id="2"
public class AgentOkolia extends Agent {
    
    private int id;
    
    private Random generatorZaujemOSluzby;
    private Random generatorZaujemOCisteniePleti;
    private Exponencialne generatorPrichodyAutom;
    private Exponencialne generatorPrichodyPeso;

    private int kolkoLudiPrisloDoSalonuPeso;
    private int kolkoLudiPrisloDoSalonuAutom;
    private int kolkoLudiOdisloZoSalonu;
    
    private Priemer casZakaznikaVSysteme;
    
    Vector<Zakaznik> zoznamZakaznikov;
    
    public AgentOkolia(int id, Simulation mySim, Agent parent) {
	super(id, mySim, parent);
	init();
    }

    @Override
    public void prepareReplication() {
	super.prepareReplication();
        
        id = 1;
	generatorZaujemOSluzby = new Random();
        generatorZaujemOCisteniePleti = new Random();
        //generatorPrichodyAutom = new Exponencialne((double) 1 / 360);
        //generatorPrichodyPeso = new Exponencialne((double) 1 / 600);
        
        generatorPrichodyAutom = new Exponencialne((double) 1 / 450);
        generatorPrichodyPeso = new Exponencialne((double) 1 / 720);
        
        kolkoLudiPrisloDoSalonuPeso = 0;
        kolkoLudiPrisloDoSalonuAutom = 0;
        kolkoLudiOdisloZoSalonu = 0;
        
        casZakaznikaVSysteme = new Priemer();
        
        zoznamZakaznikov = new Vector<>();
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private void init() {
	new ManagerOkolia(Id.managerOkolia, mySim(), this);
	new PlanovaniePrichoduPeso(Id.planovaniePrichoduPeso, mySim(), this);
	new PlanovaniePrichoduAutom(Id.planovaniePrichoduAutom, mySim(), this);
	addOwnMessage(Mc.inicializuj);
	addOwnMessage(Mc.odchodZakaznika);
        addOwnMessage(Mc.prichodZakaznika);
    }
    //meta! tag="end"
    
    public double dajHodnotuZaujemOSluzby() {
        return this.generatorZaujemOSluzby.nextDouble();
    }
    
    public double dajHodnotuZaujemOCisteniePleti() {
        return this.generatorZaujemOCisteniePleti.nextDouble();
    }
    
    public double dajHodnotuPrichoduAutom() {
        return this.generatorPrichodyAutom.dajVygenerovanuHodnotu();
    }
    
    public double dajHodnotuPrichoduPeso() {
        return this.generatorPrichodyPeso.dajVygenerovanuHodnotu();
    }
    
    public int dajPocetLudiKtoriPrisliDoSalonuPeso() {
        return this.kolkoLudiPrisloDoSalonuPeso;
    }
    
    public int dajPocetLudiKtoriPrisliDoSalonuAutom() {
        return this.kolkoLudiPrisloDoSalonuAutom;
    }
    
    public void zvysPocetLudiKtoriPrisliDoSalonuPeso() {
        this.kolkoLudiPrisloDoSalonuPeso = this.kolkoLudiPrisloDoSalonuPeso + 1;
    }
    
    public void zvysPocetLudiKtoriPrisliDoSalonuAutom() {
        this.kolkoLudiPrisloDoSalonuAutom = this.kolkoLudiPrisloDoSalonuAutom + 1;
    }
    
    public int dajPocetLudiKtoriOdisliZoSalonu() {
        return this.kolkoLudiOdisloZoSalonu;
    }
    
    public void zvysPocetLudiKtoriOdisliZoSalonu() {
        this.kolkoLudiOdisloZoSalonu= this.kolkoLudiOdisloZoSalonu + 1;
    }
    
    public Priemer dajStatistikuCasVSysteme() {
        return this.casZakaznikaVSysteme;
    }
    
    public Vector<Zakaznik> dajZoznamZakaznikov() {
        return this.zoznamZakaznikov;
    }
    
    public int dajVolneID() {
        int posledneVolne = id;
        id++;
        return posledneVolne;
    }
}
