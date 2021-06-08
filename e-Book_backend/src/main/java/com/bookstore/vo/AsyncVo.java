package com.bookstore.vo;


import org.springframework.web.context.request.async.DeferredResult;

/**
 * 存储异步处理信息
 *
 * @author Logen
 *
 * @param <I> 接口输入参数
 * @param <O> 接口返回参数
 */
public class AsyncVo<I, O> {

    /**
     * 请求参数
     */
    private I params;

    /**
     * 响应结果
     */
    private DeferredResult<O> result;

    public I getParams() {
        return params;
    }

    public void setParams(I params) {
        this.params = params;
    }

    public DeferredResult<O> getResult() {
        return result;
    }

    public void setResult(DeferredResult<O> result) {
        this.result = result;
    }

}
