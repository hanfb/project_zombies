package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import java.util.List;
import edu.monash.fit2099.engine.WeaponItem;
import edu.monash.fit2099.engine.World;
/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}
	
	/**
	 * Function for zombie to randomly drop its arm after finish its attack
	 * @param actor Zombie actor
	 * @param map map object for determine a location if zombie limb to drop
	 */
	private void doDropLimb(Actor actor,GameMap map){
		List<Item> zombieLimbsList = actor.getInventory();
		Item toDrop = null;
		if(actor instanceof Zombie){
			if(actor.getInventory().size() != 0){
				if(rand.nextDouble() <= 0.5){
					if(zombieLimbsList.size() != 0){
						//Drop random limb with 25%chance
						int indexToGet = Util.rand.nextInt(zombieLimbsList.size());
						Item pickedLimb = zombieLimbsList.get(indexToGet);

						pickedLimb.getDropAction().execute(actor,map);
						Util.printer.println(actor.toString() + " Drop its " + pickedLimb.toString());
						

						if(((Zombie) actor).isWeaponExists()){
							for(Item item:actor.getInventory()){
								if(item instanceof WeaponItem)
									toDrop = item;
							}
							toDrop.getDropAction().execute(actor,map);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Special attack function for zombie
	 * the function will generate random number and compare with the condition then return the result
	 * @param actor Zombie actor
	 * @return boolean result of special attack flag
	 */
	private boolean specialAttack(Actor actor){
		if(actor instanceof Zombie){
			return rand.nextDouble() <= 0.5;
		}
		return false;
	}

	/**
	 * Function for zombie to perform normal bite attack only when it got no limbs left
	 * @param actor
	 * @return boolean flag to determine that does zombie has to perform bite attack only?
	 */
	private boolean biteOnly(Actor actor){
		Zombie attacker = null;
		if(actor instanceof  Zombie){
			attacker = ((Zombie)actor);
			return attacker.getCountPart(ZombiePart.ARM) == 0;
		}
		return false;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		
		if(actor instanceof Zombie){
			if(specialAttack(actor)){
				actor.heal(5);
				damage = 20;
				result = actor + " bites " + target + " for " + damage + " damage " + "and regains 5 health points.";
			}

			if(biteOnly(actor)){
				result = actor + " bites " + target + " for " + damage + " damage";
			}
			//Random drop limbs when zombie finishes it attack
			doDropLimb(actor,map);

			//Assign damage to the target when zombie performs its attack
			target.hurt(damage);
		}
		
		target.hurt(damage);
				
		if (!target.isConscious()) {
			// Create corpse if human is killed
			if (target.hasCapability(ZombieCapability.ALIVE)) {
				Ground corpse = new DeadHuman();
				map.locationOf(target).setGround(corpse);
			}
			if (target.hasCapability(ZombieCapability.MA_MARIE)) {World.slainMarie = true;}
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
