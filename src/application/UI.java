package application;

import chess.ChessMatch;
import chess.ChessPieces;
import chess.ChessPosition;
import chess.enums.Color;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UI {

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static ChessPosition readChessPosition(Scanner sc){
        try {
            String s = sc.nextLine();
            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new ChessPosition(column, row);
        } catch (RuntimeException e){
            throw new InputMismatchException("Erro lendo posição de Xadrez! (Valido de a1 - h8)");
        }
    }

    public static void printMatch(ChessMatch chessMatch, List<ChessPieces> capturedPieces){
        printBoard(chessMatch.getPieces());
        System.out.println();
        printCaputredPieces(capturedPieces);
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.print("Turn: " + chessMatch.getTurn() + "\n");
        System.out.print("Current Player: " + chessMatch.getCurrentPlayer() + "\n");
        if(chessMatch.getCheck()){
            System.out.println("CHECK!");
        }
        System.out.println("----------------------------------------------");
    }

    public static void printBoard(ChessPieces[][] pieces){
        for(int x=0;x<pieces.length;x++){
            System.out.print((8-x) + " | ");
            for(int y=0;y<pieces[x].length;y++){
                printPiece(pieces[x][y],false);
            }
            System.out.println();
        }
        System.out.println("    a b c d e f g h");
    }

    public static void printBoard(ChessPieces[][] pieces,boolean[][] possibleMoves){
        for(int x=0;x<pieces.length;x++){
            System.out.print((8-x) + " | ");
            for(int y=0;y<pieces[x].length;y++){
                printPiece(pieces[x][y],possibleMoves[x][y]);
            }
            System.out.println();
        }
        System.out.println("    a b c d e f g h");
    }


    private static void printPiece(ChessPieces piece, boolean background){
        if(background){
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if(piece == null){
            System.out.print("-" + ANSI_RESET);
        } else {
            if(piece.getColor() == Color.WHITE){
                System.out.print(ANSI_WHITE + piece + ANSI_RESET); //ANSI_RESET PARA ELE IMPRIMIR APENAS A COR CORRETA
            } else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    private static void printCaputredPieces(List<ChessPieces> pieces){
        List<ChessPieces> whitePieces = pieces.stream().filter(x -> x.getColor() == Color.WHITE).toList();
        List<ChessPieces> blackPieces = pieces.stream().filter(x -> x.getColor() == Color.BLACK).toList();

        System.out.println("Captured Pieces: ");
        System.out.print("White: ");
        System.out.print(ANSI_WHITE);
        System.out.print(Arrays.toString(whitePieces.toArray()));
        System.out.println(ANSI_RESET);
        System.out.print("Black: ");
        System.out.print(ANSI_YELLOW);
        System.out.print(Arrays.toString(blackPieces.toArray()));
        System.out.print(ANSI_RESET);

    }
}
