import threading

# Clase ListaPedidos: gestiona la lista compartida y el log
class ListaPedidos:
    def __init__(self, pedidos):
        self.pedidos = pedidos
        self.lock = threading.Lock()
        self.log_file = "logs/log_pedidos.txt"
        self._crear_carpeta_logs()

    def _crear_carpeta_logs(self):
        import os
        if not os.path.exists("logs"):
            os.mkdir("logs")

    def tomar_pedido(self):
        with self.lock:
            if not self.pedidos:
                return None
            return self.pedidos.pop(0)

    def registrar_pedido(self, pedido, id_cocinero):
        mensaje = f"Pedido {pedido.id} ({pedido.nombre_plato}) preparado por cocinero {id_cocinero}"
        print(mensaje)
        with self.lock:
            with open(self.log_file, "a", encoding="utf-8") as f:
                f.write(mensaje + "\n")
