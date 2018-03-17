package com.abex.arena.core.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.abex.arena.core.dto.UserDto;
import com.abex.arena.core.error.UserAlreadyExistException;
import com.abex.arena.core.persistence.dao.PasswordResetTokenRepository;
import com.abex.arena.core.persistence.dao.PrivilegeRepository;
import com.abex.arena.core.persistence.dao.UserRepository;
import com.abex.arena.core.persistence.dao.UserRoleRepository;
import com.abex.arena.core.persistence.dao.VerificationTokenTokenRepository;
import com.abex.arena.core.persistence.model.user.PasswordResetToken;
import com.abex.arena.core.persistence.model.user.User;
import com.abex.arena.core.persistence.model.user.UserRole;
import com.abex.arena.core.persistence.model.user.VerificationToken;

@Service
public interface AccountService {
	UserRole createUserRole(UserRole entity);

	PasswordResetTokenRepository getPasswordResetTokenRepository();

	PrivilegeRepository getPrivilegeRepository();

	UserRepository getUserRepository();

	UserRoleRepository getUserRoleRepository();

	VerificationTokenTokenRepository getVerificationTokenTokenRepository();
	
	
	
	
    User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void deleteUser(User user);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    VerificationToken generateNewVerificationToken(String token);

    void createPasswordResetTokenForUser(User user, String token);

    User findUserByEmail(String email);

    PasswordResetToken getPasswordResetToken(String token);

    User getUserByPasswordResetToken(String token);

    User getUserByID(long id);

    void changeUserPassword(User user, String password);

    boolean checkIfValidOldPassword(User user, String password);

    String validateVerificationToken(String token);

    String generateQRUrl(User user) throws UnsupportedEncodingException;

    User updateUser2FA(boolean use2FA);

    List<String> getUsersFromSessionRegistry();
}
