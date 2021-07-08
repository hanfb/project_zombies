package game;


import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.ActorLocations;

/**
 * Checks if game is won or lost
 * 
 * @author Kevin
 *
 */
public class CheckEndCondition {
	private ActorLocations allActorsLocation;
	/**
	 * Constructor for CheckEndCondition 
	 * 
	 * @param actorLocations The location of all actors
	 */
	public CheckEndCondition(ActorLocations actorLocations) {
		// TODO Auto-generated constructor stub
		allActorsLocation = actorLocations;
	}
	/**
	 * checks if game is lost by seeing if all humans excluding player is all dead
	 * 
	 * @return Boolean: False = not lost, True = lost
	 */
	public boolean checkLose(){
		boolean lost = true;
		for (Actor actor:allActorsLocation) {
			if (actor.hasCapability(ZombieCapability.ALIVE) && !actor.hasCapability(ZombieCapability.PLAYER)) {
				lost = false;
				break;
			}
		}
		return lost;
	}
	
	/**
	 * Checks to see if game is won by seeing if there's any zombies still alive
	 * 
	 * @return Boolean: False = not won, True = won
	 */
	public boolean checkWin() {
		boolean win = true;
		for (Actor actor:allActorsLocation) {
			if (actor.hasCapability(ZombieCapability.UNDEAD) && !actor.hasCapability(ZombieCapability.MA_MARIE)) {
				System.out.println(actor);
				win = false;
				break;
			}
		}
		return win;
	}
}
