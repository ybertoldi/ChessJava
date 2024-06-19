
public abstract class Piece {

    private boolean killed = false;
    private boolean white = false;

    public Piece(boolean white){
        this.white = white;
    }

    
    public void kill(){
        killed = true;
    }

    public boolean isKilled(){
        return  killed;
    }

    
    public boolean isWhite(){
        return white;
    }
    
    public abstract boolean canMove(Board board, Spot start, Spot end);

}
