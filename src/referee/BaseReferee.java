package referee;

import java.util.List;

import rules.Player;

public abstract class BaseReferee implements Referee{
	protected List<Player> players;
	
	@Override
	public int turn() {
		send();
		receive();
		return winningConditions();
	}
	
	public abstract void send();
	public abstract void receive();
	public abstract int winningConditions();
	
}
