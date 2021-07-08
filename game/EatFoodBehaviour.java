package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * A class that generates an EatAction if the current Actor is standing on top of food
 * 
 * @author Kevin
 *
 */
public class EatFoodBehaviour implements Behaviour{

	/**
	 * Returns an EatAction that eats the food if actor is able to access food
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// am I standing on food?
		for (Item item:map.locationOf(actor).getItems()) {
			if (item.getDisplayChar() == 'Q') {
				return new EatAction(item);
			}
		}
		return null;
	}

}
