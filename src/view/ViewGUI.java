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
import model.User;

public class ViewGUI {

    private Font mainFont;
    private Color mainColor;
    private ImageIcon mainIcon;

    private JFrame mainFrame;
    private JPanel mainPanel;
    private JLabel titleLabel, userLabel;
    private JButton playButton, historyButton, continueButton, backButton;
    private JTextField userText;
    private JComboBox figureChoose;
    private JTabbedPane tabbedPane;

    private HistoryMenuTable historyTable;

    private ActionListener listener;

    public ViewGUI(ActionListener listener) {
        this.listener = listener;

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
        playButton.addActionListener(listener);
        mainPanel.add(playButton);

        historyButton = new JButton("Show History");
        historyButton.setFont(new java.awt.Font("yu mincho", 1, 30));
        historyButton.setBackground(Color.WHITE);
        historyButton.setForeground(new Color(180, 0, 64));
        historyButton.setFocusPainted(false);
        historyButton.addActionListener(listener);
        mainPanel.add(historyButton);

        mainFrame.setContentPane(mainPanel);
    }

    public void playMenu() {
        mainFrame = new JFrame();
        mainFrame.setTitle("Panel de jugador");
        mainFrame.setSize(500, 600);
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

        userLabel = new JLabel("UserName:");
        userLabel.setFont(new java.awt.Font("yu mincho", 1, 15));
        userLabel.setForeground(Color.WHITE);
        mainPanel.add(userLabel);

        userText = new JTextField(20);
        userText.setFont(new java.awt.Font("yu mincho", 0, 12));
        userText.setForeground(Color.BLACK);
        mainPanel.add(userText);

        JLabel figureLabel = new JLabel("Choose a figure:");
        figureLabel.setFont(new java.awt.Font("yu mincho", 1, 15));
        figureLabel.setForeground(Color.WHITE);
        mainPanel.add(figureLabel);

        String[] figures = { "Select", "Circle", "Cross" };
        figureChoose = new JComboBox(figures);
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
        continueButton.addActionListener(listener);
        mainPanel.add(continueButton);

        backButton = new JButton("Back");
        backButton.setFont(new java.awt.Font("yu mincho", 1, 30));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(new Color(180, 0, 64));
        backButton.setFocusPainted(false);
        backButton.addActionListener(listener);
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
        mainIcon.setImage(null);

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

    public void gameMenu(User user) {
        mainFrame = new JFrame();
        mainFrame.setTitle("Juego");
        mainFrame.setSize(500, 500);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        ImageIcon mainIcon = new ImageIcon("Triki/src/resources/mainIcon.png");
        mainFrame.setIconImage(mainIcon.getImage());
        mainFrame.setVisible(true);

        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainPanel.setBackground(new Color(180, 0, 64));

        JPanel pane1 = new JPanel();


        JPanel pane2 = new JPanel();
        pane2.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 70));
        JLabel userNameLabel = new JLabel("NickName: " + user.getUserName());
        userNameLabel.setFont(new java.awt.Font("yu mincho", 1, 30));
        JLabel userFigureLabel = new JLabel("Figure: " + user.getUserFigure());
        userFigureLabel.setFont(new java.awt.Font("yu mincho", 1, 30));
        pane2.add(userNameLabel);
        pane2.add(userFigureLabel);

        JPanel pane3 = new JPanel();

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Tab1", pane1);
        tabbedPane.addTab("Tab2", pane2);
        tabbedPane.addTab("Tab3", pane3);
        tabbedPane.setPreferredSize(new Dimension(450, 450));
        mainPanel.add(tabbedPane);

        mainFrame.setContentPane(mainPanel);
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JLabel getUserLabel() {
        return userLabel;
    }

    public JButton getPlayButton() {
        return playButton;
    }

    public JButton getHistoryButton() {
        return historyButton;
    }

    public JButton getContinueButton() {
        return continueButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JTextField getUserText() {
        return userText;
    }

    public JComboBox getFigureChoose() {
        return figureChoose;
    }
}