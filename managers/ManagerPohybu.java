package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="72"
public class ManagerPohybu extends Manager
{
	public ManagerPohybu(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="ObsluhaPohybU10", id="103", type="Finish"
	public void processFinishObsluhaPohybU10(MessageForm message)
	{
	}

	//meta! sender="ObsluhaPohybU5", id="93", type="Finish"
	public void processFinishObsluhaPohybU5(MessageForm message)
	{
	}

	//meta! sender="ObsluhaPohybU7", id="97", type="Finish"
	public void processFinishObsluhaPohybU7(MessageForm message)
	{
	}

	//meta! sender="ObsluhaPohybU8", id="99", type="Finish"
	public void processFinishObsluhaPohybU8(MessageForm message)
	{
	}

	//meta! sender="ObsluhaPohybU4", id="91", type="Finish"
	public void processFinishObsluhaPohybU4(MessageForm message)
	{
	}

	//meta! sender="ObsluhaPohybU9", id="101", type="Finish"
	public void processFinishObsluhaPohybU9(MessageForm message)
	{
	}

	//meta! sender="ObsluhaPohybU1", id="85", type="Finish"
	public void processFinishObsluhaPohybU1(MessageForm message)
	{
	}

	//meta! sender="ObsluhaPohybU2", id="87", type="Finish"
	public void processFinishObsluhaPohybU2(MessageForm message)
	{
	}

	//meta! sender="ObsluhaPohybU3", id="89", type="Finish"
	public void processFinishObsluhaPohybU3(MessageForm message)
	{
	}

	//meta! sender="ObsluhaPohybU6", id="95", type="Finish"
	public void processFinishObsluhaPohybU6(MessageForm message)
	{
	}

	//meta! sender="AgentSalonu", id="75", type="Request"
	public void processPohybujZakaznika(MessageForm message)
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
		case Mc.finish:
			switch (message.sender().id())
			{
			case Id.obsluhaPohybU10:
                            message.setCode(Mc.pohybujZakaznika);
                            response(message);
			break;

			case Id.obsluhaPohybU5:
                            message.setCode(Mc.pohybujZakaznika);
                            ((MyMessage)message).nastavOdKoho(1);
                            response(message);
			break;

			case Id.obsluhaPohybU7:
                            message.setCode(Mc.pohybujZakaznika);
                            ((MyMessage)message).nastavOdKoho(1);
                            response(message);
			break;

			case Id.obsluhaPohybU8:
                            message.setCode(Mc.pohybujZakaznika);
                            response(message);
			break;

			case Id.obsluhaPohybU4:
                            message.setCode(Mc.pohybujZakaznika);
                            response(message);
			break;

			case Id.obsluhaPohybU9:
                            message.setCode(Mc.pohybujZakaznika);
                            response(message);
			break;

			case Id.obsluhaPohybU1:
                            message.setCode(Mc.pohybujZakaznika);
                            response(message);
			break;

			case Id.obsluhaPohybU2:
                            message.setCode(Mc.pohybujZakaznika);
                            response(message);
			break;

			case Id.obsluhaPohybU3:
                            message.setCode(Mc.pohybujZakaznika);
                            ((MyMessage)message).nastavOdKoho(1);
                            response(message);
			break;

			case Id.obsluhaPohybU6:
                            message.setCode(Mc.pohybujZakaznika);
                            response(message);
			break;
			}
		break;

		case Mc.pohybujZakaznika:
                    int odkialIde = ((MyMessage)message).dajObsluhovanehoZakaznika().dajUsek();
                    
                        // zakaznik prichadza z useku 0
                        if (odkialIde == 0) {
                            // nastav zakaznikovi rychlost pohybu peso
                            ((MyMessage)message).dajObsluhovanehoZakaznika().nastavRychlostPeso(myAgent().dajHodnotuRychlostPeso());
                            
                            // nastav novy usek
                            ((MyMessage)message).dajObsluhovanehoZakaznika().nastavUsek(1);
                            // nastav cas vstupu na usek
                            ((MyMessage)message).dajObsluhovanehoZakaznika().nastavCasVstupuNaUsek(mySim().currentTime());
                            message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU1));
                            startContinualAssistant(message);
                        }
                        
                        // zakaznik prichadza z useku 1
                        else if (odkialIde == 1) {
                            // nastav novy usek
                            ((MyMessage)message).dajObsluhovanehoZakaznika().nastavUsek(2);
                            // nastav cas vstupu na usek
                            ((MyMessage)message).dajObsluhovanehoZakaznika().nastavCasVstupuNaUsek(mySim().currentTime());
                            message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU2));
                            startContinualAssistant(message);
                        }
                        
                        // zakaznik prichadza z useku 2
                        else if (odkialIde == 2) {
                            Zakaznik zakaznik = ((MyMessage) message).dajObsluhovanehoZakaznika();
                            if (zakaznik.dajKtoryRadIdeSkusit() == 0) {
                                // nastav novy usek
                                ((MyMessage)message).dajObsluhovanehoZakaznika().nastavUsek(3);
                                ((MyMessage)message).dajObsluhovanehoZakaznika().nastavPriKtoromParkovacomMiesteJe(0);
                                // nastav cas vstupu na usek
                                ((MyMessage)message).dajObsluhovanehoZakaznika().nastavCasVstupuNaUsek(mySim().currentTime());
                                message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU3));
                                startContinualAssistant(message);
                            } else {
                                // nastav novy usek
                                ((MyMessage)message).dajObsluhovanehoZakaznika().nastavUsek(4);
                                // nastav cas vstupu na usek
                                ((MyMessage)message).dajObsluhovanehoZakaznika().nastavCasVstupuNaUsek(mySim().currentTime());
                                message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU4));
                                startContinualAssistant(message);
                            }                 
                        }
                        
                        // zakaznik prichadza z useku 3
                        else if (odkialIde == 3) {
                            Zakaznik vytiahnuty = ((MyMessage)message).dajObsluhovanehoZakaznika();
                            int noveParkovacieMiesto = vytiahnuty.dajPriKtoromParkovacomMiesteJe() + 1;
                            // este prechadza prvy rad
                            if (noveParkovacieMiesto >= 0 && noveParkovacieMiesto <= 14) {
                                vytiahnuty.nastavPriKtoromParkovacomMiesteJe(noveParkovacieMiesto);
                                vytiahnuty.nastavUsek(3);
                                vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU3));
                                startContinualAssistant(message);
                            } else {
                                if (vytiahnuty.dajChceIstPrecZoSystemu()) {
                                    vytiahnuty.nastavUsek(10);
                                    vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                    message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU10));
                                    startContinualAssistant(message);
                                } else {
                                    vytiahnuty.zvysKolkokratPresielRad1();
                                    // prvy rad presiel raz a bolo tam volne miesto, to znamena, ze tadial prejde este raz
                                    if (vytiahnuty.dajKolkokratPresielRad1() == 1 && vytiahnuty.dajPosledneVolneMiesto() != -1) {
                                        vytiahnuty.nastavPriKtoromParkovacomMiesteJe(-1);
                                        vytiahnuty.nastavKtoryRadIdeSkusit(0);
                                        vytiahnuty.nastavUsek(10);
                                        vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                        message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU10));
                                        startContinualAssistant(message);
                                        
                                    // prvy rad presiel raz a nenasiel tam volne miesto
                                    } else if (vytiahnuty.dajKolkokratPresielRad1() == 1 && vytiahnuty.dajPosledneVolneMiesto() == -1) { 
                                        if (myAgent().dajPocetParkovacichRadov() > 1) {
                                            vytiahnuty.nastavPriKtoromParkovacomMiesteJe(-1);
                                            vytiahnuty.nastavKtoryRadIdeSkusit(1);
                                            vytiahnuty.nastavPosledneVolneMiesto(-1);
                                            vytiahnuty.nastavUsek(10);
                                            vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                            message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU10));
                                            startContinualAssistant(message);
                                        } else {
                                            vytiahnuty.nastavChceIstPrecZoSystemu(true);
                                            vytiahnuty.nastavUsek(10);
                                            vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                            message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU10));
                                            startContinualAssistant(message);
                                        }                                                                            
                                    } else if (vytiahnuty.dajKolkokratPresielRad1() == 2 && vytiahnuty.dajZaparkoval() == false) {
                                        vytiahnuty.nastavChceIstPrecZoSystemu(true);
                                        vytiahnuty.nastavUsek(10);
                                        vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                        message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU10));
                                        startContinualAssistant(message);
                                    } else if (vytiahnuty.dajKolkokratPresielRad1() == 2 && vytiahnuty.dajZaparkoval()) {
                                        vytiahnuty.nastavUsek(10);
                                        vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                        message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU10));
                                        startContinualAssistant(message);
                                    }
                                }
                            }                      
                        }
                        
                        // zakaznik prichadza z useku 4
                        else if (odkialIde == 4) {
                            Zakaznik zakaznik = ((MyMessage) message).dajObsluhovanehoZakaznika();
                            if (zakaznik.dajKtoryRadIdeSkusit() == 1) {
                                // nastav novy usek
                                ((MyMessage)message).dajObsluhovanehoZakaznika().nastavUsek(5);
                                ((MyMessage)message).dajObsluhovanehoZakaznika().nastavPriKtoromParkovacomMiesteJe(0);
                                // nastav cas vstupu na usek
                                ((MyMessage)message).dajObsluhovanehoZakaznika().nastavCasVstupuNaUsek(mySim().currentTime());
                                message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU5));
                                startContinualAssistant(message);
                            } else {
                                // nastav novy usek
                                ((MyMessage)message).dajObsluhovanehoZakaznika().nastavUsek(6);
                                // nastav cas vstupu na usek
                                ((MyMessage)message).dajObsluhovanehoZakaznika().nastavCasVstupuNaUsek(mySim().currentTime());
                                message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU6));
                                startContinualAssistant(message);
                            }                 
                        }
                        
                        // zakaznik prichadza z useku 5
                        else if (odkialIde == 5) {
                            Zakaznik vytiahnuty = ((MyMessage)message).dajObsluhovanehoZakaznika();
                            int noveParkovacieMiesto = vytiahnuty.dajPriKtoromParkovacomMiesteJe() + 1;
                            // este prechadza prvy rad
                            if (noveParkovacieMiesto >= 0 && noveParkovacieMiesto <= 14) {
                                vytiahnuty.nastavPriKtoromParkovacomMiesteJe(noveParkovacieMiesto);
                                vytiahnuty.nastavUsek(5);
                                vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU5));
                                startContinualAssistant(message);
                            } else {
                                if (vytiahnuty.dajChceIstPrecZoSystemu()) {
                                    vytiahnuty.nastavUsek(9);
                                    vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                    message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU9));
                                    startContinualAssistant(message);
                                } else {
                                    vytiahnuty.zvysKolkokratPresielRad2();
                                    // prvy rad presiel raz a bolo tam volne miesto, to znamena, ze tadial prejde este raz
                                    if (vytiahnuty.dajKolkokratPresielRad2() == 1 && vytiahnuty.dajPosledneVolneMiesto() != -1) {
                                        vytiahnuty.nastavPriKtoromParkovacomMiesteJe(-1);
                                        vytiahnuty.nastavKtoryRadIdeSkusit(1);
                                        vytiahnuty.nastavUsek(9);
                                        vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                        message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU9));
                                        startContinualAssistant(message);
                                        
                                    // prvy rad presiel raz a nenasiel tam volne miesto
                                    } else if (vytiahnuty.dajKolkokratPresielRad2() == 1 && vytiahnuty.dajPosledneVolneMiesto() == -1) { 
                                        if (myAgent().dajPocetParkovacichRadov() > 2) {
                                            vytiahnuty.nastavPriKtoromParkovacomMiesteJe(-1);
                                            vytiahnuty.nastavKtoryRadIdeSkusit(2);
                                            vytiahnuty.nastavPosledneVolneMiesto(-1);
                                            vytiahnuty.nastavUsek(9);
                                            vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                            message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU9));
                                            startContinualAssistant(message);
                                        } else {
                                            vytiahnuty.nastavChceIstPrecZoSystemu(true);
                                            vytiahnuty.nastavUsek(9);
                                            vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                            message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU9));
                                            startContinualAssistant(message);
                                        }                                                                            
                                    } else if (vytiahnuty.dajKolkokratPresielRad2() == 2 && vytiahnuty.dajZaparkoval() == false) {
                                        vytiahnuty.nastavChceIstPrecZoSystemu(true);
                                        vytiahnuty.nastavUsek(9);
                                        vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                        message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU9));
                                        startContinualAssistant(message);
                                    } else if (vytiahnuty.dajKolkokratPresielRad2() == 2 && vytiahnuty.dajZaparkoval()) {
                                        vytiahnuty.nastavUsek(9);
                                        vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                        message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU9));
                                        startContinualAssistant(message);
                                    }
                                }
                            }                      
                        }
                        
                        // zakaznik prichadza z useku 6
                        else if (odkialIde == 6) {
                            // nastav novy usek
                            ((MyMessage)message).dajObsluhovanehoZakaznika().nastavUsek(7);
                            ((MyMessage)message).dajObsluhovanehoZakaznika().nastavPriKtoromParkovacomMiesteJe(0);
                            // nastav cas vstupu na usek
                            ((MyMessage)message).dajObsluhovanehoZakaznika().nastavCasVstupuNaUsek(mySim().currentTime());
                            message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU7));
                            startContinualAssistant(message);             
                        }
                        
                        // zakaznik prichadza z useku 7
                        else if (odkialIde == 7) {
                            Zakaznik vytiahnuty = ((MyMessage)message).dajObsluhovanehoZakaznika();
                            int noveParkovacieMiesto = vytiahnuty.dajPriKtoromParkovacomMiesteJe() + 1;
                            // este prechadza prvy rad
                            if (noveParkovacieMiesto >= 0 && noveParkovacieMiesto <= 14) {
                                vytiahnuty.nastavPriKtoromParkovacomMiesteJe(noveParkovacieMiesto);
                                vytiahnuty.nastavUsek(7);
                                vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU7));
                                startContinualAssistant(message);
                            } else {
                                if (vytiahnuty.dajChceIstPrecZoSystemu()) {
                                    vytiahnuty.nastavUsek(8);
                                    vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                    message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU8));
                                    startContinualAssistant(message);
                                } else {
                                    vytiahnuty.zvysKolkokratPresielRad3();
                                    // prvy rad presiel raz a bolo tam volne miesto, to znamena, ze tadial prejde este raz
                                    if (vytiahnuty.dajKolkokratPresielRad3() == 1 && vytiahnuty.dajPosledneVolneMiesto() != -1) {
                                        vytiahnuty.nastavPriKtoromParkovacomMiesteJe(-1);
                                        vytiahnuty.nastavKtoryRadIdeSkusit(2);
                                        vytiahnuty.nastavUsek(8);
                                        vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                        message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU8));
                                        startContinualAssistant(message);
                                        
                                    // prvy rad presiel raz a nenasiel tam volne miesto
                                    } else if (vytiahnuty.dajKolkokratPresielRad3() == 1 && vytiahnuty.dajPosledneVolneMiesto() == -1) { 
                                        if (myAgent().dajPocetParkovacichRadov() > 3) {
                                            vytiahnuty.nastavPriKtoromParkovacomMiesteJe(-1);
                                            vytiahnuty.nastavKtoryRadIdeSkusit(3);
                                            vytiahnuty.nastavPosledneVolneMiesto(-1);
                                            vytiahnuty.nastavUsek(8);
                                            vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                            message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU8));
                                            startContinualAssistant(message);
                                        } else {
                                            vytiahnuty.nastavChceIstPrecZoSystemu(true);
                                            vytiahnuty.nastavUsek(8);
                                            vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                            message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU8));
                                            startContinualAssistant(message);
                                        }                                                                            
                                    } else if (vytiahnuty.dajKolkokratPresielRad3() == 2 && vytiahnuty.dajZaparkoval() == false) {
                                        vytiahnuty.nastavChceIstPrecZoSystemu(true);
                                        vytiahnuty.nastavUsek(8);
                                        vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                        message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU8));
                                        startContinualAssistant(message);
                                    } else if (vytiahnuty.dajKolkokratPresielRad3() == 2 && vytiahnuty.dajZaparkoval()) {
                                        vytiahnuty.nastavUsek(8);
                                        vytiahnuty.nastavCasVstupuNaUsek(mySim().currentTime());
                                        message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU8));
                                        startContinualAssistant(message);
                                    }
                                }
                            }                      
                        }
                        
                        // zakaznik prichadza z useku 8
                        else if (odkialIde == 8) {
                            // nastav novy usek
                            ((MyMessage)message).dajObsluhovanehoZakaznika().nastavUsek(9);
                            // nastav cas vstupu na usek
                            ((MyMessage)message).dajObsluhovanehoZakaznika().nastavCasVstupuNaUsek(mySim().currentTime());
                            message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU9));
                            startContinualAssistant(message);
                        }
                        
                        // zakaznik prichadza z useku 9
                        else if (odkialIde == 9) {
                            // nastav novy usek
                            ((MyMessage)message).dajObsluhovanehoZakaznika().nastavUsek(10);
                            // nastav cas vstupu na usek
                            ((MyMessage)message).dajObsluhovanehoZakaznika().nastavCasVstupuNaUsek(mySim().currentTime());
                            message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU10));
                            startContinualAssistant(message);
                        }
                        
                        // zakaznik prichadza z useku 10
                        else if (odkialIde == 10) {
                            // nastav novy usek
                            ((MyMessage)message).dajObsluhovanehoZakaznika().nastavUsek(1);
                            // nastav cas vstupu na usek
                            ((MyMessage)message).dajObsluhovanehoZakaznika().nastavCasVstupuNaUsek(mySim().currentTime());
                            message.setAddressee(myAgent().findAssistant(Id.obsluhaPohybU1));
                            startContinualAssistant(message);
                        }
                    }		
	}
	//meta! tag="end"

	@Override
	public AgentPohybu myAgent()
	{
		return (AgentPohybu)super.myAgent();
	}

}
