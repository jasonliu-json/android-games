package uoft.csc207.gameapplication;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class RegisterUtility {
    public static final String emailRegex = "^[a-zA-Z0-9]+([-_+.][a-zA-Z0-9]+)*" +
            "@([a-zA-Z0-9]+)([-.][a-zA-Z0-9]+)*\\.[a-zA-Z]{2,7}$";
    public static final String passwordRegex = "^\\.*(?=.*[0-9])\\.*(?=.*[a-z])" +
            "\\.*(?=.*[A-Z])\\.*(?=.*[@#$%-_=+!^&*\\\\])\\.*(?=\\S+$).{8,}$";

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
    public static boolean validEmail(String email) {
        Pattern regexPattern = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return regexPattern.matcher(email).matches();
    }

    public static boolean strongPassword(String password) {
        Pattern regexPattern = Pattern.compile(passwordRegex);
        if (password == null)
            return false;
        return regexPattern.matcher(password).matches();
    }
}
