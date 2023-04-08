package com.mr.reggie.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 员工实体类
 */
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

<<<<<<< HEAD
    private String name; //管理员
=======
    private String name;
>>>>>>> 3a7b85ab07acfb1fbe749b0c4fd94dcb68150d6a

    private String password;

    private String phone;

    private String sex;

    private String idNumber; //身份证

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

<<<<<<< HEAD
}
=======
}
>>>>>>> 3a7b85ab07acfb1fbe749b0c4fd94dcb68150d6a
