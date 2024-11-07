
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        Piece queen = game.getPieceAt(3,0);
        
        while (!game.hasEnded()){
            game.draw();
            
            System.out.print("Enter next movement: ");         
            String input = scanner.nextLine();

            game.movePiece(input);
            
        }
        scanner.close();        

    }
}
