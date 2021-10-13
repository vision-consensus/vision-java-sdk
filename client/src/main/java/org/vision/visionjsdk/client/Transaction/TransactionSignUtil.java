package org.vision.visionjsdk.client.Transaction;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Message;
import org.spongycastle.jcajce.provider.digest.Keccak;
import org.spongycastle.util.encoders.Base64;
import org.spongycastle.util.encoders.Hex;
import org.vision.visionjsdk.client.Transaction.visioncore.Base58;
import org.vision.visionjsdk.client.Transaction.visioncore.ContractType;
import org.vision.visionjsdk.client.Transaction.visioncore.JsonFormat;
import org.vision.visionjsdk.client.Transaction.visioncore.TransactionFactory;
import org.vision.visionjsdk.crypto.Hash;
import org.vision.visionjsdk.crypto.SECP256K1;
import org.vision.visionjsdk.proto.Chain;
import org.vision.visionjsdk.utils.Base58Check;
import org.vision.visionjsdk.utils.ByteUtils;

import java.lang.reflect.Constructor;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Arrays;

public class TransactionSignUtil {

    public static final String VISIBLE = "visible";

    public static void main2(String[] args) throws Exception {
        String hex = "19564953494f4e205369676e6564204d6573736167653a0a33327b22636f6e747261637441646472657373223a2256537a315a7262636450697351577147794e5579545a786275507172347764457574222c22656d61696c223a223132403136332e636f6d222c22676565746573744368616c6c656e6765223a223962363539633163653963303034393664626638646630356439336164343766222c226765657465737456616c6964617465223a226266303061313363663565613931373939666131636237373163616234616338222c2267656574657374536563636f6465223a2262663030613133636635656139313739396661316362373731636162346163387c6a6f7264616e222c22686f6d6550616765223a2268747470733a2f2f62616964752e636f6d222c22696d6755726c223a2268747470733a2f2f766973696f6e2d312e6f73732d616363656c65726174652e616c6979756e63732e636f6d2f752f302f302f3230323131302f6f2f356236313762656332393762346532623839376665366664393932316138383864633637353863613764386434663937386535386136313638323934396566322e706e67222c226f776e657241646472657373223a22565650637859747459486b445670743850714e6e6e7a42613931546f3676796a4156222c22736f6369616c4d65646961223a5b7b226d6574686f64223a2254776974746572222c22616374697665223a747275652c226c696e6b223a5b2268747470733a2f2f746569747465722e636f6d225d7d5d2c22746f6b656e44657363223a223132336672207373222c226e616d65223a225061792d564b542d3230222c2273796d626f6c223a2270796b222c2274696d657374616d70223a313633333735303830313637317d";
        byte[] bytes = ByteUtils.HexToBytes(hex);
        String str = ByteString.copyFrom(bytes).toStringUtf8();
        System.out.println(str);

        ECKey key = ECKey.fromPrivate(ByteUtils.HexToBytes("15c6be35d1d2ec30facf88fc8dc211b3301e534369c2b9e09917e1ac40eb4c92"));

        String sign = "eac61fa2cf649bf46bd4193790d5cb2d1f11d8ac4a0bc8676d11da9fceca847d3505a25ad8cb625505dc483bf6db0a1f424c583d8bf2bb3c74f31a7b57874e881b";
        byte[] signBytes = ByteUtils.HexToBytes(sign);
        System.out.println(sign.length()+"-"+signBytes.length);
        byte[] messageHash = Hash.sha3(bytes);
        String signatureBase64 = TransactionSignUtil.getBase64FromByteString(ByteString.copyFrom(signBytes));
        System.out.println("key.sign(messageHash).toHex()L: "+key.sign(messageHash).toHex());
//        ECKey.signatureToAddress(messageHash, ECKey.ECDSASignature.fromComponents())
//        byte[] address = ECKey.signatureToAddress(messageHash,
//                ECKey.ECDSASignature.fromComponents(
//                        ByteUtils.HexToBytes(sign.substring(0, 64)),
//                        ByteUtils.HexToBytes(sign.substring(64, 128)),
//                                (byte) 27));
        byte[] address = ECKey.signatureToAddress(messageHash,
                signatureBase64);


        String addr = encode58Check(address);
        System.out.println(addr);
        //VLf544zi9WhCdmtuHKmEiD28DDeFoSzqhU
        //VLf544zi9WhCdmtuHKmEiD28DDeFoSzqhU
        //VVPcxYttYHkDVpt8PqNnnzBa91To6vyjAV
    }

    public static String encode58Check(byte[] input) {
        byte[] hash0 = Sha256Hash.hash(true, input);
        byte[] hash1 = Sha256Hash.hash(true, hash0);
        byte[] inputCheck = new byte[input.length + 4];
        System.arraycopy(input, 0, inputCheck, 0, input.length);
        System.arraycopy(hash1, 0, inputCheck, input.length, 4);
        return Base58.encode(inputCheck);
    }


    public static void main(String[] args) throws Exception{
        String hex = "7b22636f6e747261637441646472657373223a2256537a315a7262636450697351577147794e5579545a786275507172347764457574222c22656d61696c223a223132403136332e636f6d222c22676565746573744368616c6c656e6765223a223962363539633163653963303034393664626638646630356439336164343766222c226765657465737456616c6964617465223a226266303061313363663565613931373939666131636237373163616234616338222c2267656574657374536563636f6465223a2262663030613133636635656139313739396661316362373731636162346163387c6a6f7264616e222c22686f6d6550616765223a2268747470733a2f2f62616964752e636f6d222c22696d6755726c223a2268747470733a2f2f766973696f6e2d312e6f73732d616363656c65726174652e616c6979756e63732e636f6d2f752f302f302f3230323131302f6f2f356236313762656332393762346532623839376665366664393932316138383864633637353863613764386434663937386535386136313638323934396566322e706e67222c226f776e657241646472657373223a22565650637859747459486b445670743850714e6e6e7a42613931546f3676796a4156222c22736f6369616c4d65646961223a5b7b226d6574686f64223a2254776974746572222c22616374697665223a747275652c226c696e6b223a5b2268747470733a2f2f746569747465722e636f6d225d7d5d2c22746f6b656e44657363223a223132336672207373222c226e616d65223a225061792d564b542d3230222c2273796d626f6c223a2270796b222c2274696d657374616d70223a313633333735303830313637317d";
        byte[] bytes = ByteUtils.HexToBytes(hex);
        String str = ByteString.copyFrom(bytes).toStringUtf8();
        System.out.println(str);
        String pub = "VVPcxYttYHkDVpt8PqNnnzBa91To6vyjAV";
        String sign = "7eb151a6f40de3a2e2564ea212a21ae5e2441f560d90b3c25319ef0cadd58afb05b1987caa664a24241b5a8c1389428915173a7fa0bbc0eb89becb98d9740dfc1b";
        byte[] signBytes = ByteUtils.HexToBytes(sign);
        System.out.println("sign.length():"+sign.length()+" signBytes.length:"+signBytes.length);
        //SignUtils.signatureToAddress(hash, base64, CommonParameter.getInstance().isECKeyCryptoEngine());
        //"\x19VISION Signed Message:\n32"
        //"\\x19Ethereum Signed Message:\n32";
        byte[] bytesHeader = "\\x19Ethereum Signed Message:\n32".getBytes();
        byte[] messageHash = Hash.sha3(ByteUtils.Append(bytesHeader, bytes));//
        System.out.println(Hex.toHexString(messageHash));
        //482bcd2029f05f25e1ebed52b328a1dcd93f4dab6c9c190062ff4504c75bcc23
        String hash = "635c06d4d0022b8d58628e17c59b23e2a0aa10d1dedb047bb5f472e3007fc273";
        String signatureBase64 = TransactionSignUtil.getBase64FromByteString(ByteString.copyFrom(signBytes));
        byte[] address = ECKey.signatureToAddress(messageHash, signatureBase64);
//        byte[] address = ECKey.signatureToAddress( ByteUtils.HexToBytes(hash), signatureBase64);
        String addr = Base58Check.bytesToBase58(address);
        System.out.println(addr);
        //VWgTa9f8CYicbu2zAzrEDsBXA2tBirni4t
        //VUBz4C76g74ucVUcsTnzH9LDGfvdUJo25g


        /**
         公钥
         VVPcxYttYHkDVpt8PqNnnzBa91To6vyjAV   或  46cd76f64494a82f10ab400be0ac224482a23e843a

         原文：
         7b22636f6e747261637441646472657373223a2256537a315a7262636450697351577147794e5579545a786275507172347764457574222c22656d61696c223a223132403136332e636f6d222c22676565746573744368616c6c656e6765223a223962363539633163653963303034393664626638646630356439336164343766222c226765657465737456616c6964617465223a226266303061313363663565613931373939666131636237373163616234616338222c2267656574657374536563636f6465223a2262663030613133636635656139313739396661316362373731636162346163387c6a6f7264616e222c22686f6d6550616765223a2268747470733a2f2f62616964752e636f6d222c22696d6755726c223a2268747470733a2f2f766973696f6e2d312e6f73732d616363656c65726174652e616c6979756e63732e636f6d2f752f302f302f3230323131302f6f2f356236313762656332393762346532623839376665366664393932316138383864633637353863613764386434663937386535386136313638323934396566322e706e67222c226f776e657241646472657373223a22565650637859747459486b445670743850714e6e6e7a42613931546f3676796a4156222c22736f6369616c4d65646961223a5b7b226d6574686f64223a2254776974746572222c22616374697665223a747275652c226c696e6b223a5b2268747470733a2f2f746569747465722e636f6d225d7d5d2c22746f6b656e44657363223a223132336672207373222c226e616d65223a225061792d564b542d3230222c2273796d626f6c223a2270796b222c2274696d657374616d70223a313633333735303830313637317d

         签名：
         865036f3a5f0476f782304e10c37a8106bdf9b72f4ec19821c7c2ba7c8e2144130d6c8bde41de2f4cc206246ac54704e5fc152a3d13099802dd0a4c4ed2045861b

         打印 数据：
         635c06d4d0022b8d58628e17c59b23e2a0aa10d1dedb047bb5f472e3007fc273
         */
    }

    public static byte[] signatureToKeyBytes(byte[] messageHash, ECKey.ECDSASignature sig) throws SignatureException {
        int header = sig.v;
        if (header < 27 || header > 34)
            throw new SignatureException("Header byte out of range: " + header);
        if (header >= 31) {
            header -= 4;
        }
        int recId = header - 27;
        byte[] key = ECKey.recoverPubBytesFromSignature(recId, sig, messageHash);
        if (key == null)
            throw new SignatureException("Could not recover public key from signature");
        return key;
    }

    public static void main1(String[] args) throws Exception {
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

    public static boolean verifySignature(String hex, String sign,
                                          String address, boolean visible) {

        try {
            byte[] bytes = ByteUtils.HexToBytes(hex);
            byte[] signBytes = ByteUtils.HexToBytes(sign);
            byte[] messageHash = Hash.sha3(bytes);
            String signatureBase64 = TransactionSignUtil.getBase64FromByteString(ByteString.copyFrom(signBytes));
//        ECKey.signatureToAddress(messageHash, ECKey.ECDSASignature.fromComponents())
//        byte[] address = ECKey.signatureToAddress(messageHash,
//                ECKey.ECDSASignature.fromComponents(
//                        ByteUtils.HexToBytes(sign.substring(0, 64)),
//                        ByteUtils.HexToBytes(sign.substring(64, 128)),
//                                (byte) 27));
            byte[] addr = ECKey.signatureToAddress(messageHash, signatureBase64);
            return visible ? encode58Check(addr).equals(address) : ByteString.copyFrom(addr).toStringUtf8().equals(address);
        } catch (Exception e) {
            // e.printStackTrace();
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
