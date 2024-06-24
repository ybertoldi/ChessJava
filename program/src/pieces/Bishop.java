public class Bishop extends Piece{
    static int wInBoard = 0;
    static int bInBoard = 0;
    
    public Bishop(boolean white){
        super(white);

        int n;        
        if(white){
            wInBoard++;
            n = wInBoard;
        }
        else{
            bInBoard++;
            n = bInBoard;
        }
        
        setType("B" + n);
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
