public class Pawn extends Piece{

    public Pawn(boolean white){
        super(white);
        setType("p");
        setMoved(false);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if(this.isWhite() == (end.getPiece() != null && end.getPiece().isWhite())){
            return false;
        }

        if (end.getY() - start.getY() != 1){
            return false;
        }

        if( isExposingTheKing(board, start, end)){
            return false;
        }

        if (end.getX() != start.getX()){
            if (Math.abs(start.getX() - end.getX()) != 1){
                return false;
            }

            if (end.getPiece() != null && end.getPiece().isWhite() != this.isWhite()){
                return true;
            }
            else {
                return false;
            }
        }

        return true;
    }

}
