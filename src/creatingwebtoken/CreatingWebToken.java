/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creatingwebtoken;

import java.security.Key;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.JoseException;

/**
 *
 * @author davidmunro
 */
public class CreatingWebToken {

    private static Key key;
    private static JsonWebEncryption webToken;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        generateWebToken();

    }

    public static void generateWebToken() {
        try {
            key = new AesKey(ByteUtil.randomBytes(16));
            webToken = new JsonWebEncryption();
            webToken.setPayload("Hello David");
            
            webToken.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
            webToken.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
            webToken.setKey(key);
            String encrypted = webToken.getCompactSerialization();

            System.out.println("Hello David : Encrypted " + encrypted);

            webToken = new JsonWebEncryption();
            webToken.setKey(key);
            webToken.setCompactSerialization(encrypted);
            System.out.println("Payload: " + webToken.getPayload());
        } catch (JoseException ex) {
            ex.printStackTrace();
        }
    }

}
