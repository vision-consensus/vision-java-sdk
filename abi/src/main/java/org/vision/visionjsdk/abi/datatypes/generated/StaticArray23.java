package org.vision.visionjsdk.abi.datatypes.generated;

import java.util.List;
import org.vision.visionjsdk.abi.datatypes.StaticArray;
import org.vision.visionjsdk.abi.datatypes.Type;

/**
 * Auto generated code.
 * <p>Do not modifiy!
 * <p>Please use org.vision.visionjsdk.codegen.AbiTypesGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 */
public class StaticArray23<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray23(List<T> values) {
        super(23, values);
    }

    @Deprecated
    @SafeVarargs
    public StaticArray23(T... values) {
        super(23, values);
    }

    public StaticArray23(Class<T> type, List<T> values) {
        super(type, 23, values);
    }

    @SafeVarargs
    public StaticArray23(Class<T> type, T... values) {
        super(type, 23, values);
    }
}
