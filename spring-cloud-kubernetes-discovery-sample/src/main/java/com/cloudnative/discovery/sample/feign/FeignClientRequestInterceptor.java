package com.cloudnative.discovery.sample.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Component
public class FeignClientRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        log.debug("feign request url={} method={} queries={} headers={}", template.url(), template.method(), template.queries(), template.headers());
        try {
            /**
             * 开启了hystrix线程隔离策略后,这里request就无法拿到了,因为已经脱离了上层的web上下文了
             * 1.通过feign.hystrix.enabled: false参数来关闭hystrix,此时就可以拿到request信息了
             * 2.也可以通过设置hystrix为信号量模式，来获取request信息
             * 3.通过自定义hystrix策略来传递上下文信息 https://www.cnblogs.com/huanchupkblog/p/11895979.html
             */
            log.debug("feign request attribute={}", RequestContextHolder.currentRequestAttributes());
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            log.debug("feign request get http request={}", servletRequestAttributes.getRequest());
        } catch (Exception e) {
            log.error("获取request信息异常:", e);
        }
    }

}