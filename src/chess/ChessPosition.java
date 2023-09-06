package chess;

import boardgame.Position;
import chess.exceptions.ChessException;

public class ChessPosition {
    private Character column;
    private Integer row;

    public ChessPosition(Character column, Integer row) {
        if(column < 'a' || column > 'h' || row < 1 || row > 8){
            throw new ChessException("Erro na inicialização do ChessPosition: Valor informado invalido!");
        }
        this.column = column;
        this.row = row;
    }

    public Character getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }

    protected Position toPosition(){
        return new Position(8 - row,column - 'a');
    }

    protected static ChessPosition fromPosition(Position position){
        return new ChessPosition((char)('a'-position.getColumn()),8 - position.getRow());
    }

    @Override
    public String toString() {
        return "" + column + row;
    }
}
