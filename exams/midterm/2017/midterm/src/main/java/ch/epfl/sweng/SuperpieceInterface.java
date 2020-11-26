package ch.epfl.sweng;

public interface SuperpieceInterface {
    public void moveTo(Position intermediateDestination, Position finalDestination) throws InvalidMoveException, InvalidPositionException;
    public void moveTo(char intermediateColumn, int intermediateRow, char finalColumn, int finalRow) throws InvalidMoveException, InvalidPositionException;
}
