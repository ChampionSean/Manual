/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
/**
 *
 * @author Dragon
 */
public class Excel{
    
    @SuppressWarnings("null")
    public void readDataBase(Context context) throws IOException{
        // TODO code application logic here
        DataBase db = new DataBase(context);
        
        FileInputStream file = null;
        try {
            file = new FileInputStream(new File("Libro1.xlsx"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Get the workbook instance for XLS file 
        XSSFWorkbook workbook = new XSSFWorkbook (file);
        
        int width,initDate,endDate,currentSheet = 0;
        int serie, rin, widthRin, stop = 10;
        long [] options = new long[4];
        String lastBrand = "",lastVersion = "", lastModel = "";
        String chargeIndex, rv, aux, lastYear, lastPosition, lastChargeType;
        Row row;
        Cell cell;
        Iterator<Cell> cellIterator;
        Iterator<Row> rowIterator;
        while(currentSheet < 2){
            //Get first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(currentSheet);
            
            //Get iterator to all the rows in current sheet
            rowIterator = sheet.iterator();
            
            //ignore whites spaces
            rowIterator.next();
            rowIterator.next();
            rowIterator.next();
            while(rowIterator.hasNext() || stop--==0) {
                row = rowIterator.next();

                //For each row, iterate through each columns
                
                //All models of cars
                cellIterator = row.cellIterator();
                aux = cellIterator.next().getStringCellValue();
                if(!aux.equals(""))lastBrand = aux;
                
                cell = cellIterator.next();
                if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                    lastModel = (new Double(cell.getNumericCellValue())).intValue()+"";
                }else{
                    if(!cell.getStringCellValue().equals("")){
                        lastModel = cell.getStringCellValue();
                    }
                }
                
                cell = cellIterator.next();
                if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                    lastVersion = (new Double(cell.getNumericCellValue())).intValue()+"";
                }else{
                    if(!cell.getStringCellValue().equals("")){
                        lastVersion = cell.getStringCellValue();
                    }
                }
                
                cell = cellIterator.next();
                if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                    lastYear = (new Double(cell.getNumericCellValue())).intValue() + "";
                }else{
                    lastYear = cell.getStringCellValue();
                }
                
                lastPosition = cellIterator.next().getStringCellValue();
                
                lastChargeType = cellIterator.next().getStringCellValue();
                
                cell = cellIterator.next();
                if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                    width = Integer.parseInt(cell.getStringCellValue().trim());
                }else{
                    width = (new Double(cell.getNumericCellValue())).intValue();
                }
                
                //ignore gray space
                cellIterator.next();
                int count=0;
                while(count < 4){
                    serie = -1;
                    cell = cellIterator.next();
                    if(cell.getCellType() != Cell.CELL_TYPE_STRING){
                        serie = (new Double(cell.getNumericCellValue())).intValue();
                    }
                    rin = -1;
                    cell = cellIterator.next();
                    if(cell.getCellType() != Cell.CELL_TYPE_STRING){
                        rin = (new Double(cell.getNumericCellValue())).intValue();
                    }
                    chargeIndex = null;
                    if(count == 0){
                        cell = cellIterator.next();
                        if(cell.getCellType() != Cell.CELL_TYPE_STRING){
                            chargeIndex = (new Double(cell.getNumericCellValue())).intValue() + "";
                        }else{
                            chargeIndex = cell.getStringCellValue();
                        }
                    }
                    cell = cellIterator.next();
                    if(cell.getCellType() != Cell.CELL_TYPE_STRING){
                        rv = (new Double(cell.getNumericCellValue())).intValue() + "";
                    }else{
                        rv = cell.getStringCellValue();
                    }
                    widthRin = -1;
                    if(count != 3){
                        cell = cellIterator.next();
                        if(cell.getCellType() != Cell.CELL_TYPE_STRING){
                            widthRin = (new Double(cell.getNumericCellValue())).intValue();
                        }
                        cellIterator.next();
                    }
                    
                    options[count] = db.addRin(serie,rin,chargeIndex,rv,widthRin);
                    if(options[count] == -1){
                        options[count] = db.getIdRin(stop, rin, rv, rv, rin);
                    }
                    count++;
                }
                
                lastYear = lastYear.replace("O", "0");
                if(lastYear.length() == 5){
                    endDate = Integer.parseInt(lastYear.substring(0,2));
                    initDate = Integer.parseInt(lastYear.substring(3));
                    if(initDate > endDate){
                        initDate += 1900;
                        endDate += 2000;
                    }else{
                        if(initDate < 30){
                            initDate += 2000;
                            endDate += 2000;
                        }else{
                            initDate += 1900;
                            endDate += 1900;
                        }
                    }
                    for(int ak = endDate; ak > initDate-1; ak--){
                        db.addCar(lastBrand, lastModel, lastVersion, ak,
                                lastPosition, lastChargeType, width, (int)options[0],
                                (int)options[1], (int)options[2], (int)options[3]);
                        System.out.println(lastBrand + "\t\t" + lastModel + "\t\t" +
                            lastVersion + "\t\t" + ak +  "\t\t" + lastPosition +
                            "\t\t" + lastChargeType +  "\t\t" + width +
                            "\t\t" + options[0] +  "\t\t" + options[1] +
                            "\t\t" + options[2] +  "\t\t" + options[3]);
                    }
                }else{
                    db.addCar(lastBrand, lastModel, lastVersion, Integer.parseInt(lastYear),
                                lastPosition, lastChargeType, width, (int)options[0],
                                (int)options[1], (int)options[2], (int)options[3]);
                    System.out.println(lastBrand + "\t\t" + lastModel + "\t\t" +
                        lastVersion + "\t\t" + lastYear +  "\t\t" + lastPosition +
                        "\t\t" + lastChargeType +  "\t\t" + width);
                }
            }
            currentSheet++;
        }
        file.close();
    }
}
