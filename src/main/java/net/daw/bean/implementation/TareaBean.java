/*
 * Copyright (c) 2016 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * andamio-server: Helps you to develop easily AJAX web applications 
 *                   by copying and modifying this Java Server.
 *
 * Sources at https://github.com/rafaelaznar/andamio-server
 * 
 * andamio-server is distributed under the MIT License (MIT)
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

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.UsuarioDao;
import net.daw.dao.implementation.EstadoDao;
import net.daw.dao.implementation.PrioridadDao;
import net.daw.dao.implementation.ValoracionDao;
import net.daw.dao.implementation.ProyectoDao;
import net.daw.helper.statics.EncodingUtilHelper;

public class TareaBean implements GenericBean {

    @Expose
    private Integer id = 0;
    @Expose
    private String descripcion;
    @Expose
    private Date alta;
    @Expose
    private Date integracion;
    @Expose
    private Integer puntos;
    @Expose
    private String observaciones;
    @Expose
    private Date fechaturno;
    @Expose
    private Integer turno;
    @Expose
    private Integer numeroturno;

    @Expose(serialize = false)
    private Integer id_usuario = 0;
    @Expose(deserialize = false)
    private UsuarioBean obj_usuario = null;

    @Expose(serialize = false)
    private Integer id_estado = 0;
    @Expose(deserialize = false)
    private EstadoBean obj_estado = null;

    @Expose(serialize = false)
    private Integer id_prioridad = 0;
    @Expose(deserialize = false)
    private PrioridadBean obj_prioridad = null;

    @Expose(serialize = false)
    private Integer id_valoracion = 0;
    @Expose(deserialize = false)
    private ValoracionBean obj_valoracion = null;

    @Expose(serialize = false)
    private Integer id_proyecto = 0;
    @Expose(deserialize = false)
    private ProyectoBean obj_proyecto = null;
    
    @Expose
    private Integer completada;

    public TareaBean() {
    }

    public TareaBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Date getIntegracion() {
        return integracion;
    }

    public void setIntegracion(Date integracion) {
        this.integracion = integracion;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaturno() {
        return fechaturno;
    }

    public void setFechaturno(Date fechaturno) {
        this.fechaturno = fechaturno;
    }

    public Integer getTurno() {
        return turno;
    }

    public void setTurno(Integer turno) {
        this.turno = turno;
    }

    public Integer getNumeroturno() {
        return numeroturno;
    }

    public void setNumeroturno(Integer numeroturno) {
        this.numeroturno = numeroturno;
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

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public EstadoBean getObj_estado() {
        return obj_estado;
    }

    public void setObj_estado(EstadoBean obj_estado) {
        this.obj_estado = obj_estado;
    }

    public Integer getId_prioridad() {
        return id_prioridad;
    }

    public void setId_prioridad(Integer id_prioridad) {
        this.id_prioridad = id_prioridad;
    }

    public PrioridadBean getObj_prioridad() {
        return obj_prioridad;
    }

    public void setObj_prioridad(PrioridadBean obj_prioridad) {
        this.obj_prioridad = obj_prioridad;
    }

    public Integer getId_valoracion() {
        return id_valoracion;
    }

    public void setId_valoracion(Integer id_valoracion) {
        this.id_valoracion = id_valoracion;
    }

    public ValoracionBean getObj_valoracion() {
        return obj_valoracion;
    }

    public void setObj_valoracion(ValoracionBean obj_valoracion) {
        this.obj_valoracion = obj_valoracion;
    }

    public Integer getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(Integer id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public ProyectoBean getObj_proyecto() {
        return obj_proyecto;
    }

    public void setObj_proyecto(ProyectoBean obj_proyecto) {
        this.obj_proyecto = obj_proyecto;
    }

    public Integer getCompletada() {
        return completada;
    }

    public void setCompletada(Integer completada) {
        this.completada = completada;
    }


    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "descripcion,";
        strColumns += "alta,";
        strColumns += "integracion,";
        strColumns += "puntos,";
        strColumns += "observaciones,";
        strColumns += "fechaturno,";
        strColumns += "turno,";
        strColumns += "numeroturno,";
        strColumns += "id_usuario,";
        strColumns += "id_estado,";
        strColumns += "id_prioridad,";
        strColumns += "id_valoracion,";
        strColumns += "id_proyecto,";
        strColumns += "completada";

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.quotate(descripcion) + ",";
        strColumns += EncodingUtilHelper.stringifyAndQuotate(alta) + ",";
        strColumns += EncodingUtilHelper.stringifyAndQuotate(integracion) + ",";
        strColumns += puntos + ",";
        strColumns += EncodingUtilHelper.quotate(observaciones) + ",";
        strColumns += EncodingUtilHelper.stringifyAndQuotate(fechaturno) + ",";
        strColumns += turno + ",";
        strColumns += numeroturno + ",";
        strColumns += id_usuario + ",";
        strColumns += id_estado + ",";
        strColumns += id_prioridad + ",";
        strColumns += id_valoracion + ",";
        strColumns += id_proyecto + ",";
        strColumns += completada;
        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "descripcion=" + EncodingUtilHelper.quotate(descripcion) + ",";
        strPairs += "alta=" + EncodingUtilHelper.stringifyAndQuotate(alta) + ",";
        strPairs += "integracion=" + EncodingUtilHelper.stringifyAndQuotate(integracion) + ",";
        strPairs += "puntos=" + puntos + ",";
        strPairs += "observaciones=" + EncodingUtilHelper.quotate(observaciones) + ",";
        strPairs += "fechaturno=" + EncodingUtilHelper.stringifyAndQuotate(fechaturno) + ",";
        strPairs += "turno=" + turno + ",";
        strPairs += "numeroturno=" + numeroturno + ",";
        strPairs += "id_usuario=" + id_usuario + ",";
        strPairs += "id_estado=" + id_estado + ",";
        strPairs += "id_prioridad=" + id_prioridad + ",";
        strPairs += "id_valoracion=" + id_valoracion + ",";
        strPairs += "id_proyecto=" + id_proyecto + ",";
        strPairs += "completada=" + completada;
        return strPairs;
    }

    @Override
    public TareaBean fill(ResultSet oResultSet, Connection pooledConnection, PusuarioBean oPuserBean_security, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setDescripcion(oResultSet.getString("descripcion"));
        this.setAlta(oResultSet.getDate("alta"));
        this.setIntegracion(oResultSet.getDate("integracion"));
        this.setPuntos(oResultSet.getInt("puntos"));
        this.setObservaciones(oResultSet.getString("observaciones"));
        this.setFechaturno(oResultSet.getDate("fechaturno"));
        this.setTurno(oResultSet.getInt("turno"));
        this.setNumeroturno(oResultSet.getInt("numeroturno"));
        this.setCompletada(oResultSet.getInt("completada"));

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
            EstadoBean oEstadoBean = new EstadoBean();
            EstadoDao oEstadoDao = new EstadoDao(pooledConnection, oPuserBean_security);
            oEstadoBean.setId(oResultSet.getInt("id_estado"));
            oEstadoBean = oEstadoDao.get(oEstadoBean, expand - 1);
            this.setObj_estado(oEstadoBean);
        } else {
            this.setId_estado(oResultSet.getInt("id_estado"));
        }

        if (expand > 0) {
            PrioridadBean oPrioridadBean = new PrioridadBean();
            PrioridadDao oPrioridadDao = new PrioridadDao(pooledConnection, oPuserBean_security);
            oPrioridadBean.setId(oResultSet.getInt("id_prioridad"));
            oPrioridadBean = oPrioridadDao.get(oPrioridadBean, expand - 1);
            this.setObj_prioridad(oPrioridadBean);
        } else {
            this.setId_prioridad(oResultSet.getInt("id_prioridad"));
        }

        if (expand > 0) {
            ValoracionBean oValoracionBean = new ValoracionBean();
            ValoracionDao oValoracionDao = new ValoracionDao(pooledConnection, oPuserBean_security);
            oValoracionBean.setId(oResultSet.getInt("id_valoracion"));
            oValoracionBean = oValoracionDao.get(oValoracionBean, expand - 1);
            this.setObj_valoracion(oValoracionBean);
        } else {
            this.setId_valoracion(oResultSet.getInt("id_valoracion"));
        }

        if (expand > 0) {
            ProyectoBean oProyectoBean = new ProyectoBean();
            ProyectoDao oProyectoDao = new ProyectoDao(pooledConnection, oPuserBean_security);
            oProyectoBean.setId(oResultSet.getInt("id_proyecto"));
            oProyectoBean = oProyectoDao.get(oProyectoBean, expand - 1);
            this.setObj_proyecto(oProyectoBean);
        } else {
            this.setId_proyecto(oResultSet.getInt("id_proyecto"));
        }

        return this;
    }

}
