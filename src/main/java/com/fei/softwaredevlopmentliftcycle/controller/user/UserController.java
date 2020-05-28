package com.fei.softwaredevlopmentliftcycle.controller.user;

import com.fei.common.data.ApiResult;
import com.fei.softwaredevlopmentliftcycle.data.Role;
import com.fei.softwaredevlopmentliftcycle.data.User;
import com.fei.softwaredevlopmentliftcycle.model.bug.BugModel;
import com.fei.softwaredevlopmentliftcycle.model.role.RoleModel;
import com.fei.softwaredevlopmentliftcycle.model.user.CreateUserModel;
import com.fei.softwaredevlopmentliftcycle.model.user.UserModel;
import com.fei.softwaredevlopmentliftcycle.model.user.WebLoginTokenModel;
import com.fei.softwaredevlopmentliftcycle.service.UserService;
import com.fei.softwaredevlopmentliftcycle.util.DownloadExcel;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/12/15
 * @Description: User控制器
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据Token获取用户信息，用户名查询（后期，维护到Redis中）
     * @param token token值，也就是用户名
     * @return UserModel 用户信息
     */
    @GetMapping("/info")
    public ApiResult<UserModel> userInfo(String token) {
        if (StringUtils.isEmpty(token)) {
            return ApiResult.fail("token不能为空");
        }
        return ApiResult.ok(userService.userInfo(token));
    }

    /**
     * 登录获取token接口
     * @param user 接收参数的时候注意，需要使用对象接受，并且@RequestBody
     * @return WebLoginTokenModel token值（这里是用phone代替）
     */
    @PostMapping("/login")
    public ApiResult<WebLoginTokenModel> login(@RequestBody User user) {
        if (StringUtils.isEmpty(user.getPhone()) || StringUtils.isEmpty(user.getPassword())) {
            return ApiResult.fail(500, "用户名或密码，参数为空！！！");
        }
        WebLoginTokenModel webLoginTokenModel = new WebLoginTokenModel(
                userService.login(user.getPhone(), user.getPassword()));
        return ApiResult.ok(webLoginTokenModel);
    }

    /**
     * 登出接口
     * @param token post请求传递单参数，而且后端也是单参数接受，要以application/x-www-form-urlencoded发送
     */
    @PostMapping("/logout")
    public ApiResult<String> logout(String token) {
        if (StringUtils.isEmpty(token)) {
            return ApiResult.of(500, "请求失败", "token不存在！");
        }
        return ApiResult.ok();
    }

    /**
     * 查询所有的用户
     */
    @GetMapping("/all")
    public ApiResult<List<UserModel>> all(String status) {
        return ApiResult.ok(userService.all(status));
    }

    @GetMapping("/getProUser")
    public ApiResult<List<UserModel>> getProUser(Integer pid, String roleName) {
        if (pid == null) {
            return ApiResult.fail(500, "请求失败，参数为空");
        }
        return ApiResult.ok(userService.getProUser(pid, roleName));
    }

    @PostMapping("/delete")
    public ApiResult<String> delete(Integer uid) {
        if (uid == null) {
            return ApiResult.fail(500, "请求失败，参数为空");
        }
        userService.delete(uid);
        return ApiResult.ok("删除成功");
    }

    @GetMapping("/excel")
    public ResponseEntity downExcel(String name, Integer roleNum)
            throws UnsupportedEncodingException {
        List<UserModel> userModels = userService.all("");
        String[] titles = { "姓名", "手机号", "性别", "角色", "创建时间" };

        String[][] datas = new String[userModels.size()][titles.length];
        for (int i = 0, size = userModels.size(); i < size; i++) {
            // 赋值
            datas[i][0] = String.valueOf(userModels.get(i).getUsername());
            datas[i][1] = userModels.get(i).getPhone();
            datas[i][2] = userModels.get(i).getSex();
            datas[i][3] = userModels.get(i).getUserRoles().get(0);
            datas[i][4] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(userModels.get(i).getCreateTime());
        }

        String filename = "用户信息列表";
        HSSFWorkbook workbook = DownloadExcel.downloadExcel(filename, titles, datas);

        // 创建响应头
        HttpHeaders httpHeaders = new HttpHeaders();
        filename = URLEncoder.encode(filename + ".xls", "UTF-8");
        httpHeaders.add("Content-Disposition", "attachment;filename=" + filename);

        // 将byte数组、HttpHeader和HttpStatus传入ResponseEntity
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(workbook.getBytes(),
                httpHeaders, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/roles")
    public ApiResult<List<Role>> roles() {
        return ApiResult.ok(userService.roles());
    }

    @PostMapping("/edit")
    public ApiResult<String> editRole(@RequestBody RoleModel roleModel) {
        if (roleModel.getId() == null || StringUtils.isEmpty(roleModel.getRoleName())) {
            return ApiResult.fail(500, "请求失败，参数为空");
        }
        userService.editRole(roleModel);
        return ApiResult.ok("角色修改成功");
    }

    @PostMapping("/create")
    public ApiResult<Integer> create(@RequestBody CreateUserModel userModel) {
        if (StringUtils.isEmpty(userModel.getUsername()) || userModel.getRoleId() == null) {
            return ApiResult.fail(500, "请求失败，参数为空");
        }
        return ApiResult.ok(userService.create(userModel));
    }
}
