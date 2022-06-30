public class Productor extends Thread {
    private Granero granero;

    private String nombre;
    private int turno;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
}
