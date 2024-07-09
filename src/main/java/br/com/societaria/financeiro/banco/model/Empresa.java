package br.com.societaria.financeiro.banco.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

/*
   Representa uma empresa que possui uma estrutura societária.
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Empresa extends Socio {

    private Set<Socio> socios;

    public Empresa(String nome) {
        super(nome, 0);
        this.socios = new HashSet<>();
    }

    public void adicionarSocio(Socio socio) {
        socios.add(socio);
    }

    public Set<Socio> getSocios() {
        return socios;
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
        Empresa empresa = (Empresa) obj;
        return nome != null ? nome.equals(empresa.nome) : empresa.nome == null;
    }
}
