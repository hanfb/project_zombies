package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Base class for aiming attack action
 */
public abstract class MultiTurnAimingAction extends SniperAttackAction {
    protected Actor target; //Target to be attacked
    protected int counter; //Turn counter for aiming
    protected boolean isAimingComplete; //Status of aiming

    public MultiTurnAimingAction(Actor target, SniperRifle fireArmToShoot,int counter){
        super(fireArmToShoot);
        this.target = target;
        this.counter = counter;
    }

    /**
     * Function for checking the aiming status
     * @return boolean flag which determine is the aiming completed or not
     */
    public boolean isAimingComplete() {
        return isAimingComplete;
    }
}
