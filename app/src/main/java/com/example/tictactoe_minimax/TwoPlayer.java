package com.example.tictactoe_minimax;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class TwoPlayer extends AppCompatActivity {

    private boolean buttonsEnabled = true;
    String currentPlr = "X";
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
        setContentView(R.layout.activity_two_player);

    }

    @SuppressLint("SetTextI18n")
    public void onButtonClick(View view)
    {
        Button button = (Button)view ;
        if (buttonsEnabled) {
            // Disable buttons to prevent multiple clicks
            setButtonsEnabled(false);
            if (!(button.getText().equals("X") || button.getText().equals("O"))) {
                updateBoard(button.getText(), currentPlr.charAt(0));
                button.setText(currentPlr);
                button.setTextColor(getColor(R.color.black));
                if (isWinner(board, currentPlr.charAt(0))) {

                    Toast.makeText(this, currentPlr+" won", Toast.LENGTH_SHORT).show();
                    TextView Pscr ;
                    if(currentPlr.equals("X"))
                        Pscr = findViewById(R.id.Score1Val) ;
                    else
                        Pscr = findViewById(R.id.Score2Val) ;
                    Pscr.setText(Integer.toString(Integer.parseInt(Pscr.getText().toString())+1));
                    setScoreColor();
                    notover = false ;
                    animate();
                }
                else if (isBoardFull(board)) {

                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                    notover = false ;
                    animate();
                } else {
                    if(currentPlr.equals("X"))
                    {
                        currentPlr = "O";
                    }
                    else
                    {
                        currentPlr = "X";
                    }
                }
            } else {
                Toast.makeText(this, "preoccupied", Toast.LENGTH_SHORT).show();
            }
            new Handler().postDelayed(() -> {
                // Enable buttons after the delay
                setButtonsEnabled(notover);
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
            if(!buttons[a].getText().equals("O") && !buttons[a].getText().equals("X"))
                buttons[a].setTextColor(getColor(R.color.fade));
        }


        // Enable buttons after the delay
        new Handler().postDelayed(this::glow, 200);
    }

    private void glow() {
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0]!=' ')
        {
            Button b1 = findViewById(R.id.button1);
            b1.setBackgroundColor(getColor(R.color.glow));
            Button b2 = findViewById(R.id.button5);
            b2.setBackgroundColor(getColor(R.color.glow));
            Button b3 = findViewById(R.id.button9);
            b3.setBackgroundColor(getColor(R.color.glow));
        }
        else if(board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[1][1]!=' ')
        {
            Button b1 = findViewById(R.id.button3);
            b1.setBackgroundColor(getColor(R.color.glow));
            Button b2 = findViewById(R.id.button5);
            b2.setBackgroundColor(getColor(R.color.glow));
            Button b3 = findViewById(R.id.button7);
            b3.setBackgroundColor(getColor(R.color.glow));
        }
        else if(board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][0]!=' ')
        {
            Button b1 = findViewById(R.id.button1);
            b1.setBackgroundColor(getColor(R.color.glow));
            Button b2 = findViewById(R.id.button2);
            b2.setBackgroundColor(getColor(R.color.glow));
            Button b3 = findViewById(R.id.button3);
            b3.setBackgroundColor(getColor(R.color.glow));
        }
        else if(board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][1]!=' ')
        {
            Button b1 = findViewById(R.id.button4);
            b1.setBackgroundColor(getColor(R.color.glow));
            Button b2 = findViewById(R.id.button5);
            b2.setBackgroundColor(getColor(R.color.glow));
            Button b3 = findViewById(R.id.button6);
            b3.setBackgroundColor(getColor(R.color.glow));
        }
        else if(board[2][0] == board[2][2] && board[2][2] == board[2][1] && board[2][2]!=' ')
        {
            Button b1 = findViewById(R.id.button7);
            b1.setBackgroundColor(getColor(R.color.glow));
            Button b2 = findViewById(R.id.button8);
            b2.setBackgroundColor(getColor(R.color.glow));
            Button b3 = findViewById(R.id.button9);
            b3.setBackgroundColor(getColor(R.color.glow));
        }
        else if(board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[0][0]!=' ')
        {
            Button b1 = findViewById(R.id.button1);
            b1.setBackgroundColor(getColor(R.color.glow));
            Button b2 = findViewById(R.id.button4);
            b2.setBackgroundColor(getColor(R.color.glow));
            Button b3 = findViewById(R.id.button7);
            b3.setBackgroundColor(getColor(R.color.glow));
        }
        else if(board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[1][1]!=' ')
        {
            Button b1 = findViewById(R.id.button2);
            b1.setBackgroundColor(getColor(R.color.glow));
            Button b2 = findViewById(R.id.button5);
            b2.setBackgroundColor(getColor(R.color.glow));
            Button b3 = findViewById(R.id.button8);
            b3.setBackgroundColor(getColor(R.color.glow));
        }
        else if(board[2][2] == board[1][2] && board[1][2] == board[0][2] && board[2][2]!=' ')
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
            currentPlr = "X";


            initboard();
            clearButtons();
            notover = true ;
            buttonsEnabled = true ;
            unanimate();
        } else {
            currentPlr = "X" ;

            Toast.makeText(this, "Board is already clear", Toast.LENGTH_SHORT).show();
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

    @SuppressLint("SetTextI18n")
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