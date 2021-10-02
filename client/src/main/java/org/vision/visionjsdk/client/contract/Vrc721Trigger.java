package org.vision.visionjsdk.client.contract;

import org.vision.visionjsdk.abi.FunctionReturnDecoder;
import org.vision.visionjsdk.abi.TypeReference;
import org.vision.visionjsdk.abi.datatypes.Function;
import org.vision.visionjsdk.abi.datatypes.Utf8String;
import org.vision.visionjsdk.client.VisionClient;
import org.vision.visionjsdk.proto.Response;
import org.vision.visionjsdk.utils.Base58Check;
import org.vision.visionjsdk.utils.Numeric;

import java.util.Arrays;
import java.util.Collections;

public class Vrc721Trigger {
    public static String constantOfUtf8String(String hexAddressContract, String grpcIpPort,String grpcSolidityIpPort,
                                              String funcName) {
        VisionClient client;
        if (!Vrc20Trigger.isBlank(grpcIpPort) && !Vrc20Trigger.isBlank(grpcSolidityIpPort)) {
            client = VisionClient.ofSelfFullNode("0000000000000000000000000000000000000000000000000000000000000000", grpcIpPort, grpcSolidityIpPort);
        } else {
            client = VisionClient.ofVtestIp("0000000000000000000000000000000000000000000000000000000000000000");
        }

        Function function = new Function(funcName,
                Collections.emptyList(), Arrays.asList(new TypeReference<Utf8String>() {}));

        byte[] ownerAddr = Vrc20Trigger.fromHexString("460000000000000000000000000000000000000000");
        byte[] cntrAddr = Vrc20Trigger.fromHexString(hexAddressContract);
        try {
            Response.TransactionExtention txnExt = client.constantCallIgnore(Base58Check.bytesToBase58(ownerAddr),
                    Base58Check.bytesToBase58(cntrAddr), function);
            //Convert constant result to human readable text
            int resultCount = txnExt.getConstantResultCount();
            if (resultCount > 0) {
                String result = Numeric.toHexString(txnExt.getConstantResult(0).toByteArray());
                String i = (String) FunctionReturnDecoder.decode(result, function.getOutputParameters()).get(0).getValue();
                // System.out.println("testDecimalsVrc20Transaction.result=" + i);
                return i;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
