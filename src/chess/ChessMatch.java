package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.enums.Color;
import chess.exceptions.ChessException;
import chess.pieces.King;
import chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {
    private Board board;
    private Integer turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> piecesCaptured = new ArrayList<>();


    public ChessMatch(){
        turn = 1;
        board = new Board(8,8);
        currentPlayer = Color.WHITE;
        InitialSetup();
    }

    public Integer getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
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

    public boolean getCheck(){
        return check;
    }

    public boolean getCheckMat(){
        return checkMate;
    }

    public ChessPieces performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        //METODO COM OBJETIVO DE ORGANIZAR PARA MOVER A PEÇA
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source,target);

        Piece capturedPiece = makeMove(source,target);

        if(testCheck(currentPlayer)){
            undoMove(source,target,capturedPiece);
            throw new ChessException("Você não pode se colocar em Xeque!");
        }

        check = (testCheck(opponent(currentPlayer)));

        if(testCheckMate(opponent(currentPlayer))){
            checkMate = true;
        } else {
            nextTurn();
        }


        return (ChessPieces) capturedPiece;
    }

    private Piece makeMove(Position source,Position target){
        Piece p = board.removePiece(source); //REMOVA A PEÇA QUE SERÁ MOVIDA NO TABULEIRO
        Piece capturedPiece = board.removePiece(target); //REMOVE A POSSIVEL PEÇA QUE ESTEJA NO LOCAL DE DESTINO A CAPTURANDO
        board.placePiece(p,target);

        if(capturedPiece != null){
            piecesOnTheBoard.remove(capturedPiece);
            piecesCaptured.add(capturedPiece);
        }

        return capturedPiece;
    }

    private void undoMove(Position source,Position target, Piece capturedPiece){
        Piece p = board.removePiece(target);
        board.placePiece(p,source);

        if(capturedPiece != null){
            board.placePiece(capturedPiece,target);
            piecesCaptured.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position)){
            throw new ChessException("Nao existe peca nessa posicao!");
        }
        if(currentPlayer != ((ChessPieces)board.piece(position)).getColor()){
            throw new ChessException("A peca escolhida é do adversario");
        }
        if(!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("Nao existe movimentos possiveis para a peca!");
        }

    }

    private void validateTargetPosition(Position source, Position target){
        if(!board.piece(source).possibleMove(target)){
            throw new ChessException("A peca escolhida nao pode se mover para o destino");
        }
    }

    private void nextTurn(){
        turn++;
        currentPlayer = currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    private Color opponent(Color player){
        return player == Color.WHITE ? Color.BLACK:Color.WHITE;
    }

    private ChessPieces king(Color color){
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPieces)x).getColor() == color).collect(Collectors.toList());
        for(Piece p : list){
            if(p instanceof King){
                return (ChessPieces) p;
            }
        }
        throw new IllegalStateException("Não existe o rei " + color + " no tabuleiro!");
    }

    private boolean testCheck(Color color){
        Position kingPosition = king(color).getChessPosition().toPosition();
        for(Piece pieces : piecesOnTheBoard){
            if(((ChessPieces)pieces).getColor() == opponent(color)){
                boolean[][] possibleMovement = pieces.possibleMoves();
                if(possibleMovement[kingPosition.getRow()][kingPosition.getColumn()] == true){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean testCheckMate(Color color){
        if(!testCheck(color)){
            return false;
        }

        List<Piece> listP = piecesOnTheBoard.stream().filter(x -> ((ChessPieces)x).getColor() == color).collect(Collectors.toList());
        for(Piece p : listP){
            boolean[][] pMoves = p.possibleMoves();
            for(int x=0;x<pMoves.length;x++){
                for(int z=0;z < pMoves[x].length;z++){
                    if(pMoves[x][z]){
                        Position source = ((ChessPieces)p).getChessPosition().toPosition();
                        Position target = new Position(x,z);
                        Piece capturedPice = makeMove(source,target);
                        boolean testCheck = testCheck(color);
                        undoMove(source,target,capturedPice);
                        if(!testCheck){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void placeNewPiece(char column, int row, ChessPieces piece){
        board.placePiece(piece,new ChessPosition(column,row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void InitialSetup(){
        placeNewPiece('h', 7, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));


        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new King(board, Color.BLACK));

    }
}
