package com.basic.basic.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.basic.basic.service.UserService;

public class AuthenticationProviderImpl implements AuthenticationProvider{

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationProviderImpl.class);
	
	@Autowired
	private UserService service;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		logger.debug("authenticate started");
		
		String username = (String) authentication.getPrincipal();
		String credential = (String) authentication.getCredentials();
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		
		HashMap<String, Object> userInfo = service.selectUsers(params).get(0);
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		
		if(!pwdCheck(credential, (String)userInfo.get("PASSWORD"))) {
			throw new BadCredentialsException(username);
		}
		
		roles.add(new SimpleGrantedAuthority("ROLE_" + (String)userInfo.get("AUTHORITY")));
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, credential, roles);
		
		logger.debug("authenticate ended");
		
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		//provider를 통해 인증처리를 하려면 true로 지정해주어야 한다.
		return true;
	}

	private boolean pwdCheck(String credential, String password) {
		if(!credential.equals(password)) {
			return false;
		} else {
			return true;
		}
	}
}
