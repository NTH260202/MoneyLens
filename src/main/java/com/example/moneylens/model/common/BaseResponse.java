package com.example.moneylens.model.common;

import com.example.moneylens.common.constant.AppCode;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseResponse<T> {

    private AppCode code;
    private String message;
    private T data;

    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> res = new BaseResponse<T>() {};
        res.setCode(AppCode.SUCCESS);
        res.setData(data);
        res.setMessage("Success");
        return res;
    }

    public static <T> BaseResponse<T> success() {
        BaseResponse<T> res = new BaseResponse<T>() {};
        res.setCode(AppCode.SUCCESS);
        res.setMessage("Success");
        return res;
    }
}
