package br.com.societaria.financeiro.banco.service;

import br.com.societaria.financeiro.banco.model.Empresa;
import br.com.societaria.financeiro.banco.model.Socio;

import java.util.HashSet;
import java.util.Set;

/*
    Classe do serviço responsável por calcular o total de bens imóveis do comprometimento financeiro.
*/
public class CalculoFinanceiro {

    public static double comprometimentoFinanceiro(Empresa empresa) {
        return comprometimentoFinanceiro(empresa, new HashSet<>());
    }

    private static double comprometimentoFinanceiro(Socio socio, Set<Socio> socios) {
        if (!socios.add(socio)) {
            return 0;
        }

        socios.add(socio);

        double total = socio.getTotalBensImoveis();

        if (socio instanceof Empresa) {
            Empresa empresa = (Empresa) socio;
            for (Socio s : empresa.getSocios()) {
                total += comprometimentoFinanceiro(s, socios);
            }
        }

        return total;
    }
}
