public class Productor extends Thread {

    private int panes = 10;
    private Granero granero;
    private int turno;
    private String nombre;

    public Productor(Granero granero, int turno, String nombre) {
        this.granero = granero;
        this.turno = turno;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        while (true){
            granero.producir(panes, this);
        }
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
