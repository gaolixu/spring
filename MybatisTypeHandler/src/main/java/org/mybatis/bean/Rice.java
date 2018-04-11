package org.mybatis.bean;


public class Rice extends Food {
    
    private String way;

    @Override
    public String toString() {
        return "Rice{" +
                "way='" + way + '\'' +
                ",name="+name+"}";
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }
}
