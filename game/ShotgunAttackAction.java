package game;
import edu.monash.fit2099.engine.*;
import java.util.ArrayList;

/**
 * Action for shotgun
 * @author Chutiwat Banyat
 */
public class ShotgunAttackAction extends FireArmAttackAction {
    private Shotgun shotgun;

    public ShotgunAttackAction(Shotgun shotgun) {
        this.shotgun = shotgun;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String actionReport = actor + "shoot nothing";
        Location here = map.locationOf(actor);
        ArrayList<Actor> toHurtLsit = new ArrayList<>();
        NumberRange xs = null, ys = null;

       if(ammoCheck(actor,FireArmType.SHOTGUN)){

           String user_input =Util.getUserInput("Choosing shotgun firing direction\nn:Fire to North direction\n" +
                   "s: Fire to South direction\n" +
                   "w: Fire to West direction\n" +
                   "e: Fire to East direction\n" +
                   "q: Stop firing");

           //Shotgun ammo shell splash
           switch(user_input){
               case "e":
               //Shoot east
                xs = new NumberRange(here.x()+1,3);
                ys = new NumberRange(here.y()-1,3);
               break;

               case "w":
               //Shoot west
                xs = new NumberRange(here.x()-3,3);
                ys = new NumberRange(here.y()-1,3);
               break;

               case "s":
               //Shoot south
                xs = new NumberRange(here.x()-1,3);
                ys = new NumberRange(here.y()+1,3);
                break;

               case "n":
               //Shoot north
                xs = new NumberRange(here.x()-1,3);
                ys = new NumberRange(here.y()-3,3);
               break;

               case "q":
                   return "Not firing anything";
               default:
                   Util.printer.println("Invalid option");
               break;
           }

           for(int x :xs){
               for(int y:ys){
                   Location toGet = new Location(map,x,y);
                   if(toGet.getActor() != null){
                       if(map.at(x,y).getGround().blocksThrownObjects())
                           break;
                       else
                        toHurtLsit.add(map.getActorAt(toGet));
                   }
               }
           }

           if(!toHurtLsit.isEmpty()){
               actionReport = "";

               for(Actor a:toHurtLsit){
                   a.hurt(shotgun.damage());
                   actionReport += actor + " shot " + a + " for " + shotgun.damage() + " damage\n";
               }

               //Check if actor is killed
               for(Actor a:toHurtLsit){
            	   checkKill(a, map); // changed by Kevin
            	   /*
                   if(!a.isConscious()){
                       map.removeActor(a);
                       Util.printer.println(a + "is killed");
                   }
                   */
               }
           }
           ammoConsume(actor,FireArmType.SHOTGUN);
       } else{
           actionReport = "No ammo to fire!!!";
       }
        return actionReport;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Fire a shotgun";
    }
}
