package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPieces;
import chess.enums.Color;

public class Pawn extends ChessPieces {
    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0,0);
        Position pTest;

        if(this.getColor() == Color.WHITE){
            p.setValues(position.getRow()-1,position.getColumn());
            if(getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()-2,position.getColumn());
            pTest = new Position(position.getRow()-1,position.getColumn());
            if(getBoard().positionExist(p) && !getBoard().thereIsAPiece(p) && getMoveCount() == 0 && getBoard().positionExist(pTest) && !getBoard().thereIsAPiece(pTest)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()-1,position.getColumn()-1);
            if(getBoard().positionExist(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()-1,position.getColumn()+1);
            if(getBoard().positionExist(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
        } else {
            p.setValues(position.getRow()+1,position.getColumn());
            if(getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()+2,position.getColumn());
            pTest = new Position(position.getRow()+1,p.getColumn());
            if(getBoard().positionExist(p) && !getBoard().thereIsAPiece(p) && getMoveCount() == 0 && getBoard().positionExist(pTest) && !getBoard().thereIsAPiece(pTest)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()+1,position.getColumn()+1);
            if(getBoard().positionExist(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()+1,position.getColumn()-1);
            if(getBoard().positionExist(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }

        }
        return mat;
    }
}
