package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.ArrayList;

/**
 * SniperAttack action class for SniperRifle object
 * @author Chutiwat Banyat
 */
public class SniperAttackAction extends FireArmAttackAction {
    protected SniperRifle rifleToFire;

    public SniperAttackAction(SniperRifle rifleToFire){
        this.rifleToFire = rifleToFire;
    }

    /**
     * Function for searching all Zombie and MamboMarie actor in the map
     * @param map current map that player is there
     * @return ArrayList of zombie actor
     */
    private ArrayList<Actor> locateTargets(GameMap map){
        ArrayList<Actor> targetList = new ArrayList<>();
        Actor toAdd;
        for(int x:map.getXRange()){
            for(int y:map.getYRange()){
                toAdd = map.at(x,y).getActor();
                if(toAdd != null && (toAdd.hasCapability(ZombieCapability.UNDEAD) || toAdd.hasCapability(ZombieCapability.MA_MARIE)))
                    targetList.add(toAdd);
            }
        }
        return targetList;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Player actorToPlayer = ((Player)actor);
        String actionReport = actor + " shoot nothing.";
        String userInput = "";
        ArrayList<Actor> zombies = locateTargets(map);
        Actor target = null;
        String targetList = "";
        boolean isTargetSelect = false;
        
        int ct = 0; //Target counter (number of targets that player can shoot)
        //Process target list
        if(!zombies.isEmpty()){
            for(Actor a:zombies){
                targetList += ct+" Aim at "+ a + "\n";
                ct++;
            }
        }

        if(!zombies.isEmpty()){
        	//Validating input for target choosing the loop will break only when the target is valid
        	while(!isTargetSelect) {
        		try {
            		userInput = Util.getUserInput("Select your target\nq: Cancel the aim\n"+targetList);
                    //Process user input for target choosing
                    if(userInput.equalsIgnoreCase("q"))
                        return "Aim canceled";
                    else{
                        if(Integer.parseInt(userInput) >= 0 && Integer.parseInt(userInput) <=ct-1){
                            target = zombies.get(Integer.parseInt(userInput));
                            isTargetSelect = true;
                        }else {
                        	Util.printer.println("Invalid target number please try again");
                        	isTargetSelect = false;
                        }
                    }
            	}
            	catch(NumberFormatException nfe) {
            		Util.printer.println("Invalid target number please try again");
            		isTargetSelect = false;
            	}
        	}
            
        } else
            return "No target to choose";

        //String for firing option which will take a user input and determine which option 
        String fireOption = Util.getUserInput("Choose your firing option\n"
        +"n: No aim standard fire\n" +
                "s: Standard aim 1 turn wait\n" +
                "t: two rounds aim 2 turn wait");

        if(ammoCheck(actor,FireArmType.SNIPER)){
            if(target != null){
                switch(fireOption){
                	//Case for standard fire
                    case "n":
                        if(Util.rand.nextDouble() <= 0.75){
                            target.hurt(rifleToFire.damage());
                            actionReport = actor + " shoot " + target;
                        } else
                            actionReport = actor + " miss " + target;

                        break;
                    //Case for single turn aiming
                    case "s":
                        actorToPlayer.setSnipeModeFlag(true);
                        actorToPlayer.setSnipeAction(new SingleTurnAimingAction(target,rifleToFire));
                        return "1 turn aiming is set!";
                    //Case for double turn aiming
                    case "t":
                        actorToPlayer.setSnipeModeFlag(true);
                        actorToPlayer.setSnipeAction(new DoubleTurnAiming(target,rifleToFire));
                        return "2 turn aiming is set!";
                }
                checkKill(target,map);
                ammoConsume(actor,FireArmType.SNIPER);
            }
        } else {
            actionReport = "No ammo to fire!!!";
        }
        return actionReport;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Do snipe";
    }
}