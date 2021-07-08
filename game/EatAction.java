package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Special Action for eating food
 * 
 * @author Kevin
 *
 */
public class EatAction extends Action{
	/**
	 * The food that is to be eaten
	 */
	private Item food;
	
	/**
	 * Constructor
	 * 
	 * @param food The food to eat
	 */
	public EatAction(Item food) {
		this.food = food;
	}
	
	/**
	 * Heal hitpoint by 5 and remove from inventory if player and remove item from map
	 */
	public String execute(Actor actor, GameMap map) {
		actor.heal(20);
		if (actor.hasCapability(ZombieCapability.PLAYER)) {
			actor.removeItemFromInventory(food);
		}
		
		map.locationOf(actor).removeItem(food);
		
		return actor + " has eaten food and healed for 20 points";
	}
	
	/**
	 * The display for the player in menu for EatAction
	 */
	public String menuDescription(Actor actor) {
		return actor + " eats food";
	}
}
