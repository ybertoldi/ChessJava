public class Rook extends Piece{
    static int wInBoard = 0;
    static int bInBoard = 0;

    public Rook(boolean white){
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
