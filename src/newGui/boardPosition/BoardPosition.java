package newGui.boardPosition;

import persistenceData.Store;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.util.ArrayList;

public class BoardPosition extends JFrame {

  private final String[] columns = {"Nro", "Nombre", "Puntaje"};
  private Store store;
  private Object[][] data;
  private JTable jTable;
  private JScrollPane jScrollPane;

  public BoardPosition() throws Exception {
    super("Tabla de Posiciones");
    fullTable();
    jTable = new JTable(data, columns);
    jTable.setPreferredScrollableViewportSize(new Dimension(500, 80));
    jScrollPane = new JScrollPane(jTable);
    this.getContentPane().add(jScrollPane, BorderLayout.CENTER);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.pack();
  }

  public void refreshTable() {
    try {
      this.fullTable();
    } catch (Exception e) {
      /// put exception
    }
    DefaultTableModel dm = new DefaultTableModel(data, columns);
    jTable.setModel(dm);
    dm.fireTableDataChanged();
  }

  private void fullTable() throws Exception {
    store = new Store();
    ArrayList<String> list = store.getList();
    data = new Object[list.size()][list.size() + 1];
    for (int i = 0; i < list.size(); i++) {
      String[] player = list.get(i).split(" ");
      data[i] = new Object[]{i + 1, player[0], player[1]};
    }
  }

  public void showWindow(boolean state) {
    this.setVisible(state);
  }

}