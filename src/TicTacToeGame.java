import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;


public class TicTacToeGame {
        JFrame frame = new JFrame("Tic-Tac-Toe");
        JLabel textLabel = new JLabel();
        JPanel playpanel = new JPanel();



        JButton[][] board = new JButton[3][3];

        String playerX = "X";
        String playerO = "O";
        String currentPlayer = playerX;

        boolean gameOver = false;
        int turns = 0;

        TicTacToeGame() {
            frame.setVisible(true);

            frame.setSize(600, 650);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            textLabel.setText("Tic-Tac-Toe");
            textLabel.setBackground(Color.black);
            textLabel.setOpaque(true);
            textLabel.setForeground(Color.WHITE);
            textLabel.setFont(new Font("Arial",Font.BOLD, 50));
            textLabel.setHorizontalAlignment(JLabel.CENTER);
            textLabel.setLayout(new BorderLayout());
            frame.add(textLabel, BorderLayout.NORTH);

            playpanel.setLayout(new GridLayout(3,3));
            playpanel.setBackground(Color.black);
            frame.add(playpanel);

            for(int row =0; row <3; row++){
                for(int col =0; col <3; col++ ){
                    JButton button = new JButton();
                    board[row][col] =button;
                    playpanel.add(button);

                    button.setBackground(Color.black);
                    button.setForeground(Color.white);
                    button.setFont(new Font("Arial", Font.BOLD, 120));

                    button.addActionListener(e -> {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (Objects.equals(tile.getText(), "")) {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = Objects.equals(currentPlayer, playerX) ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                        }

                    });
                }
            }

        }
    void checkWinner() {
        //horizontal
        for (int r = 0; r < 3; r++) {
            if (Objects.equals(board[r][0].getText(), "")) continue;

            if (Objects.equals(board[r][0].getText(), board[r][1].getText()) && Objects.equals(board[r][1].getText(), board[r][2].getText())) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        //vertical
        for (int c = 0; c < 3; c++) {
            if (Objects.equals(board[0][c].getText(), "")) continue;

            if (Objects.equals(board[0][c].getText(), board[1][c].getText()) && Objects.equals(board[1][c].getText(), board[2][c].getText())) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        //diagonally
        if (Objects.equals(board[0][0].getText(), board[1][1].getText()) && Objects.equals(board[1][1].getText(), board[2][2].getText()) && !Objects.equals(board[0][0].getText(), "")) {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }

        //anti-diagonally
        if (Objects.equals(board[0][2].getText(), board[1][1].getText()) && Objects.equals(board[1][1].getText(), board[2][0].getText()) && !Objects.equals(board[0][2].getText(), "")) {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }

        if (turns == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }
    }
    void setWinner(JButton button) {
            button.setBackground(Color.gray);
            button.setForeground(Color.green);
            textLabel.setText(currentPlayer + " is Winner!");
    }
    void setTie(JButton button){
            button.setBackground(Color.gray);
            button.setForeground(Color.RED);
            textLabel.setText("TIE!");
    }

}
