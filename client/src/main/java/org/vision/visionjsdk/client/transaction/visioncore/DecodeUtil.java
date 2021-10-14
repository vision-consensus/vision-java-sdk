package org.vision.visionjsdk.client.transaction.visioncore;


public class DecodeUtil {

  public static final int ADDRESS_SIZE = 42;
  public static byte addressPreFixByte = (byte) 0x46;

  public static String addressPreFixString = "46";

  public static boolean addressValid(byte[] address) {
    if (isEmpty(address)) {
      // logger.warn("Warning: Address is empty !!");
      return false;
    }
    if (address.length != ADDRESS_SIZE / 2) {
      // logger.warn(
      //     "Warning: Address length need " + ADDRESS_SIZE + " but " + address.length
      //         + " !!");
      return false;
    }

    if (address[0] != addressPreFixByte) {
      // logger.warn("Warning: Address need prefix with " + addressPreFixByte + " but "
      //     + address[0] + " !!");
      return false;
    }
    return true;
  }

  public static boolean isEmpty(final byte[] array) {
    return array == null || array.length == 0;
  }

}
