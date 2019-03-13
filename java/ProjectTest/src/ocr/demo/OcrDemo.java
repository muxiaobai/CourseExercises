/**
 * Project Name:ProjectTest
 * File Name:OcrDemo.java
 * Package Name:ocr.demo
 * Date:2019年3月7日下午5:49:48
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package ocr.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * ClassName:OcrDemo  需要安装 Tesseract-OCR
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月7日 下午5:49:48 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public class OcrDemo {
    
    public static void main(String[] args) throws Exception{
        Ocr ocr = new Ocr();
        File file = new File("C:\\Users\\zzz\\Desktop\\QQͼƬ20171024104441.jpg");
        InputStream imgStream = new FileInputStream(file);
        String value= ocr.parseImg(imgStream);
        System.out.println(value);
    }
    
}

