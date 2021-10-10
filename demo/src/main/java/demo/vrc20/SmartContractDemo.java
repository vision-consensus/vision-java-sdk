package demo.vrc20;

/**
 * The {@code SmartContractDemo} class includes demos of common 
 * smart contract related operations.
 */


import com.google.protobuf.ByteString;
import org.vision.visionjsdk.client.contract.Contract;
import org.vision.visionjsdk.client.contract.ContractFunction;
import org.vision.visionjsdk.client.transaction.TransactionBuilder;
import org.vision.visionjsdk.client.VisionClient;
import org.vision.visionjsdk.proto.Chain.Transaction;
import org.vision.visionjsdk.proto.Response.TransactionReturn;

public class SmartContractDemo {

    private final VisionClient client = VisionClient.ofVtest("5176b5624639dc479272605eb0e8feb54e3ff7104cfec4dd2b0faca33750fd99");

    /**
     * deploy a smart contract with its bytecode and abi.
     * 
     * Except from the contract's abi and bytecode, other attributes
     * of a contract have default values. You may choose to set values to 
     * any other attributes base on needs. The optional attributes are commented out
     * in this demo, refer to {@link org.vision.visionjsdk.client.contract.Contract}.
     * 
     * Notice that call_token_value is the amount of VRC-10 token to be deposited.
     * 
     * The demo contract is:
     * 
     * pragma solidity >=0.4.16 <0.8.0;
     * 
     * contract SimpleStorage {
     *     uint storedData;
     *     function set(uint x) public {
     *         storedData = x;
     *     }
     *     function get() public view returns (uint) {
     *         return storedData;
     *     }
     * }
     * 
     * @throws RuntimeException if deployment duplicating / owner and origin address don't match
     */
    public void deploySmartContract() {
        try {
            String bytecode = "608060405234801561001057600080fd5b50d380156" +
                    "1001d57600080fd5b50d2801561002a57600080fd5" +
                    "b5060dd806100396000396000f3fe6080604052348" +
                    "015600f57600080fd5b50d38015601b57600080fd5" +
                    "b50d28015602757600080fd5b5060043610604a576" +
                    "0003560e01c806360fe47b114604f5780636d4ce63" +
                    "c14607a575b600080fd5b607860048036036020811" +
                    "015606357600080fd5b81019080803590602001909" +
                    "291905050506096565b005b608060a0565b6040518" +
                    "082815260200191505060405180910390f35b80600" +
                    "08190555050565b6000805490509056fea26474726" +
                    "f6e58206304a660fbed31eff4077ef91f51652f50c" +
                    "642cdd52d505857b0b3f17e51e1a864736f6c634300050e0031";

            String abi = "{\"constant\":true,\"inputs\":[],\"name\":\"get\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"x\",\"type\":\"uint256\"}],\"name\":\"set\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}";


            Contract cntr = new Contract.Builder()
                                    .setClient(client)
                                    .setOwnerAddr(ByteString.copyFromUtf8("VTMMrpWMr8QVTmwCf9GVwXNyE4jc7xzJgQ"))
                                    .setBytecode(ByteString.copyFromUtf8(bytecode))
                                    .setAbi(abi)
                                    // .setCallValue()
                                    // .setName()
                                    // .setConsumeUserResourcePercent()
                                    // .setOriginEntropyLimit()
                                    .build();
        
            TransactionBuilder builder = cntr.deploy();
            //use the following method with parameters to call if has any VRC-10 deposit
            //TransactionBuilder builder = cntr.deploy(tokenId, callTokenValue);
            builder.setFeeLimit(1000000000L);
            builder.setMemo("Let's go!");
            //sign transaction
            Transaction signedTxn = client.signTransaction(builder.build());
            System.out.println(signedTxn.toString());
            //broadcast transaction
            TransactionReturn ret = client.broadcastTransaction(signedTxn);
            System.out.println("======== Result ========\n" + ret.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * get a smart contract from its address, return a org.vision.visionjsdk.client.contract.Contract object.
     */
    public void getSmartContract() {
        try {
            Contract cntr = client.getContract("VTMMrpWMr8QVTmwCf9GVwXNyE4jc7xzJgQ");
            System.out.println("Contract name: " + cntr.getName());
            // System.out.println("Contract ABI: " + cntr.getAbi());
            System.out.println("Contract functions: " + cntr.getFunctions().size());
            for (ContractFunction cf : cntr.getFunctions()) {
                System.out.println(cf.toString());
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public static void main(String[] args) {
        new SmartContractDemo().deploySmartContract();
    }

//    /**
//     * This is a constant call demo.
//     * <p>the example function is: function name() public view returns (string).
//     * A constant call does not require signature or broadcasting. This demos shows the stpes
//     * to  </p>
//     *
//     *
//     */
//    public void constantCallDemo() {
//        Function name = new Function("name",
//                Collections.emptyList(), Arrays.asList(new TypeReference<Utf8String>() {}));
//
//        TransactionExtention txnExt = client.constantCall(Base58Check.bytesToBase58(ownerAddr.toByteArray()),
//                Base58Check.bytesToBase58(cntrAddr.toByteArray()), name);
//        //Convert constant result to human readable text
//        String result = Numeric.toHexString(txnExt.getConstantResult(0).toByteArray());
//        return (String) FunctionReturnDecoder.decode(result, name.getOutputParameters()).get(0).getValue();
//    }

}