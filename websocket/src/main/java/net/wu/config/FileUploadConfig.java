package net.wu.config;

import net.wu.util.FileUpLoad;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件上传配置类
 * @Author Wu Zihan
 * @Date 2022-08-04 17:14
 */
@Configuration
public class FileUploadConfig {
    @Bean
    public FileUpLoad fileUpLoad(){
        return new FileUpLoad();
    }
}
