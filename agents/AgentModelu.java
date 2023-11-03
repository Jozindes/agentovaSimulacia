package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="1"
public class AgentModelu extends Agent
{
	public AgentModelu(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerModelu(Id.managerModelu, mySim(), this);
		addOwnMessage(Mc.prichodZakaznika);
		addOwnMessage(Mc.obsluzZakaznika);
	}
	//meta! tag="end"
        
        public void spustiSimulaciu() {
            MyMessage sprava = new MyMessage(mySim());
            sprava.setCode(Mc.inicializuj);
            sprava.setAddressee(Id.agentOkolia);
            manager().notice(sprava);
        }
        
        // formatuje aktualny simulacny cas do formatu hh:mm:ss
    public String dajFormatovanySimulacnyCas() {
        int kolkoJeHodinNaZaciatku = 9;
        double kopiaSimulacnehoCasu = _mySim.currentTime();
        int hodiny = (int) kopiaSimulacnehoCasu / 3600;
        kopiaSimulacnehoCasu = kopiaSimulacnehoCasu - (hodiny * 3600);
        int minuty = (int) kopiaSimulacnehoCasu / 60;
        kopiaSimulacnehoCasu = kopiaSimulacnehoCasu - (minuty * 60);
        int sekundy = (int) kopiaSimulacnehoCasu;
        
        String hod;
        if (kolkoJeHodinNaZaciatku + hodiny >= 10) {
            hod = String.valueOf((kolkoJeHodinNaZaciatku + hodiny) % 24);
        } else {
            hod = "0" + String.valueOf((kolkoJeHodinNaZaciatku + hodiny) % 24);
        }
        
        String min;
        if (minuty >= 10) {
            min = String.valueOf(minuty);
        } else {
            min = "0" + String.valueOf(minuty);
        }
        
        String sek;
        if (sekundy >= 10) {
            sek = String.valueOf(sekundy);
        } else {
            sek = "0" + String.valueOf(sekundy);
        }
        
        return hod + ":" + min + ":" + sek;
    }
}
