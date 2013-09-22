package game;

import java.io.IOException;
import java.util.List;

import referee.Referee;
import rules.Player;

public class Game {
	protected Referee referee;
	protected List<Player> players;
	
	public Game(Referee referee, List<Player> players) {
		this.referee = referee;
		this.players = players;
	}
	
	public int play() throws IOException {
		int winner;
		while((winner = referee.turn()) == -1) ;//System.in.read();
		return winner;
	}
}
