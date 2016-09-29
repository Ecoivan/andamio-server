/*
 * Copyright (c) 2016 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * zylka server: Helps you to develop easily AJAX web applications 
 *               by copying and modifying this Java Server.
 *
 * Sources at https://github.com/rafaelaznar/zylka
 * 
 * zylka server is distributed under the MIT License (MIT)
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
import net.daw.bean.implementation.TipodocumentoBean;
import net.daw.dao.publicinterface.TableDaoInterface;
import net.daw.dao.publicinterface.ViewDaoInterface;
import net.daw.data.implementation.MysqlDataSpImpl;
import net.daw.helper.statics.ExceptionBooster;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.helper.statics.SqlBuilder;

public class TipodocumentoDao implements ViewDaoInterface<TipodocumentoBean>, TableDaoInterface<TipodocumentoBean> {

    private String strTable = "tipodocumento";
    private String strSQL = "select * from tipodocumento where 1=1 ";
    private MysqlDataSpImpl oMysql = null;
    private Connection oConnection = null;

    public TipodocumentoDao(Connection oPooledConnection) throws Exception {
        try {
            oConnection = oPooledConnection;
            oMysql = new MysqlDataSpImpl(oConnection);
        } catch (Exception ex) {
            throw new Exception(this.getClass().getName() + ":constructor ERROR: " + ex.getMessage());
        }
    }

    @Override
    public int getPages(int intRegsPerPag, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        int pages = 0;
        try {
            pages = oMysql.getPages(strSQL, intRegsPerPag);
        } catch (Exception ex) {
            throw new Exception(this.getClass().getName() + ":getPages ERROR: " + ex.getMessage());
        }
        return pages;
    }

    @Override
    public int getCount(ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        int pages = 0;
        try {
            pages = oMysql.getCount(strSQL);
        } catch (Exception ex) {
            throw new Exception(this.getClass().getName() + ":getCount ERROR: " + ex.getMessage());
        }
        return pages;
    }

    @Override
    public ArrayList<TipodocumentoBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> hmFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL += SqlBuilder.buildSqlLimit(oMysql.getCount(strSQL), intRegsPerPag, intPage);
        ArrayList<TipodocumentoBean> arrTipodocumento = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    TipodocumentoBean oTipodocumentoBean = new TipodocumentoBean();
                    arrTipodocumento.add(oTipodocumentoBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            throw new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage());
        }
        return arrTipodocumento;
    }

    @Override
    public ArrayList<TipodocumentoBean> getAll(ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        ArrayList<TipodocumentoBean> arrTipodocumento = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    TipodocumentoBean oTipodocumentoBean = new TipodocumentoBean();
                    arrTipodocumento.add(oTipodocumentoBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            throw new Exception(this.getClass().getName() + ":getAll ERROR: " + ex.getMessage());
        }
        return arrTipodocumento;
    }

    @Override
    public TipodocumentoBean get(TipodocumentoBean oTipodocumentoBean, Integer expand) throws Exception {
        if (oTipodocumentoBean.getId() > 0) {
            try {
                ResultSet oResultSet = oMysql.getAllSql(strSQL + " And id= " + oTipodocumentoBean.getId() + " ");
                if (oResultSet != null) {
                    while (oResultSet.next()) {
                        oTipodocumentoBean = oTipodocumentoBean.fill(oResultSet, oConnection, expand);
                    }
                }
            } catch (Exception ex) {
                throw new Exception(this.getClass().getName() + ":get ERROR: " + ex.getMessage());
            }
        } else {
            oTipodocumentoBean.setId(0);
        }
        return oTipodocumentoBean;
    }

    @Override
    public Integer set(TipodocumentoBean oTipodocumentoBean) throws Exception {
          Integer iResult = null;
        try {
            if (oTipodocumentoBean.getId() == 0) {
                strSQL = "INSERT INTO " + strTable + " ";
                strSQL += "(" + oTipodocumentoBean.getColumns() + ")";
                strSQL += "VALUES(" + oTipodocumentoBean.getValues() + ")";
                iResult = oMysql.executeInsertSQL(strSQL);
            } else {
                strSQL = "UPDATE " + strTable + " ";
                strSQL += " SET " + oTipodocumentoBean.toPairs();
                strSQL += " WHERE id=" + oTipodocumentoBean.getId();
                iResult = oMysql.executeUpdateSQL(strSQL);
            }

        } catch (Exception ex) {
            throw new Exception(this.getClass().getName() + ":set ERROR: " + ex.getMessage());
        }
        return iResult;
    }

    @Override
    public Integer remove(Integer id) throws Exception {
        int result = 0;
        try {
            result = oMysql.removeOne(id, strTable);
        } catch (Exception ex) {
            throw new Exception(this.getClass().getName() + ":remove ERROR: " + ex.getMessage());
        }
        return result;
    }

}
