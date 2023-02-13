package org.vision.visionjsdk.abi.datatypes.generated;

import java.math.BigInteger;
import org.vision.visionjsdk.abi.datatypes.Uint;

/**
 * Auto generated code.
 * <p>Do not modifiy!
 * <p>Please use org.vision.visionjsdk.codegen.AbiTypesGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 */
public class Uint256 extends Uint {
    public static final Uint256 DEFAULT = new Uint256(BigInteger.ZERO);

    public Uint256(BigInteger value) {
        super(256, value);
    }

    public Uint256(long value) {
        this(BigInteger.valueOf(value));
    }
}
