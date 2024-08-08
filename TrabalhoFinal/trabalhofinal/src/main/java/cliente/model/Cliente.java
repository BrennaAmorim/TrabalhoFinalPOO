package cliente.model;

public abstract class Cliente {
    private int idCliente;

    private String nome;

    private String email;

    private String endereco;

    private String telefone;

    private String userName;

    private String password;

    public Cliente(int idCliente, String nome, String email, String endereco, String telefone,
        String userName, String password) {
        if (nome == null) {
            throw new RuntimeException("Nome e obrigatorio!");
        }

        if (email == null) {
            throw new RuntimeException("Email e obrigatorio!");
        }

        if (password == null) {
            throw new RuntimeException("Senha e obrigatoria!");
        }

        if (endereco == null) {
            throw new RuntimeException("Endereco e obrigatorio!");
        }

        if (telefone == null) {
            throw new RuntimeException("Telefone e obrigatorio!");
        }

        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.userName = userName;
        this.password = password;
    }

    public boolean login(String userName, String password) {
        return this.userName.equals(userName) && this.password.equals(password);
    }

    public void atualizaDados(Cliente cliente) {
        this.nome = cliente.nome;
        this.email = cliente.email;
        this.endereco = cliente.endereco;
        this.telefone = cliente.telefone;
    }


    public int getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
