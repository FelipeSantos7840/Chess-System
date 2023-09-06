package boardgame;

import boardgame.exception.BoardException;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        if(rows < 1 || columns <1){
            throw new BoardException("Erro tabuleiro: É necessario que haja ao menos 1 linha e 1 coluna!");
        }

        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Piece piece(int row, int column){
        if(!positionExist(row,column)){
            throw new BoardException("Erro tabuleiro: Posição não existe!");
        }
        return pieces[row][column];
    }

    public Piece piece(Position pos){
        if(!positionExist(pos)){
            throw new BoardException("Erro tabuleiro: Posição não existe!");
        }
        return pieces[pos.getRow()][pos.getColumn()];
    }

    public void placePiece(Piece piece, Position position){
        if(thereIsAPiece(position)){
            throw new BoardException("Já possui uma peça nessa posição!: " + position);
        }
        piece.position = position;
        pieces[position.getRow()][position.getColumn()] = piece;
    }

    public boolean positionExist(int row, int column){
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }
    public boolean positionExist(Position position){
        return positionExist(position.getRow(),position.getColumn());
    }

    public boolean thereIsAPiece(Position position){
        if(!positionExist(position)){
            throw new BoardException("Erro tabuleiro: Posição não existe!");
        }
        return piece(position) != null;
    }

}
