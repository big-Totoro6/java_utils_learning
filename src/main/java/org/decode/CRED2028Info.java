package org.decode;

import lombok.Data;

import java.util.LinkedHashMap;

@Data
public class CRED2028Info {
    public static LinkedHashMap<String,Integer> attach = new LinkedHashMap<>();

    static {
        attach.put("cardNo",20);
        attach.put("cardName",30);
        attach.put("type",4);
    }

    // 字段
    private String cardNo;
    private String cardName;
    private String type;
}
