package chess.pieces;

import boardgame.Board;
import chess.ChessPieces;
import chess.enums.Color;

public class Rook extends ChessPieces {
    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "R";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        return mat; //IMPLEMENTAÇÃO TEMPORARIA
    }
}
