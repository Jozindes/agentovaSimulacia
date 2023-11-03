package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;

//meta! id="52"
public class PlanovaniePrichoduAutom extends Scheduler
{
	public PlanovaniePrichoduAutom(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentOkolia", id="53", type="Start"
	public void processStart(MessageForm message)
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
	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.start:
                    message.setCode(Mc.prichodZakaznika);
                    hold(myAgent().dajHodnotuPrichoduAutom(), message);                    
		break;
                
                case Mc.prichodZakaznika:
                    double zdrzanie = myAgent().dajHodnotuPrichoduAutom();
                    if ((mySim().currentTime() + zdrzanie) < 28800) {
                        MyMessage msg = new MyMessage((MyMessage)message);
                        msg.setAddressee(this);
                        msg.setCode(Mc.prichodZakaznika);
                        hold(zdrzanie, msg);
                    }
                    Zakaznik vytvoreny = new Zakaznik(myAgent().dajVolneID(), 2, mySim().currentTime(), myAgent().dajHodnotuZaujemOSluzby(), myAgent().dajHodnotuZaujemOCisteniePleti());
                    vytvoreny.nastavSposobPohybu("Auto");
                    ((MyMessage)message).nastavObsluhovanehoZakaznika(vytvoreny);
                    myAgent().dajZoznamZakaznikov().add(vytvoreny);
                    assistantFinished(message);	
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
