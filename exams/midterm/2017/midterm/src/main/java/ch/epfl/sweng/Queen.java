package ch.epfl.sweng;

/**
 *
 * the queen chess piece can move any number of steps,
 * both forward and backward, along any of the four directions
 * (vertical, horizontal, or one of the two diagonals)
 *
 */


public class Queen extends Piece {
    public Queen(Position position, Color color) {
            super(position, color);
        }

    public Queen(Queen q) { super(q.getPosition(), q.getColor()); }

        @Override
        protected boolean isPieceMovementValid(Position.Offset offset) {

            boolean diagonal = (Math.abs(offset.vertical) == Math.abs(offset.horizontal));
            boolean linear = (offset.vertical == 0) || (offset.horizontal == 0);
            return diagonal || linear;
        }

        @Override
        public Queen copy() {
            return new Queen(this);
        }


}
