package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action class for zombie to randomly says zombie-like word
 * @author Chutiwat Banyat
 *
 */
public class ZombieSayAction extends Action {
    //Array that of zombie-like words
    private String[] zombieLikeWord = {
            "Brainnnnnnn",
            "Guuuuruuuuuuu",
            "Yeeeeeeeeeeeeeeet", 
            "Burhhhhhhhhhhhhhhh",
            "Auuuuuuuuuuuuuuuu",
            "Raaauuuughhhhhh",
    };

    /**
     * Function for returning zombie like quote from zombie
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Returning string of dialog that randomly pickup
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor) + zombieLikeWord[Util.rand.nextInt(zombieLikeWord.length)];
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " says: ";
    }
}
