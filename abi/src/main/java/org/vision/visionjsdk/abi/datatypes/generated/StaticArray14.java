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
public class StaticArray14<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray14(List<T> values) {
        super(14, values);
    }

    @Deprecated
    @SafeVarargs
    public StaticArray14(T... values) {
        super(14, values);
    }

    public StaticArray14(Class<T> type, List<T> values) {
        super(type, 14, values);
    }

    @SafeVarargs
    public StaticArray14(Class<T> type, T... values) {
        super(type, 14, values);
    }
}
