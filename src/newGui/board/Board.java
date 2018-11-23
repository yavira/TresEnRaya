package newGui.board;

import tresenraya.JugarTresEnRaya;
import newGui.Game;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;

public class Board extends JPanel {

  private Piece[][] listPieces;
  private JugarTresEnRaya model;
  private Game game;

  public Board(JugarTresEnRaya model, Game panel) {
    this.model = model;
    this.game = panel;
    createButtons(3, 3, model, panel);
    this.setLayout(new GridLayout(3, 3));
  }

  public void createButtons(int fila, int columna, JugarTresEnRaya model, newGui.Game game) {
    this.listPieces = new Piece[fila][columna];
    for (int i = 0; i < fila; i += 1) {
      for (int j = 0; j < columna; j += 1) {
        listPieces[i][j] = new Piece(i, j, "", model, game);
        this.add(listPieces[i][j]);
      }
    }
  }

  public void clearBoard() {
    this.removeAll();
    this.listPieces = new Piece[3][3];
    for (int i = 0; i < this.listPieces.length; i += 1) {
      for (int j = 0; j < this.listPieces.length; j += 1) {
        listPieces[i][j] = new Piece(i, j, "", model, game);
        this.add(listPieces[i][j]);
      }
    }
    this.updateUI();

  }

  public Piece[][] getListPieces() {
    return listPieces;
  }

  public JButton getPiece(int x, int y) {
    return listPieces[x][y];
  }

  public void setPiece(int x, int y, String type) {
    if (this.listPieces[x][y].getText() == "") {
      this.listPieces[x][y].setText(type);
    }
  }
}
