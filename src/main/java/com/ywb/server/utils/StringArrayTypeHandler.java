package com.ywb.server.utils;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.type.ArrayTypeHandler;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringArrayTypeHandler extends ArrayTypeHandler {
	  private static Logger logger = LoggerFactory.getLogger(StringArrayTypeHandler.class);
	  public StringArrayTypeHandler() {
		   super();
	  }

	  @Override
	  public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
		  //ps.setArray(i, (Array) parameter);
		  Connection conn = ps.getConnection();
		  List<String> par = (List<String>)parameter;
		  logger.info("###set non null paremeter: {}", par);
		  String[] obj = (String[]) par.toArray(new String[par.size()]);
		  Array array = conn.createArrayOf("text", obj);
		  ps.setArray(i, array);
	  }
	  
	  @Override
	  public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
	    Array array = rs.getArray(columnName);
	    String[] strarray = (String[])array.getArray();
	    logger.info("#####get summary photo: {} : {}", columnName, strarray);
	    return array == null ? null : Arrays.asList(strarray);
	  }

	  @Override
	  public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
	    Array array = rs.getArray(columnIndex);
	    logger.info("#####get summary photo: {} : {}", columnIndex, array.getArray());
	    return array == null ? null : array.getArray();
	  }
}
