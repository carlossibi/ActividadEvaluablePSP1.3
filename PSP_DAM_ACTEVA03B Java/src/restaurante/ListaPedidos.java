package restaurante;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Clase que gestiona la lista de pedidos compartida por los cocineros
 * Contiene la sincronización
 * También se encarga de escribir un log en archivo de texto
 */
public class ListaPedidos {
    private List<Pedido> pedidos;  // Lista compartida
    private int pedidosProcesados; // Contador
    private final File logFile;    // Archivo para log

    public ListaPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        this.pedidosProcesados = 0;

        // Crear la carpeta "logs" si no existe
        File carpetaLogs = new File("logs");
        if (!carpetaLogs.exists()) {
            carpetaLogs.mkdir();
        }
        // Archivo de log dentro de la carpeta logs
        this.logFile = new File(carpetaLogs, "log_pedidos.txt");
    }

    /**
     * Método sincronizado para que solo un hilo a la vez tome un pedido
     * elimina y retorna el primer pedido de la lista
     * @return Pedido o null si la lista está vacía
     */
    public synchronized Pedido tomarPedido() {
        if (pedidos.isEmpty()) {
            return null;
        }
        return pedidos.remove(0);
    }

    /**
     * Método sincronizado que registra la preparación de un pedido,
     * imprime mensaje en consola y escribe en el archivo de log
     * @param pedido Pedido que se ha preparado
     * @param idCocinero Identificador del hilo/cocinero
     */
    public synchronized void registrarPedido(Pedido pedido, int idCocinero) {
        pedidosProcesados++;
        String mensaje = "Pedido " + pedido.getId() + " (" + pedido.getNombrePlato() +
                         ") preparado por cocinero " + idCocinero;
        System.out.println(mensaje);
        escribirEnLog(mensaje);
    }

    /**
     * Escribe una línea en el archivo de log, con manejo básico de errores
     */
    private void escribirEnLog(String mensaje) {
        try (FileWriter fw = new FileWriter(logFile, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
