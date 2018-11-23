package newGui.message;

import newGui.Game;
import newGui.message.MessageUser;

import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageGame extends JOptionPane {

  private JDialog jDialog;
  private JButton acept, cancel;
  private Game game;

  public MessageGame(String message, Game game) {
    this.game = game;
    acept = new JButton("Si");
    cancel = new JButton("No");
    this.defineEventCancel();
    this.defineAcepptCancel();
    this.setMessage(message);
    this.setMessageType(JOptionPane.OK_CANCEL_OPTION);
    this.setOptions(new Object[]{acept, cancel});
    jDialog = this.createDialog(null, "Mensaje");
    jDialog.setVisible(true);
  }

  private void defineEventCancel() {
    ActionListener cancelListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        pressButtonCancel();
      }
    };
    this.cancel.addActionListener(cancelListener);
  }

  private void defineAcepptCancel() {
    ActionListener acceptListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        pressButtonAccept();
      }
    };
    this.acept.addActionListener(acceptListener);
  }

  private void pressButtonCancel() {
    this.game.gameWithPc();
    this.setVisible(false);
    this.jDialog.dispose();
  }

  private void pressButtonAccept() {
    if (this.game.getTypeGame() == 1) {
      this.game.rebootGameWithPc();
    } else {
      try {
        this.game.rebootStrategy();
      } catch (Exception e) {
        new MessageUser("Se produjo un error");
      }
    }
    this.setVisible(false);
    this.jDialog.dispose();
  }
}
