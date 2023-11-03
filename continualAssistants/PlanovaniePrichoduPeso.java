package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;

//meta! id="38"
public class PlanovaniePrichoduPeso extends Scheduler
{
	public PlanovaniePrichoduPeso(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentOkolia", id="39", type="Start"
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
                    hold(myAgent().dajHodnotuPrichoduPeso(), message);                    
		break;
                
                case Mc.prichodZakaznika:
                    double zdrzanie = myAgent().dajHodnotuPrichoduPeso();
                    if ((mySim().currentTime() + zdrzanie) < 28800) {
                        MyMessage msg = new MyMessage((MyMessage)message);
                        msg.setAddressee(this);
                        msg.setCode(Mc.prichodZakaznika);
                        hold(zdrzanie, msg);
                    }
                    Zakaznik vytvoreny = new Zakaznik(myAgent().dajVolneID(), 1, mySim().currentTime(), myAgent().dajHodnotuZaujemOSluzby(), myAgent().dajHodnotuZaujemOCisteniePleti());
                    ((MyMessage)message).nastavObsluhovanehoZakaznika(vytvoreny);
                                       
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
