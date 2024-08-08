package pedido.repository;

import org.springframework.stereotype.Repository;
import pedido.model.Item;
import pedido.model.Pedido;
import pedido.model.Produto;

import api.Main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PedidoRepository {

    private final String arquivoPedidos = "database/pedidos.csv";
    private final String arquivoItens = "database/itens.csv";
    private final String arquivoProdutos = "database/produtos.csv";

    private static List<Pedido> pedidos;
    private static List<Produto> produtos;

    public List<Pedido> carregarPedidosRealizados(int idCliente) throws IOException, URISyntaxException {
        if (pedidos == null) {
            URL resourcePedidos = Main.class.getClassLoader().getResource(arquivoPedidos);
            Path caminhoArquivoPedidos = Paths.get(resourcePedidos.toURI());

            URL resourceItens = Main.class.getClassLoader().getResource(arquivoItens);
            Path caminhoArquivoItens = Paths.get(resourceItens.toURI());

            pedidos = Files.lines(caminhoArquivoPedidos)
                    .map(line -> {
                        if (line.isEmpty()) {
                            return null;
                        }
                        String[] pedido = line.split(",");
//                        Set<Item> itensDoPedido = todosItens.stream()
//                                .filter(item -> item.getIdItem() == Integer.parseInt(pedido[4]))
//                                .collect(Collectors.toSet());
                            return new Pedido(
                                    Integer.parseInt(pedido[0]),
                                    LocalDate.parse(pedido[1]),
                                    pedido[2],
                                    Integer.parseInt(pedido[3]),
//                                itensDoPedido
                                    Collections.emptySet()
                            );
                    })
                    .filter(Objects::nonNull)
                    .filter(pedido -> pedido.getIdCliente() == idCliente)
                    .collect(Collectors.toList());
        }

        return pedidos;
    }

    public List<Produto> listarProdutos() throws IOException, URISyntaxException {
        if (produtos == null) {
            URL resource = Main.class.getClassLoader().getResource(arquivoProdutos);
            Path caminhoArquivoAbsoluto = Paths.get(resource.toURI());

            produtos = Files.lines(caminhoArquivoAbsoluto)
                    .map(line -> {
                        String[] produto = line.split(",");
                        return new Produto(Integer.parseInt(produto[0]), produto[1], produto[2], Double.parseDouble(produto[3]), Integer.parseInt(produto[4]), produto[5]);
                    })
                    .collect(Collectors.toList());
        }

        return produtos;
    }

    public void salvarPedido(Pedido pedido) throws IOException, URISyntaxException {
        URL resourcePedidos = Main.class.getClassLoader().getResource(arquivoPedidos);
        Path caminhoArquivoPedidos = Paths.get(resourcePedidos.toURI());

        URL resourceItens = Main.class.getClassLoader().getResource(arquivoItens);
        Path caminhoArquivoItens = Paths.get(resourceItens.toURI());

        try (BufferedWriter writerPedido = new BufferedWriter(new FileWriter(caminhoArquivoPedidos.toFile(), true));
             BufferedWriter writerItens = new BufferedWriter(new FileWriter(caminhoArquivoItens.toFile(), true))) {

            writerPedido.newLine();
            writerPedido.write(pedido.getIdPedido() + "," +
                    pedido.getDataPedido() + "," +
                    pedido.getStatus() + "," +
                    pedido.getIdCliente() + "," +
                    pedido.getItens().stream().map(Item::getIdItem).collect(Collectors.toList()));

            for (Item item : pedido.getItens()) {
                writerItens.newLine();
                writerItens.write(item.getIdItem() + "," +
                        item.getQuantidade() + "," +
                        item.getPrecoUnitario());
            }
        }

        pedidos.add(pedido);
    }

    public Produto buscarProduto(int idProduto) throws IOException, URISyntaxException {
        if (produtos == null) {
            URL resource = Main.class.getClassLoader().getResource(arquivoProdutos);
            Path caminhoArquivoAbsoluto = Paths.get(resource.toURI());

            produtos = Files.lines(caminhoArquivoAbsoluto)
                    .map(line -> {
                        String[] produto = line.split(",");
                        return new Produto(
                                Integer.parseInt(produto[0]),
                                produto[1],
                                produto[2],
                                Double.parseDouble(produto[3]),
                                Integer.parseInt(produto[4]),
                                produto[5]
                        );
                    })
                    .collect(Collectors.toList());
        }
        for (Produto produto : produtos) {
            if (produto.getIdProduto() == idProduto) {
                return produto;
            }
        }
        return null;
    }

}
