package game; 

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * special action for fertilizing an unripe crop
 * 
 * @author Kevin
 *
 */
public class FertilizeAction extends Action{
	
	/**
	 * The location of the unripe crop
	 */
	private Location cropLocation;
	
	/**
	 * constructor
	 * 
	 * @param cropLocation Location of the unripe crop
	 */
	public FertilizeAction(Location cropLocation) {
		this.cropLocation = cropLocation;
	}
	
	/**
	 * decreases the count until ripe by 10 for the unripe crop through it experiencing tick 10 times
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		for (int i = 0; i < 10; i++) {
			cropLocation.getGround().tick(cropLocation);
		}
		return actor + " fertilizes a crop";
	}
	
	/**
	 * the menu description for fertilizing an unripe crop 
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " fertilizes crop at " + String.valueOf(cropLocation.x()) + "," + String.valueOf(cropLocation.y());
	}

}
