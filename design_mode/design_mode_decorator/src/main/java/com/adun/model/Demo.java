package com.adun.model;

/**
 * @author ADun
 * @date 2022/12/31 0:58
 */
public class Demo {
    public static void main(String[] args) {
        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
        CompressionDecorator encoded = new CompressionDecorator(
                new EncryptionDecorator(
                        new FileDataSource("out/OutputDemo.txt")
                )
        );

        encoded.writeData(salaryRecords);
        DataSource plain = new FileDataSource("out/OutputDemo.txt");
//        EncryptionDecorator plainB = new EncryptionDecorator(plain);
//        CompressionDecorator plainC = new CompressionDecorator(plainB);

        //原始输入
        System.out.println("- Input ----------------");
        System.out.println(salaryRecords);

        //加密后的
        System.out.println("- Encoded --------------");
        System.out.println(plain.readData());

        //解密后的
        System.out.println("- Decoded --------------");
        System.out.println(encoded.readData());
    }
}
