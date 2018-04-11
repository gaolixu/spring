package org.mybatis.db;

import java.util.List;

import org.mybatis.bean.City;


public interface CityMapper {
    List<City> findCityByPid(Long id);
}
