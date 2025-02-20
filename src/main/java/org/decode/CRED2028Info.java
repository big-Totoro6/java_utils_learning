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
        //在一些特殊的格式中，尤其是在银行或者某些财务系统中，数字可能会使用 + 来代替 . （小数点），这通常是为了兼容一些老旧系统或特定的格式要求。这种情况并不是标准的科学计数法或浮动数字表示方式，但在某些特定环境下，+ 可能被用来作为分隔整数和小数部分的符号
        attach.put("price",13);
    }

    // 字段
    private String cardNo;
    private String cardName;
    private String type;
    private String price;
}
