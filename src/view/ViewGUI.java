package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ViewGUI implements ActionListener {

    private Font mainFont;
    private Color mainColor;
    private ImageIcon mainIcon;

    private JFrame mainFrame;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JButton playButton;
    private JButton historyButton;

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
        playButton.addActionListener(this);
        mainPanel.add(playButton);

        historyButton = new JButton("Show History");
        historyButton.setFont(new java.awt.Font("yu mincho", 1, 30));
        historyButton.setBackground(Color.WHITE);
        historyButton.setForeground(new Color(180, 0, 64));
        historyButton.setFocusPainted(false);
        historyButton.addActionListener(this);
        mainPanel.add(historyButton);

        mainFrame.setContentPane(mainPanel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            playMenu();
        }
        if (e.getSource() == historyButton) {
            historyMenu();
        }
    }

    public void playMenu() {
        mainFrame = new JFrame();
        mainFrame.setTitle("Panel de jugador");
        mainFrame.setSize(500, 500);
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

        mainFrame.setContentPane(mainPanel);
    }

    public void historyMenu() {
        mainFrame = new JFrame();
        mainFrame.setTitle("Historial de partidas");
        mainFrame.setSize(500, 500);
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

    public static void main(String[] args) {
        new ViewGUI();
    }
}