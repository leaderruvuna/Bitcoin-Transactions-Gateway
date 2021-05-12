package com.btcapi.demo.utils.encryption;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class HashData {

    public String hashData256(String originalString) {
        String sha256hex = Hashing.sha256()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString();
        return sha256hex;
    }

    public String toHex(String str) {
        StringBuffer sb = new StringBuffer();
        char chars[] = str.toCharArray();
        for (char ch : chars) {
            String hexString = Integer.toHexString(ch);
            sb.append(hexString);
        }
        String result = sb.toString();
        return result;
    }
}
