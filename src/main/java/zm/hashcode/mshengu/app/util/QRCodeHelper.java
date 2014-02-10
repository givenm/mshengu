/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import net.glxn.qrgen.QRCode;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
//import net.glxn.qrgen.QRCode;

/**
 *
 * @author Ferox
 */
public class QRCodeHelper {

    private String generateQRCodes(String qrCodeValue) {

        return qrCodeValue;
    }

    public void generateQRCode(String qrCodeValue) {
//        QRCode.from(qrCodeValue);
//        qc.
        ByteArrayOutputStream byteAOS = null;
        QRCode.from(qrCodeValue).withSize(200, 200).stream();
        FileOutputStream fileOS;
        try {
            String path = "/qrcodes/" + qrCodeValue + ".JPG";

            fileOS = new FileOutputStream(path);
            fileOS.write(byteAOS.toByteArray());

            fileOS.flush();
            fileOS.close();
        } catch (FileNotFoundException fnException) {
            // log error
            System.out.println("FileNotFoundException was thrown and caught");
            System.out.println("Stack Trace   --> " + fnException.getMessage());
        } catch (IOException ioException) {
            // log error   
            System.out.println("IOException was thrown and caught");
            System.out.println("Stack Trace   --> " + ioException.getMessage());
        }
    }

    public void generateQRCodeFromUnitToUnit(int startUnitNumber, int endUnitNumber, String unitNamingCode) {
        int size = getArraySize(startUnitNumber, endUnitNumber);
        QRCodeImages[] qRCodeImagesArray = new QRCodeImages[size];
        int index = 0;
//        try {
//            FileOutputStream fileOS = null;
        for (int i = 0; i < size; i++) {
            String unitName = unitNamingCode + "-" + formatUnitNumber(startUnitNumber);
            startUnitNumber++;
//                ByteArrayOutputStream byteAOS = QRCode.from(unitName).withSize(200, 200).stream();

//QRCode.from("Hello World").withCharset("UTF-8");
            //ISO-8859-1 // UTF-8
            ByteArrayOutputStream byteAOS = QRCode.from(unitName).withCharset("ISO-8859-1").withSize(200, 200).stream();

            try {

                String filenName = unitName + ".JPG";

                File file = new File("/Users/Ferox/qrcodes/" + filenName);
//                                File file = new File("./qrcodes/"+filenName);
                if (!file.getParentFile().exists()) { // if the directories don't exist

                    System.out.println("!file.getParentFile().exists()");
//                    file.getParentFile().mkdirs();
//                    file.mkdir();
                    if (!file.getParentFile().mkdirs()) { // if making the directories fails
                        // directories weren't created, throw exception or something    
                        System.out.println("!file.getParentFile().mkdirs()");

                    }

                }
                QRCodeImages qrCodeImages = new QRCodeImages();
                qrCodeImages.setCaption(unitName);
                qrCodeImages.setImageBytes(byteAOS.toByteArray());
                qrCodeImages.setPath(file.getAbsolutePath());

                qRCodeImagesArray[index] = qrCodeImages;
//                qRCodeImagesArray[index].setImageBytes(byteAOS.toByteArray());
//                qRCodeImagesArray[index].setPath(file.getAbsolutePath());
                index++;
                if (!file.exists()) {
                    System.out.println("Ablosute Path" + file.getAbsolutePath());
                    file.createNewFile();
                }

                FileOutputStream fileOS = new FileOutputStream(file);

                fileOS.write(byteAOS.toByteArray());

                fileOS.flush();



            } catch (FileNotFoundException fnException) {
                // log error
                System.out.println("FileNotFoundException was thrown and caught");
                System.out.println("Error Message   --> " + fnException.getMessage());
                System.out.println("Stack Trace   --> ");
                fnException.printStackTrace();
            } catch (IOException ioException) {
                // log error   
                System.out.println("IOException was thrown and caught");
                System.out.println("Error Message   --> " + ioException.getMessage());
                System.out.println("Stack Trace   --> ");
                ioException.printStackTrace();
            }
        }
//        saveImagesToExcel(qRCodeImagesArray);

////            if (fileOS != null) {
////                fileOS.close();
////            }
//        } catch (IOException ioException) {
//            // log error   
//            System.out.println("IOException was thrown and caught");
//            System.out.println("Stack Trace   --> " + ioException.getMessage());
//        }
    }

    private void saveImagesToExcel(QRCodeImages[] qRCodeImagesArray) {
//        Workbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();
//                    Sheet sheet = wb.createSheet();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("new sheet");
        int size = qRCodeImagesArray.length;
        CreationHelper helper = wb.getCreationHelper();
        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = helper.createClientAnchor();
        try {
            //create sheet
            int nameRowIndex = 0;
            int imageRowIndex = 0;
            for (int i = 0; i < size; i++) {


                Row excelNameRow = sheet.getRow(nameRowIndex + 1);
                Row excelImageRow = sheet.getRow(imageRowIndex + 2);
                if (excelNameRow == null) {
                    excelNameRow = sheet.createRow(i + 1);
                }
                if (excelImageRow == null) {
                    excelImageRow = sheet.createRow(i + 2);
                }
                excelNameRow.createCell(0).setCellValue(qRCodeImagesArray[i].getCaption());

                //FileInputStream obtains input bytes from the image file
                InputStream inputStream = new FileInputStream(qRCodeImagesArray[i].getPath());
                //Get the contents of an InputStream as a byte[].
                byte[] bytes = IOUtils.toByteArray(inputStream);
                //Adds a picture to the workbook
                int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
                //close the input stream
                inputStream.close();
//                int pictureIdx = wb.addPicture(qRCodeImagesArray[i].getImageBytes(), Workbook.PICTURE_TYPE_PNG);
                anchor.setCol1(0);
                anchor.setRow1(imageRowIndex);
                Picture pict = drawing.createPicture(anchor, pictureIdx);

//                excelImageRow.createCell(0).setCellType(wb.PICTURE_TYPE_PNG);
//                excelImageRow.createCell(0).setCellValue(pictureIdx);
//                pict.resize();

                //Write the Excel file
                String fileName = "QRCodeFile.xlsx";
//                String fileName2 = "QRCodeFile.xlsx";
                FileOutputStream fileOut = null;
                fileOut = new FileOutputStream("/Users/Ferox/qrcodes/" + fileName, true);
                wb.write(fileOut);
                fileOut.close();


                nameRowIndex += 6;
                imageRowIndex += 6;
            }



        } catch (FileNotFoundException fnException) {
            // log error
            System.out.println("FileNotFoundException was thrown and caught");
            System.out.println("Error Message   --> " + fnException.getMessage());
            System.out.println("Stack Trace   --> ");
            fnException.printStackTrace();
        } catch (IOException ioException) {
            // log error   
            System.out.println("IOException was thrown and caught");
            System.out.println("Error Message   --> " + ioException.getMessage());
            System.out.println("Stack Trace   --> ");
            ioException.printStackTrace();
        } catch (Exception exception) {
            System.out.println("Error Message   --> " + exception.getMessage());
            System.out.println("Stack Trace   --> ");
            exception.printStackTrace();
        }
    }

    private int getArraySize(int startUnitNumber, int endUnitNumber) {
        if (startUnitNumber < endUnitNumber) {
            return endUnitNumber - startUnitNumber;
        } else {
            return 1;
        }

    }

    private String formatUnitNumber(int unitId) {
        //100000
        if (unitId < 10) {
            return "00000" + unitId; //000001-000009
        } else if (unitId < 100) {
            return "0000" + unitId;  //000010-000099
        } else if (unitId < 1000) {
            return "000" + unitId;   //000100-000999
        } else if (unitId < 10000) {
            return "00" + unitId;    //001000-009999
        } else if (unitId < 100000) {
            return "0" + unitId;     //010000-099999
        } else {
            return "" + unitId;
        }
    }
}
