package newGui.board;

import tresenraya.JugarTresEnRaya;
import newGui.Game;
import newGui.handler.ButtonListener;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class Piece  extends JButton {
  private Font font;
  private Dimension dimension;
  private ButtonListener listener;

  public Piece(int fila, int columna, String value, JugarTresEnRaya model, Game game) {
    font = new Font("Arial",Font.BOLD, 42);
    dimension = new Dimension(96, 96);
    listener = new ButtonListener(model, game);
    this.configurePiece(fila, columna, value);
  }

  private void  configurePiece(int x, int y, String value ) {
    setLayout(new GridLayout(3,3));
    this.setFont(font);
    this.setText(value);
    this.setPreferredSize(dimension);
    this.setBorder(new LineBorder( Color.white ,8));
    this.addActionListener(listener);
    this.setActionCommand("" + x + y);
  }

}
