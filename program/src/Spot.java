public class Spot {
    private Piece piece;
    private int x;
    private int y;

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

    public boolean isStraightlyAligned(Spot s){
        return (x == s.getX() || y == s.getY());
    } 
    
}
