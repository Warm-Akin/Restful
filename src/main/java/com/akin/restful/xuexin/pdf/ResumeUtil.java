package com.akin.restful.xuexin.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class ResumeUtil {

    public void createTemplate() {
        String templatePath = "D:\\test.pdf";
        String finalPdfPath = "D:\\new-test.pdf";
        PdfReader pdfReader = null;
        FileOutputStream out = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        PdfStamper pdfStamper = null;
        try {
            out = new FileOutputStream(finalPdfPath);
            pdfReader = new PdfReader(templatePath);
            byteArrayOutputStream = new ByteArrayOutputStream();
            pdfStamper = new PdfStamper(pdfReader, byteArrayOutputStream);
            AcroFields formFields = pdfStamper.getAcroFields();
            Iterator<String> iterator = formFields.getFields().keySet().iterator();
            String[] str = { "123456789", "TOP__ONE", "男", "1991-01-01", "130222111133338888", "河北省保定市" };
            int fieldsIndex = 0;
            while(iterator.hasNext()) {
                String name = iterator.next().toString();
                System.out.println("field name: " + name);
                formFields.setField(name, str[fieldsIndex++]);
            }
            // 如果为false那么生成的PDF文件还能编辑，一定要设为true
            pdfStamper.setFormFlattening(true);
            pdfStamper.close();
            Document document = new Document();
            PdfCopy pdfCopy = new PdfCopy(document, out);
            document.open();
            // todo 几页
            PdfImportedPage pdfImportedPage = pdfCopy.getImportedPage(new PdfReader(byteArrayOutputStream.toByteArray()), 1);
            pdfCopy.addPage(pdfImportedPage);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}
