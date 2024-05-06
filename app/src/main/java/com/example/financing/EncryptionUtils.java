package com.example.financing;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import android.util.Base64;

public class EncryptionUtils {

    private static final String ALGORITHM = "PBEWithMD5AndDES";
    private static final String CHARSET = "UTF-8";
    private static final int ITERATION_COUNT = 1000;
    private static final String PASSWORD = "HELLO Lain";

//    加密
    public static String encrypt(String input) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            KeySpec spec = new PBEKeySpec(PASSWORD.toCharArray());
            SecretKey key = factory.generateSecret(spec);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec("SALTSALT".getBytes(CHARSET), ITERATION_COUNT);

            cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            byte[] encrypted = cipher.doFinal(input.getBytes(CHARSET));

            return Base64.encodeToString(encrypted, Base64.NO_WRAP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    解密
    public static String decrypt(String encryptedInput) {
        try {
            byte[] encrypted = Base64.decode(encryptedInput, Base64.NO_WRAP);

            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            KeySpec spec = new PBEKeySpec(PASSWORD.toCharArray());
            SecretKey key = factory.generateSecret(spec);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec("SALTSALT".getBytes(CHARSET), ITERATION_COUNT);

            cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
            byte[] decrypted = cipher.doFinal(encrypted);

            return new String(decrypted, CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
