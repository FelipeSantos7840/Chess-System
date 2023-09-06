package chess;

import boardgame.Board;
import boardgame.Position;
import chess.enums.Color;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
    private Board board;

    public ChessMatch(){
        board = new Board(8,8);
        InitialSetup();
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

    private void InitialSetup(){
        board.placePiece(new Rook(board,Color.WHITE), new Position(2,1));
        board.placePiece(new King(board,Color.BLACK), new Position(0,4));
        board.placePiece(new King(board,Color.WHITE), new Position(7,4));
    }
}
