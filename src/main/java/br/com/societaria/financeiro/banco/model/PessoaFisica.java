package br.com.societaria.financeiro.banco.model;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
   Representa uma pessoa física com CPF e bens imóveis.
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PessoaFisica extends Socio{

    @CPF(message = "CPF inválido")
    @NotNull(message = "CPF obrigatório")
    @NotEmpty(message = "CPF obrigatório")
    @NotBlank(message = "CPF obrigatório")
    private String cpf;

    public PessoaFisica(String cpf, double totalBensImoveis) {
        super("Pessoa Física", totalBensImoveis);
        this.cpf = cpf;
    }

    @Override
    public int hashCode() {
        return cpf != null ? cpf.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PessoaFisica that = (PessoaFisica) obj;
        return cpf != null ? cpf.equals(that.cpf) : that.cpf == null;
    }
}
