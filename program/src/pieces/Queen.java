public class Queen extends Piece{
    
    public Queen (boolean white){
        super(white);
        setType("Q");
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (this.isWhite() == (end.getPiece() != null && end.getPiece().isWhite())){
            return false;
        }

        if(isExposingTheKing(board, start, end)){
            return false;
        }

        if (start.isDiagonallyAligned(end)){
            return pathIsClearDiagonally(board, start, end);
        }
        else if (start.isStraightlyAligned(end)){
            return pathIsClearStraightly(board, start, end);
        }
        else {
            return false;
        }
    }

    
}
