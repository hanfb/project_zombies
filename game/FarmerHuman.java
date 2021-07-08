package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
/**
 * Implements the Farmer type Human
 * 
 * @author Kevin
 *
 */
public class FarmerHuman extends Human{
	private Behaviour[] behaviours = {
			new HarvestBehaviour(),
			new FertilizeBehaviour(),
			new WanderBehaviour()
	};
	
	/**
	 * constructor 
	 * 
	 * @param name Name of the farmer
	 * @param displayChar Symbol used for displaying farmer on the map
	 */
	public FarmerHuman(String name, char displayChar) {
		super(name, displayChar, 50);
		addCapability(ZombieCapability.FARMER);
	}
	
	/**
	 * Farmer will always sow crop if there's dirt around him as a free action. If Farmer can Harvest, it will. If not, it 
	 * will Fertilize, if it also can't fertilize it will wander randomly.
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {	
		// performs sow crop on every dirt around farmer
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(this).getExits());
		Collections.shuffle(exits);
		char dirtSymbol = new Dirt().getDisplayChar();
		for (Exit e: exits) {
			if (e.getDestination().getGround().getDisplayChar() == dirtSymbol) {	
				sowCrop(e.getDestination());
			}
		}
		// gets actions for each behaviour
		for (Behaviour behaviour: behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) {
				return action;
			}
		}
		return new DoNothingAction();
	}
	
	/**
	 * creates a unripe crop at the specified location with a success rate of 33%
	 * 
	 * @param cropLocation location of the to be sowed crop
	 */
	private void sowCrop(Location cropLocation) {
		if (new Random().nextInt(10) > 3) {
			cropLocation.setGround(new UnripeCrop());
		}
	}

}
