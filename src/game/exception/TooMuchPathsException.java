package game.exception;
// class for the TooMuchPathsException
public class TooMuchPathsException extends Exception{

    /*constructor without message*/
    public TooMuchPathsException(){
        super();
    }
    /**constructor with message
     * @param msg the message
     */
    public TooMuchPathsException(String msg){
        super(msg);
    }
}