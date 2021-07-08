package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Special Action for harvesting a ripe crop
 * 
 * @author Kevin
 *
 */
public class HarvestAction extends Action{
	
	/**
	 * location of the ripe crop
	 */
	private Location cropLocation;
	
	/**
	 * constructor
	 * 
	 * @param location Location of the ripe crop
	 */
	public HarvestAction(Location location) {
		this.cropLocation = location;
	}
	
	/**
	 * If person who performs harvest is farmer than place food on location of ripe crop and replace ripe crop with dirt,
	 * if person who harvests is player than add food to inventory and replace ripe crop with dirt
	 */
	public String execute(Actor actor, GameMap map) {	
		if (actor.hasCapability(ZombieCapability.FARMER)){
			cropLocation.setGround(new Dirt());
			cropLocation.addItem(new Food("oats", 'Q', true));
			return actor + " harvests a crop";
		}
		cropLocation.setGround(new Dirt());
		actor.addItemToInventory(new Food("oats", 'Q', true));
		return "you have harvested some food";
	}
	
	/**
	 * the display on menu for players for HarvestAction
	 */
	public String menuDescription(Actor actor) {
		return actor + " harvests food for oats";
	}
}
