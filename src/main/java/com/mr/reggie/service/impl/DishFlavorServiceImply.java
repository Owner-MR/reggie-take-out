package com.mr.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mr.reggie.entity.Dish;
import com.mr.reggie.entity.DishFlavor;
import com.mr.reggie.mapper.DishFlavorMapper;
import com.mr.reggie.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImply extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
