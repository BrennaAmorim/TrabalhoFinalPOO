package pedido.model;

public class Produto {

    private int idProduto;

    private String nome;

    private String descricao;

    private double preco;

    private int estoque;

    private String imagem;

    public Produto(int idProduto, String nome, String descricao, double preco, int estoque, String imagem) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
        this.imagem = imagem;
    }

    public void atualizaEstoque(int quantidade) {
        this.estoque = quantidade;
    }

    public void atualizaPreco(double preco) {
        this.preco = preco;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public String getImagem() {
        return imagem;
    }
}
