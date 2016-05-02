package net.maatvirtue.usercore.service.impl;

import net.maatvirtue.usercore.api.exception.UsernameTakenRestException;
import net.maatvirtue.usercore.domain.User;
import net.maatvirtue.usercore.domain.UserStatus;
import net.maatvirtue.usercore.service.ActivationService;
import net.maatvirtue.usercore.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
@Transactional
public class ActivationServiceImpl implements ActivationService
{
	@Inject
	private UserService userService;

	public void activateUser(User user) throws UsernameTakenRestException
	{
		if(user.getStatus()!=UserStatus.PENDING_ACTIVATION)
			return;

		String username = user.getUsername();

		if(userService.usernameTaken(username))
			throw new UsernameTakenRestException("Username already used by another user: \""+username+"\"");

		user.setStatus(UserStatus.ACTIVE);

		userService.saveUser(user);
	}
}
