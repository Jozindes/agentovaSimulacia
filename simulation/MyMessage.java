package simulation;

import OSPABA.*;
import zamestnanci.Zamestnanec;

public class MyMessage extends MessageForm {
    
    private Zakaznik obsluhovanyZakaznik;
    private Zamestnanec obsluhujuciZamestnanec;
               
    private int pocetLudiRadKadernicky;
    private int pocetLudiRadKozmeticky;
    
    private int odKoho;
    
    public MyMessage(Simulation sim) {
	super(sim);
                              
        this.pocetLudiRadKadernicky = 0;
        this.pocetLudiRadKozmeticky = 0;
    }

	public MyMessage(MyMessage original)
	{
            super(original);          
	}

	@Override
	public MessageForm createCopy()
	{
		return new MyMessage(this);
	}

	@Override
	protected void copy(MessageForm message)
	{
		super.copy(message);
		MyMessage original = (MyMessage)message;
		// Copy attributes
	}
        
        // zamestnanec
        public Zamestnanec dajZamestnancaKtoryObsluhuje() {
            return obsluhujuciZamestnanec;
        }
        
        public void nastavZamestnancaKtoryObsluhuje(Zamestnanec ktory) {
            obsluhujuciZamestnanec = ktory;
        }
        // zamestnanec
        
        // zakaznik      
        public void nastavObsluhovanehoZakaznika(Zakaznik zakaznik) { 
            obsluhovanyZakaznik = zakaznik; 
        }
        
        public Zakaznik dajObsluhovanehoZakaznika() { 
            return obsluhovanyZakaznik; 
        }
        // zakaznik
        
        // kadernicky a kozmeticky
        public void nastavPocetLudiRadKadernicky(int pocet) {
            this.pocetLudiRadKadernicky = pocet;
        }
        
        public void nastavPocetLudiRadKozmeticky(int pocet) {
            this.pocetLudiRadKozmeticky = pocet;
        }
        
        public int dajPocetLudiRadKadernicky() {
            return pocetLudiRadKadernicky;
        }
        
        public int dajPocetLudiRadKozmeticky() {
            return pocetLudiRadKozmeticky;
        }
        // kadernicky a kozmeticky
        
        public void nastavOdKoho(int odKoho) {
            this.odKoho = odKoho;
        }
        
        public int dajOdKoho() {
            return this.odKoho;
        }
}
