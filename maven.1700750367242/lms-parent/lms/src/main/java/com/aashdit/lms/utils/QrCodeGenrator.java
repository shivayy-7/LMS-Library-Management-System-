package com.aashdit.lms.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.ibm.icu.text.SimpleDateFormat;

public class QrCodeGenrator {

	
	public static String generateQRCode(String memberCode, String path, String module, String code) {
		//ResourceBundle rb = ResourceBundle.getBundle("application");
	    String absolutePath = "";
	    int width = 300;
	    int height = 300;
	   // String charset = "UTF-8";  
	    try {
	        String filePath = path + File.separator + module;
	        if (code.equals("") || code.isEmpty()) {
	            filePath = filePath + File.separator + code;
	        }
	    //    String data = rb.getString("deploymentUrl")+"scanQR/"+memberCode;
	        
//	        String data = "memberCode:"+memberCode;
	        String data = memberCode;
	        BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, width, height);
	        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	        for (int x = 0; x < width; x++) {
	            for (int y = 0; y < height; y++) {
	                image.setRGB(x, y, matrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
	            }
	        }

	        File qrFile = new File(filePath);

	        // Create the directory if it doesn't exist
	        if (!qrFile.exists() && !qrFile.mkdirs()) {
	            throw new RuntimeException("Could not create directory: " + filePath);
	        }

	        // Generate a unique file name using a timestamp
	        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
	        String uniqFileName = "qrCode_" + timestamp +memberCode+ ".jpg";
	        qrFile = new File(filePath + File.separator + uniqFileName);
	        ImageIO.write(image, "jpg", qrFile);
	        absolutePath = qrFile.getAbsolutePath();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return absolutePath;
	}

	
	public static String generateQRCodeGenerateForBooking(String memberCode, String path, String module, String code,String bookUN) {
		//ResourceBundle rb = ResourceBundle.getBundle("application");
	    String absolutePath = "";
	    int width = 300;
	    int height = 300;
	   // String charset = "UTF-8";  
	    try {
	        String filePath = path + File.separator + module;
	        if (code.equals("") || code.isEmpty()) {
	            filePath = filePath + File.separator + code;
	        }
	    //    String data = rb.getString("deploymentUrl")+"scanQR/"+memberCode;
	        
	        String data = "memberCode:"+memberCode+","+"bookUKNo:"+bookUN;
	        BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, width, height);
	        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	        for (int x = 0; x < width; x++) {
	            for (int y = 0; y < height; y++) {
	                image.setRGB(x, y, matrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
	            }
	        }

	        File qrFile = new File(filePath);

	        // Create the directory if it doesn't exist
	        if (!qrFile.exists() && !qrFile.mkdirs()) {
	            throw new RuntimeException("Could not create directory: " + filePath);
	        }

	        // Generate a unique file name using a timestamp
	        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
	        String uniqFileName = "qrCode_" + timestamp +memberCode+bookUN+ ".jpg";
	        qrFile = new File(filePath + File.separator + uniqFileName);
	        ImageIO.write(image, "jpg", qrFile);
	        absolutePath = qrFile.getAbsolutePath();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return absolutePath;
	}
	
	public static String generateQRCodeForBookCat(String bookCatCode, String path, String module, String code) {
		//ResourceBundle rb = ResourceBundle.getBundle("application");
	    String absolutePath = "";
	    int width = 300;
	    int height = 300;
	   // String charset = "UTF-8";  
	    String relativePath = "";
	    try {
	        String filePath = path + File.separator + module;
	        if (code.equals("") || code.isEmpty()) {
	            filePath = filePath + File.separator + code;
	        }
	    //    String data = rb.getString("deploymentUrl")+"scanQR/"+memberCode;
	        
	        String data = "BookCatCode:"+bookCatCode;
	        BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, width, height);
	        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	        for (int x = 0; x < width; x++) {
	            for (int y = 0; y < height; y++) {
	                image.setRGB(x, y, matrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
	            }
	        }

	        File qrFile = new File(filePath);

	        // Create the directory if it doesn't exist
	        if (!qrFile.exists() && !qrFile.mkdirs()) {
	            throw new RuntimeException("Could not create directory: " + filePath);
	        }

	        // Generate a unique file name using a timestamp
	        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
	        String uniqFileName = "qrCode_" + timestamp +bookCatCode+ ".jpg";
	        qrFile = new File(filePath + File.separator + uniqFileName);
	        ImageIO.write(image, "jpg", qrFile);
	        absolutePath = qrFile.getAbsolutePath();
	        Path fullPathObj = Paths.get(absolutePath);
	        Path baseDirObj = Paths.get(path+"/"+module);

	        relativePath = baseDirObj.relativize(fullPathObj).toString();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
             
	    return relativePath;
	}
	

}