package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;
import generatory.RovnomerneSpojite;

//meta! id="72"
public class AgentPohybu extends Agent
{
    private int kolkoJeRadov;
    
    private RovnomerneSpojite generatorRychlostiPohybuPeso;
    
	public AgentPohybu(int id, Simulation mySim, Agent parent, int pocetParkovacichMiest)
	{
		super(id, mySim, parent);
                kolkoJeRadov = pocetParkovacichMiest;
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
                generatorRychlostiPohybuPeso = new RovnomerneSpojite(1.8, 3.2);
		// Setup component for the next replication
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerPohybu(Id.managerPohybu, mySim(), this);
		new ObsluhaPohybU3(Id.obsluhaPohybU3, mySim(), this);
		new ObsluhaPohybU7(Id.obsluhaPohybU7, mySim(), this);
		new ObsluhaPohybU8(Id.obsluhaPohybU8, mySim(), this);
		new ObsluhaPohybU10(Id.obsluhaPohybU10, mySim(), this);
		new ObsluhaPohybU4(Id.obsluhaPohybU4, mySim(), this);
		new ObsluhaPohybU2(Id.obsluhaPohybU2, mySim(), this);
		new ObsluhaPohybU6(Id.obsluhaPohybU6, mySim(), this);
		new ObsluhaPohybU9(Id.obsluhaPohybU9, mySim(), this);
		new ObsluhaPohybU5(Id.obsluhaPohybU5, mySim(), this);
		new ObsluhaPohybU1(Id.obsluhaPohybU1, mySim(), this);
		addOwnMessage(Mc.pohybujZakaznika);
	}
	//meta! tag="end"
        
        public int dajPocetParkovacichRadov() {
            return this.kolkoJeRadov;
        }
        
        public double dajHodnotuRychlostPeso() {
            return this.generatorRychlostiPohybuPeso.dajVygenerovanuHodnotu();
        }
}
