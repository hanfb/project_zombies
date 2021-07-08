package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A craftable weapon from zombie arm
 * 
 * @author Kevin
 *
 */
public class Club extends WeaponItem{
	
	/**
	 * constructor
	 * 
	 * 2 hit = 1 kill
	 */
	public Club() {
		super("club", 'P', 50, "slams");
	}

}
