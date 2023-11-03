package simulation;

import OSPABA.*;
import agents.*;
import statistiky.Priemer;

public class MySimulation extends Simulation
{
    
    private Priemer priemernyCasCakaniaRadRecepcneObjednavka;
    private Priemer priemernyCasCakaniaRadRecepcnePlatba;
    private Priemer priemernyCasCakaniaRadKadernicky;
    private Priemer priemernyCasCakaniaRadKozmeticky;
    
    private Priemer priemernaDlzkaRaduRecepcneObjednavka;
    private Priemer priemernaDlzkaRaduRecepcnePlatba;
    private Priemer priemernaDlzkaRaduKadernicky;
    private Priemer priemernaDlzkaRaduKozmeticky;
    
    private Priemer priemerneVytazenieRecepcne;
    private Priemer priemerneVytazenieKadernicky;
    private Priemer priemerneVytazenieKozmeticky;
    
    private Priemer priemernyCasOtvoreneNaviac;
    private Priemer priemernyCasZakaznikaVSysteme;
    
    private Priemer priemernaDobaObsluhyRecepcne;
    private Priemer priemernaDobaObsluhyKadernicky;
    private Priemer priemernaDobaObsluhyKozmeticky;
    
    private Priemer priemernyPocetVyhodenychLudi;
    
    private Priemer priemernaSpokojnost;
    private Priemer priemernaUspesnostParkovania;
    
    // do 17
    
    private Priemer priemernyCasCakaniaRadRecepcneObjednavka17;
    private Priemer priemernyCasCakaniaRadRecepcnePlatba17;
    private Priemer priemernyCasCakaniaRadKadernicky17;
    private Priemer priemernyCasCakaniaRadKozmeticky17;
    
    private Priemer priemernaDlzkaRaduRecepcneObjednavka17;
    private Priemer priemernaDlzkaRaduRecepcnePlatba17;
    private Priemer priemernaDlzkaRaduKadernicky17;
    private Priemer priemernaDlzkaRaduKozmeticky17;
    
    private Priemer priemerneVytazenieRecepcne17;
    private Priemer priemerneVytazenieKadernicky17;
    private Priemer priemerneVytazenieKozmeticky17;
    
    private Priemer priemernyCasZakaznikaVSysteme17;
    
    private Priemer priemernaDobaObsluhyRecepcne17;
    private Priemer priemernaDobaObsluhyKadernicky17;
    private Priemer priemernaDobaObsluhyKozmeticky17;
    
	public MySimulation(int pocetRecepcnych, int pocetKadernicok, int pocetKozmeticok, int pocetParkovacichMiest)
	{
		init(pocetRecepcnych, pocetKadernicok, pocetKozmeticok, pocetParkovacichMiest);
	}

	@Override
	public void prepareSimulation()
	{
		super.prepareSimulation();
                priemernyCasCakaniaRadRecepcneObjednavka = new Priemer();
                priemernyCasCakaniaRadRecepcnePlatba = new Priemer();
                priemernyCasCakaniaRadKadernicky = new Priemer();
                priemernyCasCakaniaRadKozmeticky = new Priemer();
                
                priemernaDlzkaRaduRecepcneObjednavka = new Priemer();
                priemernaDlzkaRaduRecepcnePlatba = new Priemer();
                priemernaDlzkaRaduKadernicky = new Priemer();
                priemernaDlzkaRaduKozmeticky = new Priemer();
                
                priemerneVytazenieRecepcne = new Priemer();
                priemerneVytazenieKadernicky = new Priemer();
                priemerneVytazenieKozmeticky = new Priemer();
                
                priemernyCasOtvoreneNaviac = new Priemer();
                priemernyCasZakaznikaVSysteme = new Priemer();
                
                priemernaDobaObsluhyRecepcne = new Priemer();
                priemernaDobaObsluhyKadernicky = new Priemer();
                priemernaDobaObsluhyKozmeticky = new Priemer();
                
                priemernyPocetVyhodenychLudi = new Priemer();
                
                priemernaSpokojnost = new Priemer();
                priemernaUspesnostParkovania = new Priemer();
                
                // do 17
                
                priemernyCasCakaniaRadRecepcneObjednavka17 = new Priemer();
                priemernyCasCakaniaRadRecepcnePlatba17 = new Priemer();
                priemernyCasCakaniaRadKadernicky17 = new Priemer();
                priemernyCasCakaniaRadKozmeticky17 = new Priemer();
                
                priemernaDlzkaRaduRecepcneObjednavka17 = new Priemer();
                priemernaDlzkaRaduRecepcnePlatba17 = new Priemer();
                priemernaDlzkaRaduKadernicky17 = new Priemer();
                priemernaDlzkaRaduKozmeticky17 = new Priemer();
                
                priemerneVytazenieRecepcne17 = new Priemer();
                priemerneVytazenieKadernicky17 = new Priemer();
                priemerneVytazenieKozmeticky17 = new Priemer();
                
                priemernyCasZakaznikaVSysteme17 = new Priemer();
                
                priemernaDobaObsluhyRecepcne17 = new Priemer();
                priemernaDobaObsluhyKadernicky17 = new Priemer();
                priemernaDobaObsluhyKozmeticky17 = new Priemer();
                
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		agentModelu().spustiSimulaciu();
	}

	@Override
	public void replicationFinished()
	{	
		super.replicationFinished();
                priemernyCasCakaniaRadRecepcneObjednavka.pripocitajHodnotu(agentRecepcnych().dajStatistikuRadRecepcneObjednavka().dajPriemer());
                priemernyCasCakaniaRadRecepcnePlatba.pripocitajHodnotu(agentRecepcnych().dajStatistikuRadRecepcnePlatba().dajPriemer()); 
                priemernyCasCakaniaRadKadernicky.pripocitajHodnotu(agentKadernicok().dajStatistikuRadKadernicky().dajPriemer());
                priemernyCasCakaniaRadKozmeticky.pripocitajHodnotu(agentKozmeticok().dajStatistikuRadKozmeticky().dajPriemer());
        
                priemernaDlzkaRaduRecepcneObjednavka.pripocitajHodnotu(agentRecepcnych().dajStatistikuDlzkaRadRecepcneObjednavka().dajPriemernuDlzkuRadu(currentTime()));
                priemernaDlzkaRaduRecepcnePlatba.pripocitajHodnotu(agentRecepcnych().dajStatistikuDlzkaRadRecepcnePlatba().dajPriemernuDlzkuRadu(currentTime()));
                priemernaDlzkaRaduKadernicky.pripocitajHodnotu(agentKadernicok().dajStatistikuDlzkaRadKadernicky().dajPriemernuDlzkuRadu(currentTime()));
                priemernaDlzkaRaduKozmeticky.pripocitajHodnotu(agentKozmeticok().dajStatistikuDlzkaRadKozmeticky().dajPriemernuDlzkuRadu(currentTime()));
        
                priemerneVytazenieRecepcne.pripocitajHodnotu(agentRecepcnych().dajPriemerneVytazenie(currentTime()));
                priemerneVytazenieKadernicky.pripocitajHodnotu(agentKadernicok().dajPriemerneVytazenie(currentTime()));
                priemerneVytazenieKozmeticky.pripocitajHodnotu(agentKozmeticok().dajPriemerneVytazenie(currentTime()));
                
                priemernyCasOtvoreneNaviac.pripocitajHodnotu(agentSalonu().kolkoJeOtvoreneNaviac(currentTime()));
                priemernyCasZakaznikaVSysteme.pripocitajHodnotu(agentOkolia().dajStatistikuCasVSysteme().dajPriemer());
        
                priemernaDobaObsluhyRecepcne.pripocitajHodnotu(agentRecepcnych().dajStatistikuDobaObsluhyRecepcne().dajPriemer());
                priemernaDobaObsluhyKadernicky.pripocitajHodnotu(agentKadernicok().dajStatistikuDobaObsluhyKadernicky().dajPriemer());
                priemernaDobaObsluhyKozmeticky.pripocitajHodnotu(agentKozmeticok().dajStatistikuDobaObsluhyKozmeticky().dajPriemer());
                
                priemernyPocetVyhodenychLudi.pripocitajHodnotu(agentRecepcnych().dajKolkoLudiSomPoslalPrec());
                
                priemernaSpokojnost.pripocitajHodnotu(agentParkovania().dajStatistikuSpokojnost().dajPriemer());
                priemernaUspesnostParkovania.pripocitajHodnotu(agentParkovania().dajKolkoAutUspesneZaparkovalo() / (double) agentOkolia().dajPocetLudiKtoriPrisliDoSalonuAutom() * 100);
                
                // do 17
                
                priemernyCasCakaniaRadRecepcneObjednavka17.pripocitajHodnotu(agentRecepcnych().dajStatistikuRadRecepcneObjednavka().dajPriemernyCasDoCasu());
                priemernyCasCakaniaRadRecepcnePlatba17.pripocitajHodnotu(agentRecepcnych().dajStatistikuRadRecepcnePlatba().dajPriemernyCasDoCasu()); 
                priemernyCasCakaniaRadKadernicky17.pripocitajHodnotu(agentKadernicok().dajStatistikuRadKadernicky().dajPriemernyCasDoCasu());
                priemernyCasCakaniaRadKozmeticky17.pripocitajHodnotu(agentKozmeticok().dajStatistikuRadKozmeticky().dajPriemernyCasDoCasu());
        
                priemernaDlzkaRaduRecepcneObjednavka17.pripocitajHodnotu(agentRecepcnych().dajStatistikuDlzkaRadRecepcneObjednavka().dajPriemernuDlzkuRaduDoCasu(28800));
                priemernaDlzkaRaduRecepcnePlatba17.pripocitajHodnotu(agentRecepcnych().dajStatistikuDlzkaRadRecepcnePlatba().dajPriemernuDlzkuRaduDoCasu(28800));
                priemernaDlzkaRaduKadernicky17.pripocitajHodnotu(agentKadernicok().dajStatistikuDlzkaRadKadernicky().dajPriemernuDlzkuRaduDoCasu(28800));
                priemernaDlzkaRaduKozmeticky17.pripocitajHodnotu(agentKozmeticok().dajStatistikuDlzkaRadKozmeticky().dajPriemernuDlzkuRaduDoCasu(28800));
        
                priemerneVytazenieRecepcne17.pripocitajHodnotu(agentRecepcnych().dajPriemerneVytazenieDoCasu());
                priemerneVytazenieKadernicky17.pripocitajHodnotu(agentKadernicok().dajPriemerneVytazenieDoCasu());
                priemerneVytazenieKozmeticky17.pripocitajHodnotu(agentKozmeticok().dajPriemerneVytazenieDoCasu());
                
                priemernyCasZakaznikaVSysteme17.pripocitajHodnotu(agentOkolia().dajStatistikuCasVSysteme().dajPriemernyCasDoCasu());
        
                priemernaDobaObsluhyRecepcne17.pripocitajHodnotu(agentRecepcnych().dajStatistikuDobaObsluhyRecepcne().dajPriemernyCasDoCasu());
                priemernaDobaObsluhyKadernicky17.pripocitajHodnotu(agentKadernicok().dajStatistikuDobaObsluhyKadernicky().dajPriemernyCasDoCasu());
                priemernaDobaObsluhyKozmeticky17.pripocitajHodnotu(agentKozmeticok().dajStatistikuDobaObsluhyKozmeticky().dajPriemernyCasDoCasu());
        }

	@Override
	public void simulationFinished()
	{
            /*
                System.out.println("O " + this.priemernyCasCakaniaRadRecepcneObjednavka.dajPriemer());
                System.out.println("P " + this.priemernyCasCakaniaRadRecepcnePlatba.dajPriemer());
                System.out.println("Ka " + this.priemernyCasCakaniaRadKadernicky.dajPriemer());
                System.out.println("Ko " + this.priemernyCasCakaniaRadKozmeticky.dajPriemer());
                
                System.out.println("RO " + this.priemernaDlzkaRaduRecepcneObjednavka.dajPriemer());
                System.out.println("RP " + this.priemernaDlzkaRaduRecepcnePlatba.dajPriemer());
                System.out.println("RKa " + this.priemernaDlzkaRaduKadernicky.dajPriemer());
                System.out.println("RKo " + this.priemernaDlzkaRaduKozmeticky.dajPriemer());
                
                System.out.println("VR " + this.priemerneVytazenieRecepcne.dajPriemer());
                System.out.println("VKa " + this.priemerneVytazenieKadernicky.dajPriemer());
                System.out.println("VKo " + this.priemerneVytazenieKozmeticky.dajPriemer());
                
                System.out.println("Naviac " + this.priemernyCasOtvoreneNaviac.dajPriemer());
                System.out.println("Priemer v systeme " + this.priemernyCasZakaznikaVSysteme.dajPriemer());
                
                System.out.println("Priemer obsluha R " + this.priemernaDobaObsluhyRecepcne.dajPriemer());
                System.out.println("Priemer obsluha Ka " + this.priemernaDobaObsluhyKadernicky.dajPriemer());
                System.out.println("Priemer obsluha Ko " + this.priemernaDobaObsluhyKozmeticky.dajPriemer());
                
                System.out.println("Priemer vyhodenych " + this.priemernyPocetVyhodenychLudi.dajPriemer());
*/
		super.simulationFinished();
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init(int pocetRecepcnych, int pocetKadernicok, int pocetKozmeticok, int pocetParkovacichMiest)
	{
		setAgentModelu(new AgentModelu(Id.agentModelu, this, null));
		setAgentOkolia(new AgentOkolia(Id.agentOkolia, this, agentModelu()));
		setAgentSalonu(new AgentSalonu(Id.agentSalonu, this, agentModelu()));
		setAgentParkovania(new AgentParkovania(Id.agentParkovania, this, agentSalonu(), pocetParkovacichMiest));
		setAgentRecepcnych(new AgentRecepcnych(Id.agentRecepcnych, this, agentSalonu(), pocetRecepcnych));
		setAgentKadernicok(new AgentKadernicok(Id.agentKadernicok, this, agentSalonu(), pocetKadernicok));
		setAgentKozmeticok(new AgentKozmeticok(Id.agentKozmeticok, this, agentSalonu(), pocetKozmeticok));
		setAgentPohybu(new AgentPohybu(Id.agentPohybu, this, agentSalonu(), pocetParkovacichMiest));
	}

	private AgentModelu _agentModelu;

public AgentModelu agentModelu()
	{ return _agentModelu; }

	public void setAgentModelu(AgentModelu agentModelu)
	{_agentModelu = agentModelu; }

	private AgentOkolia _agentOkolia;

public AgentOkolia agentOkolia()
	{ return _agentOkolia; }

	public void setAgentOkolia(AgentOkolia agentOkolia)
	{_agentOkolia = agentOkolia; }

	private AgentSalonu _agentSalonu;

public AgentSalonu agentSalonu()
	{ return _agentSalonu; }

	public void setAgentSalonu(AgentSalonu agentSalonu)
	{_agentSalonu = agentSalonu; }

	private AgentParkovania _agentParkovania;

public AgentParkovania agentParkovania()
	{ return _agentParkovania; }

	public void setAgentParkovania(AgentParkovania agentParkovania)
	{_agentParkovania = agentParkovania; }

	private AgentRecepcnych _agentRecepcnych;

public AgentRecepcnych agentRecepcnych()
	{ return _agentRecepcnych; }

	public void setAgentRecepcnych(AgentRecepcnych agentRecepcnych)
	{_agentRecepcnych = agentRecepcnych; }

	private AgentKadernicok _agentKadernicok;

public AgentKadernicok agentKadernicok()
	{ return _agentKadernicok; }

	public void setAgentKadernicok(AgentKadernicok agentKadernicok)
	{_agentKadernicok = agentKadernicok; }

	private AgentKozmeticok _agentKozmeticok;

public AgentKozmeticok agentKozmeticok()
	{ return _agentKozmeticok; }

	public void setAgentKozmeticok(AgentKozmeticok agentKozmeticok)
	{_agentKozmeticok = agentKozmeticok; }

	private AgentPohybu _agentPohybu;

public AgentPohybu agentPohybu()
	{ return _agentPohybu; }

	public void setAgentPohybu(AgentPohybu agentPohybu)
	{_agentPohybu = agentPohybu; }
	//meta! tag="end"
        
        public Priemer priemerneVytazenieRecepcneCELE() {
            return this.priemerneVytazenieRecepcne;
        }
        
        public Priemer priemerneVytazenieKadernickyCELE() {
            return this.priemerneVytazenieKadernicky;
        }
        
        public Priemer priemerneVytazenieKozmetickyCELE() {
            return this.priemerneVytazenieKozmeticky;
        }
        
        public Priemer priemernyCasRadRecepcneObjednavkaCELE() {
            return this.priemernyCasCakaniaRadRecepcneObjednavka;
        }
        
        public Priemer priemernyCasRadRecepcnePlatbaCELE() {
            return this.priemernyCasCakaniaRadRecepcnePlatba;
        }
        
        public Priemer priemernyCasRadKadernickyCELE() {
            return this.priemernyCasCakaniaRadKadernicky;
        }
        
        public Priemer priemernyCasRadKozmetickyCELE() {
            return this.priemernyCasCakaniaRadKozmeticky;
        }
        
        public Priemer priemernaDlzkaRaduRecepcneObjednavka() {
            return this.priemernaDlzkaRaduRecepcneObjednavka;
        }
        
        public Priemer priemernaDlzkaRaduRecepcnePlatba() {
            return this.priemernaDlzkaRaduRecepcnePlatba;
        }
        
        public Priemer priemernaDlzkaRaduKadernicky() {
            return this.priemernaDlzkaRaduKadernicky;
        }
        
        public Priemer priemernaDlzkaRaduKozmeticky() {
            return this.priemernaDlzkaRaduKozmeticky;
        }
        
        public Priemer priemernaDobaObsluhyRecepcne() {
            return this.priemernaDobaObsluhyRecepcne;
        }
        
        public Priemer priemernaDobaObsluhyKadernicky() {
            return this.priemernaDobaObsluhyKadernicky;
        }
        
        public Priemer priemernaDobaObsluhyKozmeticky() {
            return this.priemernaDobaObsluhyKozmeticky;
        }
        
        public Priemer priemernaDobaOtvoreniaNaviac() {
            return this.priemernyCasOtvoreneNaviac;
        }
        
        public Priemer priemernyPocetVyhodenychLudi() {
            return this.priemernyPocetVyhodenychLudi;
        }
        
        public Priemer priemernyCasZakaznikaVSysteme() {
            return this.priemernyCasZakaznikaVSysteme;
        }        
        
        public Priemer priemernaUspesnostParkovania() {
            return this.priemernaUspesnostParkovania;
        }
        
        public Priemer priemernaSpokojnost() {
            return this.priemernaSpokojnost;
        }
        
        // do 17
        
        public Priemer priemerneVytazenieRecepcne17() {
            return this.priemerneVytazenieRecepcne17;
        }
        
        public Priemer priemerneVytazenieKadernicky17() {
            return this.priemerneVytazenieKadernicky17;
        }
        
        public Priemer priemerneVytazenieKozmeticky17() {
            return this.priemerneVytazenieKozmeticky17;
        }
        
        public Priemer priemernyCasRadRecepcneObjednavka17() {
            return this.priemernyCasCakaniaRadRecepcneObjednavka17;
        }
        
        public Priemer priemernyCasRadRecepcnePlatba17() {
            return this.priemernyCasCakaniaRadRecepcnePlatba17;
        }
        
        public Priemer priemernyCasRadKadernicky17() {
            return this.priemernyCasCakaniaRadKadernicky17;
        }
        
        public Priemer priemernyCasRadKozmeticky17() {
            return this.priemernyCasCakaniaRadKozmeticky17;
        }
        
        public Priemer priemernaDlzkaRaduRecepcneObjednavka17() {
            return this.priemernaDlzkaRaduRecepcneObjednavka17;
        }
        
        public Priemer priemernaDlzkaRaduRecepcnePlatba17() {
            return this.priemernaDlzkaRaduRecepcnePlatba17;
        }
        
        public Priemer priemernaDlzkaRaduKadernicky17() {
            return this.priemernaDlzkaRaduKadernicky17;
        }
        
        public Priemer priemernaDlzkaRaduKozmeticky17() {
            return this.priemernaDlzkaRaduKozmeticky17;
        }
        
        public Priemer priemernaDobaObsluhyRecepcne17() {
            return this.priemernaDobaObsluhyRecepcne17;
        }
        
        public Priemer priemernaDobaObsluhyKadernicky17() {
            return this.priemernaDobaObsluhyKadernicky17;
        }
        
        public Priemer priemernaDobaObsluhyKozmeticky17() {
            return this.priemernaDobaObsluhyKozmeticky17;
        }              
        
        public Priemer priemernyCasZakaznikaVSysteme17() {
            return this.priemernyCasZakaznikaVSysteme17;
        }              
}
