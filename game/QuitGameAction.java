package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Allows the player to manually quit the game
 * 
 * @author Kevin
 *
 */
public class QuitGameAction extends Action{
	/**
	 * Constructor for QuitGameAction
	 */
	public QuitGameAction() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Quits the game by removing player from map 
	 */
	public String execute(Actor actor, GameMap map) {
		if (actor.hasCapability(ZombieCapability.PLAYER)) {
			map.removeActor(actor);
		}
		
		return actor + " has quit the game";
	}
	/**
	 * Menu description for quitting game
	 */
	public String menuDescription(Actor actor) {
		return "quit game";
	}

}
