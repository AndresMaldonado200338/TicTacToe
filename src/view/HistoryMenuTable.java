package view;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HistoryMenuTable extends JTable {
    private String[] columnNames = { "USERNAME", "STATUS", "FIGURE", "DATETIME" };
    private String[] rowValues;
    String path = "Triki/src/resources/History/history_game.txt";
    DefaultTableModel model;

    public HistoryMenuTable() {
        model = createModel();
        setData();
        super.setModel(model);
        setRowHeight(30);
        setPreferredScrollableViewportSize(new Dimension(450, getRowHeight() * model.getRowCount()));
        setFillsViewportHeight(false);
    }

    public DefaultTableModel createModel() {
        return new DefaultTableModel(columnNames, 0);
    }

    public void setData() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                rowValues = line.split(";");
                model.addRow(rowValues);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
