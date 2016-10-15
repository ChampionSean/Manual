/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Arrays;
import java.util.logging.Level;
import android.test.mock.MockContext;
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

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("null")
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        //DataBase db = new DataBase(new MockContext());
        
        FileInputStream file = null;
        try {
            file = new FileInputStream(new File("Libro1.xlsx"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Get the workbook instance for XLS file 
        XSSFWorkbook workbook = new XSSFWorkbook (file);
        
        int lastYearI,lastModelI,width,currentSheet = 0;
        String lastBrand = "",lastVersion = "", lastModelS = "";
        String aux, lastYearS, lastPosition, lastChargeType,toShow,toShow2="";
        Row row;
        Cell cell;
        Iterator<Cell> cellIterator;
        Iterator<Row> rowIterator;
        while(currentSheet < 2){
            //Get first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(currentSheet);
            
            //Get iterator to all the rows in current sheet
            rowIterator = sheet.iterator();
            
            rowIterator.next();
            rowIterator.next();
            rowIterator.next();
            while(rowIterator.hasNext()) {
                row = rowIterator.next();

                //For each row, iterate through each columns
                cellIterator = row.cellIterator();
                aux = cellIterator.next().getStringCellValue();
                if(!aux.equals(""))lastBrand = aux;
                
                cell = cellIterator.next();
                if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                    lastModelI = (new Double(cell.getNumericCellValue())).intValue();
                    toShow2 = lastModelI +"";
                }else{
                    if(!cell.getStringCellValue().equals("")){
                        lastModelS = cell.getStringCellValue();
                        toShow2 = lastModelS;
                    }
                }
                
                aux = cellIterator.next().getStringCellValue();
                if(!aux.equals(""))lastVersion = aux;
                
                cell = cellIterator.next();
                if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                    lastYearI = (new Double(cell.getNumericCellValue())).intValue();
                    toShow = lastYearI +"";
                }else{
                    lastYearS = cell.getStringCellValue();
                    toShow = lastYearS;
                }
                
                lastPosition = cellIterator.next().getStringCellValue();
                
                lastChargeType = cellIterator.next().getStringCellValue();
                
                width = (new Double(cellIterator.next().getNumericCellValue())).intValue();
                
                System.out.println(lastBrand + "\t\t" + toShow2 + "\t\t" +
                        lastVersion + "\t\t" + toShow +  "\t\t" + lastPosition +
                        "\t\t" + lastChargeType +  "\t\t" + width);
            }
            currentSheet++;
        }
        file.close();
        /*
        Row fila;
		Cell celda;
		int k=0;
	try{	
            File archivoXLS = new File("Clientes.xls");
            //Si el archivo existe se elimina
            if(archivoXLS.exists()) archivoXLS.delete();
            //Se crea el archivo
            archivoXLS.createNewFile();
        
            //Se crea el libro de excel usando el objeto de tipo Workbook
            Workbook libro = new HSSFWorkbook();
            //Utilizamos la clase Sheet para crear una nueva hoja de trabajo dentro del libro que creamos anteriormente
             //Se inicializa el flujo de datos con el archivo xls
            try (FileOutputStream archivo = new FileOutputStream(archivoXLS)) {
                //Utilizamos la clase Sheet para crear una nueva hoja de trabajo dentro del libro que creamos anteriormente
                Sheet hoja = libro.createSheet("Clientes Registrados");
                fila = hoja.createRow(k++);
                celda = fila.createCell(0);
                celda.setCellValue("Nombre");
                celda = fila.createCell(1);
                celda.setCellValue("Apellidos");
                celda = fila.createCell(2);
                celda.setCellValue("Ciudad");
                celda = fila.createCell(3);
                celda.setCellValue("Telefono");
                celda = fila.createCell(4);
                celda.setCellValue("Email");
                celda = fila.createCell(5);
                celda.setCellValue("Fecha de Nacimiento");
                celda = fila.createCell(6);
                celda.setCellValue("Tiempo");
                celda = fila.createCell(7);
                celda.setCellValue("Imagen");
                //Recorremos el cursor hasta que no haya más registros
                fila = hoja.createRow(k++);
                for(int i=0;i<8;i++){
                    celda = fila.createCell(i);
                    celda.setCellValue("Prueba:"+i);
                }   Escribimos en el libro
                    libro.write(archivo);
                //Cerramos el flujo de datos
            }
        }catch(IOException ex){
        }*/
    }
}
