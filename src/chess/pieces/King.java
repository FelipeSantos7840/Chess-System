package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPieces;
import chess.enums.Color;

public class King extends ChessPieces {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position){
        ChessPieces piece = (ChessPieces) getBoard().piece(position);
        return piece == null || piece.getColor() != getColor();
    }

    private boolean testRookCastling(Position position){
        ChessPieces p = (ChessPieces) getBoard().piece(position);
        return (p != null && p instanceof Rook && p.getColor() == this.getColor() && p.getMoveCount() == 0);
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

        //SPECIAL MOVE Castling
        if(this.getMoveCount() == 0 && !chessMatch.getCheck()){
            //SPECIAL MOVE king side rook
            Position pRook1 = new Position(position.getRow(),position.getColumn()+3);
            if(testRookCastling(pRook1)){
                Position p1 = new Position(position.getRow(),position.getColumn()+1);
                Position p2 = new Position(position.getRow(),position.getColumn()+2);
                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null){
                    mat[position.getRow()][position.getColumn() + 2] = true;
                }
            }

            //SPECIAL MOVE queen side rook
            Position pRook2 = new Position(position.getRow(),position.getColumn()-4);
            if(testRookCastling(pRook1)){
                Position p1 = new Position(position.getRow(),position.getColumn()-1);
                Position p2 = new Position(position.getRow(),position.getColumn()-2);
                Position p3 = new Position(position.getRow(),position.getColumn()-3);

                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null){
                    mat[position.getRow()][position.getColumn() - 2] = true;
                }
            }
        }

        return mat;
    }
}
