package com.yonjar.demo.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author luoyj
 * @Date 2021/5/19.
 */
@Builder
@Data
@AllArgsConstructor
public class TeamworkEditMessage {

    private String appCode;

    private String appVersion;

    private String groupCode;

    private int type;

    private String domainCode;

    private String pageCode;

    private String workflowCode;

    private boolean haveLock;

    private int expireTime;

    private String loginId;

    private String loginName;
}
