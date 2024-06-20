public class Rook extends Piece{
    static int numberInBoard = 0;

    public Rook(boolean white){
        super(white);
        numberInBoard++;
        if(numberInBoard == 3){
			numberInBoard = 1;
		}
        setType("R" + numberInBoard);
        setMoved(false);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (this.isWhite() == (end.getPiece() != null && end.getPiece().isWhite())){
            return false;
        }

        if (start.getX() - end.getX() != 0 && start.getY() - end.getY() != 0){
            return false;
        }

        if (isExposingTheKing(board, start, end)){
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
