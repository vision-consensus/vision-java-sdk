package org.vision.visionjsdk.client.Transaction;

import com.google.protobuf.ByteString;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.signers.ECDSASigner;
import org.spongycastle.util.encoders.Base64;
import org.spongycastle.util.encoders.Hex;
import org.vision.visionjsdk.crypto.Hash;
import org.vision.visionjsdk.utils.ByteUtils;

import java.math.BigInteger;
import java.security.SignatureException;
import java.util.Arrays;

public class TransactionSignUtil {
    public static void main(String[] args) throws Exception {

        String srcHex = "0a02bede2208332a15241fc6f6f440809ccce6af2f5a6a080412660a30747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e566f74655769746e657373436f6e747261637412320a154693dfece2fa3631c59e399638ae48f74ca88b870012190a1546418cd9bfbacbcff8c731752f362d574b9b1a908c100870cedcc8e6af2f";
        String signHex = "e9fcef72140429038ed2b660e35b780ff22f7fe9f19ca97fd53aa6e0744e6d3f57b78a2f1f2df7013ae3a5519968420e893f06fa0d7a9c903b898469aaf8b31a01";
        String pubHex = "4693DfeCE2fA3631C59E399638AE48F74ca88b8700";
        Boolean k = verifySignature(srcHex, signHex, pubHex);
        System.out.println("k=" + k);
    }

    public static boolean verifySignature(String rawData, String signature, String address) {
        try {
            byte[] dataBytes = ByteUtils.HexToBytes(rawData);
            byte[] signatureEncoded = ByteUtils.HexToBytes(signature);

            byte[] hash = Sha256Hash.of(true,
                    dataBytes).getBytes();
            ByteString bs = ByteString.copyFrom(signatureEncoded);
            byte[] addressGen;

            addressGen = ECKey.signatureToAddress(hash, getBase64FromByteString(bs));

            return toHexString(addressGen).equals(address.toLowerCase());
        } catch (SignatureException e) {
            e.printStackTrace();

        }
        return false;
    }

    private static String toHexString(byte[] data) {

        return data == null ? "" : Hex.toHexString(data);
    }

    public static byte[] signatureToKeyBytes(byte[] messageHash, String
            signatureBase64) throws SignatureException {
        byte[] signatureEncoded;
        try {
            signatureEncoded = Base64.decode(signatureBase64);
        } catch (RuntimeException e) {
            // This is what you getData back from Bouncy Castle if base64 doesn't
            // decode :(
            throw new SignatureException("Could not decode base64", e);
        }
        // Parse the signature bytes into r/s and the selector value.
        if (signatureEncoded.length < 65) {
            throw new SignatureException("Signature truncated, expected 65 " +
                    "bytes and got " + signatureEncoded.length);
        }

        return ECKey.signatureToKeyBytes(
                messageHash,
                ECKey.ECDSASignature.fromComponents(
                        Arrays.copyOfRange(signatureEncoded, 1, 33),
                        Arrays.copyOfRange(signatureEncoded, 33, 65),
                        (byte) (signatureEncoded[0] & 0xFF)));
    }

    public static String getBase64FromByteString(ByteString sign) {
        byte[] r = sign.substring(0, 32).toByteArray();
        byte[] s = sign.substring(32, 64).toByteArray();
        byte v = sign.byteAt(64);
        if (v < 27) {
            v += 27; //revId -> v
        }
        ECKey.ECDSASignature signature = ECKey.ECDSASignature.fromComponents(r, s, v);
        return signature.toBase64();
    }

    private static void testStr() throws Exception {
        String srcHex = "0a02bede2208332a15241fc6f6f440809ccce6af2f5a6a080412660a30747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e566f74655769746e657373436f6e747261637412320a154693dfece2fa3631c59e399638ae48f74ca88b870012190a1546418cd9bfbacbcff8c731752f362d574b9b1a908c100870cedcc8e6af2f";
        String signHex = "e9fcef72140429038ed2b660e35b780ff22f7fe9f19ca97fd53aa6e0744e6d3f57b78a2f1f2df7013ae3a5519968420e893f06fa0d7a9c903b898469aaf8b31a01";
        String pubHex = "4693DfeCE2fA3631C59E399638AE48F74ca88b8700";
        byte[] data = ByteUtils.HexToBytes(srcHex);
        byte[] sign = ByteUtils.HexToBytes(signHex);
        byte[] pub = ByteUtils.HexToBytes("4693DfeCE2fA3631C59E399638AE48F74ca88b8700".toLowerCase());
        boolean verify = ECKey.verify(srcHex, true, signHex, pubHex);
        System.out.println(verify);
    }

    private static void testOri() {
        byte[] data = ByteUtils.HexToBytes("0a02bede2208332a15241fc6f6f440809ccce6af2f5a6a080412660a30747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e566f74655769746e657373436f6e747261637412320a154693dfece2fa3631c59e399638ae48f74ca88b870012190a1546418cd9bfbacbcff8c731752f362d574b9b1a908c100870cedcc8e6af2f");
        byte[] sign = ByteUtils.HexToBytes("e9fcef72140429038ed2b660e35b780ff22f7fe9f19ca97fd53aa6e0744e6d3f57b78a2f1f2df7013ae3a5519968420e893f06fa0d7a9c903b898469aaf8b31a01");
        byte[] pub = ByteUtils.HexToBytes("4693DfeCE2fA3631C59E399638AE48F74ca88b8700".toLowerCase());
        boolean verify = ECKey.verify(data, sign, pub);
        System.out.println(verify);
    }

}
