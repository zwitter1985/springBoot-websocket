package net.wu.util;

import net.wu.entity.ProgressEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传通用工具
 * @Author Wu Zihan
 * @Date 2022-08-04 17:41
 */
public class FileUploadCommonTool {
    public static Map<String, Object> upload(MultipartFile Fdata, String Sid, HttpServletRequest request) {
        String infilename = Fdata.getOriginalFilename();
        String endstring = infilename.substring(infilename.lastIndexOf("."));
        //这里写自己的文件名和文件夹即可
        String fromString = SystemDateFormat.SdfForTimeString(new Date());
//        String fromstring = SystemDateFormat.SdfForTimeString.format(new Date());
        String path = "C:\\Users\\Administrator\\Desktop\\专用\\" + fromString + endstring;
        Map<String, Object> map = new HashMap<>(1);
        InputStream fis = null;
        FileOutputStream fos = null;
        try {
            File fileo = new File(path);
            if (!fileo.exists()) {
                fileo.createNewFile();
            }
            fos = new FileOutputStream(fileo);
            fis = Fdata.getInputStream();
            byte[] bytes = new byte[1024];
            int aa = 0;
            while (true) {
                aa = fis.read(bytes, 0, bytes.length);
                if (aa == -1) {
                    break;
                }
                fos.write(bytes, 0, aa);
            }
        } catch (Exception e) {
            map.put("issuccess", false);

        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static Map<String, Object> getUploadInfo(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        ProgressEntity status = (ProgressEntity) request.getSession(true)
                // 从session中读取上传信息
                .getAttribute("status");
        if (status == null) {
            result.put("error", "没发现上传文件!");
            return result;
        }
        // 上传开始时间
        long startTime = status.getStartTime();
        // 现在时间
        long currentTime = System.currentTimeMillis();
        // 已经传顺的时间 单位：s
        long time = (currentTime - startTime) / 1000 + 1;
        // 传输速度：byte/s
        double velocity = status.getBytesRead() / time;
        // 估计总时间
        double totalTime = status.getContentLength() / velocity;
        // 估计剩余时间
        double timeLeft = totalTime - time;
        int percent = (int) (100 * (double) status.getBytesRead() / (double) status
                .getContentLength()); // 百分比
        // 已完成数
        double length = status.getBytesRead() / 1024 / 1024;
        // 总长度 M
        double totalLength = status.getContentLength() / 1024 / 1024;
        result.put("startTime", startTime);
        result.put("currentTime", currentTime);
        result.put("time", time);
        result.put("velocity", velocity);
        result.put("totalTime", totalTime);
        result.put("timeLeft", timeLeft);
        result.put("percent", percent);
        result.put("length", length);
        result.put("totalLength", totalLength);
        if (length >= totalLength) {
            result.put("isfinish", 1);
        }
        return result;
    }
}
