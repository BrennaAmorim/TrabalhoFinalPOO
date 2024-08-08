package configuration;

import cliente.repository.ClienteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pedido.repository.PedidoRepository;

import java.io.IOException;
import java.net.URISyntaxException;

@Configuration
public class DatabaseConfiguration {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostConstruct
    public void carregarDados() throws IOException, URISyntaxException {
        clienteRepository.carregarClientes();
        pedidoRepository.carregarPedidosRealizados(0);
        pedidoRepository.listarProdutos();
    }
}
