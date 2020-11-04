package ch.epfl.sweng.tests;

import org.junit.Test;

import ch.epfl.sweng.Color;
import ch.epfl.sweng.InvalidMoveException;
import ch.epfl.sweng.InvalidPositionException;
import ch.epfl.sweng.Position;
import ch.epfl.sweng.Queen;

import static org.junit.Assert.assertEquals;

public class QueenTests extends PieceTests<Queen> {
        @Override
        public void setUp() {
            piece = new Queen(position, Color.BLACK);
        }

        @Override
        public void testLegal() throws InvalidMoveException, InvalidPositionException {

            // Diagonal moves
            for (int distance : new int[]{1, 2, 3}) {
                for (int horizontalDirection : new int[]{1, -1}) {
                    for (int verticalDirection : new int[]{1, -1}) {
                        Queen b = new Queen(position, Color.WHITE);
                        int row  = position.getVertical() + distance * horizontalDirection;
                        char col = (char) (position.getHorizontal() + distance * verticalDirection);

                        Position destination = Position.positionIfLegal(col, row);

                        // can we move forward?
                        b.moveTo(col, row);
                        assertEquals(b.getPosition(), destination);

                        // can we move back?
                        b.moveTo('d', 4);
                        assertEquals(b.getPosition(), position);
                    }
                }
            }

            for (int distance : new int[]{1, 2, 3}) {
                for (int verticalDistance : new int[]{-1, 1}) {
                    Queen b = new Queen(position, Color.WHITE);
                    int row  = position.getVertical();
                    char col = (char) (position.getHorizontal() + distance * verticalDistance);

                    Position destination = Position.positionIfLegal(col, row);

                    // can we move forward?
                    b.moveTo(col, row);
                    assertEquals(b.getPosition(), destination);

                    // can we move back?
                    b.moveTo('d', 4);
                    assertEquals(b.getPosition(), position);
                }

                for (int horizontalDistance : new int[]{-1, 1}) {
                    Queen b = new Queen(position, Color.WHITE);
                    int row  = position.getVertical() + distance * horizontalDistance;
                    char col = (char) (position.getHorizontal());

                    Position destination = Position.positionIfLegal(col, row);

                    // can we move forward?
                    b.moveTo(col, row);
                    assertEquals(b.getPosition(), destination);

                    // can we move back?
                    b.moveTo('d', 4);
                    assertEquals(b.getPosition(), position);

                }
            }

        }

        @Override
        public void testIllegal() throws InvalidMoveException, InvalidPositionException {
            piece.moveTo('e', 6);
        }


}
