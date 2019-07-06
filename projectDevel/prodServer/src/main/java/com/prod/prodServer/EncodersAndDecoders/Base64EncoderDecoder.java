/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.EncodersAndDecoders;

import com.google.common.base.Preconditions;
import org.apache.commons.codec.binary.Base64;


/**
 *
 * @author shubham
 */
public class Base64EncoderDecoder {
    
    public static String convert(Integer value) {
    byte[] intbytes = new byte[6];
    for (int i = 5; i > 1; --i) {
      intbytes[i] = value.byteValue();
      value = value >>> 8;
    }
    byte[] encodedBytes = Base64.encodeBase64(intbytes, false, true);
    Preconditions.checkArgument(encodedBytes.length == 8, "Encoded  should be 8 bytes for base64");
    int i = 2;
    for (; i < encodedBytes.length; ++i) {
      if (encodedBytes[i] != 'A') {
        break;
      }
    }
    byte[] truncatedBytes = new byte[encodedBytes.length - i];
    System.arraycopy(encodedBytes, i, truncatedBytes, 0, truncatedBytes.length);
    return new String(truncatedBytes);
  }

  public static Integer reverseConvert(String value) {
    byte[] stringBytes = value.getBytes();
      Preconditions.checkArgument(stringBytes.length <= 6, stringBytes);
    byte[] encodedBytes = new byte[8];
    int i = 0;
    for (; i < 8 - stringBytes.length; ++i) {
      encodedBytes[i] = 'A';
    }
    for (int j = 0; i < 8; ++i, ++j) {
      encodedBytes[i] = stringBytes[j];
    }
    byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
    Preconditions.checkArgument(decodedBytes.length == 6);
    Preconditions.checkArgument(decodedBytes[0] == 0 && decodedBytes[1] == 0);
    int intValue = 0;
    for (int byteIndex = 2; byteIndex < 6; ++byteIndex) {
      int byteValue = decodedBytes[byteIndex];
      if (byteValue < 0) {
        byteValue ^= -256;
      }
      intValue = (intValue << 8) | byteValue;
    }
    return intValue;
  }
    
}
