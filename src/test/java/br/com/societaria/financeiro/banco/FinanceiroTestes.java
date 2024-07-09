package br.com.societaria.financeiro.banco;

import br.com.societaria.financeiro.banco.model.Empresa;
import br.com.societaria.financeiro.banco.model.PessoaFisica;
import br.com.societaria.financeiro.banco.model.PessoaJuridica;
import br.com.societaria.financeiro.banco.service.CalculoFinanceiro;
import br.com.societaria.financeiro.banco.validator.CnpjValidator;
import br.com.societaria.financeiro.banco.validator.CpfValidator;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.junit.Assert.*;

public class FinanceiroTestes {
    private static final double DELTA = 1e-6; // precisão para comparação de doubles
    private static final Logger log = LoggerFactory.getLogger(FinanceiroTestes.class);

    @Test
    public void testCalcularTotalComprometimentoFinanceiro() {
        // Criação de pessoas físicas
        log.info(() -> "Criação de pessoas fisicas.");
        PessoaFisica pf1 = new PessoaFisica("123.456.789-00", 500000);
        PessoaFisica pf2 = new PessoaFisica("987.654.321-00", 300000);

        // Criação de empresa D
        log.info(() -> "Criação de empresa D.");
        Empresa empresaD = new Empresa("Empresa D");
        empresaD.adicionarSocio(pf1);
        empresaD.adicionarSocio(pf2);

        // Cálculo do total de bens Empresa D
        log.info(() -> "Cálculo do total de bens empresa D.");
        double totalEmpresaD = CalculoFinanceiro.comprometimentoFinanceiro(empresaD);
        assertEquals(800000, totalEmpresaD, DELTA);
        log.info(() -> "Total de bens empresa D: " + totalEmpresaD);

        // Criação de empresa A
        log.info(() -> "Criação de empresa A.");
        Empresa empresaA = new Empresa("Empresa A");
        empresaA.adicionarSocio(empresaD);
        empresaA.adicionarSocio(new PessoaFisica("111.222.333-44", 200000));
        empresaA.adicionarSocio(new PessoaJuridica("00.123.456/0001-78", 400000));

        // Cálculo do total de bens Empresa A
        log.info(() -> "Cálculo do total de bens empresa A.");
        double totalEmpresaA = CalculoFinanceiro.comprometimentoFinanceiro(empresaA);
        assertEquals(1400000, totalEmpresaA, DELTA);
        log.info(() -> "Total de bens empresa A: " + totalEmpresaA);
    }

    @Test
    public void testCalcularTotalComprometimentoFinanceiroComCiclo() {
        // Criação de pessoas físicas
        log.info(() -> "Criação de pessoa fisica.");
        PessoaFisica pf1 = new PessoaFisica("123.456.789-00", 500000);

        // Criação de empresas
        log.info(() -> "Criação da empresa A.");
        Empresa empresaA = new Empresa("Empresa A");

        log.info(() -> "Criação da empresa B.");
        Empresa empresaB = new Empresa("Empresa B");

        // Adicionar ciclos
        log.info(() -> "Adicionando ciclos.");
        empresaA.adicionarSocio(empresaB);
        empresaB.adicionarSocio(empresaA);

        // Adicionar sócios
        log.info(()-> "Adicionando socios.");
        empresaA.adicionarSocio(pf1);

        // Cálculo do total de bens Empresa A
        log.info(() -> "Cálculo do total de bens empresa A.");
        double totalEmpresaA = CalculoFinanceiro.comprometimentoFinanceiro(empresaA);
        assertEquals(500000, totalEmpresaA, DELTA);
        log.info(() -> "Total de bens empresa A: " + totalEmpresaA);
    }

    @Test
    public void testValidarCpfCnpj() {

        CpfValidator cpfValidator = new CpfValidator();
        CnpjValidator cnpjValidator = new CnpjValidator();

        // Criação de pessoa fisica
        log.info(() -> "Criação de pessoa fisica.");
        PessoaFisica pf1 = new PessoaFisica("123.456.789-00", 500000);

        // Validar cpf pessoa fisica
        log.info(() -> "Validando cpf pessoa fisica.");
        boolean isValidCpf = cpfValidator.isValid(pf1.getCpf(), null);
        assertEquals(false, isValidCpf);
        boolean finalIsValidCpf = isValidCpf;
        log.info(() -> "Cpf valido? " + finalIsValidCpf);


        // Criação de pessoa juridica
        log.info(() -> "Criação de pessoa juridica.");
        PessoaJuridica pj1 = new PessoaJuridica("00.123.456/0001-78", 500000);

        // Validar cnpj pessoa juridica
        log.info(() -> "Validando cnpj pessoa juridica.");
        boolean isValidCnpj = cnpjValidator.isValid(pj1.getCnpj(), null);
        assertEquals(false, isValidCnpj);
        boolean finalIsValidCnpj = isValidCnpj;
        log.info(() -> "Cnpj valido? " + finalIsValidCnpj);


        // Criação de pessoa fisica 2
        log.info(() -> "Criação de pessoa fisica 2.");
        PessoaFisica pf2 = new PessoaFisica("12345678912", 500000);

        // Validar cpf pessoa fisica 2
        log.info(() -> "Validando cpf pessoa fisica 2.");
        isValidCpf = cpfValidator.isValid(pf2.getCpf().replaceAll("[^\\d]", ""), null);
        assertEquals(false, isValidCpf);
        boolean finalIsValidCpf1 = isValidCpf;
        log.info(() -> "Cpf valido? " + finalIsValidCpf1);


        // Criação de pessoa juridica 2
        log.info(() -> "Criação de pessoa juridica 2.");
        PessoaJuridica pj2 = new PessoaJuridica("00123456000178", 500000);

        // Validar cnpj pessoa juridica 2
        log.info(() -> "Validando cnpj pessoa juridica 2.");
        isValidCnpj = cnpjValidator.isValid(pj2.getCnpj().replaceAll("[^\\d]", ""), null);
        assertEquals(false, isValidCnpj);
        boolean finalIsValidCnpj1 = isValidCnpj;
        log.info(() -> "Cnpj valido? " + finalIsValidCnpj1);

    }

    @Test
    public void testValidarNome() {

        log.info(() -> "Criação de empresa D.");
        Empresa empresaD = new Empresa("Empresa D");


        // Validar nome empresa D não sendo nulo
        log.info(() -> "Validando nome empresa D não sendo nulo.");
        assertNotNull(empresaD.getNome());
        log.info(() -> "Nome da empresa D: " + empresaD.getNome());


        log.info(() -> "Criação de empresa A.");
        Empresa empresaA = new Empresa();


        // Validar nome empresa A sendo nulo
        log.info(() -> "Validando nome empresa A sendo nulo.");
        assertNull(empresaA.getNome());
        log.info(() -> "Nome da empresa A: " + empresaA.getNome());

    }
}
