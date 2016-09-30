package br.eti.arthurgregorio.shirotest.utils.shiro;

import br.eti.arthurgregorio.shirotest.service.AccountService;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 29/09/2016
 */
@ApplicationScoped
public class ShiroConfiguration {

    @Inject
    private AccountService accountService;

    private DefaultWebSecurityManager securityManager;
    
    /**
     *
     */
    public ShiroConfiguration() { }

    /**
     *
     * @return
     */
    @Produces
    public WebSecurityManager getSecurityManager() {
        if (this.securityManager == null) {
            this.securityManager = new DefaultWebSecurityManager(
                    new SecurityRealm(this.accountService));
        }
        return this.securityManager;
    }

    /**
     *
     * @return
     */
    @Produces
    public FilterChainResolver getFilterChainResolver() {

    }
}
