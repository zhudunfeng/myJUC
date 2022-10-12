package com.adun.controller;

import lombok.Cleanup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author ADun
 * @date 2022/10/9 14:06
 */
@RestController
public class FileDownController {

    public static final Logger logger = LoggerFactory.getLogger(FileDownController.class);


    public static final String UTF_8 = "UTF-8";

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public void downLoadFile(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        //目标下载文件
        File file = new File("C:\\Users\\21015\\Pictures\\20220910115542.jpg");
        //设置编码集
        response.setCharacterEncoding(UTF_8);
        //输入流
        InputStream is = null;
        //输出流
        OutputStream os = null;

        try {
            //分片下载    需要与前端进行请求头约定
            // http        Range 前端设置请求头   后端设置响应头  Range bytes=100-1000  bytes=100-

            //文件大小
            long fSize = file.length();

            //设置下载响应头
            response.setContentType("application/x-download");
            //base64编码文件名，防止传输乱码
            String fileName = URLEncoder.encode(file.getName(), UTF_8);
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setHeader("Accept-Range", "bytes");

            //为了httpclient请求方便，设置文件大小响应头
            response.setHeader("fSize", String.valueOf(fSize));
            response.setHeader("fName", fileName);


            //请求头中约定
            //分片起始位置
            long pos = 0;
            //分片结束位置
            long last = fSize - 1;
            //当前下载总大小[已经读取了多少]
            long sum = 0;

            //如果客户端支持分片，走分片逻辑
            if (null != request.getHeader("Range")) {
                //206 提示客户端当前只返回了部分内容
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

                String numRange = request.getHeader("Range").replaceAll("bytes=", "");
                String[] strRange = numRange.split("-");

                //两种情况
                //bytes=100-1000
                if (strRange.length == 2) {
                    pos = Long.parseLong(strRange[0].trim());
                    last = Long.parseLong(strRange[1].trim());

                    //当最后一个分片结束位置大于文件大小，last以文件大小为标准
                    if (last > fSize - 1) {
                        last = fSize - 1;
                    }
                } else {
                    //bytes=100-
                    pos = Long.parseLong(numRange.replaceAll("-", "").trim());
                }
            }


            //当前需要读取的内容长度
            long rangeLength = last - pos + 1;
            //响应头约定 bytes 100-1000/5000
            String contentRange = new StringBuffer("bytes ")
                    .append(pos).append("-").append(last).append("/")
                    .append(fSize).toString();

            response.setHeader("Content-Range", contentRange);
            response.setHeader("Content-Length", String.valueOf(rangeLength));

            is = new BufferedInputStream(new FileInputStream(file));
            os = new BufferedOutputStream(response.getOutputStream());

            //跳到当前起始位置 可以采用RandomAsFile进行随机读写
            is.skip(pos);
            //创建缓存
            byte[] buffer = new byte[1024];

            int length = 0;
            //sum初始为0
            while (sum < rangeLength) {
                //读取文件
                length = is.read(buffer, 0, (((rangeLength - sum) <= buffer.length)
                        ? ((int) (rangeLength - sum)) : buffer.length));

                if (length < 0) {
                    break;
                }

                sum = sum + length;
                //输出文件流
                os.write(buffer, 0, length);
                os.flush();
            }

            os.flush();

//            while ((length=is.read(buffer))>0){
//                os.write(buffer, 0, length);
//                os.flush();
//            }
            logger.info("fSize={},sum={},fSize==sum={}", fSize, sum, fSize == sum);
            logger.info("下载完成");
        } finally {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }


    /**
     * 文件下载
     * * 思路
     * * 读服务器，写客户端（浏览器）
     * *
     */
    @RequestMapping("download1")
    public void download1(HttpServletRequest request, HttpServletResponse response) {
        FileInputStream fis = null;
        ServletOutputStream ops = null;
        try( PrintWriter writer=response.getWriter()) {
            //request.setCharacterEncoding("UTF-8");

            //目标下载文件
            File file = new File("C:\\Users\\21015\\Pictures\\20220910115542.jpg");

            //文件名
            String fName = file.getName();

            //创建输入流
            fis = new FileInputStream(file);
            //创建输出流
            ops = response.getOutputStream();
//            writer = response.getWriter();

            //设置浏览器响应体文件类型
            String mimeType = request.getServletContext().getMimeType(fName);
            System.out.println("mimeType:" + mimeType);
            response.setContentType(mimeType);
            //解决文件名中文乱码问题
            String header = request.getHeader("User-Agent");
            if (header != null && header.contains("Firefox")) {
                fName = "=?utf-8?B?" + new BASE64Encoder().encode(fName.getBytes("utf-8")) + "?=";
            } else {
                fName = URLEncoder.encode(fName, "UTF-8");
            }
            //设置浏览器响应体内容格式，为附件格式。(告诉浏览器，文件为附件，别打开，下载。)
            response.setHeader("Content-Disposition", "attachment; filename=" + fName);

            byte[] b = new byte[1024];
            int len;
            //边读边写
            while ((len = fis.read(b)) > 0) {
                ops.write(b, 0, len);
                ops.flush();
            }
//            ops.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (ops != null) {
                    ops.close();
                }

                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }


    @RequestMapping("/download2")
    public ResponseEntity<byte[]> testResponseEntity() throws IOException{

//        ServletContext servletContext = session.getServletContext();
        //获得文件相对地址在服务器中的映射的绝对地址，并获得输入流
//        InputStream resourceAsStream = servletContext.getResourceAsStream("C:\\Users\\21015\\Pictures\\20220910115542.jpg");
//        byte[] body = new byte[resourceAsStream.available()] ;//文件多大字节数组就多大

        File file = new File("C:\\Users\\21015\\Pictures\\20220910115542.jpg");
        @Cleanup InputStream fileInputStream = new FileInputStream(file);
        byte[] body = new byte[fileInputStream.available()] ;//文件多大字节数组就多大
        fileInputStream.read(body);//将文件写入字节数组中

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename="+URLEncoder.encode(file.getName(), "UTF-8"));//让文件以附件的形式在浏览器显示
        headers.add("Content-Length", String.valueOf(file.length()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        HttpStatus statusCode = HttpStatus.OK;//状态码，OK是200

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(body, headers, statusCode);

        return responseEntity ;
    }

    /**
     * 文件下载
     * * 思路
     * * 读服务器，写客户端（浏览器）
     * *
     */
    @RequestMapping("download3")
    public void download3(HttpServletRequest request, HttpServletResponse response) {
        Reader fis = null;
        Writer ops = null;
        try {
            //request.setCharacterEncoding("UTF-8");

            //目标下载文件
            File file = new File("C:\\Users\\21015\\Pictures\\20220910115542.jpg");

            //文件名
            String fName = file.getName();

            //创建输入流
            fis = new InputStreamReader(new FileInputStream(file));
            //创建输出流
            ops = response.getWriter();
//            writer = response.getWriter();

            //设置浏览器响应体文件类型
            String mimeType = request.getServletContext().getMimeType(fName);
            System.out.println("mimeType:" + mimeType);
            response.setContentType(mimeType);
            //解决文件名中文乱码问题
            String header = request.getHeader("User-Agent");
            if (header != null && header.contains("Firefox")) {
                fName = "=?utf-8?B?" + new BASE64Encoder().encode(fName.getBytes("utf-8")) + "?=";
            } else {
                fName = URLEncoder.encode(fName, "UTF-8");
            }
            //设置浏览器响应体内容格式，为附件格式。(告诉浏览器，文件为附件，别打开，下载。)
            response.setHeader("Content-Disposition", "attachment; filename=" + fName);

//            char[] b = new char[1024];
            int len;
            //边读边写
            while ((len = fis.read()) != -1) {
                ops.write(len);
                ops.flush();
            }
//            ops.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (ops != null) {
                    ops.close();
                }

                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }


}
