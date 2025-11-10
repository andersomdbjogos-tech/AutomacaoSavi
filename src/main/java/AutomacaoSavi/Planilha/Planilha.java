package AutomacaoSavi.Planilha;

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

    public int getNumeroTipoAto() {
        return numeroTipoAto;
    }

    public void setNumeroTipoAto(int numeroTipoAto) {
        this.numeroTipoAto = numeroTipoAto;
    }

    public int getNumeroViaAcesso() {
        return numeroViaAcesso;
    }

    public void setNumeroViaAcesso(int numeroViaAcesso) {
        this.numeroViaAcesso = numeroViaAcesso;
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
