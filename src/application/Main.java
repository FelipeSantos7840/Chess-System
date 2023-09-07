package application;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPieces;
import chess.ChessPosition;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ChessMatch chessMatch = new ChessMatch();
        Scanner sc = new Scanner(System.in);

        while(true){
            UI.printBoard(chessMatch.getPieces());
            System.out.println();
            System.out.println("Source: ");
            ChessPosition source = UI.readChessPosition(sc);
            System.out.println();
            System.out.println("Target: ");
            ChessPosition target = UI.readChessPosition(sc);

            ChessPieces capturedPiece = chessMatch.performChessMove(source,target);
        }
    }
}