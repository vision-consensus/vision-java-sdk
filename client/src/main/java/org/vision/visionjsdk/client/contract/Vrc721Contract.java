package org.vision.visionjsdk.client.contract;

import org.vision.visionjsdk.abi.FunctionReturnDecoder;
import org.vision.visionjsdk.abi.TypeReference;
import org.vision.visionjsdk.abi.datatypes.Address;
import org.vision.visionjsdk.abi.datatypes.Function;
import org.vision.visionjsdk.abi.datatypes.Utf8String;
import org.vision.visionjsdk.abi.datatypes.generated.Uint256;
import org.vision.visionjsdk.client.VisionClient;
import org.vision.visionjsdk.proto.Response;
import org.vision.visionjsdk.utils.Base58Check;
import org.vision.visionjsdk.utils.Numeric;
import org.vision.visionjsdk.proto.Response.TransactionExtention;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

public class Vrc721Contract extends Contract {

    public Vrc721Contract(Contract cntr, String ownerAddr, VisionClient client) {
        super(cntr, ownerAddr, client);
    }

    public BigInteger balanceOf(String accountAddr) {
        Function balanceOf = new Function("balanceOf",
                Arrays.asList(new Address(accountAddr)), Arrays.asList(new TypeReference<Uint256>() {}));

        Response.TransactionExtention txnExt = client.constantCall(Base58Check.bytesToBase58(ownerAddr.toByteArray()),
                Base58Check.bytesToBase58(cntrAddr.toByteArray()), balanceOf);
        //Convert constant result to human readable text
        String result = Numeric.toHexString(txnExt.getConstantResult(0).toByteArray());
        return (BigInteger) FunctionReturnDecoder.decode(result, balanceOf.getOutputParameters()).get(0).getValue();
    }

    /**
     * Call function name() public view returns (string).
     *
     * Returns the name of the token - e.g. "MyToken".
     *
     * @return A TransactionExtention object contains constant result to resolve
     */
    public String name() {
        Function name = new Function("name",
                Collections.emptyList(), Arrays.asList(new TypeReference<Utf8String>() {}));

        TransactionExtention txnExt = client.constantCall(Base58Check.bytesToBase58(ownerAddr.toByteArray()),
                Base58Check.bytesToBase58(cntrAddr.toByteArray()), name);
        //Convert constant result to human readable text
        String result = Numeric.toHexString(txnExt.getConstantResult(0).toByteArray());
        return (String)FunctionReturnDecoder.decode(result, name.getOutputParameters()).get(0).getValue();
    }

    /**
     * Call function name() public view returns (string).
     *
     * Returns the name of the token - e.g. "MyToken".
     *
     * @return A TransactionExtention object contains constant result to resolve
     */
    public String _name() {
        Function name = new Function("_name",
                Collections.emptyList(), Arrays.asList(new TypeReference<Utf8String>() {}));

        TransactionExtention txnExt = client.constantCall(Base58Check.bytesToBase58(ownerAddr.toByteArray()),
                Base58Check.bytesToBase58(cntrAddr.toByteArray()), name);
        //Convert constant result to human readable text
        String result = Numeric.toHexString(txnExt.getConstantResult(0).toByteArray());
        return (String)FunctionReturnDecoder.decode(result, name.getOutputParameters()).get(0).getValue();
    }

    /**
     * Call function symbol() public view returns (string).
     *
     * Returns the symbol of the token. E.g. "HIX".
     *
     * @return A TransactionExtention object contains constant result to resolve
     */
    public String symbol() {
        Function symbol = new Function("symbol",
                Collections.emptyList(), Arrays.asList(new TypeReference<Utf8String>() {}));

        TransactionExtention txnExt = client.constantCall(Base58Check.bytesToBase58(ownerAddr.toByteArray()),
                Base58Check.bytesToBase58(cntrAddr.toByteArray()), symbol);
        //Convert constant result to human readable text
        String result = Numeric.toHexString(txnExt.getConstantResult(0).toByteArray());
        return (String)FunctionReturnDecoder.decode(result, symbol.getOutputParameters()).get(0).getValue();
    }

    /**
     * Call function symbol() public view returns (string).
     *
     * Returns the symbol of the token. E.g. "HIX".
     *
     * @return A TransactionExtention object contains constant result to resolve
     */
    public String _symbol() {
        Function symbol = new Function("_symbol",
                Collections.emptyList(), Arrays.asList(new TypeReference<Utf8String>() {}));

        TransactionExtention txnExt = client.constantCall(Base58Check.bytesToBase58(ownerAddr.toByteArray()),
                Base58Check.bytesToBase58(cntrAddr.toByteArray()), symbol);
        //Convert constant result to human readable text
        String result = Numeric.toHexString(txnExt.getConstantResult(0).toByteArray());
        return (String)FunctionReturnDecoder.decode(result, symbol.getOutputParameters()).get(0).getValue();
    }
}
