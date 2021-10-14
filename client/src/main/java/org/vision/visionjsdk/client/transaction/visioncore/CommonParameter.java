package org.vision.visionjsdk.client.transaction.visioncore;


import java.util.Set;

public class CommonParameter {

  public static final String IGNORE_WRONG_WITNESS_ADDRESS_FORMAT =
      "The localWitnessAccountAddress format is incorrect, ignored";
  public static CommonParameter PARAMETER = new CommonParameter();

  public Set<String> actuatorSet;

  public Set<String> getActuatorSet() {
    return actuatorSet;
  }

  public void setActuatorSet(Set<String> actuatorSet) {
    this.actuatorSet = actuatorSet;
  }

  public static CommonParameter getInstance() {
    return PARAMETER;
  }

  public boolean isECKeyCryptoEngine() {

    return true;
  }
}
