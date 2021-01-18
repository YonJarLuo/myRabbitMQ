package com.yonjar.demo.cmd;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("消息实体")
@Data
public class MessageCmd {
    @ApiModelProperty("消息字段 context")
    private String context;
}
