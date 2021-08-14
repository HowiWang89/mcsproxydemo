package com.huifu.mcsproxydemo.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSA2Utils {

    public static void main(String[] args) {
        String str = "{\"cust_id\":\"6666000000041651\",\"device_id\":\"mock001\",\"order_type\":\"P\",\"order_id\":\"mock20190820000007\",\"party_order_id\":\"\"}";
        test1(str);

    }

    //私钥加密，公钥解密

    public static void test1(String src) {
        try {
            //1.初始化密钥

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

            keyPairGenerator.initialize(1024);

            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();

            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

            //String strPublicKey = Base64.encodeBase64String(rsaPublicKey.getEncoded());

            //String strPrivateKey = Base64.encodeBase64String(rsaPrivateKey.getEncoded());


            String strPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzOBw+l+jvjjDfKJO54NBNhehz9HZu0ZJN/6RF4kJXpMx62qhFckvf1VyuTC6LtPbwPVF+rKEgN7RVprpQ+a3o2K4Kuxwd4BG2aoCsefNMLzCEB7H/7BolW87mDyQ3GS2U0hD4tGtck5+0lpnLOZdAasRdXRiGbC+16a76ryIkysy5w2lTSFzQ1DLa3p7bDjjk99ZphAt0LBhzommbxcCVCpj659EUqFwgs7ubLMS0lBGuvcGlBZh2Yz8QrYnsIQLIcV9sKIGp6EGGYWEkzC8QH+UbOwaqluZU5X5CTpjUs2i9ao9qKh9doeOFdEXNU7ErqzL/Tsm7hAh8QpVffQ82wIDAQAB";


            String strPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDM4HD6X6O+OMN8ok7ng0E2F6HP0dm7Rkk3/pEXiQlekzHraqEVyS9/VXK5MLou09vA9UX6soSA3tFWmulD5rejYrgq7HB3gEbZqgKx580wvMIQHsf/sGiVbzuYPJDcZLZTSEPi0a1yTn7SWmcs5l0BqxF1dGIZsL7XprvqvIiTKzLnDaVNIXNDUMtrentsOOOT31mmEC3QsGHOiaZvFwJUKmPrn0RSoXCCzu5ssxLSUEa69waUFmHZjPxCtiewhAshxX2woganoQYZhYSTMLxAf5Rs7BqqW5lTlfkJOmNSzaL1qj2oqH12h44V0Rc1TsSurMv9OybuECHxClV99DzbAgMBAAECggEBAKrO6IJloE+kZZ8QdvZfoMunUjnAfGW9SKgNbgwQLSaTU8gxAxH1xMrFg7tQG7mU2h76nphPo1cJ7b/lsVryDOKEGIV40J9g7DDmpA6M8q6SgL0N3LKY8ei8bxQSUrfco4G1uxtsss2dEfDwfBqcCKiuZgTlvjArAkEq+nrPKpJ+rpmhFS16LBwlKZahGVtRW7OyNndkYf37NB7HsnTDoRGLjXgD437XJE0+rqJOkXeqhxvi6b3Xu4bpft3UAS9RCMkQoOnSo0d/FY85xxkZivGHzJgXNjmK9gm22dGmWbWsjzPjxKjx0KVVorDS3wBXoyEHLmXgkncRIlaYtglpgJkCgYEA/mwgK0l+1n7r54oYLFYpgxmaXg+rfR532XIQWVn6GBBMH2U6f2KQo2T4D7jj3nAnRp9qCFUaF98cCJXTQVH7O3arL7XCfn1976hiVXOShl60y2PFzsBWBISPXus+tKV/E2IuBmjXGoR3uoEmfqi0QNiUhcVt33OtBgy0scOGeBcCgYEAziWqg+b5PaPbrYIs8FL1/eh2oJ6crrLTBnO5H4U9uOsyMM+vH2EcCTG/S0jCPdBYJk4O491ArbTefsL2zFwUCzHSZkHPiSqnygJcgdadBl9rlFc4vIReY21bSUnoMcpNjKVMge36Q1Gv++GqqFoL1NZZ3wO/k+psgqyxEyrfl90CgYA74LaxeVqgoL5kf9uwk5axhBUQjh+OEt2iD3ZJkSOLBkrkGK7rzAa+bcTz7k+yyjJ0LJCH5X9+nsO6QYi2ltTJ6ePgFqbvnhlFjhuWgNy7Qw7Jz3F+d0XHSmtzq2nWmhyOM00htQrq8sI64qpspMfHezF5Sj3RmSffhJ4COEItgQKBgFVMxKfbtRpKaSX8jXfL/DnaLOv9dbvcBvHOhLWEPqv7tkL4TPBhEGbFjPAr5SQKx87h0ct7y72U79Pn/JNHLowLS5JAfQ229sLiA0E+eq8eYLd69+EZrKzbnUBQMqIXocsmwueofzvH21gQYrtStz6Ui496T5V3FPtlVcgmn2qJAoGBAKtUga7JbdjhNwlVAhS11NQfKrFOwEcHFqcx5dKj/r50ncn2Zq74Qp+iwRonqaWQBe/uBT5xnTtCSco8yygaXUoXtdbemO9iFQSnE52Eq37La4ZMLNQ9MKTxjFPwtpo+sIt96GRW+w2D5hMgE8L5eGhwvR3YTVbtc9wmxOMRb+Q/";


            System.out.println("Public Key : " + strPublicKey);

            System.out.println("Private Key : " + strPrivateKey);


            //1.私钥加密加密

            byte[] keyPrivateBytes = Base64.decodeBase64(strPrivateKey);

            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyPrivateBytes);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

            Cipher cipher = Cipher.getInstance("RSA");

            cipher.init(Cipher.ENCRYPT_MODE, privateKey);

            byte[] result = cipher.doFinal(src.getBytes());

            System.out.println("私钥加密、公钥解密——加密 : " + Base64.encodeBase64String(result));


            //2.公钥解密

            byte[] keyPublicBytes = Base64.decodeBase64(strPublicKey);

            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyPublicBytes);

            keyFactory = KeyFactory.getInstance("RSA");

            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

            cipher = Cipher.getInstance("RSA");

            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            result = cipher.doFinal(result);

            System.out.println("私钥加密、公钥解密——解密：" + new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
