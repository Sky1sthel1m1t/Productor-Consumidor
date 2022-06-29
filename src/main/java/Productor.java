public class Productor extends Thread {
    private Granero granero;

    private String nombre;

    public Productor(Granero granero, String nombre) {
        this.granero = granero;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        while (true){
            int panes = (int) (Math.random() * granero.getCantidadMaxPanes()) + 1;
            granero.producir(1, this);
        }
    }

    public int cantidadProducir(){
        int panesMaxAProducir = granero.getCantidadMaxPanes() - granero.getCantidadPanes();
        return (int) (Math.random() * panesMaxAProducir);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
