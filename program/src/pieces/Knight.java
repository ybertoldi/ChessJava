public class Knight extends Piece { 
	static int numberInBoard = 0;

	public Knight(boolean white) 
	{ 
		super(white); 
		numberInBoard++;
		if(numberInBoard == 3){
			numberInBoard = 1;
		}
		setType("N" + numberInBoard);
		
	} 

	@Override
	public boolean canMove(Board board, Spot start, 
											Spot end) 
	{ 
		// we can't move the piece to a spot that has 
		// a piece of the same colour 
		if( end.getPiece() != null){
            if(this.isWhite() == end.getPiece().isWhite()){
                return false;
            }
        }

		int x = Math.abs(start.getX() - end.getX()); 
		int y = Math.abs(start.getY() - end.getY()); 
		
		if (x * y == 2){
			if (isExposingTheKing(board, start, end)){
				return false;
			}
			else{
				return true;
			}
		}
		
		return false;
	} 
} 
