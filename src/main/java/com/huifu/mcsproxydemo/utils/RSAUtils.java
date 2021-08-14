package com.huifu.mcsproxydemo.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {
    private static final String SIGN_TYPE_RSA2 = "RSA2";
    private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    private static final String DEFAULT_CHARSET = "UTF-8";

    public static String rsaCheck(String signType, String sign, String content, String publicKey) throws Exception {
        java.security.Signature signature = null;
        PublicKey pubKey = getPublicKeyFromX509("RSA", new ByteArrayInputStream(publicKey.getBytes()));
        if (SIGN_TYPE_RSA2.equals(signType)) {
            signature = java.security.Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);
        } else {
            return "不支持该签名类型";
        }
        signature.initVerify(pubKey);
        signature.update(content.getBytes(DEFAULT_CHARSET));

        if (!signature.verify(Base64.decodeBase64(sign.getBytes()))) {
            return "验证签名失败";
        }
        return null;
    }

    public static String rsaSign(String signType, String content, String privateKey) throws Exception {
        PrivateKey priKey = null;
        java.security.Signature signature = null;
        String charset = DEFAULT_CHARSET;
        if ("RSA2".equals(signType)) {
            priKey = getPrivateKeyFromPKCS8("RSA", new ByteArrayInputStream(privateKey.getBytes()));
            signature = java.security.Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);
        } else {
            throw new Exception("不是支持的签名类型 : : signType=" + signType);
        }
        signature.initSign(priKey);

        if (StringUtils.isEmpty(charset)) {
            signature.update(content.getBytes());
        } else {
            signature.update(content.getBytes(charset));
        }
        byte[] signed = signature.sign();
//        return new String(Base64.encodeBase64(signed));
        return java.util.Base64.getEncoder().encodeToString(signed);
    }

    private static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        StringWriter writer = new StringWriter();
        io(new InputStreamReader(ins), writer, -1);

        byte[] encodedKey = writer.toString().getBytes();
        encodedKey = Base64.decodeBase64(encodedKey);
        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }

    public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins) throws Exception {
        if (ins == null || StringUtils.isEmpty(algorithm)) {
            return null;
        }

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        byte[] encodedKey = readText(ins).getBytes();

        encodedKey = Base64.decodeBase64(encodedKey);

        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }

    private static String readText(InputStream ins) throws IOException {
        Reader reader = new InputStreamReader(ins);
        StringWriter writer = new StringWriter();

        io(reader, writer, -1);
        return writer.toString();
    }

    private static void io(Reader in, Writer out, int bufferSize) throws IOException {
        if (bufferSize == -1) {
            bufferSize = DEFAULT_BUFFER_SIZE >> 1;
        }

        char[] buffer = new char[bufferSize];
        int amount;

        while ((amount = in.read(buffer)) >= 0) {
            out.write(buffer, 0, amount);
        }
    }
}
