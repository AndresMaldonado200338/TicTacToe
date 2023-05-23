package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ViewGUI {

    private Font mainFont;
    private Color mainColor;
    private ImageIcon mainIcon;

    private JFrame mainFrame;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JButton playButton;
    private JButton historyButton;
    private JButton continueButton;
    private JButton backButton;

    private HistoryMenuTable historyTable;

    public ViewGUI() {
        mainFont = new Font("yu mincho", 1, 30);
        mainColor = new Color(180, 0, 64);
        mainIcon = new ImageIcon("Triki/src/resources/mainIcon.png");

        mainFrame = new JFrame();
        mainFrame.setTitle("Triki");
        mainFrame.setSize(500, 500);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setIconImage(mainIcon.getImage());
        mainFrame.setVisible(true);

        mainMenu();
    }

    public void mainMenu() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 70));
        mainPanel.setBackground(mainColor);

        titleLabel = new JLabel("Welcome to Tic tac toe");
        titleLabel.setFont(mainFont);
        titleLabel.setForeground(Color.WHITE);
        mainPanel.add(titleLabel);

        playButton = new JButton("Play");
        playButton.setFont(mainFont);
        playButton.setForeground(mainColor);
        playButton.setBackground(Color.WHITE);
        playButton.setFocusPainted(false);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == playButton) {
                    playMenu();
                }
            }
        });

        mainPanel.add(playButton);

        historyButton = new JButton("Show History");
        historyButton.setFont(new java.awt.Font("yu mincho", 1, 30));
        historyButton.setBackground(Color.WHITE);
        historyButton.setForeground(new Color(180, 0, 64));
        historyButton.setFocusPainted(false);
        historyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == historyButton) {
                    historyMenu();
                }
            }
        });
        mainPanel.add(historyButton);

        mainFrame.setContentPane(mainPanel);
    }

    public void playMenu() {
        mainFrame = new JFrame();
        mainFrame.setTitle("Panel de jugador");
        mainFrame.setSize(500, 500);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setIconImage(mainIcon.getImage());
        mainFrame.setVisible(true);

        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 70));
        mainPanel.setBackground(new Color(180, 0, 64));

        titleLabel = new JLabel("Menu del jugador");
        titleLabel.setFont(new java.awt.Font("yu mincho", 1, 30));
        titleLabel.setForeground(Color.WHITE);
        mainPanel.add(titleLabel);

        JLabel userLabel = new JLabel("UserName:");
        userLabel.setFont(new java.awt.Font("yu mincho", 1, 15));
        userLabel.setForeground(Color.WHITE);
        mainPanel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setFont(new java.awt.Font("yu mincho", 0, 12));
        userText.setForeground(Color.BLACK);
        mainPanel.add(userText);

        JLabel figureLabel = new JLabel("Choose a figure:");
        figureLabel.setFont(new java.awt.Font("yu mincho", 1, 15));
        figureLabel.setForeground(Color.WHITE);
        mainPanel.add(figureLabel);

        String[] figures = { "Select", "Circle", "Cross" };
        JComboBox figureChoose = new JComboBox(figures);
        Dimension size = new Dimension(170, 30);
        figureChoose.setPreferredSize(size);
        figureChoose.setFont(new java.awt.Font("yu mincho", 1, 12));
        figureChoose.setForeground(Color.BLACK);
        mainPanel.add(figureChoose);

        continueButton = new JButton("Continue");
        continueButton.setFont(new java.awt.Font("yu mincho", 1, 30));
        continueButton.setBackground(Color.WHITE);
        continueButton.setForeground(new Color(180, 0, 64));
        continueButton.setFocusPainted(false);
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (userText.getText().isEmpty() || figureChoose.getSelectedItem().equals("Select")) {
                    JOptionPane.showMessageDialog(mainFrame, "Error, por favor completar los campos", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    gameMenu();
                }
            }
        });
        mainPanel.add(continueButton);

        backButton = new JButton("Back");
        backButton.setFont(new java.awt.Font("yu mincho", 1, 30));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(new Color(180, 0, 64));
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == backButton) {
                    mainMenu();
                }
            }
        });
        mainPanel.add(backButton);

        mainFrame.setContentPane(mainPanel);
    }

    public void historyMenu() {
        mainFrame = new JFrame();
        mainFrame.setTitle("Historial de partidas");
        mainFrame.setSize(500, 500);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        ImageIcon mainIcon = new ImageIcon("Triki/src/resources/mainIcon.png");
        mainFrame.setIconImage(mainIcon.getImage());
        mainFrame.setVisible(true);

        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 70));
        mainPanel.setBackground(new Color(180, 0, 64));

        titleLabel = new JLabel("Historial de partidas");
        titleLabel.setFont(new java.awt.Font("yu mincho", 1, 30));
        titleLabel.setForeground(Color.WHITE);
        mainPanel.add(titleLabel);

        historyTable = new HistoryMenuTable();

        JScrollPane scrollPane = new JScrollPane(historyTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        mainFrame.setContentPane(mainPanel);
    }

    public void gameMenu() {
        mainFrame = new JFrame();
        mainFrame.setTitle("Juego");
        mainFrame.setSize(500, 500);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        ImageIcon mainIcon = new ImageIcon("Triki/src/resources/mainIcon.png");
        mainFrame.setIconImage(mainIcon.getImage());
        mainFrame.setVisible(true);

        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 70));
        mainPanel.setBackground(new Color(180, 0, 64));

        mainFrame.setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        new ViewGUI();
    }
}