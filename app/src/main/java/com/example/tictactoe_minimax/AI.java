package com.example.tictactoe_minimax;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AI extends AppCompatActivity
{

    private boolean buttonsEnabled = true;
    boolean notover = true ;
    char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai);

    }

    @SuppressLint("SetTextI18n")
    public void onButtonClick(View view)
    {
        Button button = (Button)view ;
        if (buttonsEnabled) {
            // Disable buttons to prevent multiple clicks
            setButtonsEnabled(false);
            if (!(button.getText().equals("X") || button.getText().equals("O"))) {
                updateBoard(button.getText(), 'X');
                button.setText("X");
                button.setTextColor(getColor(R.color.black));
                if (isWinner(board, 'X')) {
                    Button retry = findViewById(R.id.retry);
                    retry.setText("RETRY");
                    retry.setTextColor(getColor(R.color.black));
                    retry.setBackgroundColor(getColor(R.color.retryColor));
                    Toast.makeText(this, "X won!", Toast.LENGTH_SHORT).show();
                    TextView Pscr = findViewById(R.id.Score2Val) ;
                    Pscr.setText(Integer.toString(Integer.parseInt(Pscr.getText().toString())+1));
                    setScoreColor();
                    notover = false ;
                    animate();
                }
                if (isBoardFull(board)) {
                    Button retry = findViewById(R.id.retry);
                    retry.setText("RETRY");
                    retry.setTextColor(getColor(R.color.black));
                    retry.setBackgroundColor(getColor(R.color.retryColor));
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                    notover = false ;
                    animate();
                } else {
                    int[] move = minimax(board, 'O');
                    String val = Integer.toString((move[0] * 3) + move[1] + 1);
                    int value = (move[0] * 3) + move[1] + 1;
                    updateBoard(val, 'O');
                    if (isWinner(board, 'O')) {
                        Button retry = findViewById(R.id.retry);
                        retry.setText("RETRY");
                        retry.setTextColor(getColor(R.color.black));
                        retry.setBackgroundColor(getColor(R.color.retryColor));
                        Toast.makeText(this, "O won!", Toast.LENGTH_SHORT).show();
                        TextView AIscr = findViewById(R.id.Score1Val) ;
                        AIscr.setText(Integer.toString(Integer.parseInt(AIscr.getText().toString())+1));
                        setScoreColor();
                        notover = false ;
                        animate();
                    }

                    if (isBoardFull(board)) {
                        Button retry = findViewById(R.id.retry);
                        retry.setText("RETRY");

                        retry.setTextColor(getColor(R.color.black));
                        retry.setBackgroundColor(getColor(R.color.retryColor));
                        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                        notover = false;
                        animate();
                    }
                    Button ai = null;
                    if (value == 1)
                        ai = findViewById(R.id.button1);
                    if (value == 2)
                        ai = findViewById(R.id.button2);
                    if (value == 3)
                        ai = findViewById(R.id.button3);
                    if (value == 4)
                        ai = findViewById(R.id.button4);
                    if (value == 5)
                        ai = findViewById(R.id.button5);
                    if (value == 6)
                        ai = findViewById(R.id.button6);
                    if (value == 7)
                        ai = findViewById(R.id.button7);
                    if (value == 8)
                        ai = findViewById(R.id.button8);
                    if (value == 9)
                        ai = findViewById(R.id.button9);
                    if (ai != null) {
                        ai.setText("O");
                        ai.setTextColor(getColor(R.color.black));
                    }

                }
            } else {
                Toast.makeText(this, "preoccupied", Toast.LENGTH_SHORT).show();
            }
            new Handler().postDelayed(() -> {
                // Enable buttons after the delay
                setButtonsEnabled(true&notover);
            }, 100);
        }
    }

    private void setScoreColor() {
        TextView aiscr , plrscr ;
        aiscr = findViewById(R.id.Score1Val);
        plrscr = findViewById(R.id.Score2Val);
        int x = Integer.parseInt(aiscr.getText().toString());
        int y = Integer.parseInt(plrscr.getText().toString());
        if(x<y)
        {
            aiscr.setTextColor(getColor(R.color.Red));
            plrscr.setTextColor(getColor(R.color.Green));
        }
        else if(y<x){
            plrscr.setTextColor(getColor(R.color.Red));
            aiscr.setTextColor(getColor(R.color.Green));
        }
        else
        {
            plrscr.setTextColor(getColor(R.color.Yellow));
            aiscr.setTextColor(getColor(R.color.Yellow));
        }
    }

    private void animate() {
        Button b1 = findViewById(R.id.button1);
        Button b2 = findViewById(R.id.button2);
        Button b3 = findViewById(R.id.button3);
        Button b4 = findViewById(R.id.button4);
        Button b5 = findViewById(R.id.button5);
        Button b6 = findViewById(R.id.button6);
        Button b7 = findViewById(R.id.button7);
        Button b8 = findViewById(R.id.button8);
        Button b9 = findViewById(R.id.button9);

        Button[] buttons = {b1, b2, b3, b4, b5, b6, b7, b8, b9};
        for(int a= 0 ; a < 9; a++) {
            buttons[a].setBackgroundColor(getColor(R.color.fade));
            if(buttons[a].getText().equals("O") || buttons[a].getText().equals("X"))
                continue;
            else
                buttons[a].setTextColor(getColor(R.color.fade));
        }


        new Handler().postDelayed(() -> {
            // Enable buttons after the delay
            glow();
        }, 200);
    }

    private void glow() {
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ')
        {
            Button b1 = findViewById(R.id.button1);
            b1.setBackgroundColor(getColor(R.color.glow));
            Button b2 = findViewById(R.id.button5);
            b2.setBackgroundColor(getColor(R.color.glow));
            Button b3 = findViewById(R.id.button9);
            b3.setBackgroundColor(getColor(R.color.glow));
        }
        else if(board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[1][1] != ' ')
        {
            Button b1 = findViewById(R.id.button3);
            b1.setBackgroundColor(getColor(R.color.glow));
            Button b2 = findViewById(R.id.button5);
            b2.setBackgroundColor(getColor(R.color.glow));
            Button b3 = findViewById(R.id.button7);
            b3.setBackgroundColor(getColor(R.color.glow));
        }
        else if(board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][0] != ' ')
        {
            Button b1 = findViewById(R.id.button1);
            b1.setBackgroundColor(getColor(R.color.glow));
            Button b2 = findViewById(R.id.button2);
            b2.setBackgroundColor(getColor(R.color.glow));
            Button b3 = findViewById(R.id.button3);
            b3.setBackgroundColor(getColor(R.color.glow));
        }
        else if(board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][1] != ' ')
        {
            Button b1 = findViewById(R.id.button4);
            b1.setBackgroundColor(getColor(R.color.glow));
            Button b2 = findViewById(R.id.button5);
            b2.setBackgroundColor(getColor(R.color.glow));
            Button b3 = findViewById(R.id.button6);
            b3.setBackgroundColor(getColor(R.color.glow));
        }
        else if(board[2][0] == board[2][2] && board[2][2] == board[2][1]  && board[2][2] != ' ')
        {
            Button b1 = findViewById(R.id.button7);
            b1.setBackgroundColor(getColor(R.color.glow));
            Button b2 = findViewById(R.id.button8);
            b2.setBackgroundColor(getColor(R.color.glow));
            Button b3 = findViewById(R.id.button9);
            b3.setBackgroundColor(getColor(R.color.glow));
        }
        else if(board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[0][0] != ' ')
        {
            Button b1 = findViewById(R.id.button1);
            b1.setBackgroundColor(getColor(R.color.glow));
            Button b2 = findViewById(R.id.button4);
            b2.setBackgroundColor(getColor(R.color.glow));
            Button b3 = findViewById(R.id.button7);
            b3.setBackgroundColor(getColor(R.color.glow));
        }
        else if(board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[1][1] != ' ')
        {
            Button b1 = findViewById(R.id.button2);
            b1.setBackgroundColor(getColor(R.color.glow));
            Button b2 = findViewById(R.id.button5);
            b2.setBackgroundColor(getColor(R.color.glow));
            Button b3 = findViewById(R.id.button8);
            b3.setBackgroundColor(getColor(R.color.glow));
        }
        else if(board[2][2] == board[1][2] && board[1][2] == board[0][2] && board[2][2] != ' ')
        {
            Button b1 = findViewById(R.id.button3);
            b1.setBackgroundColor(getColor(R.color.glow));
            Button b2 = findViewById(R.id.button6);
            b2.setBackgroundColor(getColor(R.color.glow));
            Button b3 = findViewById(R.id.button9);
            b3.setBackgroundColor(getColor(R.color.glow));
        }

    }

    private void setButtonsEnabled(boolean b) {
        buttonsEnabled = b ;
    }

    public void onRetryClick(View view) {
        if (isnotempty()) {
            Button retry = (Button)view ;
            retry.setText("GIVE UP");
            retry.setTextColor(getColor(R.color.white));
            retry.setBackgroundColor(getColor(R.color.giveupColor));
            TextView AIscr = findViewById(R.id.Score1Val);
            if(notover)
                AIscr.setText(Integer.toString(Integer.parseInt(AIscr.getText().toString()) + 1));
            initboard();
            clearButtons();
            notover = true ;
            buttonsEnabled = true ;
            unanimate();
        } else {
            Button retry = (Button)view ;
            retry.setText("GIVE UP");
            retry.setTextColor(getColor(R.color.white));
            retry.setBackgroundColor(getColor(R.color.giveupColor));
            Toast.makeText(this, "Board is already clear", Toast.LENGTH_SHORT).show();
            notover = true ;
            notover = true ;
            buttonsEnabled = true ;
            unanimate();
        }
    }

    private void unanimate() {
        Button b1 = findViewById(R.id.button1);
        Button b2 = findViewById(R.id.button2);
        Button b3 = findViewById(R.id.button3);
        Button b4 = findViewById(R.id.button4);
        Button b5 = findViewById(R.id.button5);
        Button b6 = findViewById(R.id.button6);
        Button b7 = findViewById(R.id.button7);
        Button b8 = findViewById(R.id.button8);
        Button b9 = findViewById(R.id.button9);

        Button[] buttons = {b1, b2, b3, b4, b5, b6, b7, b8, b9};
        for(int a= 0 ; a < 9; a++)
        {
            buttons[a].setBackgroundColor(getColor(R.color.org));
            buttons[a].setTextColor(getColor(R.color.org));
        }

    }

    private void clearButtons() {
        Button b1 = findViewById(R.id.button1);
        Button b2 = findViewById(R.id.button2);
        Button b3 = findViewById(R.id.button3);
        Button b4 = findViewById(R.id.button4);
        Button b5 = findViewById(R.id.button5);
        Button b6 = findViewById(R.id.button6);
        Button b7 = findViewById(R.id.button7);
        Button b8 = findViewById(R.id.button8);
        Button b9 = findViewById(R.id.button9);

        Button[] buttons = {b1, b2, b3, b4, b5, b6, b7, b8, b9};

        for (int a = 0 ; a < 9 ; a++) {
            buttons[a].setText(Integer.toString(a+1));
            buttons[a].setTextColor(getColor(R.color.org));
        }
    }


    private boolean isnotempty() {
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                if (board[a][b] != ' ') {
                    return true;
                }
            }
        }
        return false;
    }


    private void initboard() {
        for(int a = 0 ; a < 3 ; a++)
        {
            for(int b = 0 ; b < 3 ; b++)
            {
                board[a][b] = ' ';
            }
        }
    }

    private void updateBoard(CharSequence text, char player) {
        int num = Integer.parseInt(text.toString()) - 1;
        int x = num / 3;
        int y = num % 3;
        board[x][y] = player;
    }


    private static int[] minimax(char[][] board, char player) {
        int[] bestMove = {-1, -1};
        int bestScore = (player == 'O') ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = player;
                    int score = minimaxScore(board, 0, false);
                    board[i][j] = ' ';  // Undo the move

                    if ((player == 'O' && score > bestScore) || (player == 'X' && score < bestScore)) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }

        return bestMove;
    }

    private static int minimaxScore(char[][] board, int depth, boolean isMaximizing) {
        if (isWinner(board, 'X')) return -1;
        if (isWinner(board, 'O')) return 1;
        if (isBoardFull(board)) return 0;

        if (isMaximizing) {
            int maxScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'O';
                        int score = minimaxScore(board, depth + 1, false);
                        board[i][j] = ' ';  // Undo the move
                        maxScore = Math.max(score, maxScore);
                    }
                }
            }
            return maxScore;
        } else {
            int minScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'X';
                        int score = minimaxScore(board, depth + 1, true);
                        board[i][j] = ' ';  // Undo the move
                        minScore = Math.min(score, minScore);
                    }
                }
            }
            return minScore;
        }
    }
    private static boolean isWinner(char[][] board, char player) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }
    private static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

}