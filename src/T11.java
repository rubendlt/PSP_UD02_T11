import java.util.Random;

public class T11 {

    public static void main(String[] args) {
        System.out.println("Iniciando el efecto dominó con 5 hilos");

        Thread primerHilo = new Thread(new FichaDomino(1, 5));
        primerHilo.start();
    }

    static class FichaDomino implements Runnable {
        private final int id;
        private final int totalHilos;
        private Thread siguienteHilo;

        public FichaDomino(int id, int totalHilos) {
            this.id = id;
            this.totalHilos = totalHilos;
        }

        @Override
        public void run() {
            try {
                if (id < totalHilos) {
                    siguienteHilo = new Thread(new FichaDomino(id + 1, totalHilos));
                    siguienteHilo.start();
                }

                Random random = new Random();
                for (int i = 1; i <= 5; i++) {
                    System.out.println("[Hilo-" + id + "] Iteración " + i);

                    int espera = 100 + random.nextInt(501);
                    Thread.sleep(espera);
                }

                if (id < totalHilos) {
                    siguienteHilo.join();
                }

                System.out.println("Acabó hilo Hilo-" + id);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}