from pedido import Pedido
from lista_pedidos import ListaPedidos
from cocinero import Cocinero

# Clase principal: inicia el sistema y muestra mensajes
def main():
    print("-|GESTOR DE COCINA|-")
    print("Bienvenido al sistema de gestión del restaurante\n")

    pedidos = [
        Pedido(1, "Paella"),
        Pedido(2, "Tortilla de patatas"),
        Pedido(3, "Gazpacho"),
        Pedido(4, "Croquetas"),
        Pedido(5, "Pulpo a la gallega"),
        Pedido(6, "Fabada asturiana"),
    ]

    lista_pedidos = ListaPedidos(pedidos)

    cocineros = [
        Cocinero(1, lista_pedidos),
        Cocinero(2, lista_pedidos),
        Cocinero(3, lista_pedidos),
    ]

    for cocinero in cocineros:
        cocinero.start()

    for cocinero in cocineros:
        cocinero.join()

    print("\nTodos los pedidos han sido procesados.")
    print("Se han guardado todos los pedidos en el archivo de log:log_pedidos.txt")

if __name__ == "__main__":
    main()
