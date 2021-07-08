package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * Implements the ripe crop as a ground
 * 
 * @author Kevin
 *
 */
public class RipeCrop extends Ground{

	/**
	 * constructor
	 */
	public RipeCrop() {
		super('Y');
	}
	
	/**
	 * Add HarvestAction as an allowableAction for RipeCrop so players can harvest ripe crop
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		return new Actions(new HarvestAction(location));
	}
}
