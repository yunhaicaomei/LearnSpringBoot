package com.singaldatasource.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

@Service
public class PdfService {

    public boolean creatPdf(String str, String filepath, String imagePath, String imgFolderPath,
                                   boolean isVertical) {
        OutputStream os = null;
        Document document = null;
        PdfWriter writer = null;
        try {
            os = new FileOutputStream(filepath);
            document = new Document();
            // 设置文档大小
            document.setPageSize(PageSize.A4);
            if (!isVertical) {
                document.setPageSize(PageSize.A4.rotate());
            }
            // 设置边距，单位都是像素，换算大约1厘米=28.33像素
            document.setMargins(10, 10, 10, 10);
            writer = PdfWriter.getInstance(document, os);

            document.open();

            Image png = null;
            if (imagePath != null && !imagePath.isEmpty()){
                png = Image.getInstance(imagePath);
                png.setAlignment(Image.LEFT);
                // png.setAbsolutePosition(0, 0);
                png.scaleAbsolute(80, 70);
                document.add(png);
            }
            BaseFont chinese = BaseFont.createFont("C:/windows/fonts/simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(chinese, 14, Font.NORMAL);

            String fontFamily ="";
            fontFamily +="\"Meiryo UI\",";
            fontFamily +="-apple-system,";
            fontFamily +="BlinkMacSystemFont,";
            fontFamily +="\"Segoe UI\",";
            fontFamily +="Roboto,";
            fontFamily +="Oxygen-Sans,";
            fontFamily +="Ubuntu,Cantarell,";
            fontFamily +="\"Helvetica Neue\",";
            fontFamily +="sans-serif";
            // 将html转pdf
            InputStream is = null;
            try {
                String[] htmlTextArr = str.split("<hr style=\"page-break-after:always;\" class=\"ke-pagebreak\" />");
                for (int i = 0; i < htmlTextArr.length; i++) {
                    StringBuilder html = new StringBuilder();
                    html.append("<html>");
                    html.append(
                            "<body style='font-size:14px;font-family:"+fontFamily+";'>");
                    html.append(htmlTextArr[i].replace("../img/black_square.jpg", imgFolderPath + "/black_square.jpg"));
                    html.append("</body>");
                    html.append("</html>");
                    is = new ByteArrayInputStream(html.toString().getBytes("utf-8"));

                    //new FontFactoryImp().
                    XMLWorkerHelper.getInstance().parseXHtml(writer, document, is, XMLWorkerHelper.class.getResourceAsStream("/default.css"), Charset.forName("utf-8"), new XMLWorkerFontProvider());
                    //XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
                    if (i < htmlTextArr.length - 1) {
                        document.newPage();
                        if (png != null) {
                            document.add(png);
                        }
                    }
                }
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            if (document != null) {
                document.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    private static String PDF_TEMPLATE_PATH = null;

    private void getPdfTemplate() {
        if (null == PDF_TEMPLATE_PATH) {
            // pdf模板路径
            PDF_TEMPLATE_PATH = this.getClass().getClassLoader().getResource("security/last_template.pdf").getPath();
        }
    }

    /**
     *
     * @param pdfValues 生产pdf所需要的数据
     * @param pdfPath   生成的新文件路径
     * @param sealPath  印章路径
     * @throws IOException
     * @throws DocumentException
     */
    public void generateFile(Map<String, String> pdfValues, String pdfPath, String sealPath)
            throws IOException, DocumentException {
        getPdfTemplate();
        PdfReader reader = null;
        FileOutputStream out = null;
        ByteArrayOutputStream bos = null;
        PdfStamper stamper = null;
        System.out.println(new File(PDF_TEMPLATE_PATH).getAbsoluteFile());
        reader = new PdfReader(PDF_TEMPLATE_PATH);// 读取pdf模板
        bos = new ByteArrayOutputStream();
        stamper = new PdfStamper(reader, bos);
        AcroFields acroFields = stamper.getAcroFields();

        /* 使用中文字体 */
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//		BaseFont bf = BaseFont.createFont(
//				this.getClass().getClassLoader().getResource("security/simsun.ttc").getPath() + ",1",
//				BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        ArrayList<BaseFont> fontList = new ArrayList<BaseFont>(1);
        fontList.add(bf);
        acroFields.setSubstitutionFonts(fontList);

//			Map<String, String> pdfValues = getPdfValues();
        Set<String> keySet = pdfValues.keySet();
        for (String key : keySet) {
            String value = pdfValues.get(key);
            acroFields.setField(key, value);
        }

        // 插入图片
        addImage(stamper, acroFields, sealPath);

        stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
        stamper.close();

        Document doc = new Document();

        out = new FileOutputStream(pdfPath);// 输出流
        PdfCopy copy = new PdfCopy(doc, out);
        doc.open();
        PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
        copy.addPage(importPage);
        doc.close();

    }

    private void addImage(PdfStamper stamper, AcroFields acroFields, String sealPath)
            throws BadElementException, MalformedURLException, IOException, DocumentException {
        int pageNo = acroFields.getFieldPositions("zgswjggz").get(0).page;
        Rectangle signRect = acroFields.getFieldPositions("zgswjggz").get(0).position;
        float x = signRect.getLeft();
        float y = signRect.getBottom();
        // 读图片
        Image image = Image.getInstance(sealPath);
        // 获取操作的页面
        PdfContentByte pdfContentByte = stamper.getOverContent(pageNo);
        // 根据域的大小缩放图片
        image.scaleToFit(signRect.getWidth(), signRect.getHeight());
        // 添加图片
        image.setAbsolutePosition(x, y);
        pdfContentByte.addImage(image);
    }
}
