package org.vision.visionjsdk.utils;

import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.vision.visionjsdk.utils.Base58Check.base58ToBytes;
import static org.vision.visionjsdk.utils.Base58Check.bytesToBase58;

public class Base58CheckTest {

    @Test
    public void testAddressConverting() {
        byte[] rawAddr = Hex.decode("4659d3ad9d126e153b9564417d3a05cf51c1964edf");
        assertArrayEquals(rawAddr, base58ToBytes("VJrBeLEkuw1XR8acgdbgsbh3STV85vrAEb"));
        assertEquals(bytesToBase58(rawAddr), "VJrBeLEkuw1XR8acgdbgsbh3STV85vrAEb");
    }
}
