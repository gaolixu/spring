package org.mybatis.db;

import org.mybatis.bean.Alias;


public interface AliasMapper {
    Alias findAliasByPid(Long id);
}
