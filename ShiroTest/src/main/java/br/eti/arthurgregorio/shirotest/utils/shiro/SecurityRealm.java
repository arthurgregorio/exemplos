package br.eti.arthurgregorio.shirotest.utils.shiro;

import br.eti.arthurgregorio.shirotest.entities.User;
import br.eti.arthurgregorio.shirotest.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 30/09/2016
 */
public class SecurityRealm extends AuthorizingRealm {

    private final AccountService accountService;
    
    /**
     * 
     * @param accountService 
     */
    public SecurityRealm(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 
     * @param authenticationToken
     * @return
     * @throws AuthenticationException 
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) 
            throws AuthenticationException {
        
        final UsernamePasswordToken token = 
                (UsernamePasswordToken) authenticationToken;
        
        final User user = this.accountService
                .findUserByUsername(token.getUsername());
        
        if (user != null) {
            return new SimpleAuthenticationInfo(
                    token.getUsername(), user.getPassword(), this.getName());
        }
        
        throw new IncorrectCredentialsException();
    }

    /**
     * 
     * @param principalCollection
     * @return 
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        
    }
}
