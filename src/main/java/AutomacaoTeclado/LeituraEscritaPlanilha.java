package AutomacaoTeclado;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class LeituraEscritaPlanilha {
    private static final Logger logger = Logger.getLogger(LeituraEscritaPlanilha.class.getName());

    //Metodo para localizar o arquivo
    public static String filePath() {
        return "C:\\Users\\ander\\IdeaProjects\\ProjetoAutoSavi\\CipeOutubro.xlsx";
    }

    public void sheetValidation() {
        try (FileInputStream fis = new FileInputStream(filePath());
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Row cabecalho = sheet.getRow(0);

            java.util.List<String> colunasObrigatorias = Arrays.asList("senha", "quantidade", "tipoAto", "data",
                    "hora", "viaAcesso", "valor", "OBS", "mensagem", "cadastro");
            Map<String, Integer> colunas = new HashMap<>();

            for (String coluna : colunasObrigatorias) {
                for (Cell cell : cabecalho) {
                    if (cell.getStringCellValue().trim().equalsIgnoreCase(coluna)) {
                        colunas.put(coluna, cell.getColumnIndex());
                    }
                }
            }

            if (colunas.size() < colunasObrigatorias.size()) {
                throw new RuntimeException("Colunas não encontrada ou com nome incorreto");
            } else {
                System.out.println("Colunas validadas com sucesso\n");
            }
        } catch (IOException e) {
            logger.severe("Erro ao tentar validar planilha" + e.getMessage());
            logger.log(java.util.logging.Level.SEVERE, "Detalhes do erro: ", e);
        }
    }

    public void setCellCadastro(Selenium selenium, Planilha planilha, Sheet sheet, int i) {
        Row row = sheet.getRow(i);
        if (row == null) return;

        Cell primeiraCelula = row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        if (primeiraCelula == null || primeiraCelula.getStringCellValue().isBlank()) return;

        Cell valorCadastro = row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

        if (planilha.verificacaoCadastro(selenium) == 1) {
            valorCadastro.setCellValue("OK");
        } else {
            valorCadastro.setCellValue("ERRO");
        }
    }

    public void setCellMensagem(Selenium selenium, Sheet sheet, int i) {
        Row row = sheet.getRow(i);
        if (row == null) return;

        Cell valorMensagem = row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

        valorMensagem.setCellValue(selenium.getMensagemPopUp());
    }

    public boolean checkCadastro(Sheet sheet, DataFormatter formatter, int i) {
        Row row = sheet.getRow(i);

        Cell valorCadastrado = row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        if (valorCadastrado == null) return false;

        String statusCadastrado = formatter.formatCellValue(valorCadastrado);

        return statusCadastrado.trim().equalsIgnoreCase("OK");
    }

    public void valorSteers(Automacao automacao, WebDriver driver, Selenium selenium) {
        try (FileInputStream fis = new FileInputStream(filePath())) {
            Workbook workbook = new XSSFWorkbook(fis);

            Sheet sheet = workbook.getSheetAt(0);
            Row primeiraLinha = sheet.getRow(0);
            DataFormatter formatter = new DataFormatter();
            Planilha planilha = new Planilha();
            Relatorio relatorio = new Relatorio();

            Map<String, Integer> colunas = new HashMap<>();

            for (Cell cell : primeiraLinha) {
                String cabecalho = formatter.formatCellValue(cell);
                colunas.put(cabecalho, cell.getColumnIndex());
            }

            linhaLoop:
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row linha = sheet.getRow(i);

                if (checkCadastro(sheet, formatter, i)) {
                    System.out.println("**************************\n" +
                            "Linha " + i + " com cadastro OK\n" +
                            "**************************"
                    );
                    continue;
                }

                for (Map.Entry<String, Integer> entry : colunas.entrySet()) {
                    String colunaNome = entry.getKey();
                    int indice = entry.getValue();
                    Cell cell = linha.getCell(indice, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    if (colunaNome.equals("senha") && formatter.formatCellValue(cell).isEmpty()) {
                        break linhaLoop;
                    }

                    switch (colunaNome) {
                        case "senha" -> {
                            String valorSenha = formatter.formatCellValue(cell);
                            planilha.setSenha(valorSenha);
                        }

                        case "quantidade" -> {
                            String valorQuantidade = formatter.formatCellValue(cell);
                            planilha.setQuantidade(valorQuantidade);
                        }

                        case "tipoAto" -> {
                            String valorTipoAto = formatter.formatCellValue(cell);
                            planilha.setTipoAto(valorTipoAto);
                        }

                        case "data" -> {
                            String valorData = formatter.formatCellValue(cell);
                            valorData = valorData.replaceAll("[^0-9]", "");
                            planilha.setData(valorData);
                        }

                        case "hora" -> {
                            String valorHora = formatter.formatCellValue(cell);
                            valorHora = valorHora.replaceAll("[^0-9]", "");
                            planilha.setHora(valorHora);
                        }

                        case "viaAcesso" -> {
                            String valorViaAcesso = formatter.formatCellValue(cell);
                            planilha.setViaAcesso(valorViaAcesso);
                        }

                        case "valor" -> {
                            String valorValor = formatter.formatCellValue(cell);
                            valorValor = valorValor.replaceAll("[^0-9]", "");
                            planilha.setValor(valorValor);
                        }

                        case "OBS" -> {
                            String valorObservacoes = formatter.formatCellValue(cell);
                            planilha.setObservacao(valorObservacoes);
                        }
                    }
                }
                planilha.mapaTipoAto();
                planilha.mapaViaAcesso();
                relatorio.planilhaReport(planilha);
                automacao.delayInicial();
                automacao.autoKeybord(planilha, driver, selenium);
                relatorio.autoReport(planilha, automacao);
                setCellMensagem(selenium, sheet, i);
                setCellCadastro(selenium, planilha, sheet, i);
            }
            try (FileOutputStream fos = new FileOutputStream(filePath())) {
                workbook.write(fos);
            }
            workbook.close();

        } catch (IOException e) {
            logger.severe("Erro ao tentar executar automação: " + e.getMessage());
            logger.log(java.util.logging.Level.SEVERE, "Detalhes do erro: ", e);
        }
    }
}
