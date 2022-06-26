public class Productor extends Thread {
    private Granero granero;
    private int turno;
    private String nombre;

    public Productor(Granero granero, String nombre, int turno) {
        this.granero = granero;
        this.nombre = nombre;
        this.turno = turno;
    }

    @Override
    public void run() {
        while (true){
            int panes = (int) (Math.random() * granero.getCantidadMaxPanes()) + 1;
            granero.producir(panes, this);
        }
    }

    public int cantidadProducir(){
        int panesMaxAProducir = granero.getCantidadMaxPanes() - granero.getCantidadPanes();
        return (int) (Math.random() * panesMaxAProducir);
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
