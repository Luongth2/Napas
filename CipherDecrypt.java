import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;
import javax.crypto.Cipher;

public class CipherDecrypt {
    public static void main(String args[]) throws Exception{
        //Creating a Signature object
        Signature sign = Signature.getInstance("SHA256withRSA");

        //Creating KeyPair generator object
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

        //Initializing the key pair generator
        keyPairGen.initialize(1024);
        //keyPairGen.initialize(2048);

        //Generate the pair of keys
        KeyPair pair = keyPairGen.generateKeyPair();

        //Getting the public key from the key pair
        PublicKey publicKey = pair.getPublic();

        //Creating a Cipher object
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        //Initializing a Cipher object
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        //Add data to the cipher
        byte[] input = "Hello World".getBytes();
        cipher.update(input);

        //encrypting the data
        byte[] cipherText = cipher.doFinal();
        //System.out.println( new String(cipherText, "UTF8"));
        System.out.println("cipherText "+encode(cipherText));

        //Initializing the same cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
        //System.out.println("Key: \n" + pair.getPrivate());
        System.err.println("Public key\n"+ encode(pair.getPublic().getEncoded()));
        System.err.println("Private key\n"+ encode(pair.getPrivate().getEncoded()));

        //Decrypting the text
        byte[] decipheredText = cipher.doFinal(cipherText);
        System.out.println("Decrypted: " + encode(decipheredText));
    }
     private static String encode(byte[] data) {
         return Base64.getEncoder().encodeToString(data);
     }
       
}      