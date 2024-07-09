package br.com.societaria.financeiro.banco.model;

import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
   Representa uma pessoa jurídica (empresa) com CNPJ e bens imóveis.
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PessoaJuridica extends Socio{

    @CNPJ(message = "CNPJ inválido")
    @NotNull(message = "CNPJ obrigatório")
    @NotEmpty(message = "CNPJ obrigatório")
    @NotBlank(message = "CNPJ obrigatório")
    private String cnpj;;

    public PessoaJuridica(String cnpj, double totalBensImoveis) {
        super("Pessoa Jurídica", totalBensImoveis);
        this.cnpj = cnpj;
    }

    // Adicionar os métodos hashCode e equals
    @Override
    public int hashCode() {
        return cnpj != null ? cnpj.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PessoaJuridica that = (PessoaJuridica) obj;
        return cnpj != null ? cnpj.equals(that.cnpj) : that.cnpj == null;
    }
}
