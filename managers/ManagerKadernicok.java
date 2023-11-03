package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;
import zamestnanci.Kadernicka;
import zamestnanci.Zamestnanec;

//meta! id="6"
public class ManagerKadernicok extends Manager
{
	public ManagerKadernicok(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentSalonu", id="58", type="Request"
	public void processDajPocetZakaznikovVRadeKadernicky(MessageForm message)
	{
	}

	//meta! sender="ObsluhaCesanie", id="30", type="Finish"
	public void processFinish(MessageForm message)
	{
	}

	//meta! sender="AgentSalonu", id="16", type="Request"
	public void processUrobUces(MessageForm message)
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
    public void processMessage(MessageForm message) {
	switch (message.code()) {           
            case Mc.finish:
                
                // uvolnim pracovnicku
                Kadernicka ktoraPracovala = (Kadernicka)((MyMessage)message).dajZamestnancaKtoryObsluhuje();
                ktoraPracovala.nastavPracovanie(false);
                this.myAgent().dajZoznamVolnychKadernicok().add(ktoraPracovala);
                this.myAgent().dajZoznamPracujucichKadernicok().remove(ktoraPracovala);
                // zapisem do statistiky kolko pracovala
                myAgent().dajStatistikuDobaObsluhyKadernicky().pripocitajHodnotu(mySim().currentTime() - ktoraPracovala.dajZaciatokObsluhy());
                // zapisem do statistiky bez chladenia
                if (mySim().currentTime() < 28800) {
                    myAgent().dajStatistikuDobaObsluhyKadernicky().pripocitajHodnotuDoCasu(mySim().currentTime() - ktoraPracovala.dajZaciatokObsluhy());
                }
                ktoraPracovala.zapisKoniecObsluhy(mySim().currentTime());
                
                // ak je niekto v rade pred kadernickami
                if (!myAgent().dajRadZakaznikovPredKadernickami().isEmpty()) {
                    // zapisem aktualnu dlzku radu do statistiky
                    myAgent().dajStatistikuDlzkaRadKadernicky().pripocitajVazenuPocetnost(mySim().currentTime(), myAgent().dajRadZakaznikovPredKadernickami().size());                    
                    // vytiahnem spravu
                    MyMessage nextMessage = (MyMessage)myAgent().dajRadZakaznikovPredKadernickami().dequeue();                   
                    // zakaznikovi zapisem koniec cakania
                    nextMessage.dajObsluhovanehoZakaznika().nastavKoniecCakaniaRadKadernicky(mySim().currentTime());                           
                    // zapisem do statistik, kolko dany zakaznik cakal v rade pred kadernickami
                    myAgent().dajStatistikuRadKadernicky().pripocitajHodnotu(nextMessage.dajObsluhovanehoZakaznika().dajKoniecCakaniaRadKadernicky() - nextMessage.dajObsluhovanehoZakaznika().dajZaciatokCakaniaRadKadernicky());
                    // zapisem do statistiky bez chladenia
                    if (mySim().currentTime() < 28800) {
                        myAgent().dajStatistikuRadKadernicky().pripocitajHodnotuDoCasu(nextMessage.dajObsluhovanehoZakaznika().dajKoniecCakaniaRadKadernicky() - nextMessage.dajObsluhovanehoZakaznika().dajZaciatokCakaniaRadKadernicky());
                    }
                    
                    // pracovnicka zase zacina pracovat
                    ktoraPracovala.nastavPracovanie(true);
                    ktoraPracovala.zapisZaciatokObsluhy(mySim().currentTime());
                    this.myAgent().dajZoznamVolnychKadernicok().remove(ktoraPracovala);
                    this.myAgent().dajZoznamPracujucichKadernicok().add(ktoraPracovala);
                    nextMessage.nastavZamestnancaKtoryObsluhuje(ktoraPracovala);
                      
                    // informujem agenta salonu, ze sa zmenila dlzka radu pred kadernickami spravou typu notice
                    MyMessage zmena = new MyMessage(mySim());
                    ((MyMessage)zmena).nastavPocetLudiRadKadernicky(myAgent().dajRadZakaznikovPredKadernickami().size());                   
                    zmena.setCode(Mc.zmenaPoctuLudiRadKadernicky);
                    zmena.setAddressee(mySim().findAgent(Id.agentSalonu));
                    notice(zmena);
                    
                    nextMessage.setAddressee(myAgent().findAssistant(Id.obsluhaCesanie));                                       
                    startContinualAssistant(nextMessage);
                }
                
                // ak zakaznik pojde ku kozmetickam
                if (((MyMessage)message).dajObsluhovanehoZakaznika().chceLicenie()) {
                    // ak chce zakaznik cistenie, tak mu ho naplanujem
                    if (((MyMessage)message).dajObsluhovanehoZakaznika().chceCisteniePleti()) {
                        message.setCode(Mc.urobCistenie);                        
                        response(message);
                    } else { // ak cistenie nechce, naplanujem len licenie
                        message.setCode(Mc.urobLicenie);
                        response(message);
                    }
                } else {
                    message.setCode(Mc.spracujPlatbu);
                    response(message);
                }
            break;

            case Mc.urobUces:
                // najde kadernicku, ktora pracovala najmenej
                if (!myAgent().dajZoznamVolnychKadernicok().isEmpty()) {
                    double najmenej = Double.MAX_VALUE;
                    Kadernicka ktoraMaNajmenej = null;
                    for (Kadernicka aktualna : myAgent().dajZoznamVolnychKadernicok()) {
                        if (aktualna.dajCasCelkovejObsluhy() < najmenej) {
                            najmenej = aktualna.dajCasCelkovejObsluhy();
                            ktoraMaNajmenej = aktualna;
                        }
                    }
                    ktoraMaNajmenej.nastavPracovanie(true);
                    this.myAgent().dajZoznamVolnychKadernicok().remove(ktoraMaNajmenej);
                    this.myAgent().dajZoznamPracujucichKadernicok().add(ktoraMaNajmenej);
                    ktoraMaNajmenej.zapisZaciatokObsluhy(mySim().currentTime());                        
                    ((MyMessage)message).nastavZamestnancaKtoryObsluhuje(ktoraMaNajmenej);
                    
                    // kedze zakaznik necakal, pripocitam do statistiky 0
                    myAgent().dajStatistikuRadKadernicky().pripocitajHodnotu(0);
                    // zapisem do statistiky bez chladenia
                    if (mySim().currentTime() < 28800) {
                        myAgent().dajStatistikuRadKadernicky().pripocitajHodnotuDoCasu(0);
                    }
                    
                    message.setAddressee(myAgent().findAssistant(Id.obsluhaCesanie));
                    startContinualAssistant(message);
                } else {
                    // zapisem aktualnu dlzku radu do statistiky
                    myAgent().dajStatistikuDlzkaRadKadernicky().pripocitajVazenuPocetnost(mySim().currentTime(), myAgent().dajRadZakaznikovPredKadernickami().size());                                     
                    ((MyMessage)message).dajObsluhovanehoZakaznika().nastavZaciatokCakaniaRadKadernicky(mySim().currentTime());                   
                    myAgent().dajRadZakaznikovPredKadernickami().enqueue(message);
                    
                    
                    // informujem agenta salonu, ze sa zmenila dlzka radu
                    MyMessage zmena = new MyMessage(mySim());
                    ((MyMessage)zmena).nastavPocetLudiRadKadernicky(myAgent().dajRadZakaznikovPredKadernickami().size());
                    zmena.setCode(Mc.zmenaPoctuLudiRadKadernicky);
                    zmena.setAddressee(mySim().findAgent(Id.agentSalonu));
                    notice(zmena);
                    
                }
            break;

            default:
		processDefault(message);
            break;
	}
    }
	//meta! tag="end"

	@Override
	public AgentKadernicok myAgent()
	{
            return (AgentKadernicok)super.myAgent();
	}

}
