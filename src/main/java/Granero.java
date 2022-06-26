import java.awt.*;
import java.util.ArrayList;

public class Granero {

    private int cantidadPanes = 0;
    private int cantidadMaxPanes = 10;

    private ArrayList<Consumidor> consumidores = new ArrayList<>();
    private ArrayList<Productor> productores = new ArrayList<>();
    int turnoActual = 0;
    int asignarTurno = 0;

    public Granero(int numConsumidores, int numProductores) {
        generarConsumidores(numConsumidores);
        generarProductores(numProductores);
        iniciar();
    }

    public Granero(int numConsumidores, int numProductores, boolean interfaz) {
        generarConsumidores(numConsumidores);
        generarProductores(numProductores);
        iniciar();
    }

    public synchronized void consumir(Consumidor consumidor) {
        while (turnoActual != consumidor.getTurno()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (cantidadPanes > 0) {
            cantidadPanes--;
            System.out.println("Soy la persona " + consumidor.getNombre() + " y me he sacado " + consumidor.getPanConsumir()
                    + ". Quedan " + cantidadPanes + " panes");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
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

        if ((cantidadPanes + panesCocinados) < cantidadMaxPanes){
            cantidadPanes += panesCocinados;
            System.out.println("Soy el cocinero " + productor.getNombre() + ", he preparado " + panesCocinados + " panes y quedan "
                    + getCantidadPanes() + " panes");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        pasarTurno();
        notifyAll();
    }

    public void pasarTurno() {
        turnoActual = (int) (Math.random() * asignarTurno);
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
