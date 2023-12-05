package br.cefetmg.snacksmart.utils;

public class Tester {
    public static void main(String[] args) {
        String cpfValido = "000.000.000-00";
        String cpfInvalido = "00000000000";

        System.out.println(InputValidador.validaCPF(cpfValido));
        System.out.println(InputValidador.validaCPF(cpfInvalido));

        String tel = "(31)996120318";
        System.out.println(InputValidador.validaTelefone(tel));

        String email1 = "eloymaciel06@gmail.com";
        System.out.println(InputValidador.validaEmail(email1));
        String email2 = "eloymaciel06@gmail.com.br";
        System.out.println(InputValidador.validaEmail(email2));
        String email3 = "eloymaciel06@gmail";
        System.out.println(InputValidador.validaEmail(email3));

    }
}
