
package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.UsuarioDao;
import net.daw.helper.statics.EncodingUtilHelper;

/**
 *
 * @author a044888329b
 */
public class PostBean implements GenericBean{
    
    @Expose
    private Integer id = 0;
    @Expose
    private Date fecha = new Date();
    @Expose
    private String titulo;
    @Expose
    private String contenido;
    @Expose(serialize = false)
    private Integer id_titulo = 0;
    @Expose(deserialize = false)
    private TituloBean obj_titulo = null;
    @Expose(serialize = false)
    private Integer id_usuario = 0;
    @Expose(deserialize = false)
    private UsuarioBean obj_usuario = null;
    
    
    
    public PostBean() {
        
    }
    
    
    public PostBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public Integer getId_titulo() {
        return id_titulo;
    }

    public void setId_titulo(Integer id_titulo) {
        this.id_titulo = id_titulo;
    }

    public TituloBean getObj_titulo() {
        return obj_titulo;
    }

    public void setObj_titulo(TituloBean obj_titulo) {
        this.obj_titulo = obj_titulo;
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
    
    
    
    
    public String getColumns() {
        String strColumns = "";
        
        strColumns += "id,";
        strColumns += "fecha,";
        strColumns += "titulo,";
        strColumns += "contenido,";
        strColumns += "id_titulo,";
        strColumns += "id_usuario";
        
        return strColumns;
    }
    
    
    
    public String getValues() {
        String strColumns = "";
        
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.stringifyAndQuotate(fecha) + ",";
        strColumns += EncodingUtilHelper.quotate(titulo) + ",";
        strColumns += EncodingUtilHelper.quotate(contenido) + ",";
        strColumns += id_titulo + ",";
        strColumns += id_usuario;
        
        return strColumns;
    }
    
    
    
    
    @Override
    public String toPairs() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strPairs = "";
        //strPairs += "id=" + id + ",";
        strPairs += "fecha=" + EncodingUtilHelper.quotate(format.format(fecha)) + ",";
        strPairs += "titulo=" + EncodingUtilHelper.quotate(titulo) + ",";
        strPairs += "contenido=" + EncodingUtilHelper.quotate(contenido) + ",";
        strPairs += "id_titulo=" + id_titulo + ",";
        strPairs += "id_usuario=" + id_usuario;
        
        return strPairs;
    }
    
    
    
    @Override
    public PostBean fill(ResultSet oResultSet, Connection pooledConnection, PusuarioBean oPusuarioBean_security, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setFecha(oResultSet.getDate("fecha"));
        this.setTitulo(oResultSet.getString("titulo"));
        this.setContenido(oResultSet.getString("contenido"));
        
        
        if (expand > 0) {
            TituloBean oTituloBean = new TituloBean();
            TituloDao oTituloDao = new TituloDao(pooledConnection, oPusuarioBean_security);
            oTituloBean.setId(oResultSet.getInt("id_titulo"));
            oTituloBean = oTituloDao.get(oTituloBean, expand - 1);
            this.setObj_titulo(oTituloBean);
        } else {
            this.setId_titulo(oResultSet.getInt("id_titulo"));
        }
        

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
