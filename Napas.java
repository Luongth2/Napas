import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author Anass AIT BEN EL ARBI
 * <ul>
 *     <li>AES/CBC/NoPadding (128)</li>
 *     <li>AES/CBC/PKCS5Padding (128)</li>
 *     <li>AES/ECB/NoPadding (128)</li>
 *     <li>AES/ECB/PKCS5Padding (128)</li>
 *     <li>RSA/ECB/PKCS1Padding (1024, 2048)</li>
 *     <li>RSA/ECB/OAEPWithSHA-1AndMGF1Padding (1024, 2048)</li>
 *     <li>RSA/ECB/OAEPWithSHA-256AndMGF1Padding (1024, 2048)</li>
 * </ul>
 * <p>
 * for more details @see <a href="https://docs.oracle.com/javase/7/docs/api/javax/crypto/Cipher.html">Java Ciphers</a>
 */

public class Napas {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    private static final String PUBLIC_KEY_STRING = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkGtSW++euqjpae5D7HZfzlEIYGnHUtfr4NNA0GNwRMffi+LxEFUZfWgSRQ25i7zpbE/P6NitHs4KdkKLqEOlSpfmmTbswckVlNOgcBPPQQZH576GZcfP09Gik2yp0HtCJoc+HGOM+vaJpnJ8IMjyWCEajrRJ+GAQ5prBeLMdLb219jXqIcQYlm47IBEkQ26sBsI+bg7kFFM1MHx9kusUOey/me8S9Ev4j0cTG5/gL3RRubzJwHHaxQ6s98GF7YvVEgxRQ25zokwkpNeQ+tOrXDKVqlAJYV+LlUIkLKOr0yNmdB9wja2nzHUdz4pXcDm3GqJRVArURjusWCUZUpnR5wIDAQAB";
    //Please remove the ------BEGIN PRIVATE KEY------- -----END PRIVATE KEY------- \n
    private static final String PRIVATE_KEY_STRING =  "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCQa1Jb7566qOlp7kPsdl/OUQhgacdS1+vg00DQY3BEx9+L4vEQVRl9aBJFDbmLvOlsT8/o2K0ezgp2QouoQ6VKl+aZNuzByRWU06BwE89BBkfnvoZlx8/T0aKTbKnQe0Imhz4cY4z69ommcnwgyPJYIRqOtEn4YBDmmsF4sx0tvbX2NeohxBiWbjsgESRDbqwGwj5uDuQUUzUwfH2S6xQ57L+Z7xL0S/iPRxMbn+AvdFG5vMnAcdrFDqz3wYXti9USDFFDbnOiTCSk15D606tcMpWqUAlhX4uVQiQso6vTI2Z0H3CNrafMdR3PildwObcaolFUCtRGO6xYJRlSmdHnAgMBAAECggEAM7A1qfH5PZV29RLx/I/helZGSTuSu3PMWItDh1IPvI5M1CRFYzMSDHBTGaPv5LOYV8u4/f2O+JeF5w2qLm8HmcsEHZIq6rCtqLaVlYeBhiRZV/g44fTBzW4aYeliXdu/sUp2UHLQ4oGagpg3FQTWt/QhLTIZx2uaoO6CkFCLAPN3VQ/HJBjE/uqXtDKFog+GCoaETH2lMiTMQEAeIxqGE8HSnNww5MI/NvpWaZwAVoroXw3n0Py2hGZ7clMMtdWZuG+bGAB83mQ34JCW5iLchQ0/dINcBU0/WZXXN1K+evNax7d/Eq2tjtRmlPik7y8TLXyw39exJC6KPFB1PRuFAQKBgQD28D8VTmPBjgIa6r21mAdSDSWiTDiB3ujLO5Ccde4V/Km4J7qJCVYZ6CAMn7GQsyQV/6kFmsjfm/6A4OZ4JrXwT9TxZH7BafrdOBPPKSRwsus1QgPBUOxziXPZrzNjupBLQU2hRWmEMZEIhtp7aV1/Ml4Bjwg+qODbISkWZq5nVQKBgQCVuAEAQ9XwGFFWAYZ9dm+lIPCeBS2AxTKNLQP+w6kSsG2RagLLkP6Ov6uYFAC2T9YB/fARV+cQzT5GXIwiSRSWuQi2DW9LNB1Y1cviDSAB/xZutRooiYm+kEoXWxp6PQ0bQJcPyE03jOZLULpnSr7bC14siH8UyV0aybopMONcSwKBgFJnjcWgaS24I/+zgidNWnQJKxvj7593MZxQfkuYFyGDNWfc6iS81tzCV5+e0vSEs3Ab5t2V756fQejkJETINkLBgUAk1x/YK/0Sr5mcEysAqfkV1Ib4aE+N+PkVvEJ1iZ1zZ2J/9VxF5i5AQjyYxrihqDlA7/FpDdBSWpG9JkoZAoGAMx9TaC3BnWwQSdfXfnu0f6QRxUNrWC79pesbFCDnWHRZ1W9naO8nnMKh6xnZbGcyYhe9o45qXfhXLVtolkRx/U1NRiysNfejlbPbHGLy7PuXQ1Ifjnv4RtOqGPOIxPiXwxh4/HCevTslX84++AozxUPKclZhXk2rtvvuzyXcXKUCgYAERKCKVqO1dhd9kkpHPQA/HKVzK4hrPlShCQ11IyZAxLfEsVbqBc7vNv30EtAb0dv0vgilJq09dCT+LOV9+JX/SsVg936G48sp1otLQmRkYLYZwVEtmpipDnLMFODQdsI8ikfYJaNMEMy+Sc1gpVVPCICeGGyKyK7/cvTzR8QbRA==";

    public void init(){
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048); //Must be use 2048 over, can not use 1024
            KeyPair pair = generator.generateKeyPair();
            privateKey = pair.getPrivate();
            publicKey = pair.getPublic();
            printKeys();
        } catch (Exception ignored) {
        }
    }

    public void initFromStrings(){
        try{
            X509EncodedKeySpec keySpecPublic = new X509EncodedKeySpec(decode(PUBLIC_KEY_STRING));
            PKCS8EncodedKeySpec keySpecPrivate = new PKCS8EncodedKeySpec(decode(PRIVATE_KEY_STRING));

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            publicKey = keyFactory.generatePublic(keySpecPublic);
            privateKey = keyFactory.generatePrivate(keySpecPrivate);

            printKeys();
        }catch (Exception ignored){}
    }


    public void printKeys(){
        System.out.println("Public \n"+ encode(publicKey.getEncoded()));
        System.out.println("Private \n"+ encode(privateKey.getEncoded()));
    }

    public String encrypt(String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey); //Use the public key to encryption message
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return encode(encryptedBytes);
    }

    private static String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
    private static byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    public String decrypt(String encryptedMessage) throws Exception {
        byte[] encryptedBytes = decode(encryptedMessage);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey); //Use the private key to decryption message
        byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
        return new String(decryptedMessage, "UTF8");
    }

    public static void main(String[] args) {
        Napas rsa = new Napas();
        //Use the PUBLIC_KEY_STRING and PRIVATE_KEY_STRING create the Public Key and Private Key
        rsa.initFromStrings();
        //Create the Public Key and Private Key when running, each running will differently
        //rsa.init();
        try{
            /*  Outgoing (Outward) message.
            Make the rawData = ISS_ID + ACQ_ID + BEN_ID + CARD_NO + TO_ACCOUNT + LOCAL_DATE + LOCAL_TIME + AMOUNT + TRACE_NO + TERMID + DISPUTE_TYPE + SENDER_ID +
            MESSAGE_TYPE + CLAIM_CODE + REQUEST_AMOUNT + CLAIM_NOTE + FILE_NAME + CONTENT_FUND + RETURN_CODE + REVERSE_1 + REVERSE_2 + REVERSE_3
            */
            /*
               This is outgoing (Outward) message sample
               <?xml version="1.0" encoding="utf-8"?>
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tem="http://tempuri.org/">
                   <soapenv:Header/>
                   <soapenv:Body>
                      <tem:sendDisputeMsg>
                         <tem:sdMsg>
                            <tem:DISPUTE_TYPE>HTTH</tem:DISPUTE_TYPE>
                            <tem:SENDER_ID>970424</tem:SENDER_ID>
                            <tem:MESSAGE_TYPE>200</tem:MESSAGE_TYPE>
                            <tem:ISS_ID>970424</tem:ISS_ID>
                            <tem:ACQ_ID>970488</tem:ACQ_ID>
                            <tem:BEN_ID>970488</tem:BEN_ID>
                            <tem:CARD_NO>19031200008687</tem:CARD_NO>
                            <tem:TO_ACCOUNT>>36299011119999</tem:TO_ACCOUNT>
                            <tem:CODE_REF>1</tem:CODE_REF>
                            <tem:LOCAL_DATE>18/05/2022</tem:LOCAL_DATE>
                            <tem:LOCAL_TIME>140330</tem:LOCAL_TIME>
                            <tem:AMOUNT>10000000</tem:AMOUNT>
                            <tem:TRACE_NO>314982</tem:TRACE_NO>
                            <tem:TERMID>40000014</tem:TERMID>
                            <tem:CLAIM_CODE>609</tem:CLAIM_CODE>
                            <tem:REQUEST_AMOUNT>0</tem:REQUEST_AMOUNT>
                            <tem:CLAIM_NOTE>DVH DA CHUYEN TRA LAI NGAY 18 05 2022</tem:CLAIM_NOTE>
                            <tem:CONTENT_FUND>HOAN TRA</tem:CONTENT_FUND>
                            <tem:FILE_NAME></tem:FILE_NAME>
                            <tem:ATTACH_FILE></tem:ATTACH_FILE>
                            <tem:RETURN_CODE>0</tem:RETURN_CODE>
                            <tem:REVERSE_1></tem:REVERSE_1>
                            <tem:REVERSE_2></tem:REVERSE_2>
                            <tem:REVERSE_3></tem:REVERSE_3>
                            <tem:CHECK_VALUE></tem:CHECK_VALUE>
                         </tem:sdMsg>
                      </tem:sendDisputeMsg>
                   </soapenv:Body>
                </soapenv:Envelope>
             */
            //Make the rawData with upper message
            StringBuffer rawData = new StringBuffer();
            String ISS_ID = "970424",  ACQ_ID ="970488", BEN_ID ="970488", CARD_NO ="19031200008687", TO_ACCOUNT ="36299011119999", LOCAL_DATE ="18/05/2022",LOCAL_TIME ="140330", AMOUNT ="10000000", TRACE_NO="314982";
            String TERMID ="40000014", DISPUTE_TYPE ="HTTH", SENDER_ID ="970424", MESSAGE_TYPE ="200", CLAIM_CODE ="609", REQUEST_AMOUNT ="0", CLAIM_NOTE ="DVH DA CHUYEN TRA LAI NGAY 18 05 2022", FILE_NAME ="";
            String CONTENT_FUND ="HOAN TRA", RETURN_CODE ="0", REVERSE_1 ="", REVERSE_2 ="",REVERSE_3="";

            rawData = rawData.append(ISS_ID).append(ACQ_ID).append(BEN_ID).append(CARD_NO).append(TO_ACCOUNT).append(LOCAL_DATE).append(LOCAL_TIME).append(AMOUNT).append(TRACE_NO).append(TERMID).append(DISPUTE_TYPE).append(SENDER_ID).append(MESSAGE_TYPE).append(CLAIM_CODE).append(REQUEST_AMOUNT).append(CLAIM_NOTE).append(FILE_NAME).append(CONTENT_FUND).append(RETURN_CODE).append(REVERSE_1).append(REVERSE_2).append(REVERSE_3);
            System.out.println("rawData:\n"+rawData.toString());
            //Make CHECK_VALUE before send to Partner (Napas) for outgoing (Outward) message
            String CHECK_VALUE = rsa.encrypt(rawData.toString());
            System.out.println("CHECK_VALUE:\n"+CHECK_VALUE);

            /*
            To verify the encrypted message, you can use the corresponding private key to decrypt the encrypted message and compare it with the original message. If the decrypted message matches the original message, then the encryption and decryption processes were done correctly.

            When you get the incoming (Inward), decryption the CHECK_VALUE in message and compare with rawData
            Step 1: get all field in message and message the rawData = ISS_ID + ACQ_ID + BEN_ID + CARD_NO + TO_ACCOUNT + LOCAL_DATE + LOCAL_TIME + AMOUNT + TRACE_NO + TERMID + DISPUTE_TYPE + SENDER_ID +
            MESSAGE_TYPE + CLAIM_CODE + REQUEST_AMOUNT + CLAIM_NOTE + FILE_NAME + CONTENT_FUND + RETURN_CODE + REVERSE_1 + REVERSE_2 + REVERSE_3
            Step 2: Decrypt the encrypted message (CHECK_VALUE)
            Step 3: Compare it with the original message (decryptedMessage and rawData)
            //When you want check CHECK_VALUE field invalid, please use this: ISS_ID = 999999 not 970424
            CHECK_VALUE = "CWZRW0c1oqFquN1uvmkMriRFUzzeWqeK8DzwiCHVtxeYL1SuBRPlFrCAAgqHvY0Cf/0FqvolNHfzG14DqORWBKqkSp3wNjgPTA6+ONtJWDACP8aN0pgwrc8e9aiObsFcpggRXEwu03/4olFZtEGq3xmM1cakzw+fzEL9p5sjiBvgL6heIAikK8ZSY4BGJZhPBrEot6SiCs7EVeXjHSifigWxMt2RTlEnq+qOfrFyEzlm64QgptIH70trHEuhgTcJW3ZHv0N3ZxcX1QKkgZBL5HULpqM3cIoGewBa/5P0OGgY49le3qhHHGU7q+DHnQe4GnepnfLcTf/uvm0iprnw1g==";
             */
            //CHECK_VALUE = "CWZRW0c1oqFquN1uvmkMriRFUzzeWqeK8DzwiCHVtxeYL1SuBRPlFrCAAgqHvY0Cf/0FqvolNHfzG14DqORWBKqkSp3wNjgPTA6+ONtJWDACP8aN0pgwrc8e9aiObsFcpggRXEwu03/4olFZtEGq3xmM1cakzw+fzEL9p5sjiBvgL6heIAikK8ZSY4BGJZhPBrEot6SiCs7EVeXjHSifigWxMt2RTlEnq+qOfrFyEzlm64QgptIH70trHEuhgTcJW3ZHv0N3ZxcX1QKkgZBL5HULpqM3cIoGewBa/5P0OGgY49le3qhHHGU7q+DHnQe4GnepnfLcTf/uvm0iprnw1g==";
            String decryptedMessage = rsa.decrypt(CHECK_VALUE);
            System.out.println("decryptedMessage:\n"+decryptedMessage);
            // Verify the encrypted message
            byte[] originalMessageBytes = rawData.toString().getBytes();
            byte[] decryptedMessageBytes = decryptedMessage.getBytes();
            if (originalMessageBytes.length == decryptedMessageBytes.length) {
                boolean isEqual = true;
                for (int i = 0; i < originalMessageBytes.length; i++) {
                    if (originalMessageBytes[i] != decryptedMessageBytes[i]) {
                        isEqual = false;
                        break;
                    }
                }
                if (isEqual) {
                    System.out.println("The encrypted message is valid.");
                } else {
                    System.err.println("The encrypted message is not valid.");
                }
            } else {
                System.err.println("The encrypted message is not valid.");
            }

        }catch (Exception ingored){
            System.err.println("Exception: "+ingored.getMessage() + " " + ingored.getStackTrace());
        }
    }
}
