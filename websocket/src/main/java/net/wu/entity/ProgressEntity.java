package net.wu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件上传实体类
 * @Author Wu Zihan
 * @Date 2022-08-04 17:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgressEntity {
    private Long bytesRead;
    private Long contentLength;
    private Integer items;
    // 开始上传时间，用于计算上传速率
    private Long startTime = System.currentTimeMillis();

}
