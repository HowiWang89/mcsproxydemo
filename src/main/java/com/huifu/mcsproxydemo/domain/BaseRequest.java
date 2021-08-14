package com.huifu.mcsproxydemo.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通用请求数据体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseRequest implements Serializable {
    private static final long serialVersionUID = -1006636957438181677L;
    @JSONField(name = "source_name")
    private String sourceName;
    @JSONField(name = "mer_cust_id")
    private String merCustId;
}
