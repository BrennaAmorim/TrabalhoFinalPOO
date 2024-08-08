package cliente.repository;

import cliente.model.Cliente;
import cliente.model.ClienteConcreto;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class ClienteRepository {
    private final String arquivoClientes = "database/clientes.csv";
    private static List<Cliente> clientes;

    public List<Cliente> carregarClientes() throws IOException, URISyntaxException {
        if (clientes == null) {
            // Tentar obter o caminho do arquivo como um recurso
            URL resource = getClass().getClassLoader().getResource(arquivoClientes);

            // Verificar se o recurso foi encontrado
            if (resource == null) {
                throw new IOException("Arquivo não encontrado: " + arquivoClientes);
            }

            // Converter URL para URI e obter o caminho absoluto
            Path caminhoArquivoAbsoluto = Paths.get(resource.toURI());
            clientes = Files.lines(caminhoArquivoAbsoluto)
                    .map(line -> {
                        if (line.isEmpty()) {
                            return null;
                        }
                        String[] cliente = line.split(",");
                        // Use a classe concreta ClienteConcreto para instanciar o Cliente
                        return new ClienteConcreto(
                                Integer.parseInt(cliente[0]), cliente[1], cliente[2],
                                cliente[3], cliente[4], cliente[5], cliente[6]
                        );
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        return clientes;
    }

    public boolean validarEmailCadastrado(String email) throws IOException, URISyntaxException {
        if (clientes == null) {
            carregarClientes();
        }

        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public void salvarCliente(Cliente cliente) throws IOException, URISyntaxException {
        // Tentar obter o caminho do arquivo como um recurso
        URL resource = getClass().getClassLoader().getResource(arquivoClientes);

        // Verificar se o recurso foi encontrado
        if (resource == null) {
            throw new IOException("Arquivo não encontrado: " + arquivoClientes);
        }

        // Converter URL para URI e obter o caminho absoluto
        Path caminhoArquivoAbsoluto = Paths.get(resource.toURI());

        // Usar BufferedWriter para adicionar um novo cliente ao final do arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivoAbsoluto.toFile(), true))) {
            writer.newLine();
            writer.write(cliente.getIdCliente() + "," +
                    cliente.getNome() + "," +
                    cliente.getEmail() + "," +
                    cliente.getEndereco() + "," +
                    cliente.getTelefone() + "," +
                    cliente.getUserName() + "," +
                    cliente.getPassword());
        }

        clientes.add(cliente);
    }
}
