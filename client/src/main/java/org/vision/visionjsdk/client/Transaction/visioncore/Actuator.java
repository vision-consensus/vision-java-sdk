package org.vision.visionjsdk.client.Transaction.visioncore;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

public interface Actuator {

  boolean execute(Object result) throws ContractExeException;

  boolean validate() throws ContractValidateException;

  ByteString getOwnerAddress() throws InvalidProtocolBufferException;

  long calcFee();

}
