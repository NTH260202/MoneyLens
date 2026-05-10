package com.example.moneylens.model.common;

import com.example.moneylens.common.constant.MoneyLensCode;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseResponse<T> {

    private String code;
    private String message;
    private T data;

    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> res = new BaseResponse<>() {
        };
        res.setCode(MoneyLensCode.SUCCESS.getResponseCode());
        res.setMessage(MoneyLensCode.SUCCESS.getMessageCode());
        res.setData(data);
        return res;
    }

    public static <T> BaseResponse<T> success() {
        BaseResponse<T> res = new BaseResponse<>() {
        };
        res.setCode(MoneyLensCode.SUCCESS.getResponseCode());
        res.setMessage(MoneyLensCode.SUCCESS.getMessageCode());
        return res;
    }
}
