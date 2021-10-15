package org.apache.ibatis.example.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.plugin.javascript.ReflectUtil;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author jiangwenjie
 * @date 2021/8/8
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),})
public class MyInteceptor implements Interceptor {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  static int MAPPED_STATEMENT_INDEX = 0;// 这是对应上面的args的序号
  static int PARAMETER_INDEX = 1;

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    Object[] queryArgs = invocation.getArgs();
    MappedStatement mappedStatement = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
    Object parameter = queryArgs[PARAMETER_INDEX];
    BoundSql boundSql = mappedStatement.getBoundSql(parameter);

    String sql = boundSql.getSql();
    // 修改SQL

    sql = sql + " limit 1";
    System.out.println(sql);

    // 重新new一个查询语句对像
    BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
    // 把新的查询放到statement里
    MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));
    for (ParameterMapping mapping : boundSql.getParameterMappings()) {
      String prop = mapping.getProperty();
      if (boundSql.hasAdditionalParameter(prop)) {
        newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
      }
    }
    queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
    return invocation.proceed();
  }

  /**
   * 返回封装的目标对象
   * @param target
   * @return
   */
  @Override
  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  /**
   * 设置自定义参数属性值
   * @param properties
   */
  @Override
  public void setProperties(Properties properties) {
    String dialect = properties.getProperty("dialect");
    System.out.println(dialect);
    logger.info("mybatis intercept dialect:{}", dialect);
  }
  private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
    MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
    builder.resource(ms.getResource());
    builder.fetchSize(ms.getFetchSize());
    builder.statementType(ms.getStatementType());
    builder.keyGenerator(ms.getKeyGenerator());
    if (ms.getKeyProperties() != null && ms.getKeyProperties().length > 0) {
      builder.keyProperty(ms.getKeyProperties()[0]);
    }
    builder.timeout(ms.getTimeout());
    builder.parameterMap(ms.getParameterMap());
    builder.resultMaps(ms.getResultMaps());
    builder.resultSetType(ms.getResultSetType());
    builder.cache(ms.getCache());
    builder.flushCacheRequired(ms.isFlushCacheRequired());
    builder.useCache(ms.isUseCache());
    return builder.build();
  }

  public static class BoundSqlSqlSource implements SqlSource {
    private BoundSql boundSql;
    public BoundSqlSqlSource(BoundSql boundSql) {
      this.boundSql = boundSql;
    }
    public BoundSql getBoundSql(Object parameterObject) {
      return boundSql;
    }
  }

}
