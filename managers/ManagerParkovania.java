package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="4"
public class ManagerParkovania extends Manager
{
	public ManagerParkovania(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentSalonu", id="82", type="Request"
	public void processOdparkujZakaznika(MessageForm message)
	{
	}

	//meta! sender="ObsluhaParkovanie", id="36", type="Finish"
	public void processFinish(MessageForm message)
	{
	}

	//meta! sender="AgentSalonu", id="14", type="Request"
	public void processZaparkujZakaznika(MessageForm message)
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
			processFinish(message);
		break;

		case Mc.zaparkujZakaznika:
                    
                    Zakaznik zakaznik = ((MyMessage)message).dajObsluhovanehoZakaznika();
                    
                    int odkialIde = zakaznik.dajUsek();
                    int ktoryUsekIdeSkusit = zakaznik.dajKtoryRadIdeSkusit();
                    int priKtoromParkovacomMiesteJe = zakaznik.dajPriKtoromParkovacomMiesteJe();
                    int posledneVolneMiesto = zakaznik.dajPosledneVolneMiesto();
                                        
                    if (odkialIde == 3) {
                        if (zakaznik.dajCiPotrebujeOdparkovat()) {
                            if (myAgent().dajParkovacieMiesta()[ktoryUsekIdeSkusit][priKtoromParkovacomMiesteJe] == zakaznik.dajID()) {
                                myAgent().dajParkovacieMiesta()[ktoryUsekIdeSkusit][priKtoromParkovacomMiesteJe] = 0;
                                ((MyMessage) message).dajObsluhovanehoZakaznika().nastavSposobPohybu("Auto"); 
                                ((MyMessage) message).dajObsluhovanehoZakaznika().nastavChceIstPrecZoSystemu(true);
                            }
                        } else {
                            // ide skusit zaparkovat v prvom rade
                            // ak je parkovacie miesto volne, tak si ho zapamataj a chod dalej
                            if (myAgent().dajParkovacieMiesta()[ktoryUsekIdeSkusit][priKtoromParkovacomMiesteJe] == 0 && zakaznik.dajZaparkoval() == false) {
                                // este ide len prvykrat
                                if (zakaznik.dajKolkokratPresielRad1() == 0) {
                                    // je na poslednom parkovacom mieste
                                    if (priKtoromParkovacomMiesteJe <= 14 && priKtoromParkovacomMiesteJe >= 10) {                               
                                        zakaznik.nastavZaparkoval(true);
                                        myAgent().dajParkovacieMiesta()[ktoryUsekIdeSkusit][priKtoromParkovacomMiesteJe] = zakaznik.dajID(); 
                                        ((MyMessage) message).dajObsluhovanehoZakaznika().nastavSposobPohybu("Pešo");
                                        ((MyMessage) message).dajObsluhovanehoZakaznika().zvysSpokojnost(15 - priKtoromParkovacomMiesteJe);                                    
                                        ((MyMessage) message).dajObsluhovanehoZakaznika().nastavCisloMiestaParkovisko(priKtoromParkovacomMiesteJe); 
                                        zakaznik.nastavCisloRaduParkovisko(ktoryUsekIdeSkusit);
                                        myAgent().dajStatistikuSpokojnost().pripocitajHodnotu(((MyMessage) message).dajObsluhovanehoZakaznika().dajSpokojnost());
                                        myAgent().zvysKolkoAutUspesneZaparkovalo();
                                    } else {
                                        // nastav zakaznikovi posledne volne miesto
                                        zakaznik.nastavPosledneVolneMiesto(priKtoromParkovacomMiesteJe);   
                                    }
                                } else {
                                    // uz ide druhykrat
                                    if (posledneVolneMiesto == priKtoromParkovacomMiesteJe) {
                                        zakaznik.nastavZaparkoval(true);
                                        myAgent().dajParkovacieMiesta()[ktoryUsekIdeSkusit][priKtoromParkovacomMiesteJe] = zakaznik.dajID();
                                        ((MyMessage) message).dajObsluhovanehoZakaznika().nastavSposobPohybu("Pešo");
                                        ((MyMessage) message).dajObsluhovanehoZakaznika().zvysSpokojnost(15 - priKtoromParkovacomMiesteJe);                                   
                                        ((MyMessage) message).dajObsluhovanehoZakaznika().nastavCisloMiestaParkovisko(priKtoromParkovacomMiesteJe);
                                        zakaznik.nastavCisloRaduParkovisko(ktoryUsekIdeSkusit);
                                        myAgent().dajStatistikuSpokojnost().pripocitajHodnotu(((MyMessage) message).dajObsluhovanehoZakaznika().dajSpokojnost());
                                        myAgent().zvysKolkoAutUspesneZaparkovalo();
                                    }
                                }
                            }
                        }
                    }
                    else if (odkialIde == 5) {
                        if (zakaznik.dajCiPotrebujeOdparkovat()) {
                            if (myAgent().dajParkovacieMiesta()[ktoryUsekIdeSkusit][priKtoromParkovacomMiesteJe] == zakaznik.dajID()) {
                                myAgent().dajParkovacieMiesta()[ktoryUsekIdeSkusit][priKtoromParkovacomMiesteJe] = 0;                       
                                ((MyMessage) message).dajObsluhovanehoZakaznika().nastavSposobPohybu("Auto");
                                ((MyMessage) message).dajObsluhovanehoZakaznika().nastavChceIstPrecZoSystemu(true);                              
                            } 
                        } else {
                            if (myAgent().dajParkovacieMiesta()[ktoryUsekIdeSkusit][priKtoromParkovacomMiesteJe] == 0 && zakaznik.dajZaparkoval() == false) {
                                // este ide len prvykrat
                                if (zakaznik.dajKolkokratPresielRad2() == 0) {
                                    // je na poslednom parkovacom mieste
                                    if (priKtoromParkovacomMiesteJe <= 14 && priKtoromParkovacomMiesteJe >= 10) {                               
                                        zakaznik.nastavZaparkoval(true);
                                        myAgent().dajParkovacieMiesta()[ktoryUsekIdeSkusit][priKtoromParkovacomMiesteJe] = zakaznik.dajID();           
                                        ((MyMessage) message).dajObsluhovanehoZakaznika().nastavSposobPohybu("Pešo");
                                        ((MyMessage) message).dajObsluhovanehoZakaznika().zvysSpokojnost(15 - priKtoromParkovacomMiesteJe);
                                        ((MyMessage) message).dajObsluhovanehoZakaznika().nastavCisloMiestaParkovisko(priKtoromParkovacomMiesteJe);
                                        zakaznik.nastavCisloRaduParkovisko(ktoryUsekIdeSkusit);
                                        myAgent().dajStatistikuSpokojnost().pripocitajHodnotu(((MyMessage) message).dajObsluhovanehoZakaznika().dajSpokojnost());
                                        myAgent().zvysKolkoAutUspesneZaparkovalo();
                                    } else {
                                        // nastav zakaznikovi posledne volne miesto
                                        zakaznik.nastavPosledneVolneMiesto(priKtoromParkovacomMiesteJe);   
                                    }
                                } else {
                                    // uz ide druhykrat
                                    if (posledneVolneMiesto == priKtoromParkovacomMiesteJe) {
                                        zakaznik.nastavZaparkoval(true);
                                        myAgent().dajParkovacieMiesta()[ktoryUsekIdeSkusit][priKtoromParkovacomMiesteJe] = zakaznik.dajID();
                                        ((MyMessage) message).dajObsluhovanehoZakaznika().nastavSposobPohybu("Pešo");
                                        ((MyMessage) message).dajObsluhovanehoZakaznika().zvysSpokojnost(15 - priKtoromParkovacomMiesteJe);                               
                                        ((MyMessage) message).dajObsluhovanehoZakaznika().nastavCisloMiestaParkovisko(priKtoromParkovacomMiesteJe);
                                        zakaznik.nastavCisloRaduParkovisko(ktoryUsekIdeSkusit);
                                        myAgent().dajStatistikuSpokojnost().pripocitajHodnotu(((MyMessage) message).dajObsluhovanehoZakaznika().dajSpokojnost());
                                        myAgent().zvysKolkoAutUspesneZaparkovalo();
                                    }
                                }
                            }                       
                        }
                    }
                    else if (odkialIde == 7) {
                        if (zakaznik.dajCiPotrebujeOdparkovat()) {
                            if (myAgent().dajParkovacieMiesta()[ktoryUsekIdeSkusit][priKtoromParkovacomMiesteJe] == zakaznik.dajID()) {
                                myAgent().dajParkovacieMiesta()[ktoryUsekIdeSkusit][priKtoromParkovacomMiesteJe] = 0;
                                ((MyMessage) message).dajObsluhovanehoZakaznika().nastavSposobPohybu("Auto");
                                ((MyMessage) message).dajObsluhovanehoZakaznika().nastavChceIstPrecZoSystemu(true);
                            }
                        } else {
                            if (myAgent().dajParkovacieMiesta()[ktoryUsekIdeSkusit][priKtoromParkovacomMiesteJe] == 0 && zakaznik.dajZaparkoval() == false) {
                                // este ide len prvykrat
                                if (zakaznik.dajKolkokratPresielRad3() == 0) {
                                    // je na poslednom parkovacom mieste
                                    if (priKtoromParkovacomMiesteJe <= 14 && priKtoromParkovacomMiesteJe >= 10) {                               
                                        zakaznik.nastavZaparkoval(true);
                                        myAgent().dajParkovacieMiesta()[ktoryUsekIdeSkusit][priKtoromParkovacomMiesteJe] = zakaznik.dajID();        
                                        ((MyMessage) message).dajObsluhovanehoZakaznika().nastavSposobPohybu("Pešo");
                                        ((MyMessage) message).dajObsluhovanehoZakaznika().zvysSpokojnost(15 - priKtoromParkovacomMiesteJe);
                                        ((MyMessage) message).dajObsluhovanehoZakaznika().nastavCisloMiestaParkovisko(priKtoromParkovacomMiesteJe);
                                        zakaznik.nastavCisloRaduParkovisko(ktoryUsekIdeSkusit);
                                        myAgent().dajStatistikuSpokojnost().pripocitajHodnotu(((MyMessage) message).dajObsluhovanehoZakaznika().dajSpokojnost());
                                        myAgent().zvysKolkoAutUspesneZaparkovalo();
                                    } else {
                                        // nastav zakaznikovi posledne volne miesto
                                        zakaznik.nastavPosledneVolneMiesto(priKtoromParkovacomMiesteJe);   
                                    }
                                } else {
                                    // uz ide druhykrat
                                    if (posledneVolneMiesto == priKtoromParkovacomMiesteJe) {
                                        zakaznik.nastavZaparkoval(true);
                                        myAgent().dajParkovacieMiesta()[ktoryUsekIdeSkusit][priKtoromParkovacomMiesteJe] = zakaznik.dajID();
                                        ((MyMessage) message).dajObsluhovanehoZakaznika().nastavSposobPohybu("Pešo");
                                        ((MyMessage) message).dajObsluhovanehoZakaznika().zvysSpokojnost(15 - priKtoromParkovacomMiesteJe);
                                        ((MyMessage) message).dajObsluhovanehoZakaznika().nastavCisloMiestaParkovisko(priKtoromParkovacomMiesteJe);
                                        zakaznik.nastavCisloRaduParkovisko(ktoryUsekIdeSkusit);
                                        myAgent().dajStatistikuSpokojnost().pripocitajHodnotu(((MyMessage) message).dajObsluhovanehoZakaznika().dajSpokojnost());
                                        myAgent().zvysKolkoAutUspesneZaparkovalo();
                                    }
                                }
                            }
                        }
                    }
                    message.setCode(Mc.pohybujZakaznika);
                    ((MyMessage)message).nastavOdKoho(2);
                    response(message);                            
		break;
	
		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentParkovania myAgent()
	{
		return (AgentParkovania)super.myAgent();
	}

}
