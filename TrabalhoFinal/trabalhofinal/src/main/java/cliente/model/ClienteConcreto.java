package cliente.model;

public class ClienteConcreto extends Cliente {
    public ClienteConcreto(int idCliente, String nome, String email, String endereco, String telefone,
                           String userName, String password) {
        super(idCliente, nome, email, endereco, telefone, userName, password);
    }
}
/** herda todos os atributos da classe Cliente.
 * é uma implementação minima que permite instanciar objetos de tipo Cliente
 */