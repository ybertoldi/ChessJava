public class King extends Piece{
    private  boolean  castlingDone = false;

    public King(boolean white){
        super(white);
    }

    public boolean  canCastle(){
        return !(castlingDone);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        
    }
}
