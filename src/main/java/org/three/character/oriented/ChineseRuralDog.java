package org.three.character.oriented;

import lombok.Data;

/**
 * 中华田园犬
 */
@Data
public class ChineseRuralDog extends Dog{
    /**
     * 子类重写父类方法 重写就是 方法名 方法签名都一样 子类重写父类方法 这是最典型的override
     */
    @Override
    public String whoAmI() {
        return  "i am ChineseRuralDog";
    }
}
