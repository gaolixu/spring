package org.mybatis.db;

import java.util.List;

import org.mybatis.bean.Noodle;


public interface NoodleMapper {
    List<Noodle> findNoodleByArea();
}
