package org.vision.visionjsdk.client.contract;

/**
 * The {@code Vrc20Contract} is a wrapper class of a standard VRC-20 smart contract.
 * 
 * <p>A {@code Vrc20Contract} object includes standard VRC-20 functions defined
 * in TIP-20. Each {@code Vrc20Contract} binds a {@link VisionClient} with specific
 * caller's private key and address.</p>
 * 
 * @since jdk 13.0.2+8
 * @see org.vision.visionjsdk.client.VisionClient
 * @see org.vision.visionjsdk.abi.datatypes.Function
 */

import org.vision.visionjsdk.abi.datatypes.Address;
import org.vision.visionjsdk.abi.datatypes.Bool;
import org.vision.visionjsdk.abi.datatypes.Function;
import org.vision.visionjsdk.abi.datatypes.generated.Uint8;
import org.vision.visionjsdk.abi.datatypes.generated.Uint256;
import org.vision.visionjsdk.abi.datatypes.Utf8String;
import org.vision.visionjsdk.abi.FunctionReturnDecoder;
import org.vision.visionjsdk.abi.TypeReference;
import org.vision.visionjsdk.client.transaction.TransactionBuilder;
import org.vision.visionjsdk.client.VisionClient;
import org.vision.visionjsdk.proto.Chain.Transaction;
import org.vision.visionjsdk.proto.Response.TransactionExtention;
import org.vision.visionjsdk.proto.Response.TransactionReturn;
import org.vision.visionjsdk.utils.Base58Check;
import org.vision.visionjsdk.utils.Numeric;

import com.google.protobuf.ByteString;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

public class Vrc20Contract extends Contract {
    protected int decimals;

    public Vrc20Contract(Contract cntr, String ownerAddr, VisionClient client) {
        super(cntr, ownerAddr, client);
        decimals = decimals().intValue();
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

      /**
       * Call function decimals() public view returns (uint8).
       * 
       * Returns the number of decimals the token uses - e.g. 8, 
       * means to divide the token amount by 100000000 to get its user representation
       * 
       * @return A TransactionExtention object contains constant result to resolve
       */
      public BigInteger decimals() {
        Function decimals = new Function("decimals",
                Collections.emptyList(), Arrays.asList(new TypeReference<Uint8>() {}));
        
        TransactionExtention txnExt = client.constantCall(Base58Check.bytesToBase58(ownerAddr.toByteArray()), 
                Base58Check.bytesToBase58(cntrAddr.toByteArray()), decimals);
        //Convert constant result to human readable text
        String result = Numeric.toHexString(txnExt.getConstantResult(0).toByteArray());
        return (BigInteger)FunctionReturnDecoder.decode(result, decimals.getOutputParameters()).get(0).getValue();
      }

      /**
       * Call function totalSupply() public view returns (uint256).
       * 
       * Returns the total token supply.
       * 
       * @return A TransactionExtention object contains constant result to resolve
       */
      public BigInteger totalSupply() {
        Function totalSupply = new Function("totalSupply",
                Collections.emptyList(), Arrays.asList(new TypeReference<Uint256>() {}));

        TransactionExtention txnExt = client.constantCall(Base58Check.bytesToBase58(ownerAddr.toByteArray()), 
                Base58Check.bytesToBase58(cntrAddr.toByteArray()), totalSupply);
        //Convert constant result to human readable text
        String result = Numeric.toHexString(txnExt.getConstantResult(0).toByteArray());
        return (BigInteger)FunctionReturnDecoder.decode(result, totalSupply.getOutputParameters()).get(0).getValue();
      }

      /**
       * Call function balanceOf(address _owner) public view returns (uint256 balance).
       * 
       * Returns the account balance of another account with address _owner.
       * 
       * @param accountAddr The token owner's address
       * @return A TransactionExtention object contains constant result to resolve
       */
      public BigInteger balanceOf(String accountAddr) {
        Function balanceOf = new Function("balanceOf",
                Arrays.asList(new Address(accountAddr)), Arrays.asList(new TypeReference<Uint256>() {}));

        TransactionExtention txnExt = client.constantCall(Base58Check.bytesToBase58(ownerAddr.toByteArray()), 
                Base58Check.bytesToBase58(cntrAddr.toByteArray()), balanceOf);
        //Convert constant result to human readable text
        String result = Numeric.toHexString(txnExt.getConstantResult(0).toByteArray());
        return (BigInteger)FunctionReturnDecoder.decode(result, balanceOf.getOutputParameters()).get(0).getValue();
      }

      /**
       * Call function transfer(address _to, uint256 _value) public returns (bool success).
       * 
       * Transfers _value amount of tokens to address _to.
       * 
       * @param destAddr The address to receive the token
       * @param amount The transfer amount
       * @param memo The transaction memo
       * @param feeLimit The entropy fee limit
       * @return A TransactionReturn object contains the trigger result(true / false)
       */
      public TransactionReturn transfer(String destAddr, long amount, 
             String memo, long feeLimit) {
        Function transfer = new Function("transfer",
                Arrays.asList(new Address(destAddr),
                        new Uint256(BigInteger.valueOf(amount).multiply(BigInteger.valueOf(10).pow(decimals)))),
                Arrays.asList(new TypeReference<Bool>() {}));

        TransactionBuilder builder = client.triggerCall(Base58Check.bytesToBase58(ownerAddr.toByteArray()), 
                Base58Check.bytesToBase58(cntrAddr.toByteArray()), transfer);
        builder.setFeeLimit(feeLimit);
        builder.setMemo(memo);

        Transaction signedTxn = client.signTransaction(builder.build());
        return client.broadcastTransaction(signedTxn);
      }

      /**
       * call function transferFrom(address _from, address _to, uint256 _value) public returns (bool success)
       * 
       * The transferFrom method is used for a withdraw workflow, 
       * allowing contracts to transfer tokens on your behalf. This can only be called
       * when someone has allowed you some amount.
       * 
       * @param fromAddr The address who sends tokens (or the address to withdraw from)
       * @param destAddr The address to receive the token
       * @param amount The transfer amount
       * @param memo The transaction memo
       * @param feeLimit The entropy fee limit
       * @return A TransactionReturn object contains the trigger result(true / false)
       */
      public TransactionReturn transferFrom(String fromAddr, String destAddr, long amount, 
             String memo, long feeLimit) {
        Function transferFrom = new Function("transferFrom",
                Arrays.asList(new Address(fromAddr) ,new Address(destAddr),
                        new Uint256(BigInteger.valueOf(amount).multiply(BigInteger.valueOf(10).pow(decimals)))),
                Arrays.asList(new TypeReference<Bool>() {}));

        TransactionBuilder builder = client.triggerCall(Base58Check.bytesToBase58(ownerAddr.toByteArray()), 
                Base58Check.bytesToBase58(cntrAddr.toByteArray()), transferFrom);
        builder.setFeeLimit(feeLimit);
        builder.setMemo(memo);

        Transaction signedTxn = client.signTransaction(builder.build());
        return client.broadcastTransaction(signedTxn);
      }

      /**
       * Call function approve(address _spender, uint256 _value) public returns (bool success)
       * 
       * Allows _spender to withdraw from your account multiple times, up to the _value amount. 
       * If this function is called again it overwrites the current allowance with _value.
       * 
       * @param spender The address who is allowed to withdraw.
       * @param amount The amount allowed to withdraw.
       * @param memo The transaction memo
       * @param feeLimit The entropy fee limit
       * @return A TransactionReturn object contains the trigger result(true / false)
       */
      public TransactionReturn approve(String spender ,long amount, 
             String memo, long feeLimit) {
        Function approve = new Function("approve",
                Arrays.asList(new Address(spender) ,
                        new Uint256(BigInteger.valueOf(amount).multiply(BigInteger.valueOf(10).pow(decimals)))),
                Arrays.asList(new TypeReference<Bool>() {}));

                TransactionBuilder builder = client.triggerCall(Base58Check.bytesToBase58(ownerAddr.toByteArray()), 
                Base58Check.bytesToBase58(cntrAddr.toByteArray()), approve);
        builder.setFeeLimit(feeLimit);
        builder.setMemo(memo);

        Transaction signedTxn = client.signTransaction(builder.build());
        return client.broadcastTransaction(signedTxn);
      }

      /**
       * Call function allowance(address _owner, address _spender) public view returns (uint256 remaining).
       * 
       * Returns the amount which _spender is still allowed to withdraw from _owner.
       * 
       * @param owner The address to be withdrew from.
       * @param spender The address of the withdrawer.
       * @return A TransactionExtention object contains constant result to resolve
       */
      public BigInteger allowance(String owner, String spender) {
        Function allowance = new Function("allowance",
                Arrays.asList(new Address(owner), new Address(spender)),
                Arrays.asList(new TypeReference<Uint256>() {}));
        
        TransactionExtention txnExt = client.constantCall(Base58Check.bytesToBase58(ownerAddr.toByteArray()), 
                Base58Check.bytesToBase58(cntrAddr.toByteArray()), allowance);
        //Convert constant result to human readable text
        String result = Numeric.toHexString(txnExt.getConstantResult(0).toByteArray());
        return (BigInteger)FunctionReturnDecoder.decode(result, allowance.getOutputParameters()).get(0).getValue();
      }

}