package restaurante;

/**
 * Clase que extiende Thread para simular un cocinero
 * El cocinero toma pedidos de la lista y los prepara (simulado con sleep)
 */
public class Cocinero extends Thread {
    private int id;
    private ListaPedidos listaPedidos;

    public Cocinero(int id, ListaPedidos listaPedidos) {
        this.id = id;
        this.listaPedidos = listaPedidos;
    }

    @Override
    public void run() {
        while (true) {
            Pedido pedido = listaPedidos.tomarPedido();  // toma un pedido sincronizado
            if (pedido == null) {
                break;  // no quedan pedidos, termina hilo
            }
            prepararPedido(pedido);
        }
    }

    /**
     * Simula preparación con retardo y registra en listaPedidos
     */
    private void prepararPedido(Pedido pedido) {
        System.out.println("Cocinero " + id + " está preparando el pedido " +
                           pedido.getId() + ": " + pedido.getNombrePlato());
        try {
            Thread.sleep(2000); // 2 segundos, simulan preparación
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listaPedidos.registrarPedido(pedido, id);  // registrar finalización
    }
}
