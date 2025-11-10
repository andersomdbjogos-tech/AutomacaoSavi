package AutomacaoSavi.Automacao;

import AutomacaoSavi.Planilha.Planilha;
import AutomacaoSavi.SeleniumDriver.Selenium;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Automacao {
    private Robot robot;
    private int numeroDeSetasPres;
    private int numeroDeSetasAto;
    private int numeroDeSetasVia;

    public Automacao(Robot robot) {
        this.robot = robot;
    }

    public void pressDown(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
        }
    }

    public void pressTab(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        }
    }

    private void writeText(String texto) {
        for (char c : texto.toCharArray()) {
            boolean maiuscula = Character.isUpperCase(c);
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);

            if (maiuscula) {
                robot.keyPress(KeyEvent.VK_SHIFT);
            }
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);

            if (maiuscula) {
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
        }
    }

    private void cleanText() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    public void shortDelay() {
        robot.delay(1000);
    }

    public void delayInicial() {
        robot.delay(5000); // Delay para começar
    }

    public void autoKeybord(Planilha planilha, WebDriver driver, Selenium selenium) {
        //1.1 - Clicar campo senha (Campo 1)
        selenium.selecionarCampo(driver, 1);
        cleanText();
        shortDelay();

        //2.1 - Escrever senha
        writeText(planilha.getSenha());
        pressTab(1);
        //Passo 2.2 - Pesquisar senha
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_SPACE);
        robot.delay(500);

        //Passo 3.1 - Campo quantidade (Campo 2)
        selenium.selecionarCampo(driver, 2);
        cleanText();
        //Passo 3.2 - Informar quantidade
        writeText(planilha.getQuantidade());

        //Passo 4.1 - Campo prestador
        pressTab(1);
        shortDelay();
        //Passo 4.2 - Informar prestador
        pressDown(this.numeroDeSetasPres);
        shortDelay();

        //Passo 5.1 - Campo tipo de ato
        pressTab(1);
        //Passo 5.2 - Informar tipo de ato
        this.numeroDeSetasAto = planilha.getNumeroTipoAto();
        if (this.numeroDeSetasAto != 0) {
            pressDown(numeroDeSetasAto);
        } else {
            System.out.println("Erro ao ler o tipo de ato");
        }
        shortDelay();

        //Passo 6.1 - Campo data (Campo 3)
        selenium.selecionarCampo(driver, 3);
        cleanText();
        shortDelay();
        //Passo 6.2 - Informar data
        writeText(planilha.getData());

        //Passo 7.1 - Campo hora (Campo 4)
        selenium.selecionarCampo(driver, 4);
        cleanText();
        shortDelay();
        //Passo 7.2 - Informar hora
        writeText(planilha.getHora());

        //Passo 8.1 - Campo via de acesso
        pressTab(1);
        //Passo 8.2 - Informar via de acesso
        this.numeroDeSetasVia = planilha.getNumeroViaAcesso();
        if (this.numeroDeSetasVia != 0) {
            pressDown(this.numeroDeSetasVia);
        }

        //Passo 9.1 - Campo valor (Campo 5)
        selenium.selecionarCampo(driver, 5);
        cleanText();
        //Passo 9.2 - Informar valor
        writeText(planilha.getValor());

        //Passo 10.1 - Campo observacao
        pressTab(1);
        //Passo 10.2 - Campo observacao
        writeText(planilha.getObservacao());

        //Passo 11.1 - Campo gravar guia
        pressTab(1);
        //Passo 11.2 - Gravar guia
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(3000);

        //Passo 12 - tratar pop-up
        int tentativas = 0;
        int limiteTentativas = 5;
        boolean deteccao;
        while (tentativas < limiteTentativas) {
            tentativas++;
            System.out.println(tentativas + "° Tentativa de tratamento do popUp");

            deteccao = selenium.tratarPopUp(driver);
            if (deteccao) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public int getNumeroDeSetasPres() {
        return numeroDeSetasPres;
    }

    public void setNumeroDeSetasPres(int numeroDeSetasPres) {
        this.numeroDeSetasPres = numeroDeSetasPres;
    }

    public int getNumeroDeSetasAto() {
        return numeroDeSetasAto;
    }

    public int getNumeroDeSetasVia() {
        return numeroDeSetasVia;
    }
}