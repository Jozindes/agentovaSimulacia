package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="3"
public class AgentSalonu extends Agent
{
    
    private int pocetLudiRadKadernicky = 0;
    private int pocetLudiRadKozmeticky = 0;
    
	public AgentSalonu(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerSalonu(Id.managerSalonu, mySim(), this);
		addOwnMessage(Mc.urobCistenie);
		addOwnMessage(Mc.odparkujZakaznika);
		addOwnMessage(Mc.spracujPlatbu);
		addOwnMessage(Mc.obsluzZakaznika);
		addOwnMessage(Mc.spracujObjednavku);
		addOwnMessage(Mc.zmenaPoctuLudiRadKadernicky);
		addOwnMessage(Mc.urobLicenie);
		addOwnMessage(Mc.urobUces);
		addOwnMessage(Mc.zaparkujZakaznika);
		addOwnMessage(Mc.zmenaPoctuLudiRadKozmeticky);
		addOwnMessage(Mc.dajPocetZakaznikovVRadoch);
		addOwnMessage(Mc.pohybujZakaznika);
	}
	//meta! tag="end"
        
        public void nastavPocetLudiRadKadernicky(int pocet) {
            pocetLudiRadKadernicky = pocet;
        }
        
        public int dajPocetLudiRadKadernicky() {
            return pocetLudiRadKadernicky;
        }
        
        public void nastavPocetLudiRadKozmeticky(int pocet) {
            pocetLudiRadKozmeticky = pocet;
        }
        
        public int dajPocetLudiRadKozmeticky() {
            return pocetLudiRadKozmeticky;
        }
        
        // formatuje akykolvek cas zadany v sekundach do formatu hh:mm:ss - vyuzivam pri vypise statistik v grafickom okne
    public String dajFormatovanySimulacnyCas(double paSekundy) {
        double kopiaSimulacnehoCasu = paSekundy;
        int hodiny = (int) kopiaSimulacnehoCasu / 3600;
        kopiaSimulacnehoCasu = kopiaSimulacnehoCasu - (hodiny * 3600);
        int minuty = (int) kopiaSimulacnehoCasu / 60;
        kopiaSimulacnehoCasu = kopiaSimulacnehoCasu - (minuty * 60);
        int sekundy = (int) kopiaSimulacnehoCasu;
        
        String hod;
        if (hodiny >= 10) {
            hod = String.valueOf(hodiny % 24);
        } else {
            hod = "0" + String.valueOf(hodiny % 24);
        }
        
        String min;
        if (minuty >= 10) {
            min = String.valueOf(minuty);
        } else {
            min = "0" + String.valueOf(minuty);
        }
        
        String sek;
        if (sekundy >= 10) {
            sek = String.valueOf(sekundy);
        } else {
            sek = "0" + String.valueOf(sekundy);
        }
        
        return hod + ":" + min + ":" + sek;
    }
    
    public double kolkoJeOtvoreneNaviac(double aktualnySimulacnyCas) {
        return aktualnySimulacnyCas - 28800;
    }
}
