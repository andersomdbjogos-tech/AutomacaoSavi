package AutoTest;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


/*
Botão fechar do popup:
<button type="button" class="btn btn-info" onclick="$('#formulario\\:messages').text('');$('#ModalMensagens').modal('hide');">Fechar</button>
Mensagem do POP-UP:
<span class="ui-messages-warn-summary">Tipo do ato já registrado para este procedimento.</span>

<span class="ui-messages-info-summary">Honorário Médico processado com sucesso.</span>

<input id="formulario:cd_senha" type="text" name="formulario:cd_senha" class="form-control uppercase" maxlength="9">

<span class="ui-messages-warn-summary">Senha não localizada. Por favor, tente novamente.</span>
 */

public class TratamentoTest01 {

    private String mensagemPopUp;

    public void tratarPopUp(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {

            List<WebElement> mensagens = driver.findElements(By.cssSelector("span.ui-messages-warn-summary, span.ui-messages-info-summary"));

            for (WebElement msg : mensagens){
                String texto = msg.getText();

                if (!texto.isEmpty()) {
                    System.out.println("Mensagem lida: " + texto);
                    setMensagemPopUp(texto);
                    break;
                }
            }

            WebElement botaoFechar = driver.findElement(By.cssSelector("button.btn.btn-info"));
            botaoFechar.click();

        } catch (TimeoutException e) {
            System.out.println("Nenhum pop-up detectado.");
        } catch (Exception e) {
            System.out.println("Erro ao tratar pop-up: " + e.getMessage());
        }
    }

    public void campoInformarSenha(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement campoSenha = driver.findElement(By.id("formulario:cd_senha"));
            campoSenha.click();

        } catch (TimeoutException e) {
            System.out.println("Nenhum pop-up detectado.");
        } catch (Exception e) {
            System.out.println("Erro ao tratar pop-up: " + e.getMessage());
        }
    }

    public void limparCampo(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement campoSenha = driver.findElement(By.id("formulario:cd_senha"));
            campoSenha.clear();

        } catch (TimeoutException e) {
            System.out.println("Nenhum campo detectado.");
        } catch (Exception e) {
            System.out.println("Erro ao tratar campo: " + e.getMessage());
        }
    }

    public void setMensagemPopUp(String mensagemPopUp) {
        this.mensagemPopUp = mensagemPopUp;
    }

    public String getMensagemPopUp() {
        return mensagemPopUp;
    }
}