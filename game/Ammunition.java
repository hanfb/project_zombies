package game;

import edu.monash.fit2099.engine.Item;

/**
 * Base class for ammunition in this game
 * @author Chutiwat Banyat
 */
public abstract class Ammunition extends Item {
    private FireArmType suitedForFirearm; //Type of the ammo

    public Ammunition(String name, char displayChar, boolean portable,FireArmType suitedForFirearm) {
        super(name, displayChar, portable);
        this.suitedForFirearm = suitedForFirearm;
    }
}
