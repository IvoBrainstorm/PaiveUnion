/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Paulo Amosse
 */
@Entity
@Table(name = "MATERIAL")
public class Material implements Serializable {

    @Id
    @GeneratedValue
    private Integer materialID;
    private String codigoMaterial;
    private String nome;
    private int quantidade;
    private double precoDeAluguer;
    private String categoria;
    private String descricao;
    private int pessoasMesa;
    private int quantidadeMinima; //Nr minimo de determinado material a ser alugado.
    private boolean disponivel;
    private boolean apagado;

    @ManyToOne
    private Salao salao;

    public Material() {
        disponibilizar();
    }

    public Integer getMaterialID() {
        return materialID;
    }

    public void setMaterialID(Integer materialID) {
        this.materialID = materialID;
    }

    public String getCodigoMaterial() {
        return codigoMaterial;
    }

    public void setCodigoMaterial(String codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade >= 0) {
            this.quantidade = quantidade;
        } else{
            throw new Error("A quantidade do material deve ser positiva.");
        }
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Salao getSalao() {
        return salao;
    }

    public void setSalao(Salao salao) {
        this.salao = salao;
    }

    public double getPrecoDeAluguer() {
        return precoDeAluguer;
    }

    public void setPrecoDeAluguer(double precoDeAluguer) {
        this.precoDeAluguer = precoDeAluguer;
    }

    private void setApagado(boolean apagado) {
        this.apagado = apagado;
    }

    public boolean isApagado() {
        return apagado;
    }

    public int getPessoasMesa() {
        return pessoasMesa;
    }

    public void setPessoasMesa(int pessoasMesa) {
        this.pessoasMesa = pessoasMesa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void apagar() {
        this.setApagado(true);
    }

    public void recuperar() {
        this.setApagado(false);
    }

    public boolean disponibilizar() {
        if (this.getQuantidade() > 0) {
            this.setDisponivel(true);
            return true;
        }
        return false;
    }

    public boolean indisponibilizar() {
        if (this.getQuantidade() == 0) {
            this.setDisponivel(false);
            return true;
        }
        return false;
    }

    public boolean adicionar(int quantidade) {
        if (quantidade > 0) {
            this.setQuantidade(this.getQuantidade() + quantidade);
            return true;
        }
        return false;
    }

    public boolean retirar(int quantidade) {
        if ((this.getQuantidade() > 0) && (this.getQuantidade() - quantidade) >= 0) {
            this.setQuantidade(this.getQuantidade() - quantidade);
            if (this.getQuantidade() == 0) {
                indisponibilizar();
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return nome;
    }

}
