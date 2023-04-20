package com.mr.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mr.reggie.common.R;
import com.mr.reggie.dto.DishDto;
import com.mr.reggie.entity.Dish;
import com.mr.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        log.info("添加的菜品信息：{}", dishDto.toString());
        dishService.saveWithFlavor(dishDto);
        return R.success("新增菜品分类成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize){
        Page<Dish> dishPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Dish::getCategoryId);
        dishService.page(dishPage, queryWrapper);
        return R.success(dishPage);
    }
//    @GetMapping("/{id}")
//    public R<>
}
