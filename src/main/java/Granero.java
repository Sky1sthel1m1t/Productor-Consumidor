import java.util.ArrayList;

public class Granero {

    private int cantidadPanes;
    private int cantidadMaxPanes;

    private ArrayList<Consumidor> consumidores = new ArrayList<>();
    private ArrayList<Productor> productores = new ArrayList<>();
    private Object seguro = new Object();

    public Granero(int numConsumidores, int numProductores) {
        generarConsumidores(numConsumidores);
        generarProductores(numProductores);
        iniciar();
    }

    public synchronized void consumir() {
        while (cantidadPanes <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        cantidadPanes--;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        notifyAll();
    }

    public synchronized void producir(int panesCocinados) {
        while (cantidadPanes > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        cantidadPanes += panesCocinados;
        notifyAll();
    }

    private void generarConsumidores(int num) {
        for (int i = 0; i < num; i++) {
            Consumidor c = new Consumidor(this, i + "");
            consumidores.add(c);
        }
    }

    private void generarProductores(int num) {
        for (int i = 0; i < num; i++) {
            Productor p = new Productor(this);
            productores.add(p);
        }
    }

    private void iniciar() {
        ArrayList<Thread> personas = new ArrayList<>(productores);
        personas.addAll(consumidores);
        System.out.println("Hay en la ciudad " + personas.size() + " personas");
        for (Thread persona: personas) {
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

    public Object getSeguro() {
        return seguro;
    }

    public void setSeguro(Object seguro) {
        this.seguro = seguro;
    }
}
