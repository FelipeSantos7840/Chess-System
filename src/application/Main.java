package application;

import chess.ChessMatch;
import chess.ChessPieces;
import chess.ChessPosition;
import chess.exceptions.ChessException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPieces> piecesCaptured = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while(!chessMatch.getCheckMat()){
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, piecesCaptured);
                System.out.println();
                System.out.println("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(),possibleMoves);

                System.out.println();
                System.out.println("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPieces capturedPiece = chessMatch.performChessMove(source, target);
                if(capturedPiece != null){
                    piecesCaptured.add(capturedPiece);
                }
                if(chessMatch.getPromoted() != null){
                    System.out.println("Enter piece for promotion (B/Q/N/R)");
                    String type = sc.nextLine();
                    chessMatch.replacePromotedPiece(type);
                }
            } catch (ChessException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        UI.clearScreen();
        UI.printMatch(chessMatch,piecesCaptured);
    }
}