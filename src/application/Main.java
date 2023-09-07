package application;

import chess.ChessMatch;
import chess.ChessPieces;
import chess.ChessPosition;
import chess.exceptions.ChessException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ChessMatch chessMatch = new ChessMatch();
        Scanner sc = new Scanner(System.in);

        while(true){
            try {
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces());
                System.out.println();
                System.out.println("Source: ");
                ChessPosition source = UI.readChessPosition(sc);
                System.out.println();
                System.out.println("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPieces capturedPiece = chessMatch.performChessMove(source, target);
            } catch (ChessException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }
}