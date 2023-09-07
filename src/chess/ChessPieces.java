package chess;

import boardgame.Board;
import boardgame.Piece;
import chess.enums.Color;

public abstract class ChessPieces extends Piece {

    private Color color;

    public ChessPieces(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
