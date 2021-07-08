package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * Class for MamboMarie (Zombie boss)
 * @author Chutiwat Banyat
 */

public class MamboMarie extends ZombieActor {
	  private int turnCounter; //Counter for mambo marie for moving and summoning zombies every 10 turns
	    private int appearCounter; //Counter for controlling her appearance
	    private boolean isAppear; //Flag to determine mamboMarie's appearance status
	    private boolean delMoveBehaviour = false; //Flag for remove wander behaviour
	    private ArrayList<Behaviour> behaviourList;
	    private int locToAppear_x;
	    private int locToAppear_y;
	    private int oldLoc_x;
	    private int oldLoc_y;


	    public MamboMarie(int hitPoints) {
	        super("MamboMarie", '.', hitPoints, ZombieCapability.UNDEAD);
	        this.removeCapability(ZombieCapability.UNDEAD);
	        this.removeCapability(ZombieCapability.MA_MARIE);
	        turnCounter = 1;
	        behaviourList = new ArrayList<>();
	        behaviourList.add(new SummonZombieBehaviour());
	    }

	    /**
	     * Return current value of turn counter
	     * @return current turn number
	     */
	    public int getTurnCounter(){
	        return turnCounter;
	    }

	    /**
	     * Function for checking turn of move for MamboMarie
	     * the function will add wander behaviour to behaviour list then reset the counter if the turn counter
	     * is ten else the wander behaviour wil be removed.
	     */
	    private void checkMovable(){
	        if(turnCounter % 10 == 0){
	            behaviourList.add(new WanderBehaviour());
	            delMoveBehaviour = true;
	        } else if(delMoveBehaviour && turnCounter % 10 != 0) {
	            behaviourList.remove(behaviourList.size()-1);
	            delMoveBehaviour = false;
	        }
	    }

	    /**
	     * Function for controlling mambo marie appearance by allow her to appear every 30 turn
	     * then if the counter of her appearance is 30 hid her from the map also remove all her capabilities
	     * so she can't do anything during the hiding phrase
	     * @param map current map that mambo marie is on
	     */
	    private void manageAppearance(GameMap map){
	    	//Array for corner map location X and Y
	        int locList_x[] = {0,0,0,79};
	        int locList_y[] = {0,79,24,24};
	        
	        //Variable for randoming the location for mambo marie to appear
	        int locToGo;

	        if(Util.rand.nextDouble() >= 0.50 && !isAppear){
	        	this.displayChar = 'M';
	            locToGo = Util.rand.nextInt(locList_x.length);
	            locToAppear_x = locList_x[locToGo];
	            locToAppear_y = locList_y[locToGo];

	            //Re-random the location if new location is the same is the old one
	            while(locToAppear_x == oldLoc_x && locToAppear_y == oldLoc_y ){
	                locToGo = Util.rand.nextInt(locList_x.length);
	                locToAppear_x = locList_x[locToGo];
	                locToAppear_y = locList_y[locToGo];
	            }

	            isAppear = true;
	            
	            //Move Mambo Marie to the location and add her capability back
	            map.moveActor(this,new Location(map,locToAppear_x,locToAppear_y));
	            this.addCapability(ZombieCapability.UNDEAD);
	            this.addCapability(ZombieCapability.MA_MARIE);
	            Util.printer.println("Mambo Marie has appear!");
	            
	            oldLoc_x = locList_x[locToGo];
	            oldLoc_y = locList_y[locToGo];
	        }

	        //Controller when ambo marie is appeared the counter will start counting to 30
	        //then she will disappear  
	        if(isAppear) {
	            appearCounter++;
	            if(appearCounter == 30){
	                this.displayChar = '.';
	                this.removeCapability(ZombieCapability.UNDEAD);
	                this.removeCapability(ZombieCapability.MA_MARIE);
	                Util.printer.println("Mambo Marie has disappear!");
	                appearCounter = 0;
	                isAppear = false;
	            }
	        }
	    }

	    /**
	     * Function for checking that is the player on the same map of MamboMarie
	     * @param map
	     * @return boolean value for identifying is the player is on the same map of MamboMarie
	     */
	    private boolean checkPlayerAtMarieMap(GameMap map){
	        NumberRange x_loc = map.getXRange();
	        NumberRange y_loc = map.getYRange();
	        for(int y = 0;y< y_loc.max();y++){
	            for(int x = 0; x < x_loc.max();x++){
	                Actor toGet = map.getActorAt(new Location(map,x,y));
	                if( toGet != null && toGet.hasCapability(ZombieCapability.PLAYER)){
	                    return true;
	                }
	            }
	        }
	        return false;
	    }

	    @Override
	    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
	       if(checkPlayerAtMarieMap(map)){
	           manageAppearance(map);
	           checkMovable();
	           turnCounter++;
	           for(Behaviour behaviour:behaviourList){
	               Action action = behaviour.getAction(this,map);

	               if(action != null)
	                   return action;
	           }
	       }
	        return new DoNothingAction();

	    }
}
