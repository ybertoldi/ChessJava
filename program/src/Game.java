
public class Game {
    ChessParser parser; 
    Board board;

    boolean  whiteToMove;
    boolean  gameHasEnded;

    public Game(){
        board = new Board();
        parser = new ChessParser();
        whiteToMove = true;
        gameHasEnded = false;
    }

    public void reset(){
        board.resetBoard();
        whiteToMove = true;
    }

    public boolean hasEnded(){
        return gameHasEnded;
    }

    public Piece getPieceAt(String txt){
        if (txt.length() == 2){
            int coords[] = parser.getCoords(txt);
            return board.getBox(coords[0], coords[1]).getPiece();
        }
        else{
            return null;
        }
    }

    public Piece getPieceAt(int x, int y){
        return board.getBox(x, y).getPiece();
    }

    public Spot getSpot(String txt){
        if (txt.length() == 2){
            int coords[] = parser.getCoords(txt);
            return board.getBox(coords[0], coords[1]);
        }
        else{
            return null;
        }
    }

    public Spot getSpot(int x, int y){
        return board.getBox(x, y);
    }

    
    public void draw(){  
        String indexes[] = {"a", "b", "c", "d", "e", "f", "g", "h"};
        for (int i = 7; i >= 0 ; i--){
            System.out.print(i + 1 + " | ");
            for(int j = 0; j <= 7; j++){
                Spot s = board.getBox(j, i);
                if (s.getPiece() == null){
                    System.out.print(" x ");
                }
                else{
                    System.out.print(" " + s.getPiece().getType().split("")[0] + " ");
                }
            }
            System.out.println("");
        }

        System.out.print("    ");
        for (int i = 0; i <= 7; i++){
            System.out.print("___");
        }
        System.out.println("");

        System.out.print("    ");
        for (int i = 0; i <= 7; i++){
            System.out.print(" " + indexes[i] + " ");
        }
        System.out.println("");
        System.out.println("");
    }

    public void movePiece(int sX, int sY, int eX, int eY){
        Spot start = board.getBox(sX, sY);
        Spot end = board.getBox(eX, eY);

        if (board.canMove(start, end)){
            board.movePiece(start, end);
            whiteToMove = !whiteToMove;
        }
    }

    public void movePiece(String txt){
        boolean inputIsInvalid = true;
        boolean inputIsAmbiguous = false;
        boolean moveIsIlegall = false;        

        if (parser.canParse(txt)){

            if (parser.moveIsAmbiguous(txt, board, whiteToMove)){
                inputIsAmbiguous = true;                
            }
            else{
                Spot start = parser.getStart(txt, board, whiteToMove);
                Spot end = parser.getEnd(txt, board, whiteToMove);

                if (board.canMove(start, end) && start.getPiece().isWhite() == whiteToMove){
                    board.movePiece(start, end);
                    whiteToMove = !whiteToMove;
                }
                else{
                    moveIsIlegall = true;
                }
            }
            
        }
        
        else{
            System.out.println("    Move description is not according to chess notation.");
        }

        if (inputIsInvalid && inputIsAmbiguous){
            String moves[] = parser.correctMoves(txt, board, whiteToMove);
            System.out.print("    Notation is ambiguous, you should enter one of the following moves:");
            for (int i = 0; i < moves.length; i++){
                System.out.print(" " + moves[i]);
            }
            System.out.println(".");

        }

        if (inputIsInvalid && moveIsIlegall) {
            System.out.println("    Movement is ilegal.");
        }                                
    }
    
}
