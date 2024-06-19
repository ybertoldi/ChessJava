public class Rook extends Piece{

    public Rook(boolean white){
        super(white);
        setType("R");
        setMoved(false);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (this.isWhite() && (end.getPiece() != null && end.getPiece().isWhite())){
            return false;
        }

        if (start.getX() - end.getX() != 0 && start.getY() - end.getY() != 0){
            return false;
        }

        if (pathIsClearStraightly(board, start, end)){
            setMoved(true);
            return true;
        }
        else {
            return false;
        }
    }
    
}
