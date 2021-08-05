package cl.com.nissum.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JsonWebTokenService {

	@Value("${app.jwt.secret}")
	private String secretKey;

	@Value("${app.jwt.expiry}")
	private long expiringTime;

	public String getJWTToken(String email) {

		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		return Jwts.builder().setId("nissumAppJWT").setSubject(email)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiringTime))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
	}

}
