package org.apache.ibatis.example.interceptor;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/*@Intercepts({@Signature(
    type = Executor.class,
    method = "query",
    args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
), @Signature(
    type = Executor.class,
    method = "query",
    args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
)})
public class LimitInterceptor implements Interceptor {


  public LimitInterceptor() {
  }

  public Object intercept(Invocation invocation) throws Throwable {
    if (!this.limitProperties.isOffline() && !LimitThreadLocal.isNotSupport()) {
      Object[] args = invocation.getArgs();
      MappedStatement ms = (MappedStatement) args[0];
      Object parameter = args[1];
      BoundSql boundSql = args.length == 4 ? ms.getBoundSql(parameter) : (BoundSql) args[5];
      SqlHandler sqlHandler = SqlHandler.build(boundSql, this.limitProperties.limit());
      if (!sqlHandler.needOverride()) {
        return invocation.proceed();
      } else {
        Executor executor = (Executor) invocation.getTarget();
        RowBounds rowBounds = (RowBounds) args[2];
        ResultHandler resultHandler = (ResultHandler) args[3];
        CacheKey cacheKey = args.length == 4 ? executor.createCacheKey(ms, parameter, rowBounds, boundSql) : (CacheKey) args[4];
        MetaObject metaObject = SystemMetaObject.forObject(boundSql);
        metaObject.setValue("sql", sqlHandler.getNewSql());
        return executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
      }
    } else {
      return invocation.proceed();
    }
  }

  public Object plugin(Object o) {
    return Plugin.wrap(o, this);
  }

  public void setProperties(Properties properties) {
  }
}*/
