package chess;

import boardgame.Board;

public class ChessMatch {
    private Board board;

    public ChessMatch(){
        board = new Board(8,8);
    }

    public ChessPieces[][] getPieces(){
        ChessPieces[][] mat = new ChessPieces[board.getRows()][board.getColumns()];
        for(int x=0;x < board.getRows();x++){
            for(int y=0;y < board.getColumns();y++){
                mat[x][y] = (ChessPieces) board.piece(x,y);
            }
        }
        return mat;
    }
}
