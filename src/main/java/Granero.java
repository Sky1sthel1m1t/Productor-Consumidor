import java.util.ArrayList;

public class Granero {

    private int cantidadPanes;
    private int cantidadMaxPanes;

    private ArrayList<Consumidor> consumidores = new ArrayList<>();
    private ArrayList<Productor> productores = new ArrayList<>();
    int turnoConsumidor = 0;
    int turnoProductor = 0;

    public Granero(int numConsumidores, int numProductores) {
        generarConsumidores(numConsumidores);
        generarProductores(numProductores);
        iniciar();
    }

    public synchronized void consumir(Consumidor consumidor) {
        while (cantidadPanes <= 0 || turnoConsumidor != consumidor.getTurno()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        cantidadPanes--;
        cambiarTurnoConsumidor();
        System.out.println("Soy la persona " + consumidor.getNombre() + " y me he sacado " + consumidor.getPanConsumir()
        + ". Quedan " + cantidadPanes + " panes");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        notifyAll();
    }

    public synchronized void producir(int panesCocinados, Productor productor) {
        while (cantidadPanes > 0 || turnoProductor != productor.getTurno()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        cantidadPanes += panesCocinados;
        System.out.println("Soy el cocinero " + productor.getNombre() +", he preparado " + panesCocinados + " panes y quedan "
                + getCantidadPanes() + " panes");
        cambiarTurnoProductor();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        notifyAll();
    }

    public void cambiarTurnoConsumidor(){
        this.turnoConsumidor++;
        if (this.turnoConsumidor > this.consumidores.size() - 1){
            this.turnoConsumidor = 0;
        }
    }

    public void cambiarTurnoProductor(){
        this.turnoProductor++;
        if (this.turnoProductor > this.productores.size() - 1){
            this.turnoProductor = 0;
        }
    }

    private void generarConsumidores(int num) {
        for (int i = 0; i < num; i++) {
            Consumidor c = new Consumidor(this, i + "", 1, i);
            consumidores.add(c);
        }
    }

    private void generarProductores(int num) {
        for (int i = 0; i < num; i++) {
            Productor p = new Productor(this, i, i + "");
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
}
