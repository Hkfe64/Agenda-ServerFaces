package com.hkfe.controle;

import com.hkfe.dao.PessoaDAO;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.hkfe.modelo.Pessoa;
import com.hkfe.dao.PessoaDataModel;

import org.primefaces.model.LazyDataModel;

/**
 *
 * @author hkfe6
 */
@ManagedBean
@ViewScoped
public class PessoaControle implements Serializable {

    private Pessoa pessoa = new Pessoa();

    private LazyDataModel<Pessoa> lazyModel;

    @PostConstruct
    public void init() {
        listarPessoas();
    }

    public void listarPessoas() {
        try {
            lazyModel = new PessoaDataModel();
            limparPessoa();
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }

    }

    public void limparPessoa() {
        pessoa = new Pessoa();
    }

    public void salvarPessoa() {
        if (pessoa.getId() == null) {
            new PessoaDAO().createPessoa(pessoa);
        } else {
            new PessoaDAO().updatePessoa(pessoa);
        }
        listarPessoas();
    }

    public void excluirPessoa() {
        new PessoaDAO().deletePessoa(pessoa);
        listarPessoas();
    }

    /**
     * @return the pessoa
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public LazyDataModel<Pessoa> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Pessoa> lazyModel) {
        this.lazyModel = lazyModel;
    }
}
