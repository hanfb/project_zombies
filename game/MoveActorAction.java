package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Action class for moving actor to the difference map
 * @author Chutiwat Banyat
 */
public class MoveActorAction extends Action {
    protected Location locToMove;
    protected String locDesc;

    /**
     * Constructor for MoveActor action
     * @param locToMove Location class of the destination
     * @param locDesc Description of the destination
     */
    public MoveActorAction(Location locToMove, String locDesc){
        this.locToMove = locToMove;
        this.locDesc = locDesc;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor,locToMove);
        return actor + " move to " + locDesc;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " move to " + locDesc;
    }
}
