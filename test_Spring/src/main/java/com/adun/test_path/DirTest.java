package com.adun.test_path;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Zhu Dunfeng
 * @date 2021/11/30 14:40
 */
public class DirTest {
    public static void main(String[] args) throws IOException {
        new DirTest().showURL();
    }


    public  void showURL() throws IOException {
        /*第一种：D:\IdeaProjects\datastructures-and-arithmetics\out\production\datastructures


          第一种：D:\IdeaProjects\datastructures-and-arithmetics\out\production\datastructures\com\adun\sparseArray


          第二种：D:\IdeaProjects\datastructures-and-arithmetics


          第三种：file:/D:/IdeaProjects/datastructures-and-arithmetics/out/production/datastructures/


          D:\IdeaProjects\datastructures-and-arithmetics

          D:\devtools\Java\jdk1.8.0_241\jre\lib\charsets.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\deploy.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\access-bridge-64.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\cldrdata.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\dnsns.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\jaccess.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\jfxrt.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\localedata.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\nashorn.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\sunec.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\sunjce_provider.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\sunmscapi.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\sunpkcs11.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\zipfs.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\javaws.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\jce.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\jfr.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\jfxswt.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\jsse.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\management-agent.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\plugin.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\resources.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\rt.jar;D:\IdeaProjects\datastructures-and-arithmetics\out\production\datastructures;D:\devtools\JetBrains\IntelliJ IDEA 2019.1.3\lib\idea_rt.jar

        */

        // 第一种：获取类加载的根路径   D:\git\daotie\daotie\target\classes
        File f = new File(this.getClass().getResource("/").getPath());
        System.out.println("第一种："+f+"\n\n");

        // 获取当前类的所在工程路径; 如果不加“/”  获取当前类的加载目录
        // D:\git\daotie\daotie\target\classes\my
        File f2 = new File(this.getClass().getResource("").getPath());
        System.out.println("第一种："+f2+"\n\n");

        // 第二种：获取项目路径    D:\git\daotie\daotie
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println("第二种："+courseFile+"\n\n");


        // 第三种：  file:/D:/git/daotie/daotie/target/classes/
        URL xmlpath = this.getClass().getClassLoader().getResource("");
        System.out.println("第三种："+xmlpath+"\n\n");


        // 第四种： D:\git\daotie\daotie
        System.out.println("第四种："+System.getProperty("user.dir")+"\n\n");
        /*
         * 结果： C:\Documents and Settings\Administrator\workspace\projectName
         * 获取当前工程路径
         */

        // 第五种：  获取所有的类路径 包括jar包的路径
        //D:\devtools\Java\jdk1.8.0_241\jre\lib\charsets.jar;
        // D:\devtools\Java\jdk1.8.0_241\jre\lib\deploy.jar;
        // D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\access-bridge-64.jar;
        // D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\cldrdata.jar;
        // D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\dnsns.jar;
        // D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\jaccess.jar;
        // D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\jfxrt.jar;
        // D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\localedata.jar;
        // D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\nashorn.jar;
        // D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\sunec.jar;
        // D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\sunjce_provider.jar;
        // D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\sunmscapi.jar;
        // D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\sunpkcs11.jar;
        // D:\devtools\Java\jdk1.8.0_241\jre\lib\ext\zipfs.jar;
        // D:\devtools\Java\jdk1.8.0_241\jre\lib\javaws.jar;
        // D:\devtools\Java\jdk1.8.0_241\jre\lib\jce.jar;
        // D:\devtools\Java\jdk1.8.0_241\jre\lib\jfr.jar;
        // D:\devtools\Java\jdk1.8.0_241\jre\lib\jfxswt.jar;
        // D:\devtools\Java\jdk1.8.0_241\jre\lib\jsse.jar;
        // D:\devtools\Java\jdk1.8.0_241\jre\lib\management-agent.jar;
        // D:\devtools\Java\jdk1.8.0_241\jre\lib\plugin.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\resources.jar;D:\devtools\Java\jdk1.8.0_241\jre\lib\rt.jar;D:\IdeaProjects\datastructures-and-arithmetics\out\production\datastructures;D:\IdeaProjects\datastructures-and-arithmetics\datastructures\lib\fastjson-1.2.78.jar;D:\devtools\JetBrains\IntelliJ IDEA 2019.1.3\lib\idea_rt.jar
        System.out.println("第五种："+System.getProperty("java.class.path"));

    }
}
