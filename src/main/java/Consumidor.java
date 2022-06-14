public class Consumidor extends Thread{

    private Granero granero;
    private String nombre;
    private int panConsumir = 1;
    private int turno;

    public Consumidor(Granero granero, String nombre, int panConsumir, int turno) {
        this.granero = granero;
        this.nombre = nombre;
        this.panConsumir = panConsumir;
        this.turno = turno;
    }

    @Override
    public void run() {
        while (true){
            this.granero.consumir(this );
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPanConsumir() {
        return panConsumir;
    }

    public void setPanConsumir(int panConsumir) {
        this.panConsumir = panConsumir;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
}
