package com.mr.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mr.reggie.dto.DishDto;
import com.mr.reggie.entity.Dish;
import org.springframework.stereotype.Service;


public interface DishService extends IService<Dish> {
    public void saveWithFlavor(DishDto dishDto);
}
