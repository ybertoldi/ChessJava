
public class Game {
    static ChessParser parser = new ChessParser(); //

    public static void draw(Board b){  
        String indexes[] = {"a", "b", "c", "d", "e", "f", "g", "h"};
        for (int i = 7; i >= 0 ; i--){
            System.out.print(i + 1 + " | ");
            for(int j = 0; j <= 7; j++){
                Spot s = b.getBox(j, i);
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

    static void movePiece(Board board, boolean white, String txt){
        boolean inputIsInvalid = true;
        boolean inputIsAmbiguous = false;
        boolean moveIsIlegall = false;        

        if (parser.canParse(txt)){

            if (parser.moveIsAmbiguous(txt, board, white)){
                moveIsIlegall = false;
                inputIsAmbiguous = true;                
            }
            else{
                inputIsAmbiguous = false;

                Spot start = parser.getStart(txt, board, white);
                Spot end = parser.getEnd(txt, board, white);

                if (board.canMove(start, end)){
                    inputIsInvalid = false;
                    moveIsIlegall = false;
                    board.movePiece(start, end);
                }
                else{
                    moveIsIlegall = true;
                }
            }
            
        }
        
        else{
            System.out.print("    Move description is not according to chess notation. Please write again: ");
            inputIsAmbiguous = false;
            inputIsInvalid = false;
        }

        if (inputIsInvalid && inputIsAmbiguous){
            String moves[] = parser.correctMoves(txt, board, white);
            System.out.print("    Notation is ambiguous, you should enter one of the following moves:");
            for (int i = 0; i < moves.length; i++){
                System.out.print(" " + moves[i]);
            }
            System.out.println(".");

            System.out.print("    next movement: ");
        }

        if (inputIsInvalid && moveIsIlegall) {
            System.out.println("    Movement is ilegal.");
            System.out.print("    next movement: ");
        }
        
                                
    }

    public static void main(String[] args) {
        Board b = new Board();
        draw(b);
        
        movePiece(b, true, "Nc3");

        draw(b);

        movePiece(b, true, "Nc3");

        draw(b);
    }
}
