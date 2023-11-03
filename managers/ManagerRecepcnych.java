package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;
import zamestnanci.Recepcna;

//meta! id="5"
public class ManagerRecepcnych extends Manager {
    
    public ManagerRecepcnych(int id, Simulation mySim, Agent myAgent) {
	super(id, mySim, myAgent);
    }

    @Override
    public void prepareReplication() {
	super.prepareReplication();
	// Setup component for the next replication

	if (petriNet() != null) {
            petriNet().clear();
        }
    }

    @Override
    public void processMessage(MessageForm message) {	
        
        switch (message.code()) {
            
            case Mc.spracujObjednavku:  
                // kontrola, ci je v rade pred kadernickami a pred kozmetickami menej ako 11 ludi
                if (((MyMessage)message).dajPocetLudiRadKadernicky() + ((MyMessage)message).dajPocetLudiRadKozmeticky() <= 10) {               
                    // najskor najdem recepcnu, ktora pracovala najmenej              
                    if (!myAgent().dajZoznamVolnychRecepcnych().isEmpty()) {
                        double najmenej = Double.MAX_VALUE;
                        Recepcna ktoraMaNajmenej = null;
                        for (Recepcna aktualna : myAgent().dajZoznamVolnychRecepcnych()) {
                            if (aktualna.dajCasCelkovejObsluhy() < najmenej) {
                                najmenej = aktualna.dajCasCelkovejObsluhy();
                                ktoraMaNajmenej = aktualna;
                            }
                        }
                        
                        // v premennej "ktoraMaNajmenej" je recepcna, ktora pracovala najmenej
                        ktoraMaNajmenej.nastavPracovanie(true);
                        this.myAgent().dajZoznamVolnychRecepcnych().remove(ktoraMaNajmenej);
                        this.myAgent().dajZoznamPracujucichRecepcnych().add(ktoraMaNajmenej);
                        ktoraMaNajmenej.zapisZaciatokObsluhy(mySim().currentTime());
                        
                        // zakaznik necakal, preto pripocitam do statistiky 0
                        myAgent().dajStatistikuRadRecepcneObjednavka().pripocitajHodnotu(0);
                        // zapisem do statistiky bez chladenia
                        if (mySim().currentTime() < 28800) {
                            myAgent().dajStatistikuRadRecepcneObjednavka().pripocitajHodnotuDoCasu(0);
                        }
                        
                        ((MyMessage)message).nastavZamestnancaKtoryObsluhuje(ktoraMaNajmenej);
                        message.setAddressee(myAgent().findAssistant(Id.obsluhaObjednavka));
                        startContinualAssistant(message);
                    } else {
                        // ak nenajdem volnu recepcnu, tak zakaznika musim postavit do radu
                        myAgent().dajStatistikuDlzkaRadRecepcneObjednavka().pripocitajVazenuPocetnost(mySim().currentTime(), myAgent().dajRadZakaznikovPredRecepcnymiObjednavka().size());                       
                        ((MyMessage)message).dajObsluhovanehoZakaznika().nastavZaciatokCakaniaRadRecepcneObjednavka(mySim().currentTime());
                        myAgent().dajRadZakaznikovPredRecepcnymiObjednavka().enqueue(message);
                    }
                // ak je v rade pred kadernickami a kozmetickami viac ako 10 ludi, tak postavim zakaznika do radu
                } else {
                    myAgent().dajStatistikuDlzkaRadRecepcneObjednavka().pripocitajVazenuPocetnost(mySim().currentTime(), myAgent().dajRadZakaznikovPredRecepcnymiObjednavka().size());                    
                    ((MyMessage)message).dajObsluhovanehoZakaznika().nastavZaciatokCakaniaRadRecepcneObjednavka(mySim().currentTime());
                    myAgent().dajRadZakaznikovPredRecepcnymiObjednavka().enqueue(message);
                }
            
            break;

            case Mc.finish:
                switch (message.sender().id()) {
                    case Id.obsluhaObjednavka:
                                                   
                        // uvolnim pracovnicku
                        Recepcna ktoraPracovala = (Recepcna)((MyMessage)message).dajZamestnancaKtoryObsluhuje();
                        ktoraPracovala.nastavPracovanie(false);
                        this.myAgent().dajZoznamVolnychRecepcnych().add(ktoraPracovala);
                        this.myAgent().dajZoznamPracujucichRecepcnych().remove(ktoraPracovala);
                        // zapisem do statistiky kolko pracovala
                        myAgent().dajStatistikuDobaObsluhyRecepcne().pripocitajHodnotu(mySim().currentTime() - ktoraPracovala.dajZaciatokObsluhy());
                        // zapisem do statistiky bez chladenia
                        if (mySim().currentTime() < 28800) {
                            myAgent().dajStatistikuDobaObsluhyRecepcne().pripocitajHodnotuDoCasu(mySim().currentTime() - ktoraPracovala.dajZaciatokObsluhy());
                        }
                        ktoraPracovala.zapisKoniecObsluhy(mySim().currentTime());
                        
                            
                        // ak je niekto v rade na zaplatenie, tak naplanujem platbu
                        if (!myAgent().dajRadZakaznikovPredRecepcnymiPlatba().isEmpty()) {
                            // zapisem dlzku radu do statistiky
                            myAgent().dajStatistikuDlzkaRadRecepcnePlatba().pripocitajVazenuPocetnost(mySim().currentTime(), myAgent().dajRadZakaznikovPredRecepcnymiPlatba().size());                           
                            // vytiahnem spravu
                            MyMessage nextMessage = (MyMessage)myAgent().dajRadZakaznikovPredRecepcnymiPlatba().dequeue();
                            // zakaznikovi zapisem koniec cakania
                            nextMessage.dajObsluhovanehoZakaznika().nastavKoniecCakaniaRadRecepcnePlatba(mySim().currentTime());                           
                            // zapisem do statistik, kolko dany zakaznik cakal v rade pred recepcnymi
                            myAgent().dajStatistikuRadRecepcnePlatba().pripocitajHodnotu(nextMessage.dajObsluhovanehoZakaznika().dajKoniecCakaniaRadRecepcnePlatba() - nextMessage.dajObsluhovanehoZakaznika().dajZaciatokCakaniaRadRecepcnePlatba());
                            // zapisem do statistiky bez chladenia
                            if (mySim().currentTime() < 28800) {
                                myAgent().dajStatistikuRadRecepcnePlatba().pripocitajHodnotuDoCasu(nextMessage.dajObsluhovanehoZakaznika().dajKoniecCakaniaRadRecepcnePlatba() - nextMessage.dajObsluhovanehoZakaznika().dajZaciatokCakaniaRadRecepcnePlatba());
                            }
                            
                            // uvolnena pracovnicka zacina zase pracovat
                            ktoraPracovala.nastavPracovanie(true);
                            ktoraPracovala.zapisZaciatokObsluhy(mySim().currentTime());
                            this.myAgent().dajZoznamVolnychRecepcnych().remove(ktoraPracovala);
                            this.myAgent().dajZoznamPracujucichRecepcnych().add(ktoraPracovala);                          
                            nextMessage.nastavZamestnancaKtoryObsluhuje(ktoraPracovala);
                                                     
                            nextMessage.setAddressee(myAgent().findAssistant(Id.obsluhaPlatba));
                            startContinualAssistant(nextMessage);
                        } else {                            
                            // ak je niekto v rade na objednavku, tak naplanujem objednavku
                            if (!myAgent().dajRadZakaznikovPredRecepcnymiObjednavka().isEmpty()) {
                                
                                if (mySim().currentTime() <= 28800) {
                                
                                    // kontrola, ci je v rade opred kadernickami a kozmetickami menej ako 11 ludi
                                    if (((MyMessage)message).dajPocetLudiRadKadernicky() + ((MyMessage)message).dajPocetLudiRadKozmeticky() <= 10) {
                                        // zapisem aktualnu dlzku radu do statistiky
                                        myAgent().dajStatistikuDlzkaRadRecepcneObjednavka().pripocitajVazenuPocetnost(mySim().currentTime(), myAgent().dajRadZakaznikovPredRecepcnymiObjednavka().size());                                                                           
                                        // vytiahnem spravu
                                        MyMessage nextMessage = (MyMessage)myAgent().dajRadZakaznikovPredRecepcnymiObjednavka().dequeue();
                                        // zakaznikovi zapisem koniec cakania
                                        nextMessage.dajObsluhovanehoZakaznika().nastavKoniecCakaniaRadRecepcneObjednavka(mySim().currentTime());  
                                        // zapisem do statistik, kolko dany zakaznik cakal v rade pred recepcnymi
                                        myAgent().dajStatistikuRadRecepcneObjednavka().pripocitajHodnotu(nextMessage.dajObsluhovanehoZakaznika().dajKoniecCakaniaRadRecepcneObjednavka()- nextMessage.dajObsluhovanehoZakaznika().dajZaciatokCakaniaRadRecepcneObjednavka());
                                        // zapisem do statistiky bez chladenia
                                        if (mySim().currentTime() < 28800) {
                                            myAgent().dajStatistikuRadRecepcneObjednavka().pripocitajHodnotuDoCasu(nextMessage.dajObsluhovanehoZakaznika().dajKoniecCakaniaRadRecepcneObjednavka()- nextMessage.dajObsluhovanehoZakaznika().dajZaciatokCakaniaRadRecepcneObjednavka());
                                        }
                                        
                                        // uvolnena pracovnicka zacina zase pracovat
                                        ktoraPracovala.nastavPracovanie(true);
                                        ktoraPracovala.zapisZaciatokObsluhy(mySim().currentTime());
                                        this.myAgent().dajZoznamVolnychRecepcnych().remove(ktoraPracovala);
                                        this.myAgent().dajZoznamPracujucichRecepcnych().add(ktoraPracovala);
                                        nextMessage.nastavZamestnancaKtoryObsluhuje(ktoraPracovala);

                                        nextMessage.setAddressee(myAgent().findAssistant(Id.obsluhaObjednavka));
                                        startContinualAssistant(nextMessage);
                                    }
                                } else {
                                    double dlzkaRadu = myAgent().dajRadZakaznikovPredRecepcnymiObjednavka().size();
                                    myAgent().vyprazdnitRadRecepcneObjednavka();
                                    myAgent().nastavKolkoLudiSomPoslalPrec(dlzkaRadu);
                                }
                            }
                        }
                           
                        // ak si chce zakaznik po spracovani objednavky upravit uces, tak mu ho naplanujem
                        if (((MyMessage)message).dajObsluhovanehoZakaznika().chceUpravitUces()) {
                            message.setCode(Mc.urobUces);
                            response(message);
                        } else {
                            if (((MyMessage)message).dajObsluhovanehoZakaznika().chceLicenie()) {
                                // ak chce zakaznik cistenie a aj licenie, tak naplanujem najskor cistenie
                                if (((MyMessage)message).dajObsluhovanehoZakaznika().chceCisteniePleti()) {
                                    message.setCode(Mc.urobCistenie);
                                    response(message);
                                } else {
                                    // ak chce iba licenie
                                    message.setCode(Mc.urobLicenie);
                                    response(message);
                                }
                            }
                        }
                    break;

                    case Id.obsluhaPlatba:
                    
                        // uvolnim pracovnicku
                        Recepcna ktoraPracovalaP = (Recepcna) ((MyMessage)message).dajZamestnancaKtoryObsluhuje();
                        ktoraPracovalaP.nastavPracovanie(false);
                        this.myAgent().dajZoznamVolnychRecepcnych().add(ktoraPracovalaP);
                        this.myAgent().dajZoznamPracujucichRecepcnych().remove(ktoraPracovalaP);
                        // zapisem do statistiky kolko pracovala
                        myAgent().dajStatistikuDobaObsluhyRecepcne().pripocitajHodnotu(mySim().currentTime() - ktoraPracovalaP.dajZaciatokObsluhy());
                        // zapisem do statistiky bez chladenia
                        if (mySim().currentTime() < 28800) {
                            myAgent().dajStatistikuDobaObsluhyRecepcne().pripocitajHodnotuDoCasu(mySim().currentTime() - ktoraPracovalaP.dajZaciatokObsluhy());
                        }
                        ktoraPracovalaP.zapisKoniecObsluhy(mySim().currentTime());
                        
                            
                        // ak je niekto v rade na platbu
                        if (!myAgent().dajRadZakaznikovPredRecepcnymiPlatba().isEmpty()) {
                            // zapisem aktualnu dlzku radu do statistiky
                            myAgent().dajStatistikuDlzkaRadRecepcnePlatba().pripocitajVazenuPocetnost(mySim().currentTime(), myAgent().dajRadZakaznikovPredRecepcnymiPlatba().size());                           
                            // vytiahnem spravu
                            MyMessage nextMessage = (MyMessage)myAgent().dajRadZakaznikovPredRecepcnymiPlatba().dequeue();
                            // zakaznikovi zapisem koniec cakania
                            nextMessage.dajObsluhovanehoZakaznika().nastavKoniecCakaniaRadRecepcnePlatba(mySim().currentTime());                           
                            // zapisem do statistik, kolko dany zakaznik cakal v rade pred recepcnymi
                            myAgent().dajStatistikuRadRecepcnePlatba().pripocitajHodnotu(nextMessage.dajObsluhovanehoZakaznika().dajKoniecCakaniaRadRecepcnePlatba() - nextMessage.dajObsluhovanehoZakaznika().dajZaciatokCakaniaRadRecepcnePlatba());
                            // zapisem do statistiky bez chladenia
                            if (mySim().currentTime() < 28800) {
                                myAgent().dajStatistikuRadRecepcnePlatba().pripocitajHodnotuDoCasu(nextMessage.dajObsluhovanehoZakaznika().dajKoniecCakaniaRadRecepcnePlatba() - nextMessage.dajObsluhovanehoZakaznika().dajZaciatokCakaniaRadRecepcnePlatba());
                            }
                            
                            // uvolnena pracovnicka zacina zase pracovat
                            ktoraPracovalaP.nastavPracovanie(true);
                            ktoraPracovalaP.zapisZaciatokObsluhy(mySim().currentTime());
                            this.myAgent().dajZoznamVolnychRecepcnych().remove(ktoraPracovalaP);
                            this.myAgent().dajZoznamPracujucichRecepcnych().add(ktoraPracovalaP);
                            nextMessage.nastavZamestnancaKtoryObsluhuje(ktoraPracovalaP);
                            
                            nextMessage.setAddressee(myAgent().findAssistant(Id.obsluhaPlatba));
                            startContinualAssistant(nextMessage);
                        } else {
                            // ak je niekto v rade na objednavku
                            if (!myAgent().dajRadZakaznikovPredRecepcnymiObjednavka().isEmpty()) {
                                
                                if (mySim().currentTime() < 28800) {
                                
                                    // kontrola, ci je v rade opred kadernickami a kozmetickami menej ako 11 ludi
                                    if (((MyMessage)message).dajPocetLudiRadKadernicky() + ((MyMessage)message).dajPocetLudiRadKozmeticky() <= 10) {
                                        // zapisem aktualnu dlzku radu do statistiky
                                        myAgent().dajStatistikuDlzkaRadRecepcneObjednavka().pripocitajVazenuPocetnost(mySim().currentTime(), myAgent().dajRadZakaznikovPredRecepcnymiObjednavka().size());                                       
                                        // vytiahnem spravu
                                        MyMessage nextMessage = (MyMessage)myAgent().dajRadZakaznikovPredRecepcnymiObjednavka().dequeue();                               
                                        // zakaznikovi zapisem koniec cakania
                                        nextMessage.dajObsluhovanehoZakaznika().nastavKoniecCakaniaRadRecepcneObjednavka(mySim().currentTime());  
                                        // zapisem do statistik, kolko dany zakaznik cakal v rade pred recepcnymi
                                        myAgent().dajStatistikuRadRecepcneObjednavka().pripocitajHodnotu(nextMessage.dajObsluhovanehoZakaznika().dajKoniecCakaniaRadRecepcneObjednavka()- nextMessage.dajObsluhovanehoZakaznika().dajZaciatokCakaniaRadRecepcneObjednavka());
                                        // zapisem do statistiky bez chladenia
                                        if (mySim().currentTime() < 28800) {
                                            myAgent().dajStatistikuRadRecepcneObjednavka().pripocitajHodnotuDoCasu(nextMessage.dajObsluhovanehoZakaznika().dajKoniecCakaniaRadRecepcneObjednavka()- nextMessage.dajObsluhovanehoZakaznika().dajZaciatokCakaniaRadRecepcneObjednavka());
                                        }
                                        // uvolnena pracovnicka zacina zase pracovat
                                        ktoraPracovalaP.nastavPracovanie(true);
                                        ktoraPracovalaP.zapisZaciatokObsluhy(mySim().currentTime());
                                        this.myAgent().dajZoznamVolnychRecepcnych().remove(ktoraPracovalaP);
                                        this.myAgent().dajZoznamPracujucichRecepcnych().add(ktoraPracovalaP);                               
                                        nextMessage.nastavZamestnancaKtoryObsluhuje(ktoraPracovalaP);

                                        nextMessage.setAddressee(myAgent().findAssistant(Id.obsluhaObjednavka));
                                        startContinualAssistant(nextMessage);
                                    }
                                } else {
                                    double dlzkaRadu = myAgent().dajRadZakaznikovPredRecepcnymiObjednavka().size();
                                    myAgent().vyprazdnitRadRecepcneObjednavka();
                                    myAgent().nastavKolkoLudiSomPoslalPrec(dlzkaRadu);
                                }
                            }
                        }
                        
                        // po spracovani platby naplanujem odchod zakaznika
                        ((MyMessage)message).dajObsluhovanehoZakaznika().nastavKtoryRadIdeSkusit(((MyMessage)message).dajObsluhovanehoZakaznika().dajCisloRaduParkovisko());
                        message.setCode(Mc.obsluzZakaznika);
                        //System.out.println("Zakaznik s ID: " + ((MyMessage)message).dajObsluhovanehoZakaznika().dajID() + " zaplatil a ide na parkovisko do radu" + ((MyMessage)message).dajObsluhovanehoZakaznika().dajKtoryRadIdeSkusit());
                        response(message);
                    break;
		}
            break;

            case Mc.spracujPlatbu:
                
                // najde recepcnu, ktora pracovala najmenej
                if (!myAgent().dajZoznamVolnychRecepcnych().isEmpty()) {
                    double najmenej = Double.MAX_VALUE;
                    Recepcna ktoraMaNajmenej = null;
                    for (Recepcna aktualna : myAgent().dajZoznamVolnychRecepcnych()) {
                        if (aktualna.dajCasCelkovejObsluhy() < najmenej) {
                            najmenej = aktualna.dajCasCelkovejObsluhy();
                            ktoraMaNajmenej = aktualna;
                        }
                    }
                    // v premennej "ktoraMaNajmenej" je recepcna, ktora pracovala najmenej
                    ktoraMaNajmenej.nastavPracovanie(true);
                    this.myAgent().dajZoznamVolnychRecepcnych().remove(ktoraMaNajmenej);
                    this.myAgent().dajZoznamPracujucichRecepcnych().add(ktoraMaNajmenej);
                    ktoraMaNajmenej.zapisZaciatokObsluhy(mySim().currentTime());
                    
                    myAgent().dajStatistikuRadRecepcnePlatba().pripocitajHodnotu(0);
                    // zapisem do statistiky bez chladenia
                    if (mySim().currentTime() < 28800) {
                        myAgent().dajStatistikuRadRecepcnePlatba().pripocitajHodnotuDoCasu(0);
                    }
                    
                    ((MyMessage)message).nastavZamestnancaKtoryObsluhuje(ktoraMaNajmenej);
                    message.setAddressee(myAgent().findAssistant(Id.obsluhaPlatba));
                    startContinualAssistant(message);
                } else {
                    myAgent().dajStatistikuDlzkaRadRecepcnePlatba().pripocitajVazenuPocetnost(mySim().currentTime(), myAgent().dajRadZakaznikovPredRecepcnymiPlatba().size());               
                    ((MyMessage)message).dajObsluhovanehoZakaznika().nastavZaciatokCakaniaRadRecepcnePlatba(mySim().currentTime());
                    myAgent().dajRadZakaznikovPredRecepcnymiPlatba().enqueue(message);
                }
            break;
            
            case Mc.dajPocetZakaznikovVRadoch:			
                message.setCode(Mc.dajPocetZakaznikovVRadoch);
                message.setAddressee(mySim().findAgent(Id.agentSalonu));
                request(message);
            break;
            
            case Mc.zmenaPoctuLudiRadyKadernickyKozmeticky:
                if (!myAgent().dajZoznamVolnychRecepcnych().isEmpty()) {
                    double najmenej = Double.MAX_VALUE;
                    Recepcna ktoraMaNajmenej = null;
                    for (Recepcna aktualna : myAgent().dajZoznamVolnychRecepcnych()) {
                        if (aktualna.dajCasCelkovejObsluhy() < najmenej) {
                            najmenej = aktualna.dajCasCelkovejObsluhy();
                            ktoraMaNajmenej = aktualna;
                        }
                    }
                    // v premennej "ktoraMaNajmenej" je recepcna, ktora pracovala najmenej    
                    
                    // ak niekto caka na objednavku
                    if (!myAgent().dajRadZakaznikovPredRecepcnymiObjednavka().isEmpty()) {
                        
                        if (mySim().currentTime() < 28800) {
                        
                            // a pocet ludi v rade pred kadernickami a kozmetickami je menej ako 11
                            if (((MyMessage)message).dajPocetLudiRadKadernicky() + ((MyMessage)message).dajPocetLudiRadKozmeticky() <= 10) {
                                // zapisem aktualnu dlzku radu do statistiky
                                myAgent().dajStatistikuDlzkaRadRecepcneObjednavka().pripocitajVazenuPocetnost(mySim().currentTime(), myAgent().dajRadZakaznikovPredRecepcnymiObjednavka().size());                               
                                // vytiahnem spravu
                                MyMessage nextMessage = (MyMessage)myAgent().dajRadZakaznikovPredRecepcnymiObjednavka().dequeue();
                                // zakaznikovi zapisem koniec cakania
                                nextMessage.dajObsluhovanehoZakaznika().nastavKoniecCakaniaRadRecepcneObjednavka(mySim().currentTime());  
                                // zapisem do statistik, kolko dany zakaznik cakal v rade pred recepcnymi
                                myAgent().dajStatistikuRadRecepcneObjednavka().pripocitajHodnotu(nextMessage.dajObsluhovanehoZakaznika().dajKoniecCakaniaRadRecepcneObjednavka()- nextMessage.dajObsluhovanehoZakaznika().dajZaciatokCakaniaRadRecepcneObjednavka());                              
                                // zapisem do statistiky bez chladenia
                                if (mySim().currentTime() < 28800) {
                                    myAgent().dajStatistikuRadRecepcneObjednavka().pripocitajHodnotuDoCasu(nextMessage.dajObsluhovanehoZakaznika().dajKoniecCakaniaRadRecepcneObjednavka()- nextMessage.dajObsluhovanehoZakaznika().dajZaciatokCakaniaRadRecepcneObjednavka());                          
                                }
                                
                                // najdenu pracovnicku dam pracovat
                                ktoraMaNajmenej.nastavPracovanie(true);
                                ktoraMaNajmenej.zapisZaciatokObsluhy(mySim().currentTime());
                                this.myAgent().dajZoznamVolnychRecepcnych().remove(ktoraMaNajmenej);
                                this.myAgent().dajZoznamPracujucichRecepcnych().add(ktoraMaNajmenej);
                                nextMessage.nastavZamestnancaKtoryObsluhuje(ktoraMaNajmenej);

                                nextMessage.setAddressee(myAgent().findAssistant(Id.obsluhaObjednavka));
                                startContinualAssistant(nextMessage);
                            }
                        } else {
                            double dlzkaRadu = myAgent().dajRadZakaznikovPredRecepcnymiObjednavka().size();
                            myAgent().vyprazdnitRadRecepcneObjednavka();
                            myAgent().nastavKolkoLudiSomPoslalPrec(dlzkaRadu);
                        }
                    }
                }
            break;
            
            case Mc.inicializuj:			
                request(message);
            break;
	}
    }
	//meta! tag="end"

	@Override
	public AgentRecepcnych myAgent()
	{
		return (AgentRecepcnych)super.myAgent();
	}

}
