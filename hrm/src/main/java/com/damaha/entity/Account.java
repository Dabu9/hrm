package com.damaha.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.damaha.annotation.ExcelColumn;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("acc_mgt")
@ApiModel(value = "账户管理对象", description = "")
public class Account {
    @ApiModelProperty("员工id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ExcelColumn("姓名")
    @ApiModelProperty("员工姓名")
    @TableField("name")
    private String name;

    @ExcelColumn("是否忘记密码")
    @ApiModelProperty("忘记密码标志")
    @TableField("is_forget")
    private Integer isForget;
}
