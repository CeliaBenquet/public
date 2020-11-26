package ch.epfl.sweng;

import java.util.Objects;

/**
 * Can make 2 moves
 * queen, rook, bishop, knight but not pawn or king
 * born only through promotion
 */



public class Superpiece extends Piece {

    private Piece piece;


    public Superpiece(Piece piece) {
        super(piece.getPosition(), piece.getColor());
        this.piece = piece;
    }

    @Override
    protected boolean isPieceMovementValid(Position.Offset offset) {
        return piece.isPieceMovementValid(offset);
    }

    @Override
    public Superpiece copy() {
        return new Superpiece(this);
    }

    public void moveTo(Position intermediateDestination, Position finalDestination) throws InvalidMoveException, InvalidPositionException {
        // Do a copy in case second move doesn't work
        Piece underlyingCopy = piece.copy();
        underlyingCopy.moveTo(intermediateDestination);
        underlyingCopy.moveTo(finalDestination);

        // If no error thrown then we can move the real one
        this.setPosition(finalDestination);
        this.piece = underlyingCopy;
    }

    public void moveTo(char intermediateColumn, int intermediateRow, char finalColumn, int finalRow) throws InvalidMoveException, InvalidPositionException {
        Piece underlyingCopy = piece.copy();
        underlyingCopy.moveTo(intermediateColumn,intermediateRow);
        underlyingCopy.moveTo(finalColumn, finalRow);

        Position destination = Position.positionIfLegal(finalColumn, finalRow);
        this.setPosition(destination);
        this.piece = underlyingCopy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Superpiece that = (Superpiece) o;
        return this.piece.equals(that.piece);
    }
}
