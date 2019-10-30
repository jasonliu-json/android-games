package uoft.csc207.gameapplication;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
    public static String hash(String value, String hashingAlgorithm) {
        try {
            String valueHash = "";
            MessageDigest mDigest = MessageDigest.getInstance(hashingAlgorithm);
            byte[] hash = mDigest.digest(value.getBytes(StandardCharsets.UTF_8));

            for (byte b : hash) {
                valueHash += String.format("%02x", b);
            }
            return valueHash;
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
