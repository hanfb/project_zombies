package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * Implements the unripe crop as a type of ground
 * 
 * @author Kevin
 *
 */
public class UnripeCrop extends Ground{
	
	/**
	 * the counter for when unripe crop becomes a ripe crop
	 */
	private int ripenCount = 20;
	
	/**
	 * constructor
	 */
	public UnripeCrop() {
		super(',');
	}
	
	/**
	 * decreases ripenCount whenever a tick occurs and replace unripe crop with ripe crop when 
	 * ripenCount becomes 0
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);
		
		ripenCount--;
		if (ripenCount <= 0) {
			location.setGround(new RipeCrop());
		}
	}
}
