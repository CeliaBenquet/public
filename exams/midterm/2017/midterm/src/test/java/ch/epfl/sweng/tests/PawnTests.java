package ch.epfl.sweng.tests;

import org.junit.Test;

import ch.epfl.sweng.Color;
import ch.epfl.sweng.InvalidMoveException;
import ch.epfl.sweng.InvalidPositionException;
import ch.epfl.sweng.Pawn;
import ch.epfl.sweng.PieceType;
import ch.epfl.sweng.Position;
import ch.epfl.sweng.Queen;
import ch.epfl.sweng.Superpiece;

import static org.junit.Assert.assertEquals;

public class PawnTests extends PieceTests<Pawn> {
    private Pawn pieceWhite;

    @Override
    public void setUp() {
        piece = new Pawn(position, Color.BLACK);
        pieceWhite = new Pawn(position, Color.WHITE);
    }

    @Override
    public void testLegal() throws InvalidMoveException, InvalidPositionException {
        pieceWhite.moveTo('d', 5);
        assertEquals(pieceWhite.getPosition(), Position.positionIfLegal('d', 5));

        piece.moveTo('d', 3);
        assertEquals(piece.getPosition(), Position.positionIfLegal('d', 3));

        // can't move back: that's how pawns work :)
    }

    // testing the case of a black pawn moving upwards
    @Override
    public void testIllegal() throws InvalidMoveException, InvalidPositionException {
        piece.moveTo('d', 5);
    }

    @Test(expected = InvalidMoveException.class)
    public void testMoveBackwards() throws InvalidMoveException, InvalidPositionException {
        pieceWhite.moveTo('d', 3);
    }

    @Test(expected = InvalidMoveException.class)
    public void testMoveDiagonally() throws InvalidMoveException, InvalidPositionException {
        pieceWhite.moveTo('e', 5);
    }

    @Test(expected = InvalidMoveException.class)
    public void testMoveHorizontally() throws InvalidMoveException, InvalidPositionException {
        pieceWhite.moveTo('e', 4);
    }

    @Test(expected = InvalidMoveException.class)
    public void testMoveJump() throws InvalidMoveException, InvalidPositionException {
        pieceWhite.moveTo('d', 6);
    }

    @Test
    public void testPromotion() throws InvalidMoveException, InvalidPositionException {
        pieceWhite.moveTo('d', 5);
        pieceWhite.moveTo('d', 6);
        pieceWhite.moveTo('d', 7);
        pieceWhite.moveTo('d', 8);
        Superpiece superpiece = pieceWhite.promote(PieceType.QUEEN);
        assertEquals(superpiece.getPosition(), pieceWhite.getPosition());
    }





}
