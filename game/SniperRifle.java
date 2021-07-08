package game;
/**
 * Sniper rifle class
 * @author Chutiwat Banyat
 *
 */
public class SniperRifle extends FireArm {
    public SniperRifle(String name) {
        super(name, 'R', 85, "snipe", FireArmType.SNIPER);
        this.allowableActions.add( new SniperAttackAction(this));
    }
}
