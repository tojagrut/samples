import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class SignatureUtil {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String key = "putyourkeyhere";
        String secret = "putyoursecrethere";

        System.out.println(generateSignature(key, secret));
    }

    private static String generateSignature(String key, String secret) throws NoSuchAlgorithmException {

        String utcTime = String.valueOf(Math.round(new Date().getTime() / 1000));
        String textTobeConverted = key + secret + utcTime;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(textTobeConverted.getBytes());
        byte[] byteData = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte value : byteData) {
            sb.append(Integer.toString((value & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();

    }
}
