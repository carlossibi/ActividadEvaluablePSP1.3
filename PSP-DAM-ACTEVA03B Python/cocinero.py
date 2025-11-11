import threading
import time

# Clase Cocinero: representa un hilo que prepara pedidos
class Cocinero(threading.Thread):
    def __init__(self, id, lista_pedidos):
        super().__init__()
        self.id = id
        self.lista_pedidos = lista_pedidos

    def run(self):
        while True:
            pedido = self.lista_pedidos.tomar_pedido()
            if pedido is None:
                break
            self.preparar_pedido(pedido)

    def preparar_pedido(self, pedido):
        print(f"Cocinero {self.id} está preparando el pedido {pedido.id}: {pedido.nombre_plato}")
        time.sleep(2)  # Simula el tiempo de preparación
        self.lista_pedidos.registrar_pedido(pedido, self.id)
