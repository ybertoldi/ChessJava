public class Spot {
	
    public Piece piece;
    public int x;
    public int y;

    public Piece getPiece() {
        return piece;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    
    public Spot(Piece piece, int x, int y) {
        this.piece = piece;
        this.x = x;
        this.y = y;
    }

    public boolean isDiagonallyAligned(Spot s){
        return x - y == s.getX() - s.getY() || x + y == s.getX() + s.getY();
    }
    
    public boolean isDiagonallyAligned(Spot s1, Spot s2){
        int asc1 = x - y;
        int asc2 = s1.getX() - s1.getY();
        int asc3 = s2.getX() - s2.getY();
        
        int desc1 = x + y;
        int desc2 = s1.getX() + s1.getY();
        int desc3 = s2.getX() + s1.getY();
        
        return (asc1 == asc2 && asc2 == asc3) || (desc1 == desc2 && desc2 == desc3); 
    }

    public boolean isStraightlyAligned(Spot s){
        return (x == s.getX() || y == s.getY());
    }
    
    public boolean isStraightlyAligned(Spot s1, Spot s2){
        return (x == s1.getX() && x == s2.getX() )|| (y == s1.getY() && y == s2.getY());
    }
    
    
    
}
