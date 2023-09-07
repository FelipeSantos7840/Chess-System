package chess.exceptions;

import boardgame.Board;
import boardgame.exceptions.BoardException;

public class ChessException extends BoardException {
    public ChessException(String msg){
        super(msg);
    }
}
