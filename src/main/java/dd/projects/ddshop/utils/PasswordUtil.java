package dd.projects.ddshop.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {
    public static String getMd5(final String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            final MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            final byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into Sig_num representation
            final BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            final StringBuilder hash_text = new StringBuilder(no.toString(16));
            while (hash_text.length() < 32) {
                hash_text.insert(0, "0");
            }
            return hash_text.toString();
        }

        // For specifying wrong message digest algorithms
        catch (final NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
