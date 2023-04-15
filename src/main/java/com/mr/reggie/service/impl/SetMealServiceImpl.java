package com.mr.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mr.reggie.entity.Setmeal;
import com.mr.reggie.mapper.SetMealMapper;
import com.mr.reggie.service.SetMealService;
import org.springframework.stereotype.Service;

@Service
public class SetMealServiceImpl extends ServiceImpl<SetMealMapper, Setmeal> implements SetMealService {
}
