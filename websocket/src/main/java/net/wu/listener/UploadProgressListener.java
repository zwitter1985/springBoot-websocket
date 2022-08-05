package net.wu.listener;

import net.wu.entity.ProgressEntity;
import org.apache.commons.fileupload.ProgressListener;

import javax.servlet.http.HttpSession;

/**
 * 上传进度监听器
 * @Author Wu Zihan
 * @Date 2022-08-04 17:29
 */
public class UploadProgressListener implements ProgressListener {

    private HttpSession session;

    public void setSession(HttpSession session) {
        this.session = session;
        // 保存上传状态
        ProgressEntity status = new ProgressEntity();
        session.setAttribute("status", status);
    }

    @Override
    public void update(long bytesRead, long contentLength, int items) {
        ProgressEntity status = (ProgressEntity) session.getAttribute("status");
        // 已读取数据长度
        status.setBytesRead(bytesRead);
        // 文件总长度
        status.setContentLength(contentLength);
        // 正在保存第几个文件
        status.setItems(items);

    }
}
