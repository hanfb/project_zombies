package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class SummonZombieAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        int x, y = 0;
        if (actor.hasCapability(ZombieCapability.MA_MARIE)) {
            if (((MamboMarie) actor).getTurnCounter() % 10 == 0) {
                for (int i = 0; i < 5; i++) {
                    do {
                        x = (int) Math.floor(Math.random() * 20.0 + 30.0);
                        y = (int) Math.floor(Math.random() * 7.0 + 5.0);
                    }
                    while (map.at(x, y).containsAnActor());
                    map.at(x, y).addActor(new Zombie("Mambo-Marie slave"));
                }
            }
        }
        return actor + "summon more zombies!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Summon Zombie";
    }
}
