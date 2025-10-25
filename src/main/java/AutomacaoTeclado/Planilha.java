package AutomacaoTeclado;

import AutoTest.TratamentoTest01;

import java.util.HashMap;
import java.util.Map;

public class Planilha {
    private String senha;
    private String quantidade;
    private String tipoAto;
    private int numeroTipoAto;
    private String data;
    private String hora;
    private String viaAcesso;
    private int numeroViaAcesso;
    private String valor;
    private String observacao;


    public void relatorio() {
        System.out.println("---------------Relatorio---------------");
        System.out.println("Senha foi armazenada(" + getSenha() + ")");
        System.out.println("========================================");
        System.out.println("Quantidade foi armazenada(" + getQuantidade() + ")");
        System.out.println("========================================");
        System.out.println("Tipo do ato foi armazenado(" + getTipoAto() + ")");
        System.out.println("========================================");
        System.out.println("Numero do tipo do ato foi armazenado(" + getNumeroTipoAto() + ")");
        System.out.println("========================================");
        System.out.println("Data foi armazenada(" + getData() + ")");
        System.out.println("========================================");
        System.out.println("Hora foi armazenada(" + getHora() + ")");
        System.out.println("========================================");
        System.out.println("Via de acesso foi armazenada(" + getViaAcesso() + ")");
        System.out.println("========================================");
        System.out.println("Numero da via de acesso foi armazenado(" + getNumeroViaAcesso() + ")");
        System.out.println("========================================");
        System.out.println("Valor foi armazenado(" + getValor() + ")");
        System.out.println("========================================");
    }

    public void mapaTipoAto() {
        Map<String, Integer> tipoAtoMap = new HashMap<>();
        tipoAtoMap.put("A.ESP", 1);
        tipoAtoMap.put("ANEST", 2);
        tipoAtoMap.put("ASS.RN.BER", 3);
        tipoAtoMap.put("ASS.RN.PARTO", 4);
        tipoAtoMap.put("AUX.ANEST", 5);
        tipoAtoMap.put("CIR/OBST", 6);
        tipoAtoMap.put("CONS/HON", 7);
        tipoAtoMap.put("D.AUX", 8);
        tipoAtoMap.put("PACOTE", 9);
        tipoAtoMap.put("PARECER", 10);
        tipoAtoMap.put("PERF", 11);
        tipoAtoMap.put("1AUX", 12);
        tipoAtoMap.put("2AUX", 13);
        tipoAtoMap.put("3AUX", 14);

        this.numeroTipoAto = tipoAtoMap.getOrDefault(getTipoAto(), 0);
    }

    public void mapaViaAcesso() {
        Map<String, Integer> viaAcessoMap = new HashMap<>();
        viaAcessoMap.put("M", 1);
        viaAcessoMap.put("D", 2);

        this.numeroViaAcesso = viaAcessoMap.getOrDefault(getViaAcesso(), 0);
    }

    public int verificacaoCadastro(TratamentoPopUps tratamento) {
        String mensagem = tratamento.getMensagemPopUp();
        return mensagem.contains("Honorário Médico processado com sucesso") ? 1 : 0;
    }

    public int getNumeroViaAcesso() {
        return numeroViaAcesso;
    }

    public void setNumeroViaAcesso(int numeroViaAcesso) {
        this.numeroViaAcesso = numeroViaAcesso;
    }

    public int getNumeroTipoAto() {
        return numeroTipoAto;
    }

    public void setNumeroTipoAto(int numeroTipoAto) {
        this.numeroTipoAto = numeroTipoAto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getTipoAto() {
        return tipoAto;
    }

    public void setTipoAto(String tipoAto) {
        this.tipoAto = tipoAto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getViaAcesso() {
        return viaAcesso;
    }

    public void setViaAcesso(String viaAcesso) {
        this.viaAcesso = viaAcesso;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
