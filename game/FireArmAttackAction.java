package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.World;
/**
 * Base class for firearm shooting action
 * @author Chutiwat Banyat
 */
public abstract class FireArmAttackAction extends Action {

	/**
     * Function for checking shotgun ammo in actor inventory
     * @param actor Player actor
     * @return boolean flag which will determine that is there any shotgun ammo in actor's inventory
     */
    public boolean ammoCheck(Actor actor,FireArmType fireArmType){

        for(Item x:actor.getInventory()){
            if(fireArmType == FireArmType.SHOTGUN){
                if(x instanceof ShotgunAmmo)
                    return true;
            } else if(fireArmType == FireArmType.SNIPER){
                if(x instanceof RifleAmmo)
                    return true;
            }
        }
        return false;
    }

    /**
     * Function for removing shotgun ammo after the shotgun is fired
     * @param actor
     */
    public void ammoConsume(Actor actor,FireArmType fireArmType){
        Item toDel = null;
        if(fireArmType == FireArmType.SHOTGUN){
            for(Item x:actor.getInventory()){
                if(x instanceof ShotgunAmmo)
                    toDel = x;
            }
        } else if (fireArmType == FireArmType.SNIPER){
            for(Item x:actor.getInventory()){
                if(x instanceof RifleAmmo)
                    toDel = x;
            }
        }
        if(toDel != null)
            actor.removeItemFromInventory(toDel);
    }

    /**
     * Function for check the status of target actor that attacked by player still alive
     * if target's hitpoint zero, the target will be removed from the map
     * @param target Actor that attacked by the player
     * @param map Map that player and target are on
     */
    public void checkKill(Actor target, GameMap map){
        if(!target.isConscious()){
        	if (target.hasCapability(ZombieCapability.MA_MARIE)) {World.slainMarie = true;} // added by Kevin
            Util.printer.println(target + " is killed");
            map.removeActor(target);
        }
    }
}
