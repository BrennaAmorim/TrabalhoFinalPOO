package cliente.model;

import java.util.InputMismatchException;

public class ClientePessoaJuridica extends Cliente {

    private String cnpj;

    private String razaoSocial;

    private String inscricaoEstadual;

    public ClientePessoaJuridica(int idCliente, String nome, String email, String endereco,
                                 String telefone, String userName, String password, String cnpj, String razaoSocial, String inscricaoEstadual) {
        super(idCliente, nome, email, endereco, telefone, userName, password);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public boolean validarCNPJ(String cnpj) {
        if (cnpj == null|| cnpj.length() != 14 || cnpj.matches("^(.)\1+$")) {
            return false;
        }

        try {
            int sum = 0;
            int[] weights = {6, 7, 8, 9, 2, 3, 4, 5};

            for (int i = 0; i < 12; i++) {
                sum += Character.getNumericValue(cnpj.charAt(i)) * weights[i % 8];
            }
            int firstCheckDigit = sum % 11;
            firstCheckDigit = (firstCheckDigit < 2) ? 0 : 11 - firstCheckDigit;

            sum = 0;
            weights = new int[]{5, 6, 7, 8, 9, 2, 3, 4};

            for (int i = 0; i < 13; i++) {
                sum += Character.getNumericValue(cnpj.charAt(i)) * weights[i % 8];
            }
            int secondCheckDigit = sum % 11;
            secondCheckDigit = (secondCheckDigit < 2) ? 0 : 11 - secondCheckDigit;

            return cnpj.charAt(12) == Character.forDigit(firstCheckDigit, 10) &&
                    cnpj.charAt(13) == Character.forDigit(secondCheckDigit, 10);
        } catch (InputMismatchException e) {
            return false;
        }
    }
}