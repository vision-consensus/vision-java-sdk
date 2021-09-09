package org.vision.visionjsdk.client.contract;

import org.bouncycastle.util.encoders.Hex;
import org.vision.visionjsdk.abi.FunctionReturnDecoder;
import org.vision.visionjsdk.abi.TypeReference;
import org.vision.visionjsdk.abi.datatypes.Function;
import org.vision.visionjsdk.abi.datatypes.generated.Uint256;
import org.vision.visionjsdk.abi.datatypes.generated.Uint8;
import org.vision.visionjsdk.client.VisionClient;
import org.vision.visionjsdk.proto.Response;
import org.vision.visionjsdk.utils.Base58Check;
import org.vision.visionjsdk.utils.Numeric;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;


public class Vrc20Trigger {
    public static BigInteger decimalsVrc20Transaction(String hexAddressContract, String grpcIpPort,String grpcSolidityIpPort) {
        VisionClient client;
        if (!isBlank(grpcIpPort) && !isBlank(grpcSolidityIpPort)) {
            client = VisionClient.ofSelfFullNode("0000000000000000000000000000000000000000000000000000000000000000", grpcIpPort, grpcSolidityIpPort);
        } else {
            client = VisionClient.ofVtestIp("0000000000000000000000000000000000000000000000000000000000000000");
        }

        Function decimals = new Function("decimals",
                Collections.emptyList(), Arrays.asList(new TypeReference<Uint8>() {}));
        byte[] ownerAddr = fromHexString("460000000000000000000000000000000000000000");
        byte[] cntrAddr = fromHexString(hexAddressContract);
        try {
            Response.TransactionExtention txnExt = client.constantCallIgnore(Base58Check.bytesToBase58(ownerAddr),
                    Base58Check.bytesToBase58(cntrAddr), decimals);
            //Convert constant result to human readable text
            int resultCount = txnExt.getConstantResultCount();
            if (resultCount > 0) {
                String result = Numeric.toHexString(txnExt.getConstantResult(0).toByteArray());
                BigInteger i = (BigInteger) FunctionReturnDecoder.decode(result, decimals.getOutputParameters()).get(0).getValue();
                // System.out.println("testDecimalsVrc20Transaction.result=" + i);
                return i;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BigInteger totalSupplyVrc20Transaction(String hexAddressContract, String grpcIpPort,String grpcSolidityIpPort) {
        VisionClient client;
        if (!isBlank(grpcIpPort) && !isBlank(grpcSolidityIpPort)) {
            client = VisionClient.ofSelfFullNode("0000000000000000000000000000000000000000000000000000000000000000", grpcIpPort, grpcSolidityIpPort);
        } else {
            client = VisionClient.ofVtestIp("0000000000000000000000000000000000000000000000000000000000000000");
        }

        Function totalSupply = new Function("totalSupply",
                Collections.emptyList(), Arrays.asList(new TypeReference<Uint256>() {}));
        byte[] ownerAddr = fromHexString("460000000000000000000000000000000000000000");
        byte[] cntrAddr = fromHexString(hexAddressContract);
        try {
            Response.TransactionExtention txnExt = client.constantCallIgnore(Base58Check.bytesToBase58(ownerAddr),
                    Base58Check.bytesToBase58(cntrAddr), totalSupply);
            //Convert constant result to human readable text
            int resultCount = txnExt.getConstantResultCount();
            if (resultCount > 0) {
                String result = Numeric.toHexString(txnExt.getConstantResult(0).toByteArray());
                BigInteger i = (BigInteger) FunctionReturnDecoder.decode(result, totalSupply.getOutputParameters()).get(0).getValue();
                // System.out.println("testDecimalsVrc20Transaction.result=" + i);
                return i;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    /**
     * get bytes data from hex string data.
     */
    private static byte[] fromHexString(String data) {
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

    private static boolean isBlank(String value) {
        return null == value || value.length() == 0;
    }
}
