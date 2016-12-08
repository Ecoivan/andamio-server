/*
 * Copyright (c) 2016 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * zylkanexy server: Helps you to develop easily AJAX web applications 
 *                   by copying and modifying this Java Server.
 *
 * Sources at https://github.com/rafaelaznar/zylkanexy
 * 
 * zylkanexy server is distributed under the MIT License (MIT)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.daw.bean.implementation;

import net.daw.bean.publicinterface.GenericBean;
import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.daw.dao.implementation.TipodocumentoDao;
import net.daw.dao.implementation.UsuarioDao;
import net.daw.helper.statics.EncodingUtilHelper;

public class DocumentoBean implements GenericBean {

    @Expose
    private Integer id;
    @Expose
    private String titulo = "";
    @Expose
    private String contenido = "";
    @Expose
    private Date alta = new Date();
    @Expose
    private Date cambio = new Date();
    @Expose
    private Integer hits = 0;
    @Expose(serialize = false)
    private Integer id_usuario = 0;
    @Expose(deserialize = false)
    private UsuarioBean obj_usuario = null;
    @Expose(serialize = false)
    private Integer id_tipodocumento = 0;
    @Expose(deserialize = false)
    private TipodocumentoBean obj_tipodocumento = null;
    @Expose
    private String etiquetas = "";
    @Expose
    private Boolean publicado = false;
    @Expose
    private Boolean portada = false;
    @Expose
    private Boolean destacado = false;

    public DocumentoBean() {
        this.id = 0;
    }

    public DocumentoBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Date getCambio() {
        return cambio;
    }

    public void setCambio(Date cambio) {
        this.cambio = cambio;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public UsuarioBean getObj_usuario() {
        return obj_usuario;
    }

    public void setObj_usuario(UsuarioBean obj_usuario) {
        this.obj_usuario = obj_usuario;
    }

    public Integer getId_tipodocumento() {
        return id_tipodocumento;
    }

    public void setId_tipodocumento(Integer id_tipodocumento) {
        this.id_tipodocumento = id_tipodocumento;
    }

    public TipodocumentoBean getObj_tipodocumento() {
        return obj_tipodocumento;
    }

    public void setObj_tipodocumento(TipodocumentoBean obj_tipodocumento) {
        this.obj_tipodocumento = obj_tipodocumento;
    }

    public String getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String etiquetas) {
        this.etiquetas = etiquetas;
    }

    public Boolean getPublicado() {
        return publicado;
    }

    public void setPublicado(Boolean publicado) {
        this.publicado = publicado;
    }

    public Boolean getPortada() {
        return portada;
    }

    public void setPortada(Boolean portada) {
        this.portada = portada;
    }

    public Boolean getDestacado() {
        return destacado;
    }

    public void setDestacado(Boolean destacado) {
        this.destacado = destacado;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "titulo,";
        strColumns += "contenido,";
        strColumns += "alta,";
        strColumns += "cambio,";
        strColumns += "hits,";
        strColumns += "id_usuario,";
        strColumns += "id_tipodocumento,";
        strColumns += "etiquetas,";
        strColumns += "publicado,";
        strColumns += "portada,";
        strColumns += "destacado";
        return strColumns;
    }

    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.quotate(titulo) + ",";
        strColumns += EncodingUtilHelper.quotate(contenido) + ",";
        strColumns += EncodingUtilHelper.stringifyAndQuotate(alta) + ",";
        strColumns += EncodingUtilHelper.stringifyAndQuotate(cambio) + ",";
        strColumns += hits + ",";
        strColumns += id_usuario + ",";
        strColumns += id_tipodocumento + ",";
        strColumns += EncodingUtilHelper.quotate(etiquetas) + ",";
        strColumns += publicado + ",";
        strColumns += portada + ",";
        strColumns += destacado;
        return strColumns;
    }

    @Override
    public String toPairs() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strPairs = "";
        //strPairs += "id=" + id + ",";
        strPairs += "titulo=" + EncodingUtilHelper.quotate(titulo) + ",";
        strPairs += "contenido=" + EncodingUtilHelper.quotate(contenido) + ",";
        strPairs += "alta=" + EncodingUtilHelper.quotate(format.format(alta)) + ",";
        strPairs += "cambio=" + EncodingUtilHelper.quotate(format.format(cambio)) + ",";
        strPairs += "hits=" + hits + ",";
        strPairs += "id_usuario=" + id_usuario + ",";
        strPairs += "id_tipodocumento=" + id_tipodocumento + ",";
        strPairs += "etiquetas=" + EncodingUtilHelper.quotate(etiquetas) + ",";
        strPairs += "publicado=" + publicado + ",";
        strPairs += "portada=" + portada + ",";
        strPairs += "destacado=" + destacado;
        return strPairs;
    }

    @Override
    public DocumentoBean fill(ResultSet oResultSet, Connection pooledConnection, PusuarioBean oPuserBean_security, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setTitulo(oResultSet.getString("titulo"));
        this.setContenido(oResultSet.getString("contenido"));
        this.setAlta(oResultSet.getDate("alta"));
        this.setCambio(oResultSet.getDate("cambio"));
        this.setHits(oResultSet.getInt("hits"));
        this.setEtiquetas(oResultSet.getString("etiquetas"));
        this.setPublicado(oResultSet.getBoolean("publicado"));
        this.setPortada(oResultSet.getBoolean("portada"));
        this.setDestacado(oResultSet.getBoolean("destacado"));
        if (expand > 0) {
            UsuarioBean oUsuarioBean = new UsuarioBean();
            UsuarioDao oUsuarioDao = new UsuarioDao(pooledConnection, oPuserBean_security);
            oUsuarioBean.setId(oResultSet.getInt("id_usuario"));
            oUsuarioBean = oUsuarioDao.get(oUsuarioBean, expand - 1);
            this.setObj_usuario(oUsuarioBean);
        } else {
            this.setId_usuario(oResultSet.getInt("id_usuario"));
        }
        if (expand > 0) {
            TipodocumentoBean oTipodocumentoBean = new TipodocumentoBean();
            TipodocumentoDao oTipodocumentoDao = new TipodocumentoDao(pooledConnection, oPuserBean_security);
            oTipodocumentoBean.setId(oResultSet.getInt("id_tipodocumento"));
            oTipodocumentoBean = oTipodocumentoDao.get(oTipodocumentoBean, expand - 1);
            this.setObj_tipodocumento(oTipodocumentoBean);
        } else {
            this.setId_tipodocumento(oResultSet.getInt("id_tipodocumento"));
        }
        return this;
    }

}
