package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPieces;
import chess.enums.Color;

public class Pawn extends ChessPieces {

    private ChessMatch chessMatch;
    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
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
            //ESPECIAL MOVE En Passant WHITE
            if(position.getRow() == 3){
                Position left = new Position(position.getRow(),position.getColumn()-1);
                if(getBoard().positionExist(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()){
                    mat[left.getRow()-1][left.getColumn()] = true;
                }

                Position right = new Position(position.getRow(),position.getColumn()+1);
                if(getBoard().positionExist(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()){
                    mat[right.getRow()-1][right.getColumn()] = true;
                }
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

            //ESPECIAL MOVE En Passant BLACK
            if(position.getRow() == 4){
                Position left = new Position(position.getRow(),position.getColumn()-1);
                if(getBoard().positionExist(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()){
                    mat[left.getRow()+1][left.getColumn()] = true;
                }

                Position right = new Position(position.getRow(),position.getColumn()+1);
                if(getBoard().positionExist(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()){
                    mat[right.getRow()+1][right.getColumn()] = true;
                }
            }

        }
        return mat;
    }
}
