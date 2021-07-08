package game;

import edu.monash.fit2099.engine.Actor;

/**
 * Shotgun class
 * @author Chutiwat Banyat
 */
public class Shotgun extends FireArm {
    private Actor holder;
    public Shotgun(String name) {
        super(name, 'S', 50, "shot", FireArmType.SHOTGUN);
        this.allowableActions.add(new ShotgunAttackAction(this));
    }
}
