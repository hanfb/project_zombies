package game;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * Action for zombie limb which allow player to equip the zombie limb as a zombie club weapon
 * @author Chutiwat Banyat
 *
 */
public class EquipZombieLimbsAction extends Action {
	private ZombieLimb limbsToEq = null;
	public EquipZombieLimbsAction(ZombieLimb limb){
	    limbsToEq = limb;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
	    limbsToEq.getPickUpAction().execute(actor,map);
	    actor.addItemToInventory(new ZombieClub("ZombieClub",'|',12,"hit"));
	    actor.removeItemFromInventory(limbsToEq);
	    return actor + " equip " + limbsToEq + " as a weapon" ;
	}
	
	@Override
	public String menuDescription(Actor actor) {
	    return  "Equip limbs as weapon";
	}
}
