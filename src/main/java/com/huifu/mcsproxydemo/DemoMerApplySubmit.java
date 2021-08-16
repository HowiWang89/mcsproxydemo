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

public class DemoMerApplySubmit {

    private static final String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzOBw+l+jvjjDfKJO54NBNhehz9HZu0ZJN/6RF4kJXpMx62qhFckvf1VyuTC6LtPbwPVF+rKEgN7RVprpQ+a3o2K4Kuxwd4BG2aoCsefNMLzCEB7H/7BolW87mDyQ3GS2U0hD4tGtck5+0lpnLOZdAasRdXRiGbC+16a76ryIkysy5w2lTSFzQ1DLa3p7bDjjk99ZphAt0LBhzommbxcCVCpj659EUqFwgs7ubLMS0lBGuvcGlBZh2Yz8QrYnsIQLIcV9sKIGp6EGGYWEkzC8QH+UbOwaqluZU5X5CTpjUs2i9ao9qKh9doeOFdEXNU7ErqzL/Tsm7hAh8QpVffQ82wIDAQAB";
    private static final String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDM4HD6X6O+OMN8ok7ng0E2F6HP0dm7Rkk3/pEXiQlekzHraqEVyS9/VXK5MLou09vA9UX6soSA3tFWmulD5rejYrgq7HB3gEbZqgKx580wvMIQHsf/sGiVbzuYPJDcZLZTSEPi0a1yTn7SWmcs5l0BqxF1dGIZsL7XprvqvIiTKzLnDaVNIXNDUMtrentsOOOT31mmEC3QsGHOiaZvFwJUKmPrn0RSoXCCzu5ssxLSUEa69waUFmHZjPxCtiewhAshxX2woganoQYZhYSTMLxAf5Rs7BqqW5lTlfkJOmNSzaL1qj2oqH12h44V0Rc1TsSurMv9OybuECHxClV99DzbAgMBAAECggEBAKrO6IJloE+kZZ8QdvZfoMunUjnAfGW9SKgNbgwQLSaTU8gxAxH1xMrFg7tQG7mU2h76nphPo1cJ7b/lsVryDOKEGIV40J9g7DDmpA6M8q6SgL0N3LKY8ei8bxQSUrfco4G1uxtsss2dEfDwfBqcCKiuZgTlvjArAkEq+nrPKpJ+rpmhFS16LBwlKZahGVtRW7OyNndkYf37NB7HsnTDoRGLjXgD437XJE0+rqJOkXeqhxvi6b3Xu4bpft3UAS9RCMkQoOnSo0d/FY85xxkZivGHzJgXNjmK9gm22dGmWbWsjzPjxKjx0KVVorDS3wBXoyEHLmXgkncRIlaYtglpgJkCgYEA/mwgK0l+1n7r54oYLFYpgxmaXg+rfR532XIQWVn6GBBMH2U6f2KQo2T4D7jj3nAnRp9qCFUaF98cCJXTQVH7O3arL7XCfn1976hiVXOShl60y2PFzsBWBISPXus+tKV/E2IuBmjXGoR3uoEmfqi0QNiUhcVt33OtBgy0scOGeBcCgYEAziWqg+b5PaPbrYIs8FL1/eh2oJ6crrLTBnO5H4U9uOsyMM+vH2EcCTG/S0jCPdBYJk4O491ArbTefsL2zFwUCzHSZkHPiSqnygJcgdadBl9rlFc4vIReY21bSUnoMcpNjKVMge36Q1Gv++GqqFoL1NZZ3wO/k+psgqyxEyrfl90CgYA74LaxeVqgoL5kf9uwk5axhBUQjh+OEt2iD3ZJkSOLBkrkGK7rzAa+bcTz7k+yyjJ0LJCH5X9+nsO6QYi2ltTJ6ePgFqbvnhlFjhuWgNy7Qw7Jz3F+d0XHSmtzq2nWmhyOM00htQrq8sI64qpspMfHezF5Sj3RmSffhJ4COEItgQKBgFVMxKfbtRpKaSX8jXfL/DnaLOv9dbvcBvHOhLWEPqv7tkL4TPBhEGbFjPAr5SQKx87h0ct7y72U79Pn/JNHLowLS5JAfQ229sLiA0E+eq8eYLd69+EZrKzbnUBQMqIXocsmwueofzvH21gQYrtStz6Ui496T5V3FPtlVcgmn2qJAoGBAKtUga7JbdjhNwlVAhS11NQfKrFOwEcHFqcx5dKj/r50ncn2Zq74Qp+iwRonqaWQBe/uBT5xnTtCSco8yygaXUoXtdbemO9iFQSnE52Eq37La4ZMLNQ9MKTxjFPwtpo+sIt96GRW+w2D5hMgE8L5eGhwvR3YTVbtc9wmxOMRb+Q/";

    private static final String uri = "https://mcsmtest.cloudpnr.com/api/mcsproxy/v1/mer/merApplySubmit";

    /**
     * http调用demo
     * 注意事项：1.request中data对象需要排序后加密
     * 2.返回response中的sign对象解密后用于验证参数是否被篡改
     */
    public static void main(String[] args) throws Exception {
        String data1 = "1";
        String data2 = "2";
        String data3 = "3";
        System.out.println(data1 + data2 + data3);
        String data = "{\"login_name\":\"张双双\",\"login_mobile\":\"13801543436\",\"social_credit_code\":\"91320508MA1YKCRJ9A\",\"license_start_date\":\"2019JunWed\",\"license_end_date\":\"99991231\",\"legal_name\":\"张双双\",\"legal_id_card_type\":\"10\",\"legal_id_card\":\"32032119950126042X\",\"legal_mobile\":\"13801543436\",\"legal_cert_start_date\":\"2012SepMon\",\"legal_cert_end_date\":\"2022SepSat\",\"settlement_bank_id\":\"03080000\",\"settlement_bank_branch\":\"招商银行\",\"settlement_card_no\":\"6214835104407968\",\"settlement_name\":\"张双双\",\"settlement_card_type\":\"11\",\"settlement_card_province\":\"江苏省\",\"settlement_card_city\":\"苏州市\",\"prov_code\":\"320000\",\"city_code\":\"320500\",\"service_phone\":\"13801543436\",\"district_code\":\"吴中区\",\"apply_id\":\"io1Qhj55774Ab4FWPn3Ceyeg872H86\",\"corp_name\":\"优品生活超市\",\"corp_license_type\":\"1\",\"corp_business_address\":\"江苏省苏州市吴中区优步花园1幢101\",\"corp_reg_address\":\"江苏省苏州市吴中区优步花园1幢101\",\"corp_fixed_telephone\":\"13801543436\",\"business_scope\":\"超市便利店\",\"contact_name\":\"张双双\",\"contact_email\":\"1319914722@qq.com\",\"contact_mobile\":\"13801543436\",\"controlling_shareholder_list\":{\"cust_name\":\"张双双\",\"id_card_type\":\"10\",\"id_card\":\"32032119950126042X\",\"ratio\":\"100\",\"shareholder_addr\":\"江苏省苏州市吴中区优步花园1幢101\"},\"corp_deal_type\":\"1\",\"corp_short_name\":\"优品生活\",\"business_type\":\"3\",\"cup_mcc_code\":\"5411\",\"agent_stat\":\"0\",\"business_time\":\"08:00-18:00:00\",\"trade_route\":\"\",\"mcs_lm_type\":\"\",\"settlement_bank_code\":\"\",\"settlement_card_address\":\"\",\"settlement_credential_phone\":\"\",\"settlement_card_bank_number\":\"308302007025\",\"settlement_card_bank\":\"招商银行\",\"settlement_bank_channel_no\":\"\",\"industry\":\"\",\"reg_channel\":\"01\",\"pay_way\":\"0\",\"rate_type\":\"02\",\"ali_pay_category\":\"超市便利店\",\"we_chat_category\":\"线下零售超市\",\"cls_id\":\"5411\",\"model_type\":\"1\",\"contact_phone\":\"\",\"agent_id\":\"\",\"agent_name\":\"\",\"shop_name\":\"\",\"shop_address\":\"\",\"shop_prov_code\":\"\",\"shop_city_code\":\"\",\"shop_district_code\":\"\",\"shop_contact\":\"\",\"shop_phone\":\"\",\"contract_sign_date\":\"\",\"contract_sign_manager\":\"\",\"contract_effect_date\":\"\",\"un_legal_type\":\"2\",\"contact_type\":\"01\",\"corp_channel\":\"02\",\"file_tokens\":[{\"attach_desc\":\"法人身份证正面\",\"attach_name\":\"法人身份证正面\",\"attach_type\":\"05\",\"file_id\":\"134adeff-7ade-36bd-abf9-aeccb8cc4d40\"},{\"attach_desc\":\"法人身份证反面\",\"attach_name\":\"法人身份证反面\",\"attach_type\":\"06\",\"file_id\":\"6e8e0bca-041a-3328-8539-edaa51d8fd57\"},{\"attach_desc\":\"门头照片\",\"attach_name\":\"门头照片\",\"attach_type\":\"15\",\"file_id\":\"53793666-2a9d-3063-89f6-e536e71559f9\"},{\"attach_desc\":\"内景照\",\"attach_name\":\"内景照\",\"attach_type\":\"17\",\"file_id\":\"0970c425-ae82-3e58-804d-b213ed7b2027\"},{\"attach_desc\":\"收银台\",\"attach_name\":\"收银台\",\"attach_type\":\"16\",\"file_id\":\"d10d4955-fca5-3e50-bc08-0b00ae5251f5\"},{\"attach_desc\":\"门店内全景照片\",\"attach_name\":\"门店内全景照片\",\"attach_type\":\"17\",\"file_id\":\"7170c435-2327-3255-a2d6-0b174ac0dcec\"},{\"attach_desc\":\"银行卡正面\",\"attach_name\":\"银行卡正面\",\"attach_type\":\"18\",\"file_id\":\"30d3e510-6cbc-31a9-8342-1b76ae53e49b\"},{\"attach_desc\":\"银行卡反面\",\"attach_name\":\"银行卡反面\",\"attach_type\":\"19\",\"file_id\":\"230efa90-0254-3a5a-bf99-453bab3eba40\"}]}";
        JSONObject jsonObject = JSONObject.parseObject(data);
        SortedMap sortMap = CommonStringUtils.getSortMap(jsonObject);
        CommonRequest request = new CommonRequest();
        request.setMer_cust_id("6666000000929806");
        request.setSource_num("DJS");
        request.setSign_type("RSA2");
        request.setData(jsonObject.toJSONString());
        //request.setSign(RSAUtils.rsaSign(request.getSign_type(), request.getData(), privateKey));
        request.setSign(RSAUtils.rsaSign(request.getSign_type(), JSON.toJSONString(sortMap), privateKey));

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
