package com.lbsation.ems.bassif.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class IpcUtil {

    public static String alterHexa(int decimal){

        return String.format("%04x", decimal);
    }

    public static String zeroRightAdd(String data, int len){
        return StringUtils.rightPad(data, len, (char) 0);
    }
    public static String zeroLeftAdd(String data, int len){
        return StringUtils.leftPad(data, len, (char) 0);
    }

    public static String getResponseContents(String response){
        if(!StringUtils.isEmpty(response)){
            return new String(Base64.decodeBase64(response.split(",")[5].getBytes())).substring(36);
        }
        else{
             return null;
        }
    }

    public static String getCh(int flln, int lln){
        return String.valueOf(lln - flln);
    }
}
