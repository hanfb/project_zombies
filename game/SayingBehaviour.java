package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

import java.util.Random;

/**
 *
 * @author Chutiwat Banyat
 */
public class SayingBehaviour implements Behaviour{

    /**
     * Function for factorising sayAction for return to world class and execute it
     * @param actor Zombie object
     * @return Zombie say action when the chance condition is met otherwise null action
     *
     */
    private Action sayActionFactory(Actor actor){
        double chance =Util.rand.nextDouble();
        if(chance < 0.1)
            return new ZombieSayAction();
        else
            return null;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        return sayActionFactory(actor);
    }
}
