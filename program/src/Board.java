import java.util.HashMap;

public class Board {
    Spot[][] boxes = new Spot[8][8];
    HashMap<String, Spot> wPieces = new HashMap<>();
    HashMap<String, Spot> bPieces = new HashMap<>();

    public Board(){
        this.resetBoard();
    }

    public void resetBoard(){
        boxes[0][0] = new  Spot(new Rook(true), 0, 0);
        boxes[1][0] = new  Spot(new Knight(true), 1, 0);
        boxes[2][0] = new  Spot(new Bishop(true), 2, 0);
        boxes[3][0] = new  Spot(new Queen(true), 3, 0);
        boxes[4][0] = new  Spot(new King(true), 4, 0);
        boxes[5][0] = new  Spot(new Bishop(true), 5, 0);
        boxes[6][0] = new  Spot(new Knight(true), 6, 0);
        boxes[7][0] = new  Spot(new Rook(true), 7, 0);

        boxes[0][7] = new  Spot(new Rook(false), 0, 7);
        boxes[1][7] = new  Spot(new Knight(false), 1, 7);
        boxes[2][7] = new  Spot(new Bishop(false), 2, 7);
        boxes[3][7] = new  Spot(new Queen(false), 3, 7);
        boxes[4][7] = new  Spot(new King(false), 4, 7);
        boxes[5][7] = new  Spot(new Bishop(false), 5, 7);
        boxes[6][7] = new  Spot(new Knight(false), 6, 7);
        boxes[7][7] = new  Spot(new Rook(false), 7, 7);

        for (int xPos = 0; xPos <=7; xPos++){
            boxes[xPos][1] = new Spot(new Pawn(true), xPos, 1);
            boxes[xPos][6] = new Spot(new Pawn(false), xPos, 6);
        }

        for (int xPos = 0; xPos <= 7; xPos++){
            for (int yPos = 2; yPos <= 5; yPos++){
                boxes[xPos][yPos] = new Spot(null, xPos, yPos);
            }
        }

        startPiecesTracking();
    }

    public Spot getBox(int x, int y){
        return boxes[x][y];
    }

    public void startPiecesTracking(){
        String[] names = {"R1", "R2", "N1", "N2", "B1", "B2", "Q", "K"}; 

        for(int i = 0; i <= 7; i++){
            for (int j = 0; j <= 7; j++){

                Spot currentSpot = getBox(j, i);

                if (currentSpot.getPiece() != null){
                    String type = currentSpot.getPiece().getType();
                    boolean w = currentSpot.getPiece().isWhite();

                    for (int k = 0; k <= 7; k++){
                        if (type.equals(names[k])){
                            
                            if(w){
                                wPieces.put(type, currentSpot);
                            }
                            else{
                                bPieces.put(type, currentSpot);
                            }
                        }

                }
                
            }
        }
    }
}

    public void updateTrackedPieces(Piece piece, Spot newSpot){
        String type = piece.getType();
        boolean w = piece.isWhite();

        if (w){
            wPieces.replace(type, newSpot);
        }
        else{
            bPieces.replace(type, newSpot);
        }
    }

    public Spot getTrackedPiece(String type, boolean white){
        if (white){
            return wPieces.get(type);
        }
        else{
            return bPieces.get(type);
        }
    }

    public boolean hasTrackedPiece(String type, boolean white){
        if (white){
            return wPieces.containsKey(type);
        }
        else{
            return bPieces.containsKey(type);
        }
    }

    public void movePiece(Spot start, Spot end){
        int sX = start.getX();
        int sY = start.getY();
        int eX = end.getX();
        int eY = end.getY();
        Piece piece = start.getPiece();
        
        boxes[sX][sY].setPiece(null); 
        
        piece.setMoved(true);
        boxes[eX][eY].setPiece(piece);
        
        String pieceType = piece.getType(); 
        boolean w = piece.isWhite();
        
        if (w){
            if (wPieces.containsKey(pieceType)){
                updateTrackedPieces(piece, boxes[eX][eY]);
            }
        }
        else{
            if (bPieces.containsKey(pieceType)){
                updateTrackedPieces(piece, boxes[eX][eY]);
            }
        }
    }

    public boolean canMove(Spot start, Spot end){
        Piece piece = start.getPiece();

        if (piece == null){
            return false;
        }

        else if (start.getX() < 0 || start.getX() < 0 || start.getY() > 7 || start.getY() > 7){
            return false;
        }

        else if (end.getX() < 0 || end.getX() < 0 || end.getY() > 7 || end.getY() > 7){
            return false;
        }

        else if (piece.canMove(this, start, end)){
            return true;
        }
        
        else{
            return false;
        }
    }
}
