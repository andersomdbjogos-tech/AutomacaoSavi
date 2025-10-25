//import AutoTest.PlanilhaTest01;
//import AutoTest.TratamentoTest01;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
//public void alteracaoCadastro(TratamentoTest01 tratamento, PlanilhaTest01 planilha, Workbook workbook, Sheet sheet) {
//
//    try (FileInputStream fis = new FileInputStream(filePath());
//         Workbook workbook = new XSSFWorkbook(fis)) {
//
//        Sheet sheet = workbook.getSheetAt(0);
//        System.out.println("Retorno do verificacaoCadastro: " + planilha.verificacaoCadastro(tratamento));
//
//        for (int i = 1; i < sheet.getLastRowNum(); i++) {
//            Row row = sheet.getRow(i);
//            if (row == null) continue;
//
//            Cell valorCadastro = row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//
//            if (planilha.verificacaoCadastro(tratamento) == 1) {
//                valorCadastro.setCellValue("OK");
//            } else {
//                valorCadastro.setCellValue("ERRO");
//            }
//        }
//
//    }catch (IOException e) {
//        e.printStackTrace();
//    }
//}