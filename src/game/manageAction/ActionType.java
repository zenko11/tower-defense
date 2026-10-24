package game.manageAction;

/**
 * Represents the different types of actions a player can perform during a turn.
 */
public enum ActionType {
    /**
     * Action to purchase a new tower from the shop and place it on the game board.
     */
    BUYTOWER, 
    /**
     * Action to purchase an available (inactive) upgrade for an existing tower.
     */
    BUYUPGRADE,
    /**
     * Action to remove an existing tower from the game board and refund a portion of its cost.
     */
    SELLTOWER,
    /**
     * Action to remove an active upgrade from an existing tower.
     */
    SELLUPGRADE,
    /**
     * Action representing the decision to skip the current turn without performing any transaction.
     */
    DO_NOTHING
}
