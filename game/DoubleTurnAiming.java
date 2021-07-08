package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

/**
 * 2 turn instakill action for sniper rifle attack
 * @author Chutiwat Banyat
 */
public class DoubleTurnAiming extends MultiTurnAimingAction {

    public DoubleTurnAiming(Actor target, SniperRifle fireArmToShoot) {
        super(target, fireArmToShoot, 2);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if(counter == 0){
        	if (target.hasCapability(ZombieCapability.MA_MARIE)){World.slainMarie = true;} // add by Kevin
            map.removeActor(target);
            isAimingComplete = true;
            ammoConsume(actor,FireArmType.SNIPER);
            return target + " is killed instantly!";
        } else {
            counter --;
            return "Aiming";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        switch(counter){
            case 2:
                return "Do 2 turn aim";
            case 1:
                return "One more turn for aiming";
            case 0:
                return "Fire instakill shot";
        }
        return "";
    }
}
