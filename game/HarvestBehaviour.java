package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;

/**
 * A class that generates HarvestAction if the current actor is standing
 * next to a ripe crop they can harvest.
 * 
 * @author Kevin
 *
 */
public class HarvestBehaviour implements Behaviour{
	
	/**
	 * Returns a HarvestAction that harvests an adjacent ripe crop
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// Is there a ripe crop next to me?
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		Collections.shuffle(exits);
		
		for (Exit e:exits) {
			if (e.getDestination().getGround().getDisplayChar() == 'Y') {
				return new HarvestAction(e.getDestination());
			}
		}
		return null;
	}
}
