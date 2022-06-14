public class Productor extends Thread {

    private int panes = 10;
    private Granero granero;

    public Productor(Granero granero) {
        this.granero = granero;
    }

    @Override
    public void run() {
        while (true){
            granero.producir(panes, this);

        }
    }
}
