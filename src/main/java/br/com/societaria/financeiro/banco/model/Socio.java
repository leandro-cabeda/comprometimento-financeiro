package br.com.societaria.financeiro.banco.model;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
  Representa um sócio, que pode ser uma pessoa física ou uma outra empresa.
*/
@Data
@NoArgsConstructor
@ToString
public abstract class Socio {

    @NotNull(message = "Nome obrigatório")
    @NotEmpty(message = "Nome obrigatório")
    @NotBlank(message = "Nome obrigatório")
    protected String nome;

    @Min(value = 50, message = "O valor deve ser maior que 50")
    @NotBlank(message = "Total bens imóveis obrigatório")
    protected double totalBensImoveis;

    public Socio(String nome, double totalBensImoveis) {
        this.nome = nome;
        this.totalBensImoveis = totalBensImoveis;
    }

    // Adicionar os métodos hashCode e equals
    @Override
    public int hashCode() {
        return nome != null ? nome.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Socio socio = (Socio) obj;
        return nome != null ? nome.equals(socio.nome) : socio.nome == null;
    }
}
