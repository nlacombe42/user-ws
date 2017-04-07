package net.nlacombe.userws.service;

import net.maatvirtue.authlib.jwt.JwsToken;
import net.maatvirtue.authlib.jwt.JwtUser;
import net.nlacombe.userws.domain.User;

public interface JwtService
{
	JwsToken createJwtToken(String jwtSigningSecretKey, User user);

	JwsToken createJwtToken(String jwtSigningSecretKey, JwtUser jwtUser);
}