package ch.epfl.sweng;

import static ch.epfl.sweng.Color.BLACK;
import static ch.epfl.sweng.Color.WHITE;

/**
 * Class implementing the behaviour of the pawn piece in chess.
 *
 * The Pawn class should only implement standard movement, that is 1 step ahead vertically, for
 * white pawns, and 1 step back vertically, for black pawns. No first move double-jump, no diagonal
 * captures, no promotions, etc.
 */

public class Pawn extends Piece {
    public Pawn(Position position, Color color) {
        super(position, color);
    }

    public Pawn(Pawn p) { super(p.getPosition(), p.getColor()); }

    /* Accounting only for regular move, that is 1 up for white pawns and 1 down for black pawns */
    @Override
    protected boolean isPieceMovementValid(Position.Offset offset) {
        return offset.horizontal == 0 && (offset.vertical == (getColor() == WHITE ? 1 : -1));
    }

    @Override
    public Pawn copy() {
        return new Pawn(this);
    }

    public Superpiece promote(Queen queen) throws InvalidMoveException{
        boolean whiteAtEnd = (this.getColor()== WHITE && this.getPosition().getHorizontal() == 8);
        boolean blackAtEnd = (this.getColor() == BLACK && this.getPosition().getHorizontal() == 1);
        if (whiteAtEnd || blackAtEnd) {
            return new Superpiece(queen);
        }
        throw new InvalidMoveException();
    }
}
