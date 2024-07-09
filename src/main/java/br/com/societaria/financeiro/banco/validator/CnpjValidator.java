package br.com.societaria.financeiro.banco.validator;

import lombok.NoArgsConstructor;

import javax.validation.ConstraintValidatorContext;

@NoArgsConstructor
public class CnpjValidator {

    public boolean isValid(String cnpj, ConstraintValidatorContext context) {
        if (cnpj == null || cnpj.length() != 14 || !cnpj.matches("\\d{14}")) {
            return false;
        }
        return isValidCNPJ(cnpj);
    }

    private boolean isValidCNPJ(String cnpj) {
        int[] weightCNPJ = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += (cnpj.charAt(i) - '0') * weightCNPJ[i];
        }
        int mod = sum % 11;
        char firstDigit = (mod < 2) ? '0' : (char) ((11 - mod) + '0');

        sum = 0;
        for (int i = 0; i < 13; i++) {
            sum += (cnpj.charAt(i) - '0') * (weightCNPJ[i % 12] + 1);
        }
        mod = sum % 11;
        char secondDigit = (mod < 2) ? '0' : (char) ((11 - mod) + '0');

        return cnpj.charAt(12) == firstDigit && cnpj.charAt(13) == secondDigit;
    }
}
