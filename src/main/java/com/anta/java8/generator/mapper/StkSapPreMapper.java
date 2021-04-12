package com.anta.java8.generator.mapper;

import com.anta.java8.generator.model.StkSapPre;

public interface StkSapPreMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StkSapPre record);

    int insertSelective(StkSapPre record);

    StkSapPre selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StkSapPre record);

    int updateByPrimaryKeyWithBLOBs(StkSapPre record);

    int updateByPrimaryKey(StkSapPre record);
}