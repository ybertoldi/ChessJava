public class Bishop extends Piece{

    public Bishop(boolean white){
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (start.getPiece().isWhite() == end.getPiece().isWhite()){
            return false;
        }

        if (Math.abs(start.getX() - end.getX()) != Math.abs(start.getY() - end.getY())){
            return false;
        }

        return pathIsClearDiagonally(board, start, end);
    }
}
