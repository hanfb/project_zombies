package game;

import java.util.Random;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A class that describes a corpse as a type of ground
 * 
 * @author Kevin
 *
 */
public class DeadHuman extends Ground{
	
	/**
	 * Counter for when to resurrect dead human as new zombie (5-10 turns)
	 */
	private int resurrectCounter = new Random().nextInt(6) + 5;
	
	/**
	 * constructor
	 */
	public DeadHuman() {
		super('A');
	}
	
	/**
	 * Decrement resurrectCounter each tick and summon zombie when resurrectCounter becomes 0
	 * 
	 * Also replace DeadHuman with dirt on map
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);
		
		resurrectCounter--;
		if (resurrectCounter <= 0) {
			if (!location.containsAnActor()) {
				location.addActor(new Zombie("resurrected human"));
				location.setGround(new Dirt());
			}
		}
	}
}
