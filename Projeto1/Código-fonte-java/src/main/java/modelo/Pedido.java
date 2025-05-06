package modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pedido {
    private int id;
    private int clienteId;
    private LocalDate dataPedido;
    private BigDecimal valorTotal;

    public Pedido(int id, int clienteId, LocalDate dataPedido, BigDecimal valorTotal) {
        this.id = id;
        this.clienteId = clienteId;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
    }

    public Pedido(int clienteId, LocalDate dataPedido, BigDecimal valorTotal) {
        this.clienteId = clienteId;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public Pedido() {
        // Construtor vazio necessário para instanciar sem passar parâmetros
    }
}
