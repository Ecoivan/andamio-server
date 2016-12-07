/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.UsuarioDao;
import net.daw.helper.statics.EncodingUtilHelper;

/**
 *
 * @author a022595832b
 */
public class TituloBean implements GenericBean{
    
    @Expose
    private int id = 0;
    
    @Expose
    private String descripcion;
    
    @Expose(serialize = false)
    private Integer id_usuario = 0;
    
    @Expose(deserialize = false)
    private UsuarioBean obj_usuario = null;
    
    public TituloBean() {
    }

    public TituloBean(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    
    

    @Override
    public String getColumns() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        String strColumns = "";
        strColumns += "id,";
        strColumns += "descripcion,";
        strColumns += "id_usuario";
        return strColumns;
    }

    @Override
    public String getValues() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.quotate(descripcion) + ",";
        strColumns += id_usuario;
        return strColumns;
    }

    @Override
    public String toPairs() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        String strPairs = "";
        //strPairs += "id=" + id + ",";
        strPairs += "descripcion=" + EncodingUtilHelper.quotate(descripcion) + ",";
        strPairs += "id_usuario=" + id_usuario;
        return strPairs;
    }

    @Override
    public TituloBean fill(ResultSet oResultSet, Connection pooledConnection, PusuarioBean oPusuarioBean_security, Integer expand) throws SQLException, Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        this.setId(oResultSet.getInt("id"));
        this.setDescripcion(oResultSet.getString("descripcion"));
        if (expand > 0) {
            UsuarioBean oUsuarioBean = new UsuarioBean();
            UsuarioDao oUsuarioDao = new UsuarioDao(pooledConnection, oPusuarioBean_security);
            oUsuarioBean.setId(oResultSet.getInt("id_usuario"));
            oUsuarioBean = oUsuarioDao.get(oUsuarioBean, expand - 1);
            this.setObj_usuario(oUsuarioBean);
        } else {
            this.setId_usuario(oResultSet.getInt("id_usuario"));
        }
        return this;
    }
    
}
