package ch.epfl.sweng;

public interface QueenInterface {

    //static void decorate(Position position, Color color);
    boolean isPieceMovementValid(Position.Offset offset);
}
