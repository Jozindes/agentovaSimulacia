package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import OSPABA.Process;

//meta! id="29"
public class ObsluhaCesanie extends Process
{
	public ObsluhaCesanie(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentKadernicok", id="30", type="Start"
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
                    double pravdepodobnost = myAgent().dajPravdepodobnostVyberuUcesu();
                    if (pravdepodobnost < 0.4) {
                        dlzkaObsluhy = myAgent().dajCasJednoduchehoUcesu();
                    } else if (pravdepodobnost >= 0.4 && pravdepodobnost < 0.8) {
                        dlzkaObsluhy = myAgent().dajCasZlozitehoUcesu();
                    } else {
                        dlzkaObsluhy = myAgent().dajCasSvadobnehoUcesu();
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
	public AgentKadernicok myAgent()
	{
		return (AgentKadernicok)super.myAgent();
	}

}
