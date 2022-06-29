import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {

    //private PanelPrincipal panelPrincipal;

    private Granero granero;
    private Dimension tamañoPantalla;

    public Ventana(){
        init1();
    }

    private void init1(){
        tamañoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(tamañoPantalla);
        granero = new Granero(10,1,this.getSize());
        setPreferredSize(this.getSize());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        this.add(granero, BorderLayout.CENTER);
        this.pack();
    }

}
