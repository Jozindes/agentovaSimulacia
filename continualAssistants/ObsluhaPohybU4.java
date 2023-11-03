package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import OSPABA.Process;

//meta! id="90"
public class ObsluhaPohybU4 extends Process
{
	public ObsluhaPohybU4(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentPohybu", id="91", type="Start"
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
                    if (((MyMessage)message).dajObsluhovanehoZakaznika().dajZaparkoval()) {
                        ((MyMessage)message).dajObsluhovanehoZakaznika().nastavRychlostNaUseku(((MyMessage)message).dajObsluhovanehoZakaznika().dajRychlostPeso());
                        double dlzkaObsluhy = 10.0 / ((MyMessage)message).dajObsluhovanehoZakaznika().dajRychlostPeso();
                        message.setCode(Mc.finish);
                        hold(dlzkaObsluhy, message);
                    } else {
                        double dlzkaObsluhy = 10.0 / (20000 / 3600.0);
                        ((MyMessage)message).dajObsluhovanehoZakaznika().nastavRychlostNaUseku(20000 / 3600.0);
                        message.setCode(Mc.finish);
                        hold(dlzkaObsluhy, message);
                    }
		break;
                
                case Mc.finish:
                    assistantFinished(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentPohybu myAgent()
	{
		return (AgentPohybu)super.myAgent();
	}

}
