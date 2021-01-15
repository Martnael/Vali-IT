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
        long posPawn = pawn*2;

    if ((pawn % 2 == 0 && knight == 0) || (pawn % 2 != 0 && knight == 1 ) ) {
        answer = "White";
    } else {
        answer = "Black";
    }


    return new PawnDistance(answer, posPawn);
    }
}
