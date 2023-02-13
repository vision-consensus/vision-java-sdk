package org.vision.visionjsdk.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.vision.visionjsdk.abi.FunctionEncoder;
import org.vision.visionjsdk.abi.FunctionReturnDecoder;
import org.vision.visionjsdk.abi.TypeReference;
import org.vision.visionjsdk.abi.datatypes.Address;
import org.vision.visionjsdk.abi.datatypes.Bool;
import org.vision.visionjsdk.abi.datatypes.Function;
import org.vision.visionjsdk.abi.datatypes.generated.Uint256;
import org.vision.visionjsdk.abi.datatypes.generated.Uint8;
import org.vision.visionjsdk.api.GrpcAPI.EmptyMessage;
import org.vision.visionjsdk.proto.Chain.Transaction;
import org.vision.visionjsdk.proto.Contract.TriggerSmartContract;
import org.vision.visionjsdk.proto.Response.BlockExtention;
import org.vision.visionjsdk.proto.Response.TransactionExtention;
import org.vision.visionjsdk.proto.Response.TransactionReturn;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Test;
import org.vision.visionjsdk.utils.Base58Check;
import org.vision.visionjsdk.utils.Numeric;

public class ClientTest {
    @Test
    public void testGetNowBlockQuery() {
        VisionClient client = VisionClient.ofMainnet("0000000000000000000000000000000000000000000000000000000000000000");
        BlockExtention block = client.blockingStub.getNowBlock2(EmptyMessage.newBuilder().build());

        System.out.println(block.getBlockHeader());
        assertTrue(block.getBlockHeader().getRawDataOrBuilder().getNumber() > 0);
    }

    @Test
    public void testSendVrc20Transaction() {
        VisionClient client = VisionClient.ofVpioneer("0000000000000000000000000000000000000000000000000000000000000000");

        // transfer(address,uint256) returns (bool)
        Function vrc20Transfer = new Function("transfer",
            Arrays.asList(new Address("463a86B966369B5c98F0f7E0B009F4D2aBD4d97162"),
                new Uint256(BigInteger.valueOf(10).multiply(BigInteger.valueOf(10).pow(18)))),
            Arrays.asList(new TypeReference<Bool>() {}));

        String encodedHex = FunctionEncoder.encode(vrc20Transfer);

        TriggerSmartContract trigger =
            TriggerSmartContract.newBuilder()
                .setOwnerAddress(VisionClient.parseAddress("0x4693DfeCE2fA3631C59E399638AE48F74ca88b8700"))
                .setContractAddress(VisionClient.parseAddress("0x469fe14f47516ec3941cbb5d395c61c6abda76f453"))
                .setData(VisionClient.parseHex(encodedHex))
                .build();

        System.out.println("trigger:\n" + trigger);

        TransactionExtention txnExt = client.blockingStub.triggerContract(trigger);
        System.out.println("txn id => " + Hex.toHexString(txnExt.getTxid().toByteArray()));

        Transaction signedTxn = client.signTransaction(txnExt);

        System.out.println(signedTxn.toString());
        TransactionReturn ret = client.blockingStub.broadcastTransaction(signedTxn);
        System.out.println("======== Result ========\n" + ret.toString());
    }

    @Test
    public void testDecimalsVrc20Transaction() {
        VisionClient client = VisionClient.ofVpioneer("0000000000000000000000000000000000000000000000000000000000000000");

        Function decimals = new Function("decimals",
                Collections.emptyList(), Arrays.asList(new TypeReference<Uint8>() {}));
        byte[] ownerAddr = fromHexString("460000000000000000000000000000000000000000");
        // byte[] cntrAddr = fromHexString("469fe14f47516ec3941cbb5d395c61c6abda76f453"); // local
        byte[] cntrAddr = fromHexString("46a4aaeead561244eb7912b584000b76b591864d1a"); // vtest
        try {
            TransactionExtention txnExt = client.constantCallIgnore(Base58Check.bytesToBase58(ownerAddr),
                    Base58Check.bytesToBase58(cntrAddr), decimals);
            //Convert constant result to human readable text
            String result = Numeric.toHexString(txnExt.getConstantResult(0).toByteArray());
            BigInteger i = (BigInteger) FunctionReturnDecoder.decode(result, decimals.getOutputParameters()).get(0).getValue();
            System.out.println("testDecimalsVrc20Transaction.result=" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testTotalSupplyVrc20Transaction() {
        VisionClient client = VisionClient.ofVpioneer("0000000000000000000000000000000000000000000000000000000000000000");

        Function totalSupply = new Function("totalSupply",
                Collections.emptyList(), Arrays.asList(new TypeReference<Uint256>() {}));
        byte[] ownerAddr = fromHexString("460000000000000000000000000000000000000000");
        byte[] cntrAddr = fromHexString("469fe14f47516ec3941cbb5d395c61c6abda76f453");
        try {
            TransactionExtention txnExt = client.constantCallIgnore(Base58Check.bytesToBase58(ownerAddr),
                    Base58Check.bytesToBase58(cntrAddr), totalSupply);
            //Convert constant result to human readable text
            String result = Numeric.toHexString(txnExt.getConstantResult(0).toByteArray());
            BigInteger i = (BigInteger) FunctionReturnDecoder.decode(result, totalSupply.getOutputParameters()).get(0).getValue();
            System.out.println("testDecimalsVrc20Transaction.result=" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGenerateAddress() {
        VisionClient.generateAddress();
    }

    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    /**
     * get bytes data from hex string data.
     */
    private byte[] fromHexString(String data) {
        if (data == null) {
            return EMPTY_BYTE_ARRAY;
        }
        if (data.startsWith("0x")) {
            data = data.substring(2);
        }
        if (data.length() % 2 != 0) {
            data = "0" + data;
        }
        return Hex.decode(data);
    }
}
