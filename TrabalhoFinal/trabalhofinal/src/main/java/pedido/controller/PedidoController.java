package pedido.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pedido.repository.PedidoRepository;
import pedido.model.Item;
import pedido.model.Pedido;
import pedido.model.Produto;

@RestController
public class PedidoController {

    private static final Map<Integer, Set<Item>> CARRINHO_COMPRAS = new HashMap<>();

    private static int idPedido = 1;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("/listar-produtos")
    public List<Produto> listarProdutos() throws IOException, URISyntaxException {
        return pedidoRepository.listarProdutos();
    }

    @GetMapping("/buscar-produto/{idProduto}")
    public Produto buscarProduto(@PathVariable int idProduto) throws IOException, URISyntaxException {
        return pedidoRepository.buscarProduto(idProduto);
    }

    @PostMapping("/adicionar-produto-carrinho/{idCliente}")
    public void selecionarItem(@PathVariable int idCliente, @RequestBody Item item) {
        if (CARRINHO_COMPRAS.containsKey(idCliente)) {
            CARRINHO_COMPRAS.get(idCliente).add(item);
        } else {
            Set<Item> itens = new HashSet<>();
            itens.add(item);
            CARRINHO_COMPRAS.put(idCliente, itens);
        }
    }

    @GetMapping("/buscar-item/{idCliente}")
    public Set<Item> buscarItems(@PathVariable int idCliente) {
        return CARRINHO_COMPRAS.get(idCliente);
    }

    @PostMapping("/criar-pedido/{idCliente}")
    public Pedido criarPedido(@PathVariable int idCliente) throws IOException, URISyntaxException {
        if (!CARRINHO_COMPRAS.containsKey(idCliente)) {
            throw new RuntimeException("Precisa adicionar itens no pedido");
        }

        Pedido pedido = new Pedido(idPedido++, LocalDate.now(), "CRIADO", idCliente, CARRINHO_COMPRAS.get(idCliente));
        pedidoRepository.salvarPedido(pedido);
        CARRINHO_COMPRAS.remove(pedido.getIdCliente());
        return pedido;
    }

    @GetMapping("/listar-pedidos/{idCliente}")
    public List<Pedido> listarPedidos(@PathVariable int idCliente) throws IOException, URISyntaxException {
        return pedidoRepository.carregarPedidosRealizados(idCliente);
    }
}
