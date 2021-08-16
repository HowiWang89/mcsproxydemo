package com.huifu.mcsproxydemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huifu.mcsproxydemo.domain.CommonRequest;
import com.huifu.mcsproxydemo.domain.CommonResponse;
import com.huifu.mcsproxydemo.utils.CommonStringUtils;
import com.huifu.mcsproxydemo.utils.RSAUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.SortedMap;

public class DemoBizScan {

    private static final String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzOBw+l+jvjjDfKJO54NBNhehz9HZu0ZJN/6RF4kJXpMx62qhFckvf1VyuTC6LtPbwPVF+rKEgN7RVprpQ+a3o2K4Kuxwd4BG2aoCsefNMLzCEB7H/7BolW87mDyQ3GS2U0hD4tGtck5+0lpnLOZdAasRdXRiGbC+16a76ryIkysy5w2lTSFzQ1DLa3p7bDjjk99ZphAt0LBhzommbxcCVCpj659EUqFwgs7ubLMS0lBGuvcGlBZh2Yz8QrYnsIQLIcV9sKIGp6EGGYWEkzC8QH+UbOwaqluZU5X5CTpjUs2i9ao9qKh9doeOFdEXNU7ErqzL/Tsm7hAh8QpVffQ82wIDAQAB";
    private static final String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDM4HD6X6O+OMN8ok7ng0E2F6HP0dm7Rkk3/pEXiQlekzHraqEVyS9/VXK5MLou09vA9UX6soSA3tFWmulD5rejYrgq7HB3gEbZqgKx580wvMIQHsf/sGiVbzuYPJDcZLZTSEPi0a1yTn7SWmcs5l0BqxF1dGIZsL7XprvqvIiTKzLnDaVNIXNDUMtrentsOOOT31mmEC3QsGHOiaZvFwJUKmPrn0RSoXCCzu5ssxLSUEa69waUFmHZjPxCtiewhAshxX2woganoQYZhYSTMLxAf5Rs7BqqW5lTlfkJOmNSzaL1qj2oqH12h44V0Rc1TsSurMv9OybuECHxClV99DzbAgMBAAECggEBAKrO6IJloE+kZZ8QdvZfoMunUjnAfGW9SKgNbgwQLSaTU8gxAxH1xMrFg7tQG7mU2h76nphPo1cJ7b/lsVryDOKEGIV40J9g7DDmpA6M8q6SgL0N3LKY8ei8bxQSUrfco4G1uxtsss2dEfDwfBqcCKiuZgTlvjArAkEq+nrPKpJ+rpmhFS16LBwlKZahGVtRW7OyNndkYf37NB7HsnTDoRGLjXgD437XJE0+rqJOkXeqhxvi6b3Xu4bpft3UAS9RCMkQoOnSo0d/FY85xxkZivGHzJgXNjmK9gm22dGmWbWsjzPjxKjx0KVVorDS3wBXoyEHLmXgkncRIlaYtglpgJkCgYEA/mwgK0l+1n7r54oYLFYpgxmaXg+rfR532XIQWVn6GBBMH2U6f2KQo2T4D7jj3nAnRp9qCFUaF98cCJXTQVH7O3arL7XCfn1976hiVXOShl60y2PFzsBWBISPXus+tKV/E2IuBmjXGoR3uoEmfqi0QNiUhcVt33OtBgy0scOGeBcCgYEAziWqg+b5PaPbrYIs8FL1/eh2oJ6crrLTBnO5H4U9uOsyMM+vH2EcCTG/S0jCPdBYJk4O491ArbTefsL2zFwUCzHSZkHPiSqnygJcgdadBl9rlFc4vIReY21bSUnoMcpNjKVMge36Q1Gv++GqqFoL1NZZ3wO/k+psgqyxEyrfl90CgYA74LaxeVqgoL5kf9uwk5axhBUQjh+OEt2iD3ZJkSOLBkrkGK7rzAa+bcTz7k+yyjJ0LJCH5X9+nsO6QYi2ltTJ6ePgFqbvnhlFjhuWgNy7Qw7Jz3F+d0XHSmtzq2nWmhyOM00htQrq8sI64qpspMfHezF5Sj3RmSffhJ4COEItgQKBgFVMxKfbtRpKaSX8jXfL/DnaLOv9dbvcBvHOhLWEPqv7tkL4TPBhEGbFjPAr5SQKx87h0ct7y72U79Pn/JNHLowLS5JAfQ229sLiA0E+eq8eYLd69+EZrKzbnUBQMqIXocsmwueofzvH21gQYrtStz6Ui496T5V3FPtlVcgmn2qJAoGBAKtUga7JbdjhNwlVAhS11NQfKrFOwEcHFqcx5dKj/r50ncn2Zq74Qp+iwRonqaWQBe/uBT5xnTtCSco8yygaXUoXtdbemO9iFQSnE52Eq37La4ZMLNQ9MKTxjFPwtpo+sIt96GRW+w2D5hMgE8L5eGhwvR3YTVbtc9wmxOMRb+Q/";
    private static final String uri = "https://mcspptest.cloudpnr.com/api/mcsproxypay/v1/bizscan/";

    /**
     * http调用demo
     * 注意事项：1.request中data对象需要排序后加密
     * 2.返回response中的sign对象解密后用于验证参数是否被篡改
     */
    public static void main(String[] args) throws Exception {
        String data = "{\"api_version\":\"20\",\"cust_id\":\"6666000001036124\",\"order_id\":\"wxscan20200109110811223278983\",\"device_id\":\"SMSM34358719666880610\",\"trans_amt\":\"0.01\",\"auth_code\":\"134538774437953536\",\"ip_addr\":\"47.98.20.125\",\"oper_user_id\":182,\"goods_desc\":\"商品\",\"os_version\":\"\",\"soft_version\":\"\",\"longitude\":\"\",\"latitude\":\"\",\"imei\":\"\",\"mac_ip\":\"\",\"base_station_info\":\"\",\"bg_ret_url\":\"http://firstpay.yunsoyi.cn/api/huipay/pay_notify_url\",\"mer_priv\":\"\",\"extension\":\"\",\"hb_fq_num\":\"\"}";
        SortedMap sortMap = CommonStringUtils.getSortMap(JSONObject.parseObject(data));
        CommonRequest request = new CommonRequest();
        request.setMer_cust_id("6666000000929806");
        request.setSource_num("DJS");
        request.setSign_type("RSA2");
        request.setData(JSON.toJSONString(sortMap));
        request.setSign(RSAUtils.rsaSign(request.getSign_type(), request.getData(), privateKey));
        sendRequest(request);
    }

    private static void sendRequest(CommonRequest request) {
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(uri);
        // 将request对象转换为json字符串，并放入entity中
        StringEntity entity = new StringEntity(JSON.toJSONString(request), "UTF-8");
        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        // 响应模型
        HttpResponse response;
        try {
            // 由客户端执行(发送)Post请求
            response = client.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                String content = EntityUtils.toString(responseEntity);
                System.out.println("响应内容为:" + content);
                CommonResponse commonResponse = JSON.parseObject(content, CommonResponse.class);
                if (StringUtils.isNotEmpty(commonResponse.getData())) {
                    //验签
                    String result = RSAUtils.rsaCheck("RSA2", commonResponse.getSign(), commonResponse.getData(), publicKey);
                    if (StringUtils.isNotEmpty(result)) {
                        throw new Exception("数据被篡改");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
