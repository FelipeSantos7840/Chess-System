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

    private void placeNewPiece(char column, int row, ChessPieces piece){
        board.placePiece(piece,new ChessPosition(column,row).toPosition());
    }

    private void InitialSetup(){
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }
}
