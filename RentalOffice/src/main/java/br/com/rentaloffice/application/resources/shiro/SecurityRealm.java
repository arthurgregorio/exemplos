package br.com.rentaloffice.application.resources.shiro;

import br.com.rentaloffice.model.entities.GroupPermission;
import br.com.rentaloffice.model.entities.User;
import br.com.rentaloffice.model.service.AccountService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
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

        final UsernamePasswordToken token
                = (UsernamePasswordToken) authenticationToken;

        final User user = this.accountService
                .findUserByUsername(token.getUsername());

        if (user != null) {
            return new SimpleAuthenticationInfo(
                    user, user.getPassword(), this.getName());
        }
        throw new IncorrectCredentialsException("Invalid user or password");
    }

    /**
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        final User user = (User) this.getAvailablePrincipal(principalCollection);

        final List<GroupPermission> permissions = this.accountService
                .loadUserPermissions(user.getUsername());

        final Set<String> keys = permissions
                .parallelStream()
                .map(GroupPermission::getPermissionKey)
                .collect(Collectors.toSet());

        // creates the basic authorization info
        final SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();

        authInfo.setStringPermissions(keys);

        return authInfo;
    }
}
