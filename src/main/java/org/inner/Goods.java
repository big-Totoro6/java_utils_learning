package org.inner;

public class Goods {
    private String goodsName;
    /**
     * 内部类继承了接口
     * 私有内部类
     */
    private class Content implements Contents{
        private int i=11;
        private String goodsName;
        public int value(){
            return i;
        }
        public void getOutName(){
            //如果有重名变量 内部类可以这也访问 类名.this.属性名
            System.out.println(Goods.this.goodsName);
            System.out.println(goodsName);
        }
    }
    protected class GDestination implements Destination{
        private String label;
        //构造器 设置成private 设置为之能被当前内部类调用
        private GDestination(String whereTo){
            label=whereTo;
        }

        @Override
        public String readLabel() {
            return label;
        }
    }
    public Destination dest(String s){
        return new GDestination(s);
    }
    public Contents cont(){
        return new Content();
    }
}
