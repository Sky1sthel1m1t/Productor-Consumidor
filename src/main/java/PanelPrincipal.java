import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {

    private JLabel lbNombrePersona = new JLabel();
    private Granero granero;
    private JLabel lbGranero = new JLabel();

    public PanelPrincipal(Dimension tamaño){
        this.setSize(tamaño);
    }

    private void init1(){
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int mitad = this.getWidth() / 2;
        int tierra = this.getHeight() / 4;
        int x = 0;
        int y = 0;
        // Fondo
        g.setColor(Color.CYAN);
        g.fillRect(x,y, this.getWidth(), tierra*3);
        y = tierra*3;
        g.setColor(Color.green);
        g.fillRect(x,y, this.getWidth(), tierra);

        cargarGranero(mitad,y);
    }

    private void cargarGranero(int mitad, int y){
        // Granero 348 * 348
        // Color 79, 246, 255
        ImageIcon imgGranero = new ImageIcon("Granero.png");
        lbGranero.setBounds((mitad - 174), (y - imgGranero.getIconHeight()), imgGranero.getIconWidth(),imgGranero.getIconWidth());
        Icon aux = new ImageIcon(imgGranero.getImage().getScaledInstance(lbGranero.getWidth(),lbGranero.getHeight(), Image.SCALE_DEFAULT));
        lbGranero.setIcon(aux);
        this.add(lbGranero);
    }


}
