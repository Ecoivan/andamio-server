/*
 * Copyright (c) 2016 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * zylkanexy server: Helps you to develop easily AJAX web applications 
 *               by copying and modifying this Java Server.
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
package net.daw.dao.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.bean.implementation.DocumentoBean;
import net.daw.bean.implementation.PusuarioBean;
import net.daw.dao.publicinterface.TableDaoInterface;
import net.daw.dao.publicinterface.ViewDaoInterface;
import net.daw.data.implementation.MysqlData;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.helper.statics.Log4j;
import net.daw.helper.statics.SqlBuilder;

public class DocumentoDao implements ViewDaoInterface<DocumentoBean>, TableDaoInterface<DocumentoBean> {

    private String strTable = "documento";
    private String strSQL = "select * from documento where 1=1 ";
    private MysqlData oMysql = null;
    private Connection oConnection = null;
    private PusuarioBean oPuserSecurity = null;

    public DocumentoDao(Connection oPooledConnection, PusuarioBean oPuserBean_security) throws Exception {
        try {
            oConnection = oPooledConnection;
            oMysql = new MysqlData(oConnection);
            oPuserSecurity = oPuserBean_security;
        } catch (Exception ex) {
            Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
            throw new Exception();
        }
    }

    @Override
    public Long getCount(ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        Long pages = 0L;
        try {
            pages = oMysql.getCount(strSQL);
        } catch (Exception ex) {
            Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
            throw new Exception();
        }
        return pages;
    }

    @Override
    public ArrayList<DocumentoBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(alFilter);
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL += SqlBuilder.buildSqlLimit(oMysql.getCount(strSQL), intRegsPerPag, intPage);
        ArrayList<DocumentoBean> arrDocumento = new ArrayList<>();
        ResultSet oResultSet = null;
        try {
            oResultSet = oMysql.getAllSQL(strSQL);
            while (oResultSet.next()) {
                DocumentoBean oDocumentoBean = new DocumentoBean();
                arrDocumento.add(oDocumentoBean.fill(oResultSet, oConnection, oPuserSecurity, expand));
            }
            if (oResultSet != null) {
                oResultSet.close();
            }
        } catch (Exception ex) {
            Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
            throw new Exception();
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
        }
        return arrDocumento;
    }

    @Override
    public ArrayList<DocumentoBean> getAll(ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(alFilter);
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        ArrayList<DocumentoBean> arrDocumento = new ArrayList<>();
        ResultSet oResultSet = null;
        try {
            oResultSet = oMysql.getAllSQL(strSQL);
            while (oResultSet.next()) {
                DocumentoBean oDocumentoBean = new DocumentoBean();
                arrDocumento.add(oDocumentoBean.fill(oResultSet, oConnection, oPuserSecurity, expand));
            }
        } catch (Exception ex) {
            Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
            throw new Exception();
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
        }
        return arrDocumento;
    }

    @Override
    public DocumentoBean get(DocumentoBean oDocumentoBean, Integer expand) throws Exception {
        if (oDocumentoBean.getId() > 0) {
            ResultSet oResultSet = null;
            try {
                oResultSet = oMysql.getAllSQL(strSQL + " And id= " + oDocumentoBean.getId() + " ");
                Boolean empty = true;
                while (oResultSet.next()) {
                    oDocumentoBean = oDocumentoBean.fill(oResultSet, oConnection, oPuserSecurity, expand);
                    empty = false;
                }
                if (empty) {
                    oDocumentoBean.setId(0);
                }
            } catch (Exception ex) {
                Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
                throw new Exception();
            } finally {
                if (oResultSet != null) {
                    oResultSet.close();
                }
            }
        } else {
            oDocumentoBean.setId(0);
        }
        return oDocumentoBean;
    }

    @Override
    public Integer set(DocumentoBean oDocumentoBean) throws Exception {
        Integer iResult = null;
        try {
            if (oDocumentoBean.getId() == 0) {
                strSQL = "INSERT INTO " + strTable + " ";
                strSQL += "(" + oDocumentoBean.getColumns() + ")";
                strSQL += "VALUES(" + oDocumentoBean.getValues() + ")";
                iResult = oMysql.executeInsertSQL(strSQL);
            } else {
                strSQL = "UPDATE " + strTable + " ";
                strSQL += " SET " + oDocumentoBean.toPairs();
                strSQL += " WHERE id=" + oDocumentoBean.getId();
                iResult = oMysql.executeUpdateSQL(strSQL);
            }
        } catch (Exception ex) {
            Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
            throw new Exception();
        }
        return iResult;
    }

    @Override
    public Integer remove(Integer id) throws Exception {
        int result = 0;
        try {
            result = oMysql.removeOne(id, strTable);
        } catch (Exception ex) {
            Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
            throw new Exception();
        }
        return result;
    }

}
