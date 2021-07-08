package game;

import edu.monash.fit2099.engine.Item;

/**
 * Implements food that can heal if eaten as an item
 * 
 * @author Kevin
 *
 */
public class Food extends Item{
	
	/**
	 * constructor 
	 * 
	 * @param name Name of the food
	 * @param displayChar Symbol used to display food on the map
	 * @param portable Indicate if food can be picked up
	 */
	public Food(String name, char displayChar, boolean portable) {
		super(name, displayChar, portable);
		// actors with food is able to perform EatAction
		this.allowableActions.add(new EatAction(this));
	}
}
