package br.cefetmg.snacksmart.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SenhaManager {
    public static String fazHash(final String senha) {
        if(senha.isEmpty())
            return "";
        
        MessageDigest algorithm = null;
        try {
            algorithm = MessageDigest.getInstance("SHA-256");

            byte[] hash = new byte[0];
            hash = algorithm.digest(senha.getBytes(StandardCharsets.UTF_8));

            StringBuilder senhaHash = new StringBuilder();
            for (byte b : hash) {
                senhaHash.append(String.format("%02X", 0xFF & b));
            }

            return senhaHash.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
