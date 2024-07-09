package br.com.societaria.financeiro.banco.validator;

import lombok.NoArgsConstructor;

import javax.validation.ConstraintValidatorContext;

@NoArgsConstructor
public class CpfValidator {


    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            return false;
        }
        return isValidCPF(cpf);
    }

    private boolean isValidCPF(String cpf) {
        int[] weightCPF = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * weightCPF[i];
        }
        int mod = 11 - (sum % 11);
        char firstDigit = (mod > 9) ? '0' : (char) (mod + '0');

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * (weightCPF[i] + 1);
        }
        mod = 11 - (sum % 11);
        char secondDigit = (mod > 9) ? '0' : (char) (mod + '0');

        return cpf.charAt(9) == firstDigit && cpf.charAt(10) == secondDigit;
    }
}
