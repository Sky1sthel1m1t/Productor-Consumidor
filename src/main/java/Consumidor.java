public class Consumidor extends Thread{

    private Granero granero;
    private String nombre;
    private int panConsumir;


    public Consumidor(Granero granero, String nombre, int panConsumir) {
        this.granero = granero;
        this.nombre = nombre;
        this.panConsumir = panConsumir;

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

}
