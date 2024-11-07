import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{
    static int wInBoard = 0;
    static int bInBoard = 0;
   

    public Queen (boolean white){
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
        setType("Q" + n);
    }
    
    public void updateWhosAttacking(Spot start, Spot end, Board board) {
    	List<Spot> newList = new ArrayList<Spot>();
    	
    	for (int i = 0; i < isAttacking.size(); i++) {
    		isAttacking.get(i).getPiece().removeAttacker(start);
    		isAttacking.remove(i);
    	}
    	
    	for (int i = 0; i < 8; i++) {
    		Spot s = board.findNextSpot(end, i);
    		
    		if (s.getPiece() != null && s.getPiece().isWhite() != this.isWhite()) {
    			newList.add(s);
    			s.getPiece().setBeingAttacked(true);
    			s.getPiece().addAttacker(end);;
    		}
    	}
    	
    	isAttacking = newList;
    }
    
    public void updateWhosAttacking(Spot curPos, Board board) {
    	List<Spot> newList = new ArrayList<Spot>();
    	
    	for (int i = 0; i < isAttacking.size(); i++) {
    		isAttacking.get(i).getPiece().removeAttacker(curPos);
    		isAttacking.remove(i);
    	}
    	
    	for (int i = 0; i < 4; i++) {
    		Spot s = board.findNextSpot(curPos, i);
    		
    		if (s.getPiece() != null && s.getPiece().isWhite() != this.isWhite()) {
    			newList.add(s);
    			s.getPiece().setBeingAttacked(true);
    			s.getPiece().addAttacker(curPos);
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
        
        if (! moveIsLegal(board, start, end)) {
        	return false;
        }

        if (start.isDiagonallyAligned(end) && pathIsClearDiagonally(board, start, end)) {

        	return true;
        }
            	
            	
        if (start.isStraightlyAligned(end) && pathIsClearStraightly(board, start, end) ) {
        	return true;
        }

        return false;
    }
}
