package AutomacaoSavi.SeleniumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.logging.Logger;

public class Selenium {
    private static final Logger logger = Logger.getLogger(Selenium.class.getName());
    private String mensagemPopUp;

    public boolean tratarPopUp(WebDriver driver) {
        try {
            List<WebElement> mensagens = driver.findElements(By.cssSelector("span.ui-messages-warn-summary, span.ui-messages-info-summary"));
            if (mensagens.isEmpty()) {
                return false;
            }

            for (WebElement msg : mensagens) {
                String texto = msg.getText();
                if (!texto.isEmpty()) {
                    System.out.println("Mensagem lida: " + texto);
                    setMensagemPopUp(texto);
                    break;
                }
            }

            WebElement botaoFechar = driver.findElement(By.cssSelector("button.btn.btn-info"));
            botaoFechar.click();
            return true;

        } catch (TimeoutException e) {
            System.out.println("Nenhum pop-up detectado.");
            return false;
        } catch (Exception e) {
            System.out.println("Erro ao tratar pop-up: " + e.getMessage());
            return false;
        }
    }

    public void selecionarCampo(WebDriver driver, int i) {
        try {
            switch (i) {
                case 1 -> {
                    WebElement campoSenha = driver.findElement(By.id("formulario:cd_senha"));
                    campoSenha.click();
                }

                case 2 -> {
                    WebElement campoQuantidade = driver.findElement(By.id("formulario:quant_procedimento"));
                    campoQuantidade.click();
                }

                case 3 -> {
                    WebElement campoQuantidade = driver.findElement(By.id("formulario:dataInicialInput"));
                    campoQuantidade.click();
                }

                case 4 -> {
                    WebElement campoQuantidade = driver.findElement(By.id("formulario:hora_atendimento"));
                    campoQuantidade.click();
                }

                case 5 -> {
                    WebElement campoQuantidade = driver.findElement(By.id("formulario:valor"));
                    campoQuantidade.click();
                }
            }

        } catch (Exception e) {
            logger.severe("Erro ao tentar selecionar campo: " + e.getMessage());
            logger.log(java.util.logging.Level.SEVERE, "Detalhes do erro: ", e);
        }
    }

    public void setMensagemPopUp(String mensagemPopUp) {
        this.mensagemPopUp = mensagemPopUp;
    }

    public String getMensagemPopUp() {
        return mensagemPopUp;
    }
}