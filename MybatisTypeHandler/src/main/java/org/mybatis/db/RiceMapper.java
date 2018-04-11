package org.mybatis.db;

import java.util.List;

import org.mybatis.bean.Rice;


public interface RiceMapper {
    List<Rice> findRiceByArea();
}
