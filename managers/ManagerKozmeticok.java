package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;
import zamestnanci.Kozmeticka;

//meta! id="7"
public class ManagerKozmeticok extends Manager
{
	public ManagerKozmeticok(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentSalonu", id="59", type="Request"
	public void processDajPocetZakaznikovVRadeKozmeticky(MessageForm message)
	{
	}

	//meta! sender="AgentSalonu", id="17", type="Request"
	public void processUrobCistenie(MessageForm message)
	{
	}

	//meta! sender="AgentSalonu", id="21", type="Request"
	public void processUrobLicenie(MessageForm message)
	{
	}

	//meta! sender="ObsluhaLicenie", id="27", type="Finish"
	public void processFinishObsluhaLicenie(MessageForm message)
	{
	}

	//meta! sender="ObsluhaCistenie", id="25", type="Finish"
	public void processFinishObsluhaCistenie(MessageForm message)
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
		switch (message.sender().id()) {
                    case Id.obsluhaCistenie:
                                                  
                        // uvolnim pracovnicku
                        Kozmeticka ktoraPracovala = (Kozmeticka)((MyMessage)message).dajZamestnancaKtoryObsluhuje();
                        ktoraPracovala.nastavPracovanie(false);
                        this.myAgent().dajZoznamVolnychKozmeticok().add(ktoraPracovala);
                        this.myAgent().dajZoznamPracujucichKozmeticok().remove(ktoraPracovala);
                        // zapisem do statistiky kolko pracovala
                        myAgent().dajStatistikuDobaObsluhyKozmeticky().pripocitajHodnotu(mySim().currentTime() - ktoraPracovala.dajZaciatokObsluhy());
                        // zapisem do statistiky bez chladenia
                        if (mySim().currentTime() < 28800) {
                            myAgent().dajStatistikuDobaObsluhyKozmeticky().pripocitajHodnotuDoCasu(mySim().currentTime() - ktoraPracovala.dajZaciatokObsluhy());
                        }
                        ktoraPracovala.zapisKoniecObsluhy(mySim().currentTime());
                        
                        
                        ((MyMessage)message).dajObsluhovanehoZakaznika().nastavBolUzNaCisteni(true);
                        
                        // ak je niekto v rade pred kozmetickami
                        if (!myAgent().dajRadZakaznikovPredKozmetickami().isEmpty()) {
                            // zapisem aktualnu dlzku radu do statistiky
                            myAgent().dajStatistikuDlzkaRadKozmeticky().pripocitajVazenuPocetnost(mySim().currentTime(), myAgent().dajRadZakaznikovPredKozmetickami().size());                           
                            // vytiahnem spravu
                            MyMessage nextMessage = (MyMessage)myAgent().dajRadZakaznikovPredKozmetickami().dequeue();
                            // zakaznikovi zapisem koniec cakania
                            nextMessage.dajObsluhovanehoZakaznika().nastavKoniecCakaniaRadKozmeticky(mySim().currentTime());                           
                            // zapisem do statistik, kolko dany zakaznik cakal v rade pred kozmetickami
                            myAgent().dajStatistikuRadKozmeticky().pripocitajHodnotu(nextMessage.dajObsluhovanehoZakaznika().dajKoniecCakaniaRadKozmeticky() - nextMessage.dajObsluhovanehoZakaznika().dajZaciatokCakaniaRadKozmeticky());
                            // zapisem do statistiky bez chladenia
                            if (mySim().currentTime() < 28800) {
                                myAgent().dajStatistikuRadKozmeticky().pripocitajHodnotuDoCasu(nextMessage.dajObsluhovanehoZakaznika().dajKoniecCakaniaRadKozmeticky() - nextMessage.dajObsluhovanehoZakaznika().dajZaciatokCakaniaRadKozmeticky());
                            }
                            
                            // pracovnicka zase zacina pracovat
                            ktoraPracovala.nastavPracovanie(true);
                            ktoraPracovala.zapisZaciatokObsluhy(mySim().currentTime());
                            this.myAgent().dajZoznamVolnychKozmeticok().remove(ktoraPracovala);
                            this.myAgent().dajZoznamPracujucichKozmeticok().add(ktoraPracovala);
                            nextMessage.nastavZamestnancaKtoryObsluhuje(ktoraPracovala);
                            
                            // informujem agenta salonu, ze sa zmenila dlzka radu
                            MyMessage zmena = new MyMessage(mySim());
                            ((MyMessage)zmena).nastavPocetLudiRadKozmeticky(myAgent().dajRadZakaznikovPredKozmetickami().size());
                            zmena.setCode(Mc.zmenaPoctuLudiRadKozmeticky);
                            zmena.setAddressee(mySim().findAgent(Id.agentSalonu));
                            notice(zmena);
                            
                            // vytiahnem zakaznika
                            Zakaznik vytZ = ((MyMessage)nextMessage).dajObsluhovanehoZakaznika();                                
                            // ak chce cistenie a este na nom nebol
                            if ((vytZ.chceCisteniePleti()) && (vytZ.bolUzNaCisteni() == false)) {
                                nextMessage.setAddressee(myAgent().findAssistant(Id.obsluhaCistenie));
                                startContinualAssistant(nextMessage);
                            } else if (vytZ.chceCisteniePleti() && vytZ.bolUzNaCisteni()) { // uz bol na cisteni
                                nextMessage.setAddressee(myAgent().findAssistant(Id.obsluhaLicenie));
                                startContinualAssistant(nextMessage);
                            } else {
                                nextMessage.setAddressee(myAgent().findAssistant(Id.obsluhaLicenie));
                                startContinualAssistant(nextMessage);
                            }                                                  
                        }
                        
                        // zakaznikovi naplanujem licenie, pretoze mal aj cistenie
                        message.setCode(Mc.urobLicenie);
                        response(message);
                    break;

                    case Id.obsluhaLicenie:
                                                  
                        // uvolnim pracovnicku
                        Kozmeticka ktoraPracovalaL = (Kozmeticka)((MyMessage)message).dajZamestnancaKtoryObsluhuje();
                        ktoraPracovalaL.nastavPracovanie(false);
                        this.myAgent().dajZoznamVolnychKozmeticok().add(ktoraPracovalaL);
                        this.myAgent().dajZoznamPracujucichKozmeticok().remove(ktoraPracovalaL);
                        // zapisem do statistiky kolko pracovala
                        myAgent().dajStatistikuDobaObsluhyKozmeticky().pripocitajHodnotu(mySim().currentTime() - ktoraPracovalaL.dajZaciatokObsluhy());
                        // zapisem do statistiky bez chladenia
                        if (mySim().currentTime() < 28800) {
                            myAgent().dajStatistikuDobaObsluhyKozmeticky().pripocitajHodnotuDoCasu(mySim().currentTime() - ktoraPracovalaL.dajZaciatokObsluhy());   
                        }
                        ktoraPracovalaL.zapisKoniecObsluhy(mySim().currentTime());                      
                        
                        // ak nie je rad pred kozmetickami prazdny
                        if (!myAgent().dajRadZakaznikovPredKozmetickami().isEmpty()) {
                            // zapisem aktualnu dlzku radu do statistiky
                            myAgent().dajStatistikuDlzkaRadKozmeticky().pripocitajVazenuPocetnost(mySim().currentTime(), myAgent().dajRadZakaznikovPredKozmetickami().size());                         
                            // vytiahnem spravu
                            MyMessage nextMessage = (MyMessage)myAgent().dajRadZakaznikovPredKozmetickami().dequeue();
                            // zakaznikovi zapisem koniec cakania
                            nextMessage.dajObsluhovanehoZakaznika().nastavKoniecCakaniaRadKozmeticky(mySim().currentTime());                           
                            // zapisem do statistik, kolko dany zakaznik cakal v rade pred kozmetickami
                            myAgent().dajStatistikuRadKozmeticky().pripocitajHodnotu(nextMessage.dajObsluhovanehoZakaznika().dajKoniecCakaniaRadKozmeticky() - nextMessage.dajObsluhovanehoZakaznika().dajZaciatokCakaniaRadKozmeticky());
                            // zapisem do statistiky bez chladenia
                            if (mySim().currentTime() < 28800) {
                                myAgent().dajStatistikuRadKozmeticky().pripocitajHodnotuDoCasu(nextMessage.dajObsluhovanehoZakaznika().dajKoniecCakaniaRadKozmeticky() - nextMessage.dajObsluhovanehoZakaznika().dajZaciatokCakaniaRadKozmeticky());
                            }
                            
                            // pracovnicka zase zacina pracovat
                            ktoraPracovalaL.nastavPracovanie(true);
                            nextMessage.nastavZamestnancaKtoryObsluhuje(ktoraPracovalaL);
                            ktoraPracovalaL.zapisZaciatokObsluhy(mySim().currentTime());
                            this.myAgent().dajZoznamVolnychKozmeticok().remove(ktoraPracovalaL);
                            this.myAgent().dajZoznamPracujucichKozmeticok().add(ktoraPracovalaL);
                            
                            // informujem agenta salonu, ze sa zmenila dlzka radu
                            MyMessage zmena = new MyMessage(mySim());
                            ((MyMessage)zmena).nastavPocetLudiRadKozmeticky(myAgent().dajRadZakaznikovPredKozmetickami().size());
                            zmena.setCode(Mc.zmenaPoctuLudiRadKozmeticky);
                            zmena.setAddressee(mySim().findAgent(Id.agentSalonu));
                            notice(zmena);        
                            
                            // vytiahnem zakaznika
                            Zakaznik vytZ = ((MyMessage)nextMessage).dajObsluhovanehoZakaznika();
                            // ak chce cistenie a este na nom nebol
                            if ((vytZ.chceCisteniePleti()) && (vytZ.bolUzNaCisteni() == false)) {
                                nextMessage.setAddressee(myAgent().findAssistant(Id.obsluhaCistenie));
                                startContinualAssistant(nextMessage);
                            } else if (vytZ.chceCisteniePleti() && vytZ.bolUzNaCisteni()) { // uz bol na cisteni
                                nextMessage.setAddressee(myAgent().findAssistant(Id.obsluhaLicenie));
                                startContinualAssistant(nextMessage);
                            } else {
                                nextMessage.setAddressee(myAgent().findAssistant(Id.obsluhaLicenie));
                                startContinualAssistant(nextMessage);
                            }                                                                      
                        } 
                        
                        // zakaznikovi naplanujem platbu, pretoze uz mal aj licenie
                        message.setCode(Mc.spracujPlatbu);
                        response(message);
                    break;
                }
            break;

            case Mc.urobCistenie:
                // najde kozmeticku, ktora pracovala najmenej
                if (!myAgent().dajZoznamVolnychKozmeticok().isEmpty()) {
                    double najmenej = Double.MAX_VALUE;
                    Kozmeticka ktoraMaNajmenej = null;
                    for (Kozmeticka aktualna : myAgent().dajZoznamVolnychKozmeticok()) {
                        if (aktualna.dajCasCelkovejObsluhy() < najmenej) {
                            najmenej = aktualna.dajCasCelkovejObsluhy();
                            ktoraMaNajmenej = aktualna;
                        }
                    }
                    ktoraMaNajmenej.nastavPracovanie(true);
                    this.myAgent().dajZoznamVolnychKozmeticok().remove(ktoraMaNajmenej);
                    this.myAgent().dajZoznamPracujucichKozmeticok().add(ktoraMaNajmenej);
                    ktoraMaNajmenej.zapisZaciatokObsluhy(mySim().currentTime());
                    ((MyMessage)message).nastavZamestnancaKtoryObsluhuje(ktoraMaNajmenej);
                    
                    // pripocitam 0, pretoze zakaznik necakal
                    myAgent().dajStatistikuRadKozmeticky().pripocitajHodnotu(0);
                    // zapisem do statistiky bez chladenia
                    if (mySim().currentTime() < 28800) {
                        myAgent().dajStatistikuRadKozmeticky().pripocitajHodnotuDoCasu(0);
                    }
                    
                    message.setAddressee(myAgent().findAssistant(Id.obsluhaCistenie));
                    startContinualAssistant(message);
                } else {
                    // zapisem aktualnu dlzku radu do statistiky
                    myAgent().dajStatistikuDlzkaRadKozmeticky().pripocitajVazenuPocetnost(mySim().currentTime(), myAgent().dajRadZakaznikovPredKozmetickami().size());                   
                    ((MyMessage)message).dajObsluhovanehoZakaznika().nastavZaciatokCakaniaRadKozmeticky(mySim().currentTime());             
                    myAgent().dajRadZakaznikovPredKozmetickami().enqueue(message);
                    
                    
                    // informujem agenta salonu, ze sa zmenila dlzka radu
                    MyMessage zmena = new MyMessage(mySim());
                    ((MyMessage)zmena).nastavPocetLudiRadKozmeticky(myAgent().dajRadZakaznikovPredKozmetickami().size());
                    zmena.setCode(Mc.zmenaPoctuLudiRadKozmeticky);
                    zmena.setAddressee(mySim().findAgent(Id.agentSalonu));
                    notice(zmena);
                    
                }
            break;

            case Mc.urobLicenie:
                // najde kozmeticku, ktora pracovala najmenej
                        if (!myAgent().dajZoznamVolnychKozmeticok().isEmpty()) {
                            double najmenej = Double.MAX_VALUE;
                            Kozmeticka ktoraMaNajmenej = null;
                            for (Kozmeticka aktualna : myAgent().dajZoznamVolnychKozmeticok()) {
                                if (aktualna.dajCasCelkovejObsluhy() < najmenej) {
                                    najmenej = aktualna.dajCasCelkovejObsluhy();
                                    ktoraMaNajmenej = aktualna;
                                }
                            }
                            ktoraMaNajmenej.nastavPracovanie(true);
                            this.myAgent().dajZoznamVolnychKozmeticok().remove(ktoraMaNajmenej);
                            this.myAgent().dajZoznamPracujucichKozmeticok().add(ktoraMaNajmenej);
                            ktoraMaNajmenej.zapisZaciatokObsluhy(mySim().currentTime());
                            ((MyMessage)message).nastavZamestnancaKtoryObsluhuje(ktoraMaNajmenej);
                            
                            // pripocitam 0, pretoze zakaznik necakal
                            myAgent().dajStatistikuRadKozmeticky().pripocitajHodnotu(0);
                            // zapisem do statistiky bez chladenia
                            if (mySim().currentTime() < 28800) {
                                myAgent().dajStatistikuRadKozmeticky().pripocitajHodnotuDoCasu(0);
                            }
                            
                            message.setAddressee(myAgent().findAssistant(Id.obsluhaLicenie));
                            startContinualAssistant(message);
                        } else {
                            // zapisem aktualnu dlzku radu do statistiky
                            myAgent().dajStatistikuDlzkaRadKozmeticky().pripocitajVazenuPocetnost(mySim().currentTime(), myAgent().dajRadZakaznikovPredKozmetickami().size());                           
                            ((MyMessage)message).dajObsluhovanehoZakaznika().nastavZaciatokCakaniaRadKozmeticky(mySim().currentTime());
                            myAgent().dajRadZakaznikovPredKozmetickami().enqueue(message);
                            
                            
                            // informujem agenta salonu, ze sa zmenila dlzka radu
                            MyMessage zmena = new MyMessage(mySim());
                            ((MyMessage)zmena).nastavPocetLudiRadKozmeticky(myAgent().dajRadZakaznikovPredKozmetickami().size());
                            zmena.setCode(Mc.zmenaPoctuLudiRadKozmeticky);
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
	public AgentKozmeticok myAgent()
	{
		return (AgentKozmeticok)super.myAgent();
	}

}
