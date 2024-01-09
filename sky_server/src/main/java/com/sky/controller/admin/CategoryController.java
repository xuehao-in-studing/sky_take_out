package com.sky.controller.admin;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 分类管理
 */
@RestController
@RequestMapping("/admin/category")
@Slf4j
@Tag(name = "分类管理",description = "分类相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类，默认数据格式为json数据，因为加了@RequestBody注解
     * @param categoryDTO
     * @return
     */
    @PostMapping
    @Operation(summary = "新增分类",description = "新增分类")
    public Result<String> save(@RequestBody CategoryDTO categoryDTO){
        log.info("新增分类：{}", categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }

    /**
     * 分类分页查询，默认数据格式为表单数据
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "分类分页查询",description = "分类分页查询")
    @Parameters({
            @Parameter(name = "page", description = "页码", required = true),
            @Parameter(name = "pageSize", description = "每页记录数", required = true),
            @Parameter(name = "name", description = "分类名称", required = false),
            @Parameter(name = "type", description = "分类类型 1菜品分类  2套餐分类", required = false)
    })
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分页查询：{}", categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @DeleteMapping
    @Operation(summary = "删除分类",description = "删除分类")
    public Result<String> deleteById(Long id){
        log.info("删除分类：{}", id);
        int deleteCount = categoryService.deleteById(id);
        return Result.success(Integer.toString(deleteCount));
    }

    /**
     * 修改分类
     * @param categoryDTO
     * @return
     */
    @PutMapping
    @Operation(summary = "修改分类",description = "修改分类")
    public Result<String> update(@RequestBody CategoryDTO categoryDTO){
        int count = categoryService.update(categoryDTO);
        return Result.success(Integer.toString(count));
    }

    /**
     * 启用、禁用分类
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @Operation(summary = "启用禁用分类",description = "启用禁用分类")
    @Parameters({
            @Parameter(name = "status", description = "状态 0禁用 1启用", required = true),
            @Parameter(name = "id", description = "分类id", required = true)
    })
    public Result<String> startOrStop(@PathVariable("status") Integer status, Long id){
        categoryService.startOrStop(status,id);
        return Result.success();
    }

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    @GetMapping("/list")
    @Operation(summary = "根据类型查询分类",description = "根据类型查询分类")
    public Result<List<Category>> list(Integer type){
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }
}
