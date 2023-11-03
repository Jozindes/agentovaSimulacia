package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="1"
public class ManagerModelu extends Manager
{
	public ManagerModelu(int id, Simulation mySim, Agent myAgent)
	{
		super(id, mySim, myAgent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication

		if (petriNet() != null)
		{
			petriNet().clear();
		}
	}

	//meta! sender="AgentOkolia", id="40", type="Notice"
	public void processPrichodZakaznika(MessageForm message)
	{
	}

	//meta! sender="AgentSalonu", id="47", type="Response"
	public void processObsluzZakaznika(MessageForm message)
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
	public void init()
	{
	}

	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.prichodZakaznika:
                    if (((MyMessage)message).dajObsluhovanehoZakaznika().dajAkoPrisiel() == 1) {
                        message.setCode(Mc.dajPocetZakaznikovVRadoch);
                        message.setAddressee(mySim().findAgent(Id.agentSalonu));			
                        notice(message);
                    } else {
                        message.setCode(Mc.pohybujZakaznika);
			message.setAddressee(mySim().findAgent(Id.agentSalonu));			
			notice(message);
                        }
		break;

		case Mc.obsluzZakaznika:
			message.setCode(Mc.odchodZakaznika);
			message.setAddressee(mySim().findAgent(Id.agentOkolia));			
			notice(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentModelu myAgent()
	{
		return (AgentModelu)super.myAgent();
	}

}
