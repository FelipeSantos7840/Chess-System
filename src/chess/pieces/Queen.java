package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPieces;
import chess.enums.Color;

public class Queen extends ChessPieces {
    public Queen(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "Q";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);

        //Acima da peça
        p.setValues(position.getRow()-1,position.getColumn());
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() - 1);
        }
        if(getBoard().positionExist(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //Esquerda da peça
        p.setValues(position.getRow(),position.getColumn()-1);
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() - 1);
        }
        if(getBoard().positionExist(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //Direita da peça
        p.setValues(position.getRow(),position.getColumn()+1);
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() + 1);
        }
        if(getBoard().positionExist(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //Baixo da peça
        p.setValues(position.getRow()+1,position.getColumn());
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() + 1);
        }
        if(getBoard().positionExist(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //NW da peça
        p.setValues(position.getRow()-1,position.getColumn()-1);
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() - 1,p.getColumn() - 1);
        }
        if(getBoard().positionExist(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //NE da peça
        p.setValues(position.getRow() - 1,position.getColumn() + 1);
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() - 1,p.getColumn() + 1);
        }
        if(getBoard().positionExist(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //SE da peça
        p.setValues(position.getRow() + 1 ,position.getColumn() + 1);
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() + 1,p.getColumn() + 1);
        }
        if(getBoard().positionExist(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //SW da peça
        p.setValues(position.getRow() + 1,position.getColumn() - 1);
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() + 1,p.getColumn() - 1);
        }
        if(getBoard().positionExist(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}
