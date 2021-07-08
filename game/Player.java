package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;

/**
 * Class representing the Player.
 */
public class Player extends Human {

	private Menu menu = new Menu();
	private boolean isSnipeModeSet = false; //Check if playing is aiming
	private Action snipeAction = null; //Mode of sniping (1 or 2 turn waiting)

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		addCapability(ZombieCapability.PLAYER);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		actions.add(new QuitGameAction()); // for quitting game
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null){
			return lastAction.getNextAction();
		}

		if(isSnipeModeSet){
			snipingManagement(actions,lastAction);
		}
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Function for controlling sniper aiming action
	 * when there's an aiming assigned to the player also
	 * control the aiming action when aiming is in process
	 * @param actions Player actions list
	 * @param lastAction Last action that player performed
	 */
	private void snipingManagement(Actions actions, Action lastAction){
		if(isSnipeModeSet){
			actions.add(snipeAction);

			//Remove aiming action when the aim is finished
			MultiTurnAimingAction modeToRemove = ((MultiTurnAimingAction) snipeAction);
			if(modeToRemove.isAimingComplete()){
				actions.remove(modeToRemove);
				isSnipeModeSet = false;
			}

			//Remove aiming action when there's another action during the aiming process
			if(!(lastAction instanceof SniperAttackAction)){
				actions.remove(modeToRemove);
				Util.printer.println("Concentration broken. Player lost the target");
				isSnipeModeSet = false;
			}
		}
	}

	public void setSnipeModeFlag(boolean snipeModeFlag) {
		this.isSnipeModeSet = snipeModeFlag;
	}

	public void setSnipeAction(Action snipeAction) {
		this.snipeAction = snipeAction;
	}
}
