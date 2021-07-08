package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * A class that generates a FertilizeAction if the current actor is standing on a
 * unripe crop.
 * 
 * @author Kevin
 *
 */
public class FertilizeBehaviour implements Behaviour{
	
	/**
	 * Returns a FertilizeAction if actor is standing on an unripe crop
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// Am I standing on UnripeCrop?
		Location location = map.locationOf(actor);
		if (location.getGround().getDisplayChar() == ',') {
			return new FertilizeAction(location);
		}
		return null;
	}

}
