package ee.bcs.valiit.tasks;

class PawnDistance {
    private String color;
    private long distance;
    public PawnDistance(String s, long d) {
        color = s;
        distance = d;
    }
}

public class PawnGame {
    public static PawnDistance redKnight(int knight, long pawn) {
        String answer = "";
        int posKnight = 0;
        int posPawn = (int) pawn;
        int moves = 0;
        for (int i = 0; i < 1000001; i++) {
            if (posKnight == posPawn) {
                break;
            } else {
                posKnight = posKnight + 2;
                posPawn++;
                moves++;
            }
        }

        if (knight == 0 && moves % 2 == 0 ) {
            answer = "White";
        } else if (knight == 0 && moves % 2 != 0 ){
            answer = "Black";
        } else if (knight == 1 && moves % 2 != 0) {
            answer = "White";
        } else {
            answer = "Black";
        }
    return new PawnDistance(answer, posPawn);
    }
}
