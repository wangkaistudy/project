package com.study.clay.common.util;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.util.StringUtils;

import javax.persistence.MappedSuperclass;
@MappedSuperclass
public class HibernatePhysicalNamingNamingStrategy extends
        PhysicalNamingStrategyStandardImpl {
    @Override
    public Identifier toPhysicalTableName(Identifier name,
                                          JdbcEnvironment context) {
      return new Identifier(name.getText(), name.isQuoted());
    }
    @Override
    public Identifier toPhysicalColumnName(Identifier identifier,
      JdbcEnvironment jdbcEnv) {
      return convert(identifier);
    }
    // 更改Hibernate表名映射规则，保持Entity类中对数据库表命名的不变
    private Identifier convert(Identifier identifier) {
    if (identifier == null || ! StringUtils.hasText(identifier.getText())) {
          return identifier;
      }
      String regex = "([a-z])([A-Z])";
      String replacement = "$1_$2";
      String newName = identifier.getText().replaceAll(regex,
          replacement).toLowerCase();
      return Identifier.toIdentifier(newName);
  }
}
