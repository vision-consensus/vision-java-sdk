package org.vision.visionjsdk.abi.datatypes.generated;

import java.math.BigInteger;
import org.vision.visionjsdk.abi.datatypes.Int;

/**
 * Auto generated code.
 * <p>Do not modifiy!
 * <p>Please use org.vision.visionjsdk.codegen.AbiTypesGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 */
public class Int224 extends Int {
    public static final Int224 DEFAULT = new Int224(BigInteger.ZERO);

    public Int224(BigInteger value) {
        super(224, value);
    }

    public Int224(long value) {
        this(BigInteger.valueOf(value));
    }
}
