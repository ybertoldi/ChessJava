public class Rook extends Piece{
    boolean moved = false;

    public Rook(boolean white){
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece().isWhite() == this.isWhite()){
            return false;
        }

        if (start.getX() - end.getX() != 0 && start.getY() - end.getY() != 0){
            return false;
        }

        return pathIsClearStraightly(board, start, end);
    }
    
}
