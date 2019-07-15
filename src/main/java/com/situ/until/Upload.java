package com.situ.until;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 
public class Upload {
  
    public static Map<String, Object> upload(HttpServletRequest request) throws FileUploadException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(request);
        Map<String, Object> map = new HashMap<>();
        List<String> filenames = new ArrayList<>();
        for (FileItem item : items) {
            if (item.isFormField()) {// 非文件域表单元素
                // System.out.println("1--" + item.getFieldName());
                String value = item.getString();
                value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
                // System.out.println("2--" + value);
                map.put(item.getFieldName(), value);
            } else {// 文件域表单元素--复制文件
                InputStream is = item.getInputStream();
                byte[] b = new byte[1024 * 1024];
                int length = is.read(b);
                if (length < 0)
                    continue;
                String fn = getFName();
                OutputStream fos = new FileOutputStream(getFPath() + "/" + fn);
                fos.write(b, 0, length);
                fos.flush();
                fos.close();
                is.close();
                filenames.add(fn);
            }
        }
        map.put("list", filenames);
        return map;
    }

    private static String getFName() {
        // 根据时间戳创建图片文件名
        // String filename = System.currentTimeMillis() + ".jpg";
        String filename = UUID.randomUUID().toString() + ".jpg";
        // 03bc08c1-e771-4641-9e3f-16e5e2f858ed
        return filename;
    }

    // D:/DxOffice/workspace/image/1234567890.jpg
    private static File getFPath() {
        // linux下的路径分割符是斜杠 /
        // windows下的路径分隔符是反斜杠 \
        File f = new File("D:\\aa\\a3");
        if (!f.exists()) {
            f.mkdir();
        }
        return f;
    }

    public static void delFile(String filename) {
        if (FmtEmpty.isEmpty(filename))
            return;
        File file = new File(getFPath() + "/" + filename);
        file.delete();
    }

}
