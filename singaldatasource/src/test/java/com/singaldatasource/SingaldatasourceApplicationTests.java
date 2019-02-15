package com.singaldatasource;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.pdf.qrcode.QRCode;
import com.singaldatasource.dao.UserDao;
import com.singaldatasource.service.PdfService;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SingaldatasourceApplicationTests {

//    @Autowired
//    private UserDao userDao;

    @Resource
    public PdfService pdfService;

    @Test
    public void contextLoads() throws IOException, WriterException {
        //userDao.getUserList();

        String str="12345１２３４５一二三四五测试汉字テスト漢字";
        String filepath="C:\\app\\WritePdf.pdf";
        File file = new File(filepath);
        if (file.exists()) {
            file.delete();
        }
        String imagePath="";
        String imgFolderPath="";
        boolean isVertical=false;
        pdfService.creatPdf(str, filepath, imagePath, imgFolderPath, isVertical);

        String filePath = "D://";
        String fileName = "zxing.png";
        JSONObject json = new JSONObject();
        json.put(
                "zxing",
                "/javase/src/main/java/com/google/zxing");
        json.put("author", "shihy");
        String content = json.toJSONString();// 内容
        int width = 200; // 图像宽度
        int height = 200; // 图像高度
        String format = "png";// 图像类型
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, width, height, hints);// 矩阵
        Path path = FileSystems.getDefault().getPath(filePath, fileName);
        MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输图像
        System.out.println("输功.");

    }

}

