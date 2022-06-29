import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//Hacer esta clase el panel tambien;

public class Granero extends JPanel {
    private int cantidadPanes = 0;

    private JLabel lbCantidadPanes = new JLabel("Cantidad de panes: "+cantidadPanes);
    private int entredaPersona = 0;

    private int limiteTierra;
    private int mitad;
    /////
    private JLabel lbNombrePersona = new JLabel();
    private JLabel lbPersona = new JLabel();
    private JLabel lbGranero = new JLabel();
    private JLabel accionActual = new JLabel();
    ///
    private int cantidadMaxPanes = 10;
    private int delay = 1;

    private ArrayList<Consumidor> consumidores = new ArrayList<>();
    private ArrayList<Productor> productores = new ArrayList<>();

    public Granero(int numConsumidores, int numProductores, Dimension tamaño) {
        this.setSize(tamaño);
        init1();
        generarConsumidores(numConsumidores);
        generarProductores(numProductores);
        iniciar();
    }

    private void init1(){
        this.setLayout(null);
        this.setVisible(true);

        this.mitad = this.getWidth() / 2;
        this.limiteTierra = this.getHeight() / 4;

        this.add(lbPersona);
        this.add(lbNombrePersona);
        this.add(lbGranero);
        this.add(lbCantidadPanes);
    }

    public synchronized void consumir(Consumidor consumidor) {
        while (cantidadPanes <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        lbNombrePersona.setText("Persona " + consumidor.getNombre());
        moverPersona();

        notifyAll();
    }

    public synchronized void producir(int panesCocinados, Productor productor) {
        if ((cantidadPanes + panesCocinados) <= cantidadMaxPanes){
            lbNombrePersona.setText("Cocinero " + productor.getNombre());
            moverCocinero(panesCocinados);
        }
        notifyAll();
    }

    private void cargarGranero(){
        // Granero 348 * 348
        // Color 79, 246, 255
        //((this.limiteTierra*3) - (imgGranero.getIconHeight()))
        ImageIcon imgGranero = new ImageIcon("Granero.png");
        lbGranero.setBounds((this.mitad - 174), ((this.limiteTierra*3) - imgGranero.getIconHeight()), imgGranero.getIconWidth(),imgGranero.getIconWidth());
        lbCantidadPanes.setBounds((this.mitad - 174), ((this.limiteTierra*3) - imgGranero.getIconHeight() -30), imgGranero.getIconWidth(),30);
        lbCantidadPanes.setHorizontalAlignment(JLabel.CENTER);
        Icon aux = new ImageIcon(imgGranero.getImage().getScaledInstance(lbGranero.getWidth(),lbGranero.getHeight(), Image.SCALE_DEFAULT));
        lbGranero.setIcon(aux);
    }

    public void cargarCocinero (){
        ImageIcon imgCocinero = new ImageIcon("cocinero.gif");
        lbPersona.setBounds(entredaPersona-imgCocinero.getIconWidth(),(limiteTierra*3) - 150,150,150);
        lbNombrePersona.setBounds(entredaPersona-imgCocinero.getIconWidth(),(limiteTierra*3) - 180, lbPersona.getWidth(), 30);
        lbNombrePersona.setHorizontalAlignment(JLabel.HORIZONTAL);
        Icon aux = new ImageIcon(imgCocinero.getImage().getScaledInstance(lbPersona.getWidth(),lbPersona.getHeight(), Image.SCALE_DEFAULT));
        lbPersona.setIcon(aux);
    }

    public void cargarConsumidor (){
        ImageIcon imgConsumidor = new ImageIcon("persona.gif");
        lbPersona.setBounds(entredaPersona-imgConsumidor.getIconWidth(),(limiteTierra*3) - 150, 150,150);
        lbNombrePersona.setBounds(entredaPersona-imgConsumidor.getIconWidth(),(limiteTierra*3) - 180, lbPersona.getWidth(),30);
        lbNombrePersona.setHorizontalAlignment(JLabel.HORIZONTAL);
        Icon aux = new ImageIcon(imgConsumidor.getImage().getScaledInstance(lbPersona.getWidth(),lbPersona.getHeight(), Image.SCALE_DEFAULT));
        lbPersona.setIcon(aux);
    }

    public void cargarPensamiento(){

    }

    public void moverCocinero (int panesCocinados){
        cargarCocinero();

        JLabel auxNombre = new JLabel();
        JLabel auxImg = new JLabel();
        auxNombre.setBounds(lbNombrePersona.getBounds());
        auxImg.setBounds(lbPersona.getBounds());

        for (int i = auxNombre.getX(); i < mitad-87; i++) {
            lbNombrePersona.setBounds((i), auxNombre.getY(), auxNombre.getWidth(), auxNombre.getHeight());
            lbPersona.setBounds((i), auxImg.getY(), auxImg.getWidth(),auxImg.getHeight());
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        this.cantidadPanes += panesCocinados;
        this.lbCantidadPanes.setText("Cantidad de panes: " + cantidadPanes);
        int salida = mitad - 87;

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = salida; i < (this.getWidth()+auxImg.getWidth()); i++) {
            lbNombrePersona.setBounds((i), auxNombre.getY(), auxNombre.getWidth(), auxNombre.getHeight());
            lbPersona.setBounds((i), auxImg.getY(), auxImg.getWidth(),auxImg.getHeight());
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void moverPersona(){
        cargarConsumidor();

        JLabel auxNombre = new JLabel();
        JLabel auxImg = new JLabel();
        auxNombre.setBounds(lbNombrePersona.getBounds());
        auxImg.setBounds(lbPersona.getBounds());

        for (int i = auxNombre.getX(); i < mitad-87; i++) {
            lbNombrePersona.setBounds((i), auxNombre.getY(), auxNombre.getWidth(), auxNombre.getHeight());
            lbPersona.setBounds((i), auxImg.getY(), auxImg.getWidth(),auxImg.getHeight());
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        this.cantidadPanes--;
        this.lbCantidadPanes.setText("Cantidad de panes: " + cantidadPanes);
        int salida = mitad - 87;

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = salida; i < (this.getWidth()+auxImg.getWidth()); i++) {
            lbNombrePersona.setBounds((i), auxNombre.getY(), auxNombre.getWidth(), auxNombre.getHeight());
            lbPersona.setBounds((i), auxImg.getY(), auxImg.getWidth(),auxImg.getHeight());
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void modificarEntrada (){

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 0;
        int y = 0;
        // Fondo
        Color cyan = new Color(79,246,255);
        g.setColor(cyan);
        g.fillRect(x,y, this.getWidth(), limiteTierra*3);
        y = limiteTierra*3;
        g.setColor(Color.green);
        g.fillRect(x,y, this.getWidth(), limiteTierra);

        cargarGranero();
    }

    private void generarConsumidores(int num) {
        for (int i = 0; i < num; i++) {
            Consumidor c = new Consumidor(this, i + "", 1);
            consumidores.add(c);
        }
    }

    private void generarProductores(int num) {
        for (int i = 0; i < num; i++) {
            Productor p = new Productor(this, i + "");
            productores.add(p);
        }
    }

    private void iniciar() {
        ArrayList<Thread> personas = new ArrayList<>(productores);
        personas.addAll(consumidores);
        System.out.println("Hay en la ciudad " + personas.size() + " personas");
        for (Thread persona : personas) {
            persona.start();
        }
    }


    public int getCantidadPanes() {
        return cantidadPanes;
    }

    public void setCantidadPanes(int cantidadPanes) {
        this.cantidadPanes = cantidadPanes;
    }

    public int getCantidadMaxPanes() {
        return cantidadMaxPanes;
    }

    public void setCantidadMaxPanes(int cantidadMaxPanes) {
        this.cantidadMaxPanes = cantidadMaxPanes;
    }
}
