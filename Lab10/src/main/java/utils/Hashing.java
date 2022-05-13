package utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class Hashing {
    private SecureRandom random= new SecureRandom();
    private static byte[] salt= new byte[16];


    public Hashing() {
        random.nextBytes(salt);
    }
    public static byte[] passwordHashing(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec=new PBEKeySpec(password.toCharArray(),salt,65536,128);
        SecretKeyFactory factory= SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return factory.generateSecret(spec).getEncoded();
    }
}
