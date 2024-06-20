public class Game {
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
    public static void main(String[] args) {
        Board b = new Board();
        draw(b);
        b.movePiece(b.getBox(1, 0), b.getBox(2, 2));
        draw(b);
    }
}
