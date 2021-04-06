package org.vision.visionjsdk.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.vision.visionjsdk.abi.FunctionEncoder;
import org.vision.visionjsdk.abi.TypeReference;
import org.vision.visionjsdk.abi.datatypes.Address;
import org.vision.visionjsdk.abi.datatypes.Bool;
import org.vision.visionjsdk.abi.datatypes.Function;
import org.vision.visionjsdk.abi.datatypes.generated.Uint256;
import org.vision.visionjsdk.api.GrpcAPI.EmptyMessage;
import org.vision.visionjsdk.proto.Chain.Transaction;
import org.vision.visionjsdk.proto.Contract.TriggerSmartContract;
import org.vision.visionjsdk.proto.Response.BlockExtention;
import org.vision.visionjsdk.proto.Response.TransactionExtention;
import org.vision.visionjsdk.proto.Response.TransactionReturn;

import java.math.BigInteger;
import java.util.Arrays;

import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Test;

public class ClientTest {
    @Test
    public void testGetNowBlockQuery() {
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        BlockExtention block = client.blockingStub.getNowBlock2(EmptyMessage.newBuilder().build());

        System.out.println(block.getBlockHeader());
        assertTrue(block.getBlockHeader().getRawDataOrBuilder().getNumber() > 0);
    }

    @Test
    public void testSendVrc20Transaction() {
//        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
//
//        // transfer(address,uint256) returns (bool)
//        Function vrc20Transfer = new Function("transfer",
//            Arrays.asList(new Address(""),
//                new Uint256(BigInteger.valueOf(10).multiply(BigInteger.valueOf(10).pow(18)))),
//            Arrays.asList(new TypeReference<Bool>() {}));
//
//        String encodedHex = FunctionEncoder.encode(vrc20Transfer);
//
//        TriggerSmartContract trigger =
//            TriggerSmartContract.newBuilder()
//                .setOwnerAddress(VisionClient.parseAddress(""))
//                .setContractAddress(VisionClient.parseAddress(""))
//                .setData(VisionClient.parseHex(encodedHex))
//                .build();
//
//        System.out.println("trigger:\n" + trigger);
//
//        TransactionExtention txnExt = client.blockingStub.triggerContract(trigger);
//        System.out.println("txn id => " + Hex.toHexString(txnExt.getTxid().toByteArray()));
//
//        Transaction signedTxn = client.signTransaction(txnExt);
//
//        System.out.println(signedTxn.toString());
//        TransactionReturn ret = client.blockingStub.broadcastTransaction(signedTxn);
//        System.out.println("======== Result ========\n" + ret.toString());
    }

    @Test
    public void testGenerateAddress() {
        VisionClient.generateAddress();
    }
}
