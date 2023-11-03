package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import OSPABA.Process;

//meta! id="31"
public class ObsluhaObjednavka extends Process
{
	public ObsluhaObjednavka(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentRecepcnych", id="32", type="Start"
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
                    double dlzkaObsluhy = myAgent().dajCasVybavovaniaObjednavky();
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
	public AgentRecepcnych myAgent()
	{
		return (AgentRecepcnych)super.myAgent();
	}

}
