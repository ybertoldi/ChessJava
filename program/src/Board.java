public class Board {
    Spot[][] boxes;

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

        for (int xPos = 0; xPos <= 7; xPos++){
            for (int yPos = 2; yPos <= 5; yPos++){
                boxes[xPos][yPos] = new Spot(null, xPos, yPos);
            }
        }
    }

    public Spot getBox(int x, int y){
        return boxes[x][y];
    }
}
