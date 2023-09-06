package chess.pieces;

import boardgame.Board;
import chess.ChessPieces;
import chess.enums.Color;

public class King extends ChessPieces {

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }
}
