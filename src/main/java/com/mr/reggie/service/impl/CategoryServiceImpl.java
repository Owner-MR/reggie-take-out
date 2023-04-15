package com.mr.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mr.reggie.common.CustomExeption;
import com.mr.reggie.entity.Category;
import com.mr.reggie.entity.Dish;
import com.mr.reggie.entity.Setmeal;
import com.mr.reggie.mapper.CategoryMapper;
import com.mr.reggie.service.CategoryService;
import com.mr.reggie.service.DishService;
import com.mr.reggie.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;
    @Autowired
    private SetMealService setMealService;
    @Override
    public void remove(Long id) {
        //查询当前分类id是否关联了菜品
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count = dishService.count(dishLambdaQueryWrapper);
        if (count > 0){
            //抛出异常
            throw new CustomExeption("删除失败，该分类已关联菜品");
        }
        //查询当前分类id是否关联了套餐
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = setMealService.count(setmealLambdaQueryWrapper);
        if (count2 > 0){
            //抛出异常
            throw new CustomExeption("删除失败，该分类已关联套餐");
        }
        //正常删除
        super.removeById(id);
    }
}
