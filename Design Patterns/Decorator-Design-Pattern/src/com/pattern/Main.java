package com.pattern;

import com.pattern.Decorator.CompressionDecorator;
import com.pattern.Decorator.DataSourceDecorator;
import com.pattern.Decorator.EncryptionDecorator;


public class Main {
    public static void main(String[] args) {
        String salaryRecords = "Name,Salary\nuser,1\nuser,2";
        DataSourceDecorator encoded = new CompressionDecorator(
                new EncryptionDecorator(
                        new FileDataSource("out/OutputDemo.txt")));
        encoded.writeData(salaryRecords);
        DataSource plain = new FileDataSource("out/OutputDemo.txt");

        System.out.println("- Input ----------------");
        System.out.println(salaryRecords);
        System.out.println("- Encoded --------------");
        System.out.println(plain.readData());
        System.out.println("- Decoded --------------");
        System.out.println(encoded.readData());
    }
}