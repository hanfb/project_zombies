package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A Zombie.
 *
 * This Zombie is pretty boring.  It needs to be made more interesting.
 *
 * @author ram
 *
 */
public class Zombie extends ZombieActor {
	private ArrayList<Behaviour> behaviours;
	private int turnCounter; //Turn counter variable for delay zombie's move when it got only one leg left
	private boolean allowMove = true;

	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
		this.inventory.add(new ZombieLimb("arm",'a',true));
		this.inventory.add(new ZombieLimb("arm",'a',true));
		this.inventory.add(new ZombieLimb("leg",'l',true));
		this.inventory.add(new ZombieLimb("leg",'l',true));
		turnCounter=0;
		behaviours = new ArrayList<>();
		behaviours.add(new SayingBehaviour());
//		behaviours.add(new AttackBehaviour(ZombieCapability.ALIVE));
//		behaviours.add(new HuntBehaviour(Human.class,10));
//		behaviours.add(new WanderBehaviour());
	}


	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "punches");
	}

	/**
	 * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.
	 * If no humans are close enough it will wander randomly.say
	 *
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multi turn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		checkWeaponToPickup(map,display);
		checkZombieMovingAbility();

		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}

	/**
	 * Function for getting index of moving behaviour
	 * @author Chutiwat Banyat
	 * @return index of MovingBehaviour
	 */
	private int getMovingBehaviorIndex(){
		return behaviours.size()-1;
	}

	/**
	 * Function for adding MovingBehaviour
	 * @author Chutiwat Banyat
	 */
	private void addMovingBehaviour(){
		behaviours.add(behaviours.size()-1,new WanderBehaviour());
	}

	/**
	 * Function for checking zombie legs and only allow zombie to move every turn if the number of legs is 2
	 * if a number of legs is 1 this function will only allow  the zombie to move every 2 turns
	 * @author Chutiwat Banyat
	 */
	private void checkZombieMovingAbility(){
		int legCounter  = getCountPart(ZombiePart.LEG);

		if(legCounter == 0 && allowMove){
			behaviours.remove(getMovingBehaviorIndex());
			allowMove = false;

		}

		if(allowMove && legCounter < 2){
			turnCounter ++;
			if(turnCounter % 2 == 0)
				addMovingBehaviour();
			else
				behaviours.remove(getMovingBehaviorIndex());
		}
	}

	/**
	 * Function for returning an integer of counted part (can be arm or legs) of the zombie and return
	 * @param partName Enum ZombiePart
	 * @return Number of selected part
	 * @author Chutiwat Banyat
	 */
	public int getCountPart(ZombiePart partName){
		int counter = 0;
		for(Item lm:this.getInventory()){
			if(lm.toString().equals("leg") && partName == ZombiePart.LEG)
				counter++;
			else if (lm.toString().equals("arm") && partName == ZombiePart.ARM)
				counter++;
		}

		return counter;
	}
	
	
	/**
	 * Function for check that if zombie is already holding a weapon
	 * @return boolean value which return false when there is no weapon in zombie inventory
	 */
	public boolean isWeaponExists(){
		for(Item item :getInventory()){
			if(item instanceof WeaponItem) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Function for zombie to pick up a weapon at its location
	 * @param map current game map
	 * @param display Display class for printing output to console
	 * @author Chutiwat Banyat
	 */
	private void checkWeaponToPickup(GameMap map, Display display){
		Location here = map.locationOf(this);
		List<Item> toGet = map.at(here.x(),here.y()).getItems();
		if(!toGet.isEmpty() && !(toGet.get(0) instanceof FireArm) && !isWeaponExists()){
			if(toGet.get(0) instanceof Weapon){
				display.println(this + "pick up a" + toGet.get(0).toString());
				toGet.get(0).getPickUpAction().execute(this,map);
			}
		}
	}
}

