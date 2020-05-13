package com.anta.java8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述
 *
 * @author xiaowei
 * @date 2020-05-09
 **/
public class ListOptional {

    @Test
    public void testListOptional() {
        List<E3GoodsAreaAttr> goodsAreaAttrs = new ArrayList<>();
        E3GoodsAreaAttr e3GoodsAreaAttr = new E3GoodsAreaAttr();
        e3GoodsAreaAttr.setAreaId(1000101L);
        goodsAreaAttrs.add(e3GoodsAreaAttr);

        E3GoodsAreaAttr e3GoodsAreaAttr2 = new E3GoodsAreaAttr();
        e3GoodsAreaAttr2.setAreaId(1000102L);
        goodsAreaAttrs.add(e3GoodsAreaAttr2);


        Long areaId = 1000101L;
        if (goodsAreaAttrs != null && !goodsAreaAttrs.isEmpty()) {
            // 按区域过滤数据
            E3GoodsAreaAttr areaAttr = goodsAreaAttrs.stream().filter(a -> areaId.equals(a.getAreaId())).findAny().orElse(null);
            if (areaAttr != null) {
                System.out.println(areaAttr.getAreaId());
            }

        }
    }


    private class  E3GoodsAreaAttr{

        private Long areaId;

        public Long getAreaId() {
            return areaId;
        }

        public void setAreaId(Long areaId) {
            this.areaId = areaId;
        }
    }
}
