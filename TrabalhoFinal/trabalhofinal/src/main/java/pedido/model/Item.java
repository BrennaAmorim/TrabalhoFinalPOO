package pedido.model;

public class Item {

    private int idItem;

    private int quantidade;

    private int precoUnitario;

    public Item(int idItem, int quantidade, int precoUnitario) {
        this.idItem = idItem;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public double calcularSubtotal() {
        return quantidade * precoUnitario;
    }

    public int getIdItem() {
        return idItem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getPrecoUnitario() {
        return precoUnitario;
    }
}
