package com.damaha.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.damaha.dto.Response;
import com.damaha.dto.ResponseDTO;
import com.damaha.entity.Account;
import com.damaha.entity.City;
import com.damaha.entity.Staff;
import com.damaha.mapper.AccountMapper;
import com.damaha.mapper.StaffMapper;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.damaha.util.MD5Util.MD55;

@Service
public class AccountService extends ServiceImpl<AccountMapper, Account> {
    @Resource
    AccountMapper accountMapper;
    @Resource
    StaffMapper staffMapper;
    public ResponseDTO list(Integer current, Integer size, String name) {
        IPage<Account> config = new Page<>(current, size);
        IPage<Account> page = page(config);
        // 将响应数据填充到map中
        Map map = new HashMap();
        map.put("pages", page.getPages());
        map.put("total", page.getTotal());
        map.put("list", page.getRecords());
        return Response.success(map);
    }
    public ResponseDTO resetPwd(Integer id){
        Staff staff = staffMapper.selectById(id);
        staff.setPassword(MD55("123"));
        staffMapper.updateById(staff);
        accountMapper.deleteById(id);
        return Response.success("更新密码成功");
    }

    public ResponseDTO cancelApp(Integer id){
        accountMapper.deleteById(id);
        return Response.success("申请已退回");
    }
}
