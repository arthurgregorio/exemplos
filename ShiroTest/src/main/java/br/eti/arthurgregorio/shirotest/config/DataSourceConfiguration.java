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
        className = "org.h2.jdbcx.JdbcDataSource",
        url = "jdbc:h2:mem:shirotest;DB_CLOSE_DELAY=-1",
        user = "sa_shirotest",
        password = "sa_shirotest")
public class DataSourceConfiguration { }
