package com.sky.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {

    //主键
    @Schema(description = "主键", example = "1")
    private Long id;

    //类型 1 菜品分类 2 套餐分类
    @Schema(description = "类型 1 菜品分类 2 套餐分类", example = "1")
    private Integer type;

    //分类名称
    @Schema(description = "分类名称", example = "传统主食")
    private String name;

    //排序
    @Schema(description = "前端展示排序", example = "1")
    private Integer sort;

}
