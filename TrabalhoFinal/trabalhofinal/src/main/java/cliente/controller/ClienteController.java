package cliente.controller;

import cliente.repository.ClienteRepository;
import cliente.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/cadastrar-cliente")
    public String cadastrarCliente(@RequestBody Cliente cliente) throws IOException, URISyntaxException {
        if (!clienteRepository.validarEmailCadastrado(cliente.getEmail())) {
            return "Email ja cadastrado";
        }

        clienteRepository.salvarCliente(cliente);

        return "Cliente cadastrado com sucesso!";
    }
}
