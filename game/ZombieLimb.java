package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;
/**
 * Class for zombie part which can use for weapon crafting and equip as a weapon
 * @author Chutiwat Banyat
 *
 */
public class ZombieLimb extends Item {
    public ZombieLimb(String name, char displayChar, boolean isPortable){
        super(name,displayChar,isPortable);
        // anyone with zombie limb can perform CraftAction and EquipActionn
        this.allowableActions.add(new CraftAction(this));
        this.allowableActions.add(new EquipZombieLimbsAction(this));
    }
   
}
