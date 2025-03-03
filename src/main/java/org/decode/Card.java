package org.decode;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Card {
    private String cardNo;
    private String cardName;
    private BigDecimal price;
    private BigDecimal amt;


    @Override
    public String toString() {
        return "CardNo: " + cardNo + ", CardName: " + cardName + ", Price: " + price + ", Amt: " + amt;
    }
}
