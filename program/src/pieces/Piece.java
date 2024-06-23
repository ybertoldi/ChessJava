
public abstract class Piece {

    private boolean killed = false;
    private boolean white = false;
    private boolean moved;
    private String type;

    public Piece(boolean white){
        this.white = white;
        this.setMoved(false);
    }

    
    public void kill(){
        killed = true;
    }

    public boolean isKilled(){
        return  killed;
    }

    
    public boolean isWhite(){
        return white;
    }

    public void setType(String t){
        this.type = t;
    }

    public String getType(){
        return this.type;
    }

    public void setMoved(boolean b){
        this.moved = b;
    }

    public boolean hasMoved(){
        return moved;
    }

    public boolean pathIsClearDiagonally(Board board, Spot start, Spot end){
        int sX = start.getX();
        int sY = start.getY();

        int eX = end.getX();
        int eY = end.getY();
        
        if ( sX < eX && sY < eY){
            while(sX < eX - 1 && sY < eY - 1){
                sX++;
                sY++;
                if (board.getBox(sX,sY).getPiece() != null){
                    return false;
                }
            }
        }

        else if (sX > eX && sY < eY){

            while(sX > eX + 1 && sY < eY - 1){
                sX--;
                sY++;
                if (board.getBox(sX,sY).getPiece() != null){
                    return false;
                }
            }
        }

        else if (sX < eX && sY > eY){

            while(sX < eX - 1 && sY > eY + 1){
                sX++;
                sY--;
                if (board.getBox(sX,sY).getPiece() != null){
                    return false;
                }
            }
        }

        else if (sX > eX && sY > eY){

            while(sX > eX + 1&& sY > eY + 1){
                sX--;
                sY--;
                if (board.getBox(sX,sY).getPiece() != null){
                    return false;
                }
            }
        }


        return true;
    }

    public boolean pathIsClearStraightly(Board board, Spot start, Spot end){
        int sX = start.getX();
        int sY = start.getY();

        int eX = end.getX();
        int eY = end.getY();

        int deltaX = eX - sX;
        int deltaY = eY - sY;

        if (deltaX == 0 && deltaY > 0){
            while (sY < eY - 1){
                sX++;
                if (board.getBox(sX,sY).getPiece() != null){
                    return false;
                }
            }
        }
        else if (deltaX == 0 && deltaY < 0){
            while (sY > eY + 1){
                sX--;
                if (board.getBox(sX,sY).getPiece() != null){
                    return false;
                }
            }
        }
        else if (deltaX > 0 && deltaY == 0){
            while (sX < eX - 1){
                sX++;
                if (board.getBox(sX,sY).getPiece() != null){
                    return false;
                }
            }
        }
        else if (deltaX < 0 && deltaY == 0){
            while (sX > eX + 1){
                sX--;
                if (board.getBox(sX,sY).getPiece() != null){
                    return false;
                }
            }
        }

        return true;
    };



    public boolean isExposingTheKing(Board board, Spot start, Spot end){
        Spot kingSpot = board.getTrackedPiece("K", this.isWhite());
        
        if (start.isDiagonallyAligned(kingSpot)){
            String canMoveDiagonally[] = {"B1", "B2", "Q"};
            for (int i = 0; i <= 2; i++){
                Spot currentThreat = board.getTrackedPiece(canMoveDiagonally[i], !this.isWhite());

                boolean currentThreatIsAlignedToKingAndPiece = currentThreat.isDiagonallyAligned(kingSpot) && currentThreat.isDiagonallyAligned(start);
                boolean currentThreatIsNotBlocked = currentThreat.getPiece().pathIsClearDiagonally(board,currentThreat, start);
                
                if (currentThreatIsAlignedToKingAndPiece && currentThreatIsNotBlocked){
                    return true;
                }
            }
        }

        else if (start.isStraightlyAligned(kingSpot)){
            String canMoveStraightly[] = {"R1", "R2", "Q"};
            for (int i = 0; i <= 2; i++){
                Spot currentThreat = board.getTrackedPiece(canMoveStraightly[i], !this.isWhite());

                boolean currentThreatIsAlignedToKingAndPiece = currentThreat.isStraightlyAligned(kingSpot) && currentThreat.isDiagonallyAligned(start);
                boolean currentThreatIsNotBlocked = currentThreat.getPiece().pathIsClearStraightly(board,currentThreat, start);
                
                if (currentThreatIsAlignedToKingAndPiece && currentThreatIsNotBlocked){
                    return true;
                }
            }
        }

        return false;
    }

    public abstract boolean canMove(Board board, Spot start, Spot end);

}
