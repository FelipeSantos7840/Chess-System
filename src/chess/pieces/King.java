package chess.pieces;

import boardgame.Board;
import boardgame.Position;
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

    private boolean canMove(Position position){
        ChessPieces piece = (ChessPieces) getBoard().piece(position);
        return piece == null || piece.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);

        //PARA CIMA
        p.setValues(position.getRow() - 1, position.getColumn());
        if(getBoard().positionExist(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //PARA BAIXO
        p.setValues(position.getRow() + 1, position.getColumn());
        if(getBoard().positionExist(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //PARA ESQUERDA
        p.setValues(position.getRow(), position.getColumn() - 1);
        if(getBoard().positionExist(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //PARA DIREITA
        p.setValues(position.getRow(), position.getColumn() + 1);
        if(getBoard().positionExist(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //PARA DIAGONAL SUPERIOR ESQUERDA
        p.setValues(position.getRow() - 1 , position.getColumn() - 1);
        if(getBoard().positionExist(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //PARA DIAGONAL SUPERIOR DIREITA
        p.setValues(position.getRow() - 1 , position.getColumn() + 1);
        if(getBoard().positionExist(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //PARA DIAGONAL INFERIOR ESQUERDA
        p.setValues(position.getRow() + 1 , position.getColumn() - 1);
        if(getBoard().positionExist(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //PARA DIAGONAL INFERIOR DIREITA
        p.setValues(position.getRow() + 1 , position.getColumn() + 1);
        if(getBoard().positionExist(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}
