package com.sky.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Tag(name = "分类", description = "分类")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "菜品id")
    private Long id;

    //类型: 1菜品分类 2套餐分类
    @Schema(description = "分类 1菜品分类 2套餐分类", example = "1")
    private Integer type;

    //分类名称
    @Schema(description = "分类名称", example = "传统主食")
    private String name;

    //顺序
    @Schema(description = "顺序", example = "1")
    private Integer sort;

    //分类状态 0标识禁用 1表示启用
    @Schema(description = "分类状态 0标识禁用 1表示启用", example = "0",defaultValue = "0")
    private Integer status;

    //创建时间
    @Schema(description = "创建时间", example = "2020-01-01 00:00:00")
    private LocalDateTime createTime;

    //更新时间
    @Schema(description = "更新时间", example = "2020-01-01 00:00:00")
    private LocalDateTime updateTime;

    //创建人
    @Schema(description = "创建人", example = "1")
    private Long createUser;

    //修改人
    @Schema(description = "修改人", example = "1")
    private Long updateUser;
}
