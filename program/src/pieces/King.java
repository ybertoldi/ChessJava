public class King extends Piece{
    private boolean isInCheck;
    private Spot[] checkedBy = new Spot[2];

    public King(boolean white){
        super(white);
        setType("K");
        setInCheck(false);

    }

    public void setInCheck(boolean isInCheck) {
        this.isInCheck = isInCheck;
    }

    public boolean  canCastle(Board board, Spot start, Spot end){
        if (hasMoved()){
            return false;
        }

        else{

            boolean endCoordsAreValid;
            if (this.isWhite()){
                endCoordsAreValid = (end.getX() == 0 && end.getY() == 0) || (end.getX() == 7 && end.getY() == 0);  
            }
            else {
                endCoordsAreValid = (end.getX() == 0 && end.getY() == 7) || (end.getX() == 7 && end.getY() == 7);
            }


            boolean endIsRook = (end.getPiece() != null && end.getPiece().getType() == "K");
            if (endIsRook && endCoordsAreValid){
                return ! end.getPiece().hasMoved();
            }
            else {
                return false;
            }
        }

    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if( end.getPiece() != null){
            if(this.isWhite() == end.getPiece().isWhite()){
                return false;
            }
        }

        int deltaX = Math.abs(start.getX() - end.getX());
        int deltaY =  Math.abs(start.getY() - end.getY());

        if (deltaX == 0 || deltaX == 1){
            if (deltaY == 0 || deltaY == 1){
                setMoved(true);
                return true;
            }
        }
        
        return this.canCastle(board, start, end);
    }
}
