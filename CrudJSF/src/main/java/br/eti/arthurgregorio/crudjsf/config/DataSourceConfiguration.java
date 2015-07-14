package br.eti.arthurgregorio.crudjsf.config;

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
        name = "java:app/datasources/CrudJsfDS",
        className = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
        url = "jdbc:mysql://localhost:3306/crudjsf",
        user = "sa_crudjsf",
        password = "sa_crudjsf")
public class DataSourceConfiguration { }
