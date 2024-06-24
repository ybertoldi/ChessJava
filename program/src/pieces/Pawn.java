public class Pawn extends Piece{

    public Pawn(boolean white){
        super(white);
        setType("p");
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        
        if( end.getPiece() != null){
            if(this.isWhite() == end.getPiece().isWhite()){
                return false;
            }
        }
        

        int deltaY = end.getY() - start.getY();
        int deltaX = end.getX() - start.getX();

        if (this.isWhite()){
            if (deltaY == 1){
                if (deltaX == 0){
                    return true;
                }
                if (Math.abs(deltaX) == 1){
                    if(end.getPiece() != null && end.getPiece().isWhite() != this.isWhite()){
                        return true;
                    }
                }
            }
            else if (deltaY == 2 && !this.hasMoved()){
                return true;
            }
            
            return false;         
            
        }

        if (!this.isWhite()){
            if (deltaY == -1){
                if (deltaX == 0){
                    return true;
                }
                if (Math.abs(deltaX) == 1){
                    if(end.getPiece() != null && end.getPiece().isWhite() != this.isWhite()){
                        return true;
                    }
                }
            }
            else if (deltaY == -2 && !this.hasMoved()){
                return true;
            }
            
            return false;
        }

        return false;

    }

}
