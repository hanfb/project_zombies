package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * Base class fore firearm in this game
 * @author Chutiwat Banyat
 */
public abstract class FireArm extends WeaponItem {
    protected FireArmType fireArmType; //Enum type of firearm

    public FireArm(String name, char displayChar, int damage, String verb, FireArmType fireArmType) {
        super(name, displayChar, damage, verb);
        this.fireArmType = fireArmType;
    }
}
