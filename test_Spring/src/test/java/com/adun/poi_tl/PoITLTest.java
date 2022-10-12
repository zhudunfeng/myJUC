package com.adun.poi_tl;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.util.BytePictureUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Zhu Dunfeng
 * @date 2022/7/15 12:16
 */
@SpringBootTest
public class PoITLTest {


//    @Test
//    public void test() throws IOException {
//        XWPFTemplate template = XWPFTemplate.compile("template.docx").render(
//                new HashMap<String, Object>(){{
//                    put("title", "Hi, poi-tl Word模板引擎");
//                }});
//        template.writeAndClose(new FileOutputStream("output.docx"));
//    }

    @Test
    public void test() throws IOException {
        XWPFTemplate template = XWPFTemplate.compile("template.docx").render(new HashMap<String, Object>() {{
            put("title", "Poi-tl 模板引擎");
            // 网络图片
            put("urlPicture", new PictureRenderData(640, 360, ".gif",
                    BytePictureUtils.getUrlBufferedImage(
                            "https://img-blog.csdnimg.cn/ef084499ceba4ebeab9a41f50091f2f7.gif")));
        }});
        FileOutputStream out = new FileOutputStream("out_template.docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();
    }


}
