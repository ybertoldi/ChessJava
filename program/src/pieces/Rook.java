import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{
    static int wInBoard = 0;
    static int bInBoard = 0;
    private List<Spot> isAttacking = new ArrayList<Spot>();

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
        setType("R" + n);
    }

    public void updateWhosAttacking(Spot start, Spot end, Board board) {
    	List<Spot> newList = new ArrayList<Spot>();
    	
    	for (int i = 0; i < isAttacking.size(); i++) {
    		isAttacking.get(i).getPiece().removeAttacker(start);
    		isAttacking.remove(i);
    	}
    	
    	for (int i = 4; i < 8; i++) {
    		Spot s = board.findNextSpot(end, i);
    		
    		if (s.getPiece() != null && s.getPiece().isKilled() != this.isWhite()) {
    			newList.add(s);
    			s.getPiece().setBeingAttacked(true);
    			s.getPiece().attackers.add(end);
    		}
    	}
    	
    	isAttacking = newList;
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
        
        if (! moveIsLegal(board, start, end)) {
        	return false;
        }


        if (pathIsClearStraightly(board, start, end)){
            setMoved(true);
            return true;
        }
        
        return false;
    }
    
    
    
}
