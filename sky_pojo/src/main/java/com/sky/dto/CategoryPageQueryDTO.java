package com.sky.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.io.Serializable;

@Data
@Tag(name = "分类分页查询参数", description = "分类分页查询参数")
public class CategoryPageQueryDTO implements Serializable {

    //页码
    @Schema(description = "页码", example = "1")
    private int page;

    //每页记录数
    @Schema(description = "每页记录数", example = "10")
    private int pageSize;

    //分类名称
    @Schema(description = "分类名称", example ="传统主食")
    private String name;

    //分类类型 1菜品分类  2套餐分类
    @Schema(description = "分类类型 1菜品分类  2套餐分类", example = "1")
    private Integer type;

}
