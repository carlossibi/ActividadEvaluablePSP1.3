package restaurante;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que inicia el programa
 * Crea pedidos, lista, cocineros (hilos) y muestra mensajes inicial y final
 */
public class Cocina {
    public static void main(String[] args) {
        System.out.println("|-GESTOR DE COCINA-|");
        System.out.println("Bienvenido al sistema de gestión del restaurante\n");

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido(1, "Paella"));
        pedidos.add(new Pedido(2, "Tortilla de patatas"));
        pedidos.add(new Pedido(3, "Gazpacho"));
        pedidos.add(new Pedido(4, "Croquetas"));
        pedidos.add(new Pedido(5, "Pulpo a la gallega"));
        pedidos.add(new Pedido(6, "Fabada asturiana"));

        ListaPedidos listaPedidos = new ListaPedidos(pedidos);

        Cocinero cocinero1 = new Cocinero(1, listaPedidos);
        Cocinero cocinero2 = new Cocinero(2, listaPedidos);
        Cocinero cocinero3 = new Cocinero(3, listaPedidos);

        // Iniciar hilos cocineros
        cocinero1.start();
        cocinero2.start();
        cocinero3.start();

        // Esperar a que terminen todos para mostrar mensaje final
        try {
            cocinero1.join();
            cocinero2.join();
            cocinero3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nTodos los pedidos han sido procesados.");
        System.out.println("Se han guardado todos los pedidos en el archivo de log: log_pedidos.txt");
    }
}
