package pedido.model;

import java.time.LocalDate;
import java.util.Set;

public class Pedido {

    private int idPedido;

    private LocalDate dataPedido;

    private String status;

    private int idCliente;

    private Set<Item> itens;

    public Pedido(int idPedido, LocalDate dataPedido, String status, int idCliente, Set<Item> itens) {
        this.idPedido = idPedido;
        this.dataPedido = dataPedido;
        this.status = status;
        this.idCliente = idCliente;
        this.itens = itens;
    }

    public double calcularTotal() {
        double total = 0D;

        for (Item item : itens) {
            total += item.calcularSubtotal();
        }

        return total;
    }

    public void atualizarStatus(String status) {
        this.status = status;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public String getStatus() {
        return status;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public Set<Item> getItens() {
        return itens;
    }
}
