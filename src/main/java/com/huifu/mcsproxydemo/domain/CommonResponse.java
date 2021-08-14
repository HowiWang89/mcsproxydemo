package com.huifu.mcsproxydemo.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回实体类
 */
@Data
public class CommonResponse implements Serializable {

    //共通:数据体
    protected String data;
    //共通:签名
    protected String sign;
    //共通:返回码
    protected String resp_code;
    //共通:返回描述
    protected String resp_desc;
}
