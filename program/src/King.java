public class King extends Piece{

    private  boolean  moved = false;

    public King(boolean white){
        super(white);
    }

    public boolean  canCastle(Board board, Spot start, Spot end){
        //OBS: adicionar lógica para ver se não há peças no caminho;
        
        return !(moved);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece().isWhite() == this.isWhite()){
            return false;
        }

        int deltaX = Math.abs(start.getX() - end.getX());
        int deltaY =  Math.abs(start.getY() - end.getY());

        if (deltaX == 0 || deltaX == 1){
            if (deltaY == 0 || deltaY == 1){
                moved = true;
                return true;
            }
        }
        
        return this.canCastle(board, start, end);
    }
}
