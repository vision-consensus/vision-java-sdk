package org.vision.visionjsdk.client.Transaction;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Message;
import org.spongycastle.util.encoders.Base64;
import org.spongycastle.util.encoders.Hex;
import org.vision.visionjsdk.client.Transaction.visioncore.ContractType;
import org.vision.visionjsdk.client.Transaction.visioncore.JsonFormat;
import org.vision.visionjsdk.client.Transaction.visioncore.TransactionFactory;
import org.vision.visionjsdk.proto.Chain;
import org.vision.visionjsdk.utils.Base58Check;
import org.vision.visionjsdk.utils.ByteUtils;

import java.lang.reflect.Constructor;
import java.security.SignatureException;
import java.util.Arrays;

public class TransactionSignUtil {

    public static final String VISIBLE = "visible";

    public static void main(String[] args) throws Exception {
        String srcHex = "0a02bede2208332a15241fc6f6f440809ccce6af2f5a6a080412660a30747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e566f74655769746e657373436f6e747261637412320a154693dfece2fa3631c59e399638ae48f74ca88b870012190a1546418cd9bfbacbcff8c731752f362d574b9b1a908c100870cedcc8e6af2f";
        String signHex = "e9fcef72140429038ed2b660e35b780ff22f7fe9f19ca97fd53aa6e0744e6d3f57b78a2f1f2df7013ae3a5519968420e893f06fa0d7a9c903b898469aaf8b31a01";
        String pubHex = "4693DfeCE2fA3631C59E399638AE48F74ca88b8700";


        TransactionSignDTO dto = new TransactionSignDTO();
        dto.setRawDataIsHex(false);
        String test = "{\"contract\":[{\"parameter\":{\"value\":{\"amount\":10,\"owner_address\":\"46CCFAA5C69D30AABA87AFE00EE53C18B032AC4BB4\",\"to_address\":\"469d8066d6711ce54fc3406ce58518acfea917af02\"},\"type_url\":\"type.googleapis.com/protocol.TransferContract\"},\"type\":\"TransferContract\"}],\"ref_block_bytes\":\"0e51\",\"ref_block_hash\":\"77114a3541e68e3d\",\"expiration\":1608800880000,\"timestamp\":1608800821763}";
        test = "{\"contract\":[{\"parameter\":{\"value\":{\"owner_address\":\"4693dfece2fa3631c59e399638ae48f74ca88b8700\",\"votes\":[{\"vote_address\":\"46418cd9bfbacbcff8c731752f362d574b9b1a908c\",\"vote_count\":8}]},\"type_url\":\"type.googleapis.com/protocol.VoteWitnessContract\"},\"type\":\"VoteWitnessContract\"}],\"ref_block_bytes\":\"bede\",\"ref_block_hash\":\"332a15241fc6f6f4\",\"expiration\":1627739328000,\"timestamp\":1627739270734}";
        JSONObject rawData= JSONObject.parseObject(test);
        dto.setRawData(rawData.toJSONString());
        dto.setSignature(signHex);
        dto.setAddressIsHex(false);
        pubHex = "VQ97WQWywoM4sgJ9xFu9jxfvHRSVW4xK3d";
        dto.setAddress(pubHex);
        boolean k = verifySignature(dto);
        System.out.println("k=" + k);

    }

    private void test1() {
        String srcHex = "0a02bede2208332a15241fc6f6f440809ccce6af2f5a6a080412660a30747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e566f74655769746e657373436f6e747261637412320a154693dfece2fa3631c59e399638ae48f74ca88b870012190a1546418cd9bfbacbcff8c731752f362d574b9b1a908c100870cedcc8e6af2f";
        String signHex = "e9fcef72140429038ed2b660e35b780ff22f7fe9f19ca97fd53aa6e0744e6d3f57b78a2f1f2df7013ae3a5519968420e893f06fa0d7a9c903b898469aaf8b31a01";
        String pubHex = "4693DfeCE2fA3631C59E399638AE48F74ca88b8700";
        Boolean k = verifySignature(srcHex, signHex, pubHex);
        System.out.println("k=" + k);

        JSONObject rawData= JSONObject.parseObject("{\"contract\":[{\"parameter\":{\"value\":{\"amount\":10,\"owner_address\":\"46f5fe4ca7d85944d24e28337433b6caf510a32e8c\",\"to_address\":\"469d8066d6711ce54fc3406ce58518acfea917af02\"},\"type_url\":\"type.googleapis.com/protocol.TransferContract\"},\"type\":\"TransferContract\"}],\"ref_block_bytes\":\"0e51\",\"ref_block_hash\":\"77114a3541e68e3d\",\"expiration\":1608800880000,\"timestamp\":1608800821763}");

        srcHex = rawData.toString();
        System.out.println("srcHex=" + srcHex);
        signHex = "8db9e5777d9f4ed2087097be6ce24fb0d086e685db026094975d06b3035d309e6d527fbe4597766344803e3a1b93bf9b0bbbd9954add6ad7a4ccc53e2bf492da01";
        pubHex = "VGQUTcDfgAgadLsAX6h1BSxeyaUsbznm6n";
        boolean t2 = verifySignature(srcHex, signHex, pubHex, true);
        System.out.println("t2=" + t2);
    }


    public static boolean verifySignature(String rawDataHex, String signature, String address) {
        try {
            byte[] dataBytes = ByteUtils.HexToBytes(rawDataHex);
            byte[] signatureEncoded = ByteUtils.HexToBytes(signature);

            byte[] hash = Sha256Hash.of(true,
                    dataBytes).getBytes();
            ByteString bs = ByteString.copyFrom(signatureEncoded);
            byte[] addressGen = ECKey.signatureToAddress(hash, getBase64FromByteString(bs));

            return toHexString(addressGen).equals(address.toLowerCase());
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return false;
    }

    public static boolean verifySignature(TransactionSignDTO transactionSignDTO) {
        try {

            byte[] dataBytes;
            if (transactionSignDTO.isRawDataIsHex()) {
                dataBytes = ByteUtils.HexToBytes(transactionSignDTO.getRawData());
            } else {
                JSONObject rawDataTmp = JSONObject.parseObject(transactionSignDTO.getRawData());
                JSONObject rawData = new JSONObject();
                rawData.put("raw_data", rawDataTmp);
                boolean visible = false;
                if (rawDataTmp.containsKey(VISIBLE)) {
                    visible = rawDataTmp.getBoolean(VISIBLE);
                }
                Chain.Transaction transaction = packTransaction(rawData.toJSONString(), visible);
                String rawDataHex = toHexString(transaction.getRawData().toByteArray());
                dataBytes = ByteUtils.HexToBytes(rawDataHex);
            }
            byte[] hash = Sha256Hash.of(true,
                    dataBytes).getBytes();

            byte[] signatureEncoded = ByteUtils.HexToBytes(transactionSignDTO.getSignature());
            ByteString bs = ByteString.copyFrom(signatureEncoded);

            byte[] addressGen = ECKey.signatureToAddress(hash, getBase64FromByteString(bs));
            if (transactionSignDTO.isAddressIsHex()) {
                return toHexString(addressGen).equals(transactionSignDTO.getAddress().toLowerCase());
            } else {
                String addressTmp = toHexString(Base58Check.base58ToBytes(transactionSignDTO.getAddress()));
                System.out.println("addressGen=" + toHexString(addressGen));
                System.out.println("addressGet=" + addressTmp.toLowerCase());
                return toHexString(addressGen).equals(addressTmp.toLowerCase());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static final String PARAMETER = "parameter";
    public static final String VALUE = "value";
    public static Chain.Transaction packTransaction(String strTransaction, boolean selfType) {
        JSONObject jsonTransaction = JSONObject.parseObject(strTransaction);
        JSONObject rawData = jsonTransaction.getJSONObject("raw_data");
        JSONArray contracts = new JSONArray();
        JSONArray rawContractArray = rawData.getJSONArray("contract");

        for (int i = 0; i < rawContractArray.size(); i++) {
            try {
                JSONObject contract = rawContractArray.getJSONObject(i);
                JSONObject parameter = contract.getJSONObject(PARAMETER);
                String contractType = contract.getString("type");
                Any any = null;
                Class clazz = TransactionFactory.getContract(ContractType.valueOf(contractType));
                if (clazz != null) {
                    Constructor<GeneratedMessageV3> constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    GeneratedMessageV3 generatedMessageV3 = constructor.newInstance();
                    Message.Builder builder = generatedMessageV3.toBuilder();
                    JsonFormat.merge(parameter.getJSONObject(VALUE).toJSONString(), builder, selfType);
                    any = Any.pack(builder.build());
                }
                if (any != null) {
                    String value = toHexString(any.getValue().toByteArray());
                    parameter.put(VALUE, value);
                    contract.put(PARAMETER, parameter);
                    contracts.add(contract);
                }
            } catch (Exception e) {
                e.printStackTrace();
                // logger.debug("ParseException: {}", e.getMessage());
            } /*catch (Exception e) {
                e.printStackTrace();
                // logger.error("", e);
            }*/
        }
        rawData.put("contract", contracts);
        jsonTransaction.put("raw_data", rawData);
        Chain.Transaction.Builder transactionBuilder = Chain.Transaction.newBuilder();
        try {
            JsonFormat.merge(jsonTransaction.toJSONString(), transactionBuilder, selfType);
            return transactionBuilder.build();
        } catch (Exception e) {
            e.printStackTrace();
            // logger.debug("ParseException: {}", e.getMessage());
            return null;
        }
    }

    private static boolean verifySignature(String rawData, String signature,
                                          String address, boolean visible) {
        if (visible) {

            try {
                Sha256Hash k = Sha256Hash.of(true,
                        rawData.getBytes("UTF-8"));
                byte[] in = k.getBytes();
                // in = rawData.getBytes();
                String rawDataHex = toHexString(in);
                rawDataHex = toHexString(rawData.getBytes());
                System.out.println("rawDataHex=" + rawDataHex);
                byte[] dataBytes = ByteUtils.HexToBytes(rawDataHex);
                byte[] hash = Sha256Hash.of(true,
                        dataBytes).getBytes();

                byte[] signatureEncoded = ByteUtils.HexToBytes(signature);
                ByteString bs = ByteString.copyFrom(signatureEncoded);
                byte[] addressGen = ECKey.signatureToAddress(hash, getBase64FromByteString(bs));
                System.out.println("base58=" + Base58Check.bytesToBase58(addressGen));
                return Base58Check.bytesToBase58(addressGen).toLowerCase().equals(address.toLowerCase());
            } catch (Exception e) {
                // e.printStackTrace();
            }
            return false;
        } else {
            return verifySignature(rawData, signature, address);
        }
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
