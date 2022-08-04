package net.wu.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * fastjson转换
 * @Author Wu Zihan
 * @Date 2022-08-04 10:07
 */
@Configuration
public class FastjsonConverter {

    @Bean
    public HttpMessageConverters customConverters(){
        // 定义转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        // 添加fastjson的配置信息，如：是否要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

        List<MediaType> fastMediaTypes = new ArrayList<>();

        // 处理中文乱码问题1
        // 处理中文乱码问题2
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        // 在转换器中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);

        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setDefaultCharset(Charset.forName("UTF-8"));
        stringConverter.setSupportedMediaTypes(fastMediaTypes);

        // 将转换器添加到converters中
        return new HttpMessageConverters(stringConverter, fastConverter);
    }
}
