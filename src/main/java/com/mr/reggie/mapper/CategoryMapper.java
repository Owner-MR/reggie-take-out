package com.mr.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mr.reggie.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
