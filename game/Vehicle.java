package game;

import edu.monash.fit2099.engine.*;

/**
 * Vehicle abs class which can be used for many types of vehicle in the future
 */
public abstract class Vehicle extends Item {
    public Vehicle(String name, char dispChar,boolean portable){
        super(name,dispChar,portable);
    }

    /**
     * Function for adding action to vehicle
     * @param action
     */
    public void addAction(Action action){
        this.allowableActions.add(action);
    }
}
