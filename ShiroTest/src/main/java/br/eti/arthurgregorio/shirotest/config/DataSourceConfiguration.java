package br.eti.arthurgregorio.shirotest.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Use CDI para injetar o entityManager
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 13/07/2015
 */
@Startup
@Singleton
@DataSourceDefinition(
        name = "java:/datasources/ShiroTestDS",
        className = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
        url = "jdbc:mysql://localhost:3306/shirotest",
        user = "sa_shirotest",
        password = "sa_shirotest")
public class DataSourceConfiguration { }
