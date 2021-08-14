package com.huifu.mcsproxydemo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 数据体公共参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonRequest implements Serializable {
    private static final long serialVersionUID = 1760413539045142634L;
    //共通:数据体
    protected String data;
    //共通:签名
    protected String sign;
    //共通:签名类型（目前仅支持RSA2）
    protected String sign_type;
    //共通:来源编号
    protected String source_num;
    //共通:大商户号
    protected String mer_cust_id;
}
