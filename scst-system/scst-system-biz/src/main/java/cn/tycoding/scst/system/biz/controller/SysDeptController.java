package cn.tycoding.scst.system.biz.controller;

import cn.tycoding.scst.common.controller.BaseController;
import cn.tycoding.scst.common.utils.QueryPage;
import cn.tycoding.scst.common.utils.Result;
import cn.tycoding.scst.system.api.entity.SysDept;
import cn.tycoding.scst.system.biz.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author tycoding
 * @date 2019-06-03
 */
@RestController
@RequestMapping("/dept")
@Api(value = "SysDeptController", tags = {"部门管理接口"})
public class SysDeptController extends BaseController {

    @Autowired
    private SysDeptService sysDeptService;

    @PostMapping("/list")
    @ApiOperation(value = "分页、条件查询部门列表信息")
    @ApiImplicitParam(name = "dept", value = "查询条件", required = true, dataType = "SysDept", paramType = "body")
    public Result<Map> list(SysDept dept, QueryPage queryPage) {
        return new Result<>(this.selectByPageNumSize(queryPage, () -> sysDeptService.list(dept)));
    }

    @GetMapping("/tree")
    @ApiOperation(value = "获取部门Tree树")
    public Result<List> tree() {
        return new Result<>(sysDeptService.tree());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询详细部门信息", notes = "id存在且大于0")
    @ApiImplicitParam(name = "id", value = "部门ID", required = true, dataType = "Long")
    public Result<SysDept> findById(@PathVariable Long id) {
        if (id == null || id == 0) {
            return new Result<>();
        } else {
            return new Result<>(sysDeptService.findById(id));
        }
    }

    @PostMapping
    @ApiOperation(value = "添加部门")
    @ApiImplicitParam(name = "dept", value = "部门实体信息", required = true, dataType = "SysDept", paramType = "body")
    public Result add(@RequestBody SysDept dept) {
        sysDeptService.add(dept);
        return new Result();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除部门")
    @ApiImplicitParam(name = "id", value = "部门ID", required = true, dataType = "Long")
    public Result delete(@PathVariable Long id) {
        sysDeptService.delete(id);
        return new Result();
    }

    @PutMapping("/edit")
    @ApiOperation(value = "更新部门")
    @ApiImplicitParam(name = "dept", value = "部门实体信息", required = true, dataType = "SysDept", paramType = "body")
    public Result edit(@RequestBody SysDept dept) {
        sysDeptService.update(dept);
        return new Result();
    }
}