package org.deep.copy;

import lombok.Data;

import java.io.Serializable;

@Data
public class Son implements Serializable {
    public Son(String name) {
        this.name = name;
    }

    public Son() {
    }

    private String name;
}
