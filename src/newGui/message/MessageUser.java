package newGui.message;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class MessageUser extends JOptionPane {

  private JDialog jDialog;

  public MessageUser(String message) {
    this.setMessage(message);
    this.setMessageType(JOptionPane.INFORMATION_MESSAGE);
    jDialog = this.createDialog(null, "Mensaje");
    jDialog.setVisible(true);
  }
}
