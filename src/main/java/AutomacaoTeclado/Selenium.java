package AutomacaoTeclado;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

public class Selenium {

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

    public void campoInformarSenha(WebDriver driver) {
        try {
            WebElement campoSenha = driver.findElement(By.id("formulario:cd_senha"));
            campoSenha.click();

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selecionarCampo(WebDriver driver, int i){
        try {
            switch (i){
                case 1 -> {
                    WebElement campoSenha = driver.findElement(By.id("formulario:cd_senha"));
                    campoSenha.click();
                }

            }

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMensagemPopUp(String mensagemPopUp) {
        this.mensagemPopUp = mensagemPopUp;
    }

    public String getMensagemPopUp() {
        return mensagemPopUp;
    }
}