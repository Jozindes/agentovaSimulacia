package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="3"
public class ManagerSalonu extends Manager
{
	public ManagerSalonu(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentKozmeticok", id="59", type="Response"
	public void processDajPocetZakaznikovVRadeKozmeticky(MessageForm message)
	{
	}

	//meta! sender="AgentKozmeticok", id="17", type="Response"
	public void processUrobCistenie(MessageForm message)
	{
	}

	//meta! sender="AgentRecepcnych", id="20", type="Response"
	public void processSpracujPlatbu(MessageForm message)
	{
	}

	//meta! sender="AgentKadernicok", id="58", type="Response"
	public void processDajPocetZakaznikovVRadeKadernicky(MessageForm message)
	{
	}

	//meta! sender="AgentModelu", id="47", type="Request"
	public void processObsluzZakaznika(MessageForm message)
	{
	}

	//meta! sender="AgentRecepcnych", id="19", type="Response"
	public void processSpracujObjednavku(MessageForm message)
	{
	}

	//meta! sender="AgentKozmeticok", id="21", type="Response"
	public void processUrobLicenie(MessageForm message)
	{
	}

	//meta! sender="AgentKadernicok", id="16", type="Response"
	public void processUrobUces(MessageForm message)
	{
	}

	//meta! sender="AgentParkovania", id="14", type="Response"
	public void processZaparkujZakaznika(MessageForm message)
	{
	}

	//meta! sender="AgentRecepcnych", id="57", type="Request"
	public void processDajPocetZakaznikovVRadoch(MessageForm message)
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
		case Mc.zaparkujZakaznika:
			processZaparkujZakaznika(message);
		break;
                
                case Mc.pohybujZakaznika:
                    
                    // ak zakaznik prisiel z useku 0, tak ho posli agentovi pohybu, ktory ho posle na usek 1
                    if (((MyMessage)message).dajObsluhovanehoZakaznika().dajUsek() == 0) {
                        message.setCode(Mc.pohybujZakaznika);
                        message.setAddressee(mySim().findAgent(Id.agentPohybu));
                        request(message);
                    }
                    
                    // ak prisiel z useku 1, tak ho posli agentovi pohybu, ktory ho posle na usek 2
                    else if (((MyMessage)message).dajObsluhovanehoZakaznika().dajUsek() == 1) {
                        message.setCode(Mc.pohybujZakaznika);
                        message.setAddressee(mySim().findAgent(Id.agentPohybu));
                        request(message);
                    }
                    
                    // ak prisiel z useku 2, tak ho posli agentovi pohybu, ktory rozhodne ci pojde zakaznik na usek 3 alebo usek 4
                    else if (((MyMessage)message).dajObsluhovanehoZakaznika().dajUsek() == 2) {
                        message.setCode(Mc.pohybujZakaznika);
                        message.setAddressee(mySim().findAgent(Id.agentPohybu));
                        request(message);
                    }
                    
                    // ak prisiel z useku 3
                    else if (((MyMessage)message).dajObsluhovanehoZakaznika().dajUsek() == 3) {
                        if (((MyMessage)message).dajOdKoho() == 1) {
                            message.setCode(Mc.zaparkujZakaznika);
                            message.setAddressee(mySim().findAgent(Id.agentParkovania));
                            request(message);
                        }
                        if (((MyMessage)message).dajOdKoho() == 2) {
                            message.setCode(Mc.pohybujZakaznika);
                            message.setAddressee(mySim().findAgent(Id.agentPohybu));
                            request(message);
                        }
                    }
                    
                    // ak prisiel z useku 4, tak ho posli agentovi pohybu, ktory rozhodne ci pojde zakaznik na usek 5 alebo usek 6
                    else if (((MyMessage)message).dajObsluhovanehoZakaznika().dajUsek() == 4) {
                        message.setCode(Mc.pohybujZakaznika);
                        message.setAddressee(mySim().findAgent(Id.agentPohybu));
                        request(message);
                    }
                    
                    // ak prisiel z useku 5
                    else if (((MyMessage)message).dajObsluhovanehoZakaznika().dajUsek() == 5) {
                        if (((MyMessage)message).dajOdKoho() == 1) {
                            message.setCode(Mc.zaparkujZakaznika);
                            message.setAddressee(mySim().findAgent(Id.agentParkovania));
                            request(message);
                        }
                        if (((MyMessage)message).dajOdKoho() == 2) {
                            message.setCode(Mc.pohybujZakaznika);
                            message.setAddressee(mySim().findAgent(Id.agentPohybu));
                            request(message);
                        }
                    }
                    
                    // ak prisiel z useku 6, tak ho posli agentovi pohybu, ktory ho posle na usek 7
                    else if (((MyMessage)message).dajObsluhovanehoZakaznika().dajUsek() == 6) {
                        message.setCode(Mc.pohybujZakaznika);
                        message.setAddressee(mySim().findAgent(Id.agentPohybu));
                        request(message);
                    }
                    
                    // ak prisiel z useku 7
                    else if (((MyMessage)message).dajObsluhovanehoZakaznika().dajUsek() == 7) {
                        if (((MyMessage)message).dajOdKoho() == 1) {
                            message.setCode(Mc.zaparkujZakaznika);
                            message.setAddressee(mySim().findAgent(Id.agentParkovania));
                            request(message);
                        }
                        if (((MyMessage)message).dajOdKoho() == 2) {
                            message.setCode(Mc.pohybujZakaznika);
                            message.setAddressee(mySim().findAgent(Id.agentPohybu));
                            request(message);
                        }
                    }
                    
                    // ak prisiel z useku 8, tak ho posli agentovi pohybu, ktory ho posle na usek 9
                    else if (((MyMessage)message).dajObsluhovanehoZakaznika().dajUsek() == 8) {
                        message.setCode(Mc.pohybujZakaznika);
                        message.setAddressee(mySim().findAgent(Id.agentPohybu));
                        request(message);
                    }
                    
                    // ak prisiel z useku 9, tak ho posli agentovi pohybu, ktory ho posle na usek 10
                    else if (((MyMessage)message).dajObsluhovanehoZakaznika().dajUsek() == 9) {
                        message.setCode(Mc.pohybujZakaznika);
                        message.setAddressee(mySim().findAgent(Id.agentPohybu));
                        request(message);
                    }
                    
                    // ak prisiel z useku 10, tak ho posli agentovi pohybu, ktory ho posle na usek 1 alebo prec zo systemu
                    else if (((MyMessage)message).dajObsluhovanehoZakaznika().dajUsek() == 10) {
                        // ak nezaparkoval nikde
                        if (((MyMessage)message).dajObsluhovanehoZakaznika().dajChceIstPrecZoSystemu()) {
                            message.setCode(Mc.obsluzZakaznika);
                            message.setAddressee(mySim().findAgent(Id.agentModelu));
                            notice(message);
                        } else {
                            if (((MyMessage)message).dajObsluhovanehoZakaznika().dajZaparkoval() == false) {
                                ((MyMessage) message).dajObsluhovanehoZakaznika().zvysSpokojnost(50);
                                message.setCode(Mc.pohybujZakaznika);
                                message.setAddressee(mySim().findAgent(Id.agentPohybu));
                                request(message); 
                            } else {
                                message.setCode(Mc.dajPocetZakaznikovVRadoch);
                                message.setAddressee(mySim().findAgent(Id.agentSalonu));			
                                notice(message);
                                //System.out.println("Zakaznik s ID: " + ((MyMessage) message).dajObsluhovanehoZakaznika().dajID() + " vstupil do salona.");
                                ((MyMessage) message).dajObsluhovanehoZakaznika().nastavPotrebujeOdparkovat(true);
                                ((MyMessage) message).dajObsluhovanehoZakaznika().nastavCiJeUzVnutri(true);
                                ((MyMessage) message).dajObsluhovanehoZakaznika().nastavZaparkoval(false);                                
                            }
                        }
                    }
		break;
                
                

		case Mc.spracujObjednavku:
                    message.setCode(Mc.spracujObjednavku);
                    message.setAddressee(mySim().findAgent(Id.agentRecepcnych));
                    request(message);
		break;

                // do spravy sa zapisu pocty zakaznikov v radoch pred kadernickami a pred kozmetickami a posle ju agentovi recepcnych, ktory na zaklade tychto udajov zacne obsluhovat zakaznika alebo ho postavi do radu
		case Mc.dajPocetZakaznikovVRadoch:
                    ((MyMessage)message).nastavPocetLudiRadKadernicky(myAgent().dajPocetLudiRadKadernicky());
                    ((MyMessage)message).nastavPocetLudiRadKozmeticky(myAgent().dajPocetLudiRadKozmeticky());
                    message.setCode(Mc.spracujObjednavku);
                    message.setAddressee(mySim().findAgent(Id.agentRecepcnych));
                    request(message);
		break;

		case Mc.spracujPlatbu:
                    message.setCode(Mc.spracujPlatbu);
                    message.setAddressee(mySim().findAgent(Id.agentRecepcnych));
                    request(message);
		break;

		case Mc.obsluzZakaznika:
                    if (((MyMessage) message).dajObsluhovanehoZakaznika().dajCiPotrebujeOdparkovat()) {
                        ((MyMessage) message).dajObsluhovanehoZakaznika().nastavCiJeUzVnutri(false);                       
                        message.setCode(Mc.pohybujZakaznika);
                        message.setAddressee(mySim().findAgent(Id.agentSalonu));
                        notice(message);
                    } else {
                        message.setCode(Mc.obsluzZakaznika);
                        message.setAddressee(mySim().findAgent(Id.agentModelu));
                        notice(message);
                    }
		break;

		case Mc.urobCistenie:
                    message.setCode(Mc.urobCistenie);
                    message.setAddressee(mySim().findAgent(Id.agentKozmeticok));
                    request(message);
		break;

		case Mc.urobUces:
                    message.setCode(Mc.urobUces);
                    message.setAddressee(mySim().findAgent(Id.agentKadernicok));			
                    request(message);
		break;

		case Mc.urobLicenie:
                    message.setCode(Mc.urobLicenie);
                    message.setAddressee(mySim().findAgent(Id.agentKozmeticok));
                    request(message);
		break;
                
                case Mc.zmenaPoctuLudiRadKadernicky:
                    myAgent().nastavPocetLudiRadKadernicky(((MyMessage) message).dajPocetLudiRadKadernicky());
                    ((MyMessage) message).nastavPocetLudiRadKozmeticky(myAgent().dajPocetLudiRadKozmeticky());
                    message.setCode(Mc.zmenaPoctuLudiRadyKadernickyKozmeticky);
                    message.setAddressee(mySim().findAgent(Id.agentRecepcnych));
                    notice(message);
		break;
                
                case Mc.zmenaPoctuLudiRadKozmeticky:
                    myAgent().nastavPocetLudiRadKozmeticky(((MyMessage) message).dajPocetLudiRadKozmeticky());
                    ((MyMessage) message).nastavPocetLudiRadKadernicky(myAgent().dajPocetLudiRadKadernicky());
                    message.setCode(Mc.zmenaPoctuLudiRadyKadernickyKozmeticky);
                    message.setAddressee(mySim().findAgent(Id.agentRecepcnych));
                    notice(message);
		break;
                
                case Mc.inicializuj:                   
                    message.setCode(Mc.inicializuj);
                    message.setAddressee(mySim().findAgent(Id.agentRecepcnych));
                    notice(message);
                    
                    message.setCode(Mc.inicializuj);
                    message.setAddressee(mySim().findAgent(Id.agentKadernicok));
                    notice(message);
                    
                    message.setCode(Mc.inicializuj);
                    message.setAddressee(mySim().findAgent(Id.agentKozmeticok));
                    notice(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentSalonu myAgent()
	{
		return (AgentSalonu)super.myAgent();
	}

}
