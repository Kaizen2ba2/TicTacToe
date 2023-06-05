package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;

    TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(200, 30, 50));
        frame.setLayout(new BorderLayout());

        textfield.setBackground(new Color(10, 15, 20));
        textfield.setForeground(new Color(255, 255, 255));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        frame.add(title_panel, BorderLayout.NORTH);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));
        frame.add(button_panel);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.setVisible(true);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1_turn) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        player1_turn = false;
                        textfield.setText("O turn");
                        buttons[i].setText("X");
                        check();
                    }
                } else {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        player1_turn = true;
                        textfield.setText("X turn");
                        buttons[i].setText("O");
                        check();
                    }
                }
            }
        }
    }

    public void firstTurn() {
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (random.nextInt(2) == 0) {
                    player1_turn = true;
                    textfield.setText("X turn");
                } else {
                    player1_turn = false;
                    textfield.setText("O turn");
                }
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void check() {
        String mark = "";

        for (int i = 0; i <= 6; i += 3) {
            if (!buttons[i].getText().equals("") && buttons[i].getText().equals(buttons[i + 1].getText()) &&
                    buttons[i].getText().equals(buttons[i + 2].getText())) {
                mark = buttons[i].getText();
                highlightWinningLine(i, i + 1, i + 2);
                break;
            }
        }


        for (int i = 0; i < 3; i++) {
            if (!buttons[i].getText().equals("") && buttons[i].getText().equals(buttons[i + 3].getText()) &&
                    buttons[i].getText().equals(buttons[i + 6].getText())) {
                mark = buttons[i].getText();
                highlightWinningLine(i, i + 3, i + 6);
                break;
            }
        }


        if (!buttons[0].getText().equals("") && buttons[0].getText().equals(buttons[4].getText()) &&
                buttons[0].getText().equals(buttons[8].getText())) {
            mark = buttons[0].getText();
            highlightWinningLine(0, 4, 8);
        } else if (!buttons[2].getText().equals("") && buttons[2].getText().equals(buttons[4].getText()) &&
                buttons[2].getText().equals(buttons[6].getText())) {
            mark = buttons[2].getText();
            highlightWinningLine(2, 4, 6);
        }

        if (!mark.equals("")) {
            disableButtons();
            textfield.setText(mark + " wins");
        } else if (isBoardFull()) {
            textfield.setText("It's a draw");
        }
    }

    public void highlightWinningLine(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
    }

    public void disableButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
