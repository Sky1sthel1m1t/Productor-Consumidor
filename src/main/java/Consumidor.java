public class Consumidor extends Thread{

    private Granero granero;
    private String nombre;
    private int panConsumir = 1;

    public Consumidor(Granero granero, String nombre) {
        this.granero = granero;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        while (true){
            this.granero.consumir();
            System.out.println("Soy la persona " + nombre + " y me he sacado " + panConsumir + " panes");
            System.out.println("Quedan " + this.granero.getCantidadPanes());

        }
    }
}
