package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.enums.Color;

public abstract class ChessPieces extends Piece {

    private Color color;
    private int moveCount;

    public ChessPieces(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public ChessPosition getChessPosition(){
        return ChessPosition.fromPosition(position);
    }

    public Color getColor() {
        return color;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void increaseMoveCount(){
        moveCount++;
    }

    public void decreaseMoveCount(){
        moveCount--;
    }

    protected boolean isThereOpponentPiece(Position position){
        ChessPieces piece = (ChessPieces) getBoard().piece(position);
        return piece != null && piece.getColor() != this.color;
    }

}
