public class Bishop extends Piece{

    
    public Bishop(boolean white){
        super(white);
        setType("B");
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (this.isWhite() && (end.getPiece() != null && end.getPiece().isWhite())){
            return false;
        }

        if (start.isDiagonallyAligned(end)){
            return pathIsClearDiagonally(board, start, end);
        }
        else {
            return false;
        }
    }
}
