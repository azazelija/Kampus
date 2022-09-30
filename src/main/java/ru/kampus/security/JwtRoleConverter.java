package ru.kampus.security;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.List;

public class JwtRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        JwtGrantedAuthoritiesConverter delegate = new JwtGrantedAuthoritiesConverter();

        Collection<GrantedAuthority> grantedAuthorities = delegate.convert(jwt);

        if (jwt.getClaim("realm_access") == null) {
            return grantedAuthorities;
        }
        JSONObject realmAccess = jwt.getClaim("realm_access");
        if (realmAccess.get("roles") == null) {
            return grantedAuthorities;
        }
        JSONArray roles = (JSONArray) realmAccess.get("roles");

        final List<SimpleGrantedAuthority> keycloakAuthorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.toString())).toList();
        grantedAuthorities.addAll(keycloakAuthorities);

        return grantedAuthorities;
    }
}
