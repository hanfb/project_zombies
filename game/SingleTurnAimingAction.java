package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Single turn attack action for sniper rifle
 * @author Chutiwat Canyat
 */
public class SingleTurnAimingAction extends MultiTurnAimingAction {

    public SingleTurnAimingAction(Actor target, SniperRifle fireArmToShoot) {
        super(target,fireArmToShoot, 1);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if(counter == 0){
            if(Util.rand.nextDouble() <= 0.90){
                target.hurt((rifleToFire.damage()*2));
            } else {
                target.hurt(rifleToFire.damage());
            }
            Util.printer.println(actor +" fire " + target);
            checkKill(target,map);
            isAimingComplete = true;
        } else {
            counter--;
            return "Aiming!";
        }
        ammoConsume(actor,FireArmType.SNIPER);
        return actor + " completed aiming";
    }

    @Override
    public String menuDescription(Actor actor) {
        if(counter == 1)
            return "Do 1 turn aiming";
        else
            return "Fire!";
    }
}
