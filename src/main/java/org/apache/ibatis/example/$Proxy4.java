package org.apache.ibatis.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

import org.apache.ibatis.example.dao.BlogMapper;
import org.apache.ibatis.example.entity.Blog;

/**
 * 通过arthas获取到的BlogMapper的代理类
 */
/*public final class $Proxy4 extends Proxy implements BlogMapper {
  private static Method m1;
  private static Method m3;
  private static Method m2;
  private static Method m0;

  static {
    try {
      m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
      m3 = Class.forName("org.apache.ibatis.example.dao.BlogMapper").getMethod("selectBlog", Class.forName("java.lang.Long"));
      m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
      m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
    } catch (NoSuchMethodException noSuchMethodException) {
      throw new NoSuchMethodError(noSuchMethodException.getMessage());
    } catch (ClassNotFoundException classNotFoundException) {
      throw new NoClassDefFoundError(classNotFoundException.getMessage());
    }
  }

  public $Proxy4(InvocationHandler invocationHandler) {
    super(invocationHandler);
  }

  *//**
   * @see org.apache.ibatis.binding.MapperProxy#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
   *//*
  public final Blog selectBlog(Long l) {
    try {
      return (Blog) this.h.invoke(this, m3, new Object[]{l});
    } catch (Error | RuntimeException throwable) {
      throw throwable;
    } catch (Throwable throwable) {
      throw new UndeclaredThrowableException(throwable);
    }
  }


  public final boolean equals(Object object) {
    try {
      return (Boolean) this.h.invoke(this, m1, new Object[]{object});
    } catch (Error | RuntimeException throwable) {
      throw throwable;
    } catch (Throwable throwable) {
      throw new UndeclaredThrowableException(throwable);
    }
  }

  public final String toString() {
    try {
      return (String) this.h.invoke(this, m2, null);
    } catch (Error | RuntimeException throwable) {
      throw throwable;
    } catch (Throwable throwable) {
      throw new UndeclaredThrowableException(throwable);
    }
  }

  public final int hashCode() {
    try {
      return (Integer) this.h.invoke(this, m0, null);
    } catch (Error | RuntimeException throwable) {
      throw throwable;
    } catch (Throwable throwable) {
      throw new UndeclaredThrowableException(throwable);
    }
  }
}*/
