package com.heyongming.ntwd.test;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * Created by yongminghe on 14-5-5.
 */
public class ZXingEncodeTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0 ; i < 512 ; i++) {
            stringBuffer.append("国");
        }
        String content = new String(stringBuffer.toString().getBytes("UTF-8"),"iso-8859-1");
        String imagePath = "c:\\code.png";
        File file = new File(imagePath);

        QRCodeWriter writer = new QRCodeWriter();
        try {
            //生成二维码
            BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE,200, 200);
            MatrixToImageWriter.writeToFile(matrix, "png", file);
            //读取二维码
            QRCodeReader reader = new QRCodeReader();
            BufferedImage image = ImageIO.read(file);
            LuminanceSource source = new BufferedImageLuminanceSource(image );
            Binarizer binarizer = new HybridBinarizer(source );
            BinaryBitmap imageBinaryBitmap = new BinaryBitmap(binarizer  );
            Result result = reader.decode(imageBinaryBitmap);
            System.out.println("result = "+ result.toString());
            System.out.println("resultFormat = "+ result.getBarcodeFormat());
            System.out.println("resultText = "+ result.getText());
            System.out.println(stringBuffer.length() + "=" + result.getText().length() + " " + stringBuffer.toString().getBytes().length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
