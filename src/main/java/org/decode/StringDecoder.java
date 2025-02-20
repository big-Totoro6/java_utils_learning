package org.decode;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;

public class StringDecoder {

    // 公共的解码方法，传入 Class 类型参数来实例化目标对象
    public static <T> T decode(String destring, LinkedHashMap<String, Integer> attach, Class<T> clazz) throws Exception {
        // 将 destring 转换为 GBK 编码的字节数组
        byte[] byteArray = destring.getBytes("GBK");
        // 计算 attach 中所有字段字节长度的总和
        int totalLength = attach.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("attach总字节长度:"+totalLength);
        System.out.println("byteArray.length:"+byteArray.length);
        // 校验字节数组的总长度与字段总长度是否一致
        if (byteArray.length != totalLength) {
            throw new IllegalArgumentException("字节数组长度与字段定义的总字节长度不匹配！");
        }

        // 实例化目标对象
        T target = clazz.getDeclaredConstructor().newInstance();
        int startIndex = 0;

        for (String key : attach.keySet()) {
            int length = attach.get(key); // 获取字段对应的字节长度
            String value = extractValue(byteArray, startIndex, length);
            //如果要对加号处理的话
            value = value.replace('+', '.');
            setField(target, key, value);

            startIndex += length; // 更新下一个字段的起始位置
        }

        return target; // 返回赋值完的对象
    }

    // 从 byteArray 中提取值
    private static String extractValue(byte[] byteArray, int startIndex, int length) throws UnsupportedEncodingException {
        // 确保索引不会超出 byteArray 的范围
        int endIndex = Math.min(startIndex + length, byteArray.length);  // 修正 endIndex，确保不越界

        byte[] fieldBytes = new byte[endIndex - startIndex];
        System.arraycopy(byteArray, startIndex, fieldBytes, 0, fieldBytes.length);

        return new String(fieldBytes, "GBK").trim();
    }

    // 根据字段名设置目标对象的字段值
    private static <T> void setField(T target, String key, String value) throws NoSuchFieldException, IllegalAccessException {
        // 使用反射根据字段名称设置值
        java.lang.reflect.Field field = target.getClass().getDeclaredField(key);
        field.setAccessible(true);
        field.set(target, value);
    }
}
