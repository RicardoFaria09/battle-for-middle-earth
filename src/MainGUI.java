import javax.swing.SwingUtilities;

/** Ponto de entrada para o modo com interface gráfica (Swing). */
public class MainGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Janela().setVisible(true));
    }
}
