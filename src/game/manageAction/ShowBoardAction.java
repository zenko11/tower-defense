package game.manageAction;

import game.board.Board;

/**A class to show the board */
public class ShowBoardAction implements ManageAction{
    /**the board */
    protected Board board ;

    /**
     * A action with the board
     * @param board
     */
    public ShowBoardAction(Board board){
        this.board = board;
    }

    /**
     * Perform the action of showing the board
     */
    @Override
    public void perform(){
        try {
            this.board.show();
        } catch (Exception e){
            
        }
    }
    @Override
    public String toString(){
        return "Show the board";
    }
}


