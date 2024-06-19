
public abstract class Piece {

    private boolean killed = false;
    private boolean white = false;

    public Piece(boolean white){
        this.white = white;
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

    public abstract boolean canMove(Board board, Spot start, Spot end);

}
