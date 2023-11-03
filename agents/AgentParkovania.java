package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;
import statistiky.Priemer;

//meta! id="4"
public class AgentParkovania extends Agent
{
    
    private int kolkoJeRadov;
    private int[][] parkovacieMiesta;
    
    private Priemer statistikaSpokojnost;
    private int kolkoAutUspesneZaparkovalo;
    
	public AgentParkovania(int id, Simulation mySim, Agent parent, int pocetParkovacichMiest)
	{
		super(id, mySim, parent);
                kolkoJeRadov = pocetParkovacichMiest;
		init();
	}

	@Override
	public void prepareReplication()
	{
                parkovacieMiesta = new int[kolkoJeRadov][15];
                for (int i = 0; i < kolkoJeRadov; i++) {
                    for (int j = 0; j < 15; j++) {
                        parkovacieMiesta[i][j] = 0;
                    }
                }
                statistikaSpokojnost = new Priemer();
                kolkoAutUspesneZaparkovalo = 0;
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerParkovania(Id.managerParkovania, mySim(), this);
		new ObsluhaParkovanie(Id.obsluhaParkovanie, mySim(), this);
		addOwnMessage(Mc.zaparkujZakaznika);
	}
	//meta! tag="end"
        
        public int[][] dajParkovacieMiesta() {
            return this.parkovacieMiesta;
        }
        
        public int dajPocetParkovacichRadov() {
            return this.kolkoJeRadov;
        }
        
        public Priemer dajStatistikuSpokojnost() {
            return this.statistikaSpokojnost;
        }
        
        public int dajKolkoAutUspesneZaparkovalo() {
            return this.kolkoAutUspesneZaparkovalo;
        }
        
        public void zvysKolkoAutUspesneZaparkovalo() {
            this.kolkoAutUspesneZaparkovalo++;
        }
}
