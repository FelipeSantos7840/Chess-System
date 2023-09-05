package boardgame;

public class Piece {
    //PROTECTED PARA FACILITAR O ACESSO DURANTE HERANÇA
    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
        position = null;
        //AS PEÇAS NÃO SERÃO COLOCADAS NESSA INICIALIZAÇÃO
    }

    protected Board getBoard() {
        return board;
    }
}
