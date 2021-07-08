package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Behaviour factory for Mambo marie to summons zombies every 10 turn
 * @author Chutiwat Banyat
 */
public class SummonZombieBehaviour implements Behaviour {
    /**
     * Function for generating zombie on the map if the actor is MAMBO MARIE
     * @param actor mambo marie
     * @return SummonZombieAction if the actor is MamboMarie
     */
    public Action summonZombie(Actor actor){
        if(actor.hasCapability(ZombieCapability.MA_MARIE)){
            if(((MamboMarie)actor).getTurnCounter() % 10 == 0)
                return new SummonZombieAction();
            else
                return null;
        }
        return null;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        return summonZombie(actor);
    }
}
