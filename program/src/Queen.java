public class Queen extends Piece{
    
    public Queen (boolean white){
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (start.getPiece().isWhite() == end.getPiece().isWhite()){
            return false;
        }

        boolean movingDiagonally = Math.abs(start.getX() - end.getX()) == Math.abs(start.getY() - end.getY());
        boolean movingStraightly = start.getX() - end.getX() == 0 && start.getY() - end.getY() == 0;

        if (movingDiagonally){
            return pathIsClearDiagonally(board, start, end);
        }
        if (movingStraightly){
            return pathIsClearStraightly(board, start, end);
        }
        else {
            return false;
        }
    }

    
}
