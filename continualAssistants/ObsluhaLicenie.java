package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import OSPABA.Process;

//meta! id="26"
public class ObsluhaLicenie extends Process
{
	public ObsluhaLicenie(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentKozmeticok", id="27", type="Start"
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
                    double dlzkaObsluhy;
                    double pravdepodobnost = myAgent().dajPravdepodobnostTypuLicenia();
                    if (pravdepodobnost < 0.3) {
                        dlzkaObsluhy = myAgent().dajCasJednoduchehoLicenia();
                    } else {
                        dlzkaObsluhy = myAgent().dajCasZlozitehoLicenia();
                    }
                    message.setCode(Mc.finish);
                    hold(dlzkaObsluhy, message);
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
	public AgentKozmeticok myAgent()
	{
		return (AgentKozmeticok)super.myAgent();
	}

}
