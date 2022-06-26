import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {

    private PanelPrincipal panelPrincipal;
    private Dimension tamañoPantalla;

    public Ventana(){
        init1();
    }

    private void init1(){
        tamañoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(tamañoPantalla);
        setPreferredSize(this.getSize());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        panelPrincipal = new PanelPrincipal(tamañoPantalla);
        this.add(panelPrincipal, BorderLayout.CENTER);
        this.pack();
    }

}
