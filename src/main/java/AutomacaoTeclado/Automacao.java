package AutomacaoTeclado;

import org.openqa.selenium.WebDriver;

import java.util.Scanner;
import java.awt.*;
import java.awt.event.KeyEvent;

/*
Os campos necessarios sao: Informe a senha, quantidade procedimentos, prestador, tipo ato, data, hora, via acesso e valor.
- Logo apos clicar no botão "Novo" são 7 tab para entra no campo "informe a senha".
- 1 clique no tab apos estar no campo "informe a senha" é o botão de pesquisar senha.
- 8 cliques em tab apos o botão de pesquisar senha entra no campo "quantidade procedimentos".
- 1 tab apos o campo "quantidade procedimentos" entra no campo de "prestador" e as setas funcionam para alterar entre as opções.
- Mais 1 tab apos o campo "prestador" entra no campo "Tipo ato" onde as setas tambem funcionam.
- 2 tabs entra no campo "Data" e ele é digitavel e passivel a copia e cola.
- Mais 1 clique no tab entra no campo "Hora" que tambem é passivel a copia e cola.
- Novamente clicando no tab entra no campo "Via Acesso".
- E mais 1 tab entra no campo "Valor".
- 2 tabs entra no botão "Gravar".
- 19 tabs ate fechar o pop up 01 e espaço para executar o botao "Fechar"
- 22 tabs ate retornar ao campo informar senha.
*/

public class Automacao {
    private Robot robot;
    private int numeroDeSetasPres;
    private int numeroDeSetasAto;
    private int numeroDeSetasVia;

    public Automacao(Robot robot) {
        this.robot = robot;
    }

    public void prestadorDefinition() {
        //Definindo qual prestador sera escolhido
        Scanner sequenciaPrestador = new Scanner(System.in);
        System.out.println("Verifique em seu campo de cadastro de guias no Savi " +
                "em qual posição esta o prestador que deseja selecionar " +
                "(Ex: Hapvida é o terceiro prestador listado. Resposta: 3)");
        System.out.print("Sua resposta: ");
        this.numeroDeSetasPres = sequenciaPrestador.nextInt();
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

    public void shortDelay() {
        robot.delay(1000);
    }

    public void longDelay() {
        robot.delay(3000);
    }

    public void delayInicial() {
        robot.delay(5000); // Delay para começar
    }

    public void autoKeybord(Planilha planilha, WebDriver driver, Selenium selenium) {
        //1.1 - Clicar campo senha
        selenium.campoInformarSenha(driver);
        shortDelay();
        //1.2 - Limpando campo senha
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        //2.1 - Escrever senha
        writeText(planilha.getSenha());
        pressTab(1);
        //Passo 2.2 - Pesquisar senha
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_SPACE);
        robot.delay(500);

        //Passo 3.1 - Campo quantidade
        pressTab(8);
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

        //Passo 6.1 - Campo data
        pressTab(2);
        shortDelay();
        //Passo 6.2 - Informar data
        writeText(planilha.getData());

        //Passo 7.1 - Campo hora
        pressTab(1);
        shortDelay();
        //Passo 7.2 - Informar hora
        writeText(planilha.getHora());

        //Passo 8.1 - Campo via de acesso
        pressTab(1);
        //Passo 8.2 - Informar via de acesso
        this.numeroDeSetasVia = planilha.getNumeroViaAcesso();
        if (this.numeroDeSetasVia != 0) {
            for (int i = 0; i < this.numeroDeSetasVia; i++) {
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);
            }
        }

        //Passo 9.1 - Campo valor
        pressTab(1);
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
        //selenium.tratarPopUp(driver);

        int tentativas = 0;
        int limiteTentativas = 5;
        boolean deteccao = false;
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

    public int getNumeroDeSetasAto() {
        return numeroDeSetasAto;
    }

    public int getNumeroDeSetasVia() {
        return numeroDeSetasVia;
    }
}