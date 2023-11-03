package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="2"
public class ManagerOkolia extends Manager
{
         
	public ManagerOkolia(int id, Simulation mySim, Agent myAgent)
	{
		super(id, mySim, myAgent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication

		if (petriNet() != null)
		{
			petriNet().clear();
		}
	}

	//meta! sender="AgentModelu", id="49", type="Notice"
	public void processInicializuj(MessageForm message)
	{
	}

	//meta! sender="PlanovaniePrichoduPeso", id="39", type="Finish"
	public void processFinishPlanovaniePrichoduPeso(MessageForm message)
	{
	}

	//meta! sender="PlanovaniePrichoduAutom", id="53", type="Finish"
	public void processFinishPlanovaniePrichoduAutom(MessageForm message)
	{
	}

	//meta! sender="AgentModelu", id="45", type="Notice"
	public void processOdchodZakaznika(MessageForm message)
	{
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	public void init()
	{
	}

	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.inicializuj:                 
                 
                    message.setAddressee(myAgent().findAssistant(Id.planovaniePrichoduPeso));
                    message.setCode(Mc.start);
                    startContinualAssistant(message);
                    
                    
                    MyMessage sprava1 = new MyMessage(mySim());
                    sprava1.setCode(Mc.start);
                    sprava1.setAddressee(myAgent().findAssistant(Id.planovaniePrichoduAutom));
                    startContinualAssistant(sprava1);
                    /*
                    MyMessage novaMessagePeso = new MyMessage(mySim());
                    // vytvorim noveho zakaznika
                    Zakaznik novyZakP = new Zakaznik(1, myAgent().dajHodnotuZaujemOSluzby(), myAgent().dajHodnotuZaujemOCisteniePleti());
                    novaMessagePeso.nastavObsluhovanehoZakaznika(novyZakP);
                    novaMessagePeso.setAddressee(myAgent().findAssistant(Id.planovaniePrichoduPeso));
                    startContinualAssistant(novaMessagePeso);
		*/
                break;

		case Mc.odchodZakaznika:
                    myAgent().zvysPocetLudiKtoriOdisliZoSalonu();
                    myAgent().dajStatistikuCasVSysteme().pripocitajHodnotu(mySim().currentTime() - ((MyMessage)message).dajObsluhovanehoZakaznika().dajKedyPrisiel()) ;
                    // zapisem do statistiky bez chladenia
                    if (mySim().currentTime() < 28800) {
                        myAgent().dajStatistikuCasVSysteme().pripocitajHodnotuDoCasu(mySim().currentTime() - ((MyMessage)message).dajObsluhovanehoZakaznika().dajKedyPrisiel()) ;
                    }
                    myAgent().dajZoznamZakaznikov().remove(((MyMessage)message).dajObsluhovanehoZakaznika());
                    //System.out.println("ID: " + ((MyMessage)message).dajObsluhovanehoZakaznika().dajID() );
                break;

		case Mc.finish:
			switch (message.sender().id())
			{
			case Id.planovaniePrichoduPeso:
                            message.setCode(Mc.prichodZakaznika);
                            message.setAddressee(mySim().findAgent(Id.agentModelu));
                            myAgent().zvysPocetLudiKtoriPrisliDoSalonuPeso();
                            notice(message);
			break;

			case Id.planovaniePrichoduAutom:
                            message.setCode(Mc.prichodZakaznika);
                            message.setAddressee(mySim().findAgent(Id.agentModelu));	
                            myAgent().zvysPocetLudiKtoriPrisliDoSalonuAutom();
                            notice(message);
			break;
			}
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentOkolia myAgent()
	{
		return (AgentOkolia)super.myAgent();
	}
}
