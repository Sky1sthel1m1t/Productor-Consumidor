import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
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
    private JLabel lbArbol1 = new JLabel();
    private JLabel lbArbol2 = new JLabel();
    private JLabel humo = new JLabel();
    private JLabel nube = new JLabel();
    private JLabel nube2 = new JLabel();
    private JLabel nube3 = new JLabel();
    private JLabel montaña1 = new JLabel();
    private JLabel montaña2 = new JLabel();
    private JLabel pasto1 = new JLabel();
    private JLabel pasto3 = new JLabel();
    private JLabel pasto2 = new JLabel();
    private JLabel gallina = new JLabel();
    private JLabel gallina2 = new JLabel();
    private JLabel camino = new JLabel();
    private JLabel lbGranero = new JLabel();
    private JLabel piedra1 = new JLabel();
    private JLabel piedra2 = new JLabel();
    private JLabel piedra3 = new JLabel();
    //private JLabel accionActual = new JLabel();
    ///
    private int cantidadMaxPanes = 10;
    private int delay = 4;
    private int tiempoDentroGranero = 750;
    private ArrayList<Consumidor> consumidores = new ArrayList<>();
    private ArrayList<Productor> productores = new ArrayList<>();

    private int turnoActual = 0;
    private int asignarTurno = 0;

    public Granero(int numConsumidores, int numProductores, Dimension tamaño){
        this.setSize(tamaño);
        init1();
        generarConsumidores(numConsumidores);
        generarProductores(numProductores);
        iniciar();
    }

    private void init1() {
        this.setLayout(null);
        this.setVisible(true);

        this.mitad = this.getWidth() / 2;
        this.limiteTierra = this.getHeight() / 4;


        //l.setFont(new Font("Karatula", 1, 15));
        lbNombrePersona.setFont(new Font("Karatula",4,20));
        lbCantidadPanes.setFont(new Font("Karatula",4,20));

        lbNombrePersona.setOpaque(true);
        lbNombrePersona.setBackground(Color.LIGHT_GRAY);

        lbCantidadPanes.setOpaque(true);
        lbCantidadPanes.setBackground(Color.LIGHT_GRAY);



        this.add(lbPersona);
        this.add(lbNombrePersona);
        this.add(lbGranero);
        this.add(gallina);
        this.add(gallina2);
        this.add(camino);
        this.add(pasto1);
        this.add(pasto2);
        this.add(pasto3);
        this.add(lbCantidadPanes);
        this.add(lbArbol1);
        this.add(lbArbol2);
        this.add(nube);
        this.add(nube2);
        this.add(nube3);
        this.add(montaña1);
        this.add(montaña2);
        this.add(piedra1);
        this.add(piedra2);
        this.add(piedra3);

        cargarDetalles();


    }

    public synchronized void consumir(Consumidor consumidor) {
        while (turnoActual != consumidor.getTurno()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (cantidadPanes > 0){
            lbNombrePersona.setText("Persona " + consumidor.getNombre());
            moverPersona();
        }

        pasarTurno();
        notifyAll();
    }

    public synchronized void producir(int panesCocinados, Productor productor) {
        while (turnoActual != productor.getTurno()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if ((cantidadPanes + panesCocinados) <= cantidadMaxPanes){
            lbNombrePersona.setText("Cocinero " + productor.getNombre());
            moverCocinero(panesCocinados);
        }

        pasarTurno();
        notifyAll();
    }

    public void pasarTurno() {
        turnoActual = (int) (Math.random() * asignarTurno);
    }

    private void cargarDetalles (){

        ImageIcon arbolImg = new ImageIcon("arbol.png");
        lbArbol1.setBounds(70, 340,400,500);
        Icon aux = new ImageIcon(arbolImg.getImage().getScaledInstance(lbArbol1.getWidth(),lbArbol1.getHeight(), Image.SCALE_DEFAULT));
        lbArbol1.setIcon(aux);

        ImageIcon arbol2Img = new ImageIcon("arbol2.png");
        lbArbol2.setBounds(1400, 350,400,500);
        Icon aux2 = new ImageIcon(arbol2Img.getImage().getScaledInstance(lbArbol2.getWidth(),lbArbol2.getHeight(), Image.SCALE_DEFAULT));
        lbArbol2.setIcon(aux2);

        ImageIcon nubeImg = new ImageIcon("nube.png");
        nube.setBounds(1400, 100,400,200);
        Icon aux3 = new ImageIcon(nubeImg.getImage().getScaledInstance(nube.getWidth(),nube.getHeight(), Image.SCALE_DEFAULT));
        nube.setIcon(aux3);

        nube2.setBounds(750, 10,400,200);
        //Icon aux4 = new ImageIcon(nubeImg.getImage().getScaledInstance(nube2.getWidth(),nube2.getHeight(), Image.SCALE_DEFAULT));
        nube2.setIcon(aux3);

        ImageIcon nube3Img = new ImageIcon("nube3.png");
        nube3.setBounds(10, 100,700,300);
        Icon aux5 = new ImageIcon(nube3Img.getImage().getScaledInstance(nube3.getWidth(),nube3.getHeight(), Image.SCALE_DEFAULT));
        nube3.setIcon(aux5);

        ImageIcon montañaImg = new ImageIcon("montaña.png");
        montaña1.setBounds(-200, 340,1300,800);
        Icon aux6 = new ImageIcon(montañaImg.getImage().getScaledInstance(montaña1.getWidth(), montaña1.getHeight(), Image.SCALE_DEFAULT));
        montaña1.setIcon(aux6);

        montaña2.setBounds(1100, 340,1300,800);
        //Icon aux7 = new ImageIcon(montañaImg.getImage().getScaledInstance(montaña1.getWidth(), montaña1.getHeight(), Image.SCALE_DEFAULT));
        montaña2.setIcon(aux6);

        ImageIcon pasto1Img = new ImageIcon("pasto1.png");
        pasto1.setBounds(1500, 830,350,250);
        Icon aux7 = new ImageIcon(pasto1Img.getImage().getScaledInstance(pasto1.getWidth(),pasto1.getHeight(), Image.SCALE_DEFAULT));
        pasto1.setIcon(aux7);


        pasto3.setBounds(50, 830,350,250);
        pasto3.setIcon(aux7);

        ImageIcon pasto2Img = new ImageIcon("pasto2.png");
        pasto2.setBounds(1200, 840,300,200);
        Icon aux8 = new ImageIcon(pasto2Img.getImage().getScaledInstance(pasto2.getWidth(),pasto2.getHeight(), Image.SCALE_DEFAULT));
        pasto2.setIcon(aux8);

        ImageIcon gallinaImg = new ImageIcon("gallina.png");
        gallina.setBounds(600, 900,100,100);
        Icon aux9 = new ImageIcon(gallinaImg.getImage().getScaledInstance(gallina.getWidth(),gallina.getHeight(), Image.SCALE_DEFAULT));
        gallina.setIcon(aux9);

        gallina2.setBounds(680, 910,100,100);
        gallina2.setIcon(aux9);

        ImageIcon caminoImg = new ImageIcon("caminoPiedra.png");
        camino.setBounds(812, 819,300,200);
        Icon aux10 = new ImageIcon(caminoImg.getImage().getScaledInstance(camino.getWidth(),camino.getHeight(), Image.SCALE_DEFAULT));
        camino.setIcon(aux10);

        ImageIcon piedra1Img = new ImageIcon("piedra1.png");
        piedra1.setBounds(10, 900,150,100);
        Icon aux11 = new ImageIcon(piedra1Img.getImage().getScaledInstance(piedra1.getWidth(),piedra1.getHeight(), Image.SCALE_DEFAULT));
        piedra1.setIcon(aux11);

        ImageIcon piedra2Img = new ImageIcon("piedra2.png");
        piedra2.setBounds(200, 890,100,70);
        Icon aux12 = new ImageIcon(piedra2Img.getImage().getScaledInstance(piedra2.getWidth(),piedra2.getHeight(), Image.SCALE_DEFAULT));
        piedra2.setIcon(aux12);

        ImageIcon piedra3Img = new ImageIcon("piedra3.png");
        piedra3.setBounds(350, 910,110,80);
        Icon aux13 = new ImageIcon(piedra3Img.getImage().getScaledInstance(piedra3.getWidth(),piedra3.getHeight(), Image.SCALE_DEFAULT));
        piedra3.setIcon(aux13);
    }

    private void cargarGranero(){
        // Granero 348 * 348
        // Color 79, 246, 255
        //((this.limiteTierra*3) - (imgGranero.getIconHeight()))
        ImageIcon imgGranero = new ImageIcon("Granero.png");
        lbGranero.setBounds((this.mitad - 174)-80, ((this.limiteTierra*3) - imgGranero.getIconHeight())+220, imgGranero.getIconWidth()-100,imgGranero.getIconWidth()-100);
        lbCantidadPanes.setBounds((this.mitad - 100), ((this.limiteTierra*3) - imgGranero.getIconHeight() -30)+310, 200,30);
        lbCantidadPanes.setHorizontalAlignment(JLabel.CENTER);
        Icon aux = new ImageIcon(imgGranero.getImage().getScaledInstance(lbGranero.getWidth(),lbGranero.getHeight(), Image.SCALE_DEFAULT));
        lbGranero.setIcon(aux);
    }

    public void cargarCocinero (){
        ImageIcon imgCocinero = new ImageIcon("granjeroPan.gif");
        lbPersona.setBounds(entredaPersona-imgCocinero.getIconWidth(),(limiteTierra*3) - 110,150,150);
        lbNombrePersona.setBounds(entredaPersona-imgCocinero.getIconWidth(),(limiteTierra*3) - 180, lbPersona.getWidth(), 30);
        lbNombrePersona.setHorizontalAlignment(JLabel.HORIZONTAL);
        Icon aux = new ImageIcon(imgCocinero.getImage().getScaledInstance(lbPersona.getWidth(),lbPersona.getHeight(), Image.SCALE_DEFAULT));
        lbPersona.setIcon(aux);
    }

    public void cambiarCocinero(){
        ImageIcon imgCocinero = new ImageIcon("granjero.gif");
        Icon aux = new ImageIcon(imgCocinero.getImage().getScaledInstance(lbPersona.getWidth(),lbPersona.getHeight(), Image.SCALE_DEFAULT));
        lbPersona.setIcon(aux);
    }

    public void cambiarPersona(){
        ImageIcon imgCocinero = new ImageIcon("personaPan-unscreen.gif");
        Icon aux = new ImageIcon(imgCocinero.getImage().getScaledInstance(lbPersona.getWidth(),lbPersona.getHeight(), Image.SCALE_DEFAULT));
        lbPersona.setIcon(aux);
    }

    public void cargarConsumidor (){
        ImageIcon imgConsumidor = new ImageIcon("persona.gif");
        lbPersona.setBounds(entredaPersona-imgConsumidor.getIconWidth(),(limiteTierra*3) - 110, 150,150);
        lbNombrePersona.setBounds(entredaPersona-imgConsumidor.getIconWidth(),(limiteTierra*3) - 180, lbPersona.getWidth(),30);
        lbNombrePersona.setHorizontalAlignment(JLabel.HORIZONTAL);
        Icon aux = new ImageIcon(imgConsumidor.getImage().getScaledInstance(lbPersona.getWidth(),lbPersona.getHeight(), Image.SCALE_DEFAULT));
        lbPersona.setIcon(aux);
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

        lbNombrePersona.setVisible(false);
        lbPersona.setVisible(false);
        this.cantidadPanes += panesCocinados;
        this.lbCantidadPanes.setText("Cantidad de panes: " + cantidadPanes);
        int salida = mitad - 87;

        try {
            Thread.sleep(tiempoDentroGranero);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        cambiarCocinero();
        lbNombrePersona.setVisible(true);
        lbPersona.setVisible(true);

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

        lbNombrePersona.setVisible(false);
        lbPersona.setVisible(false);
        this.cantidadPanes--;
        this.lbCantidadPanes.setText("Cantidad de panes: " + cantidadPanes);
        int salida = mitad - 87;

        try {
            Thread.sleep(tiempoDentroGranero);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        cambiarPersona();
        lbNombrePersona.setVisible(true);
        lbPersona.setVisible(true);

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
        //cargarDetalles();
    }

    private void generarConsumidores(int num) {
        for (int i = 0; i < num; i++) {
            Consumidor c = new Consumidor(this, i + "", 1, asignarTurno);
            asignarTurno++;
            consumidores.add(c);
        }
    }

    private void generarProductores(int num) {
        for (int i = 0; i < num; i++) {
            Productor p = new Productor(this, i + "", asignarTurno);
            asignarTurno++;
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
