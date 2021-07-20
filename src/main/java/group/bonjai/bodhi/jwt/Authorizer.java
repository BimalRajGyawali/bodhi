package group.bonjai.bodhi.jwt;

import group.bonjai.bodhi.exceptions.UnAuthorized;
import group.bonjai.bodhi.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


public class Authorizer {

    public static User authorizeIfUserHasAuthority(Set<String> permittedAuthorities,
                                                      Authentication authentication) throws UnAuthorized {

        // A user has only one authority( defined in Roles.java )
        String email = authentication.getName();
        String authorityOfCurrentUser = authentication.getAuthorities().iterator().next().getAuthority();
        System.out.println("\n\n"+authentication.getName()+"  "+authorityOfCurrentUser);
        if(!permittedAuthorities.contains(authorityOfCurrentUser)){
            throw new UnAuthorized();
        }
        return new User(email, authorityOfCurrentUser);
    }

}
