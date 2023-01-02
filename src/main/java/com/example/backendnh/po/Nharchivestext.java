package com.example.backendnh.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("nh_archives_text")
public class Nharchivestext implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    @TableField("daid")
    Integer daid;

    @TableField("damc")
    String damc;

    @TableField("dayw")
    String dayw;
}
