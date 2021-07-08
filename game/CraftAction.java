package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Special action for crafting a club or mace
 * 
 * @author Kevin
 *
 */
public class CraftAction extends Action{
	/**
	 * the ZombieLimb to be crafted
	 */
	Item zombieLimb;
	
	/**
	 * constructor
	 * 
	 * @param zombieLimb Limb of the zombie actor is holding/in inventory
	 */
	public CraftAction(Item zombieLimb) {
		this.zombieLimb = zombieLimb;
	}
	
	/**
	 * If player: Remove limb from inventory and add weapon Club if limb is an arm or add weapon 
	 * Mace if limb is a leg 
	 */
	public String execute(Actor actor, GameMap map) {
		if (actor.hasCapability(ZombieCapability.PLAYER)) {
			actor.removeItemFromInventory(zombieLimb);
			map.locationOf(actor).removeItem(zombieLimb);
			if (zombieLimb.getDisplayChar() == 'a') {
				actor.addItemToInventory(new Club());
				return actor + " crafted a zombie club";
			}
			if (zombieLimb.getDisplayChar() == 'l') {
				actor.addItemToInventory(new Mace());
				return actor + " crafted a zombie mace";
			}
		}
		return "tried to craft?";
	}
	
	/**
	 * The display for the player in menu for CraftAction
	 */
	public String menuDescription(Actor actor) {
		return actor + " crafts weapon";
	}
}
