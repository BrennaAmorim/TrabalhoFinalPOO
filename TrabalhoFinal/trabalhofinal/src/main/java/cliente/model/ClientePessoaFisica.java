package cliente.model;

import java.util.InputMismatchException;

public class ClientePessoaFisica extends Cliente {

    private String cpf;

    private String rg;

    private String dataNascimento;

    public ClientePessoaFisica(int idCliente, String nome, String email, String endereco,
                               String telefone, String userName, String password, String cpf, String rg, String dataNascimento) {
        super(idCliente, nome, email, endereco, telefone, userName, password);
        this.cpf = cpf;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
    }

    public boolean validarCPF(String cpf) {
        if (cpf == null||  cpf.length() != 11 || cpf.matches("^(.)\1+$;")) {
            return false;
        } //verifica se o cpf é null || verifica se o o cpf tem 11 numeros || verifica se todos os numeros são iguais

        try {
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int firstCheckDigit = 11 - (sum % 11);
            if (firstCheckDigit >= 10) firstCheckDigit = 0;

            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            int secondCheckDigit = 11 - (sum % 11);
            if (secondCheckDigit >= 10) secondCheckDigit = 0;

            return cpf.charAt(9) == Character.forDigit(firstCheckDigit, 10) &&
                    cpf.charAt(10) == Character.forDigit(secondCheckDigit, 10);
        } catch (InputMismatchException e) {
            return false;
        }
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
}
