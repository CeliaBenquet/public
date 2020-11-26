package ch.epfl.sweng.tests;

import org.junit.Test;

import ch.epfl.sweng.Bishop;
import ch.epfl.sweng.Color;
import ch.epfl.sweng.InvalidMoveException;
import ch.epfl.sweng.InvalidPositionException;
import ch.epfl.sweng.Position;
import ch.epfl.sweng.Queen;
import ch.epfl.sweng.Superpiece;

import static org.junit.Assert.assertEquals;

public class SuperpieceTests extends PieceTests<Superpiece> {

    @Override
    public void setUp() {
        piece = new Superpiece(new Bishop(position, Color.BLACK));

    }

    @Override
    public void testLegal() throws InvalidMoveException, InvalidPositionException {

        // 1 move only
        for (int distance : new int[]{1, 2, 3}) {
            for (int horizontalDirection : new int[]{1, -1}) {
                for (int verticalDirection : new int[]{1, -1}) {
                    Superpiece b = new Superpiece(new Bishop(position, Color.WHITE));
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

        // 2 moves
        for (int distance1 : new int[]{1, 2}) {
            for (int horizontalDirection1 : new int[]{1, -1}) {
                for (int verticalDirection1 : new int[]{1, -1}) {

                    int row1 = position.getVertical() + distance1 * horizontalDirection1;
                    char col1 = (char) (position.getHorizontal() + distance1 * verticalDirection1);

                    for (int distance2 : new int[]{1}) {
                        for (int horizontalDirection2 : new int[]{1, -1}) {
                            for (int verticalDirection2 : new int[]{1, -1}) {

                                Superpiece s = new Superpiece(new Bishop(position, Color.WHITE));
                                int row2 = position.getVertical() + distance1 * horizontalDirection1 + distance2 * horizontalDirection2;
                                char col2 = (char) (position.getHorizontal() + distance1 * verticalDirection1 + distance2 * verticalDirection2);

                                Position destination = Position.positionIfLegal(col2, row2);

                                // can we move forward?
                                s.moveTo(col1, row1, col2, row2);
                                assertEquals(s.getPosition(), destination);

                                // can we move back?
                                //s.moveTo('d', 4, );
                                //assertEquals(s.getPosition(), position);
                            }

                        }
                    }
                }
            }
        }



    }

    @Test
    public void testNoMove2Times() throws InvalidMoveException, InvalidPositionException {
        Position destination = Position.positionIfLegal('e', 5);
        piece.moveTo(destination, position);
    }

    @Override
    public void testIllegal() throws InvalidMoveException, InvalidPositionException {
        piece.moveTo('d', 6);
    }


}
