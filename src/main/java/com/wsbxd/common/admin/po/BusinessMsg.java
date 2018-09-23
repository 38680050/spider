package com.wsbxd.common.admin.po;

import com.wsbxd.common.utils.BusinessCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * description: 业务消息返回通用类
 *
 * @author chenhaoxuan
 * @date 2018/9/23 20:31
 */
@Setter
@Getter
@ApiModel(value = "接口返回对象",description = "接口返回对象")
public class BusinessMsg {

    /**
     * 返回状态码
     */
    @ApiModelProperty(value = "返回状态码",name = "code")
    private String code;

    /**
     * 返回描述
     */
    @ApiModelProperty(value = "返回描述",name = "msg")
    private String msg;

    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回数据",name = "data")
    private Object  data;

    public BusinessMsg(BusinessCode bussinessCode, Object data) {
        this.code = bussinessCode.getCode();
        this.msg = bussinessCode.getMsg();
        this.data = data;
    }

    public BusinessMsg(String code, String msg, Object  data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BusinessMsg(String code, String  msg) {
        this.code = code;
        this.msg = msg;
    }
}
