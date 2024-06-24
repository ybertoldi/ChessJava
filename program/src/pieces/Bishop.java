public class Bishop extends Piece{
    static int numberInBoard = 0;
    
    public Bishop(boolean white){
        super(white);
        numberInBoard++;
        if(numberInBoard == 3){
			numberInBoard = 1;
		}
        setType("B" + numberInBoard);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if( end.getPiece() != null){
            if(this.isWhite() == end.getPiece().isWhite()){
                return false;
            }
        }

        if (start.isDiagonallyAligned(end)){
            if (isExposingTheKing(board, start, end)){
                return false;
            }
            else{
                return pathIsClearDiagonally(board, start, end);
            }
            
        }
        else {
            return false;
        }
    }
}
