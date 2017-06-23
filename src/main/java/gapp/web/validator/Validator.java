package gapp.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import gapp.model.User;
import gapp.model.dao.UserDao;

@Component
public class Validator {
	@Autowired
	private UserDao userDao;

	public boolean supports(Class<?> clazz) {

		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {

		User user = (User) target;

		if (!StringUtils.hasText(user.getFirstName()))
			errors.rejectValue("firstName", "error.field.empty");
		if (!StringUtils.hasText(user.getLastName()))
			errors.rejectValue("lastName", "error.field.empty");
		if (!StringUtils.hasText(user.getEmail()))
			errors.rejectValue("email", "error.field.empty");
		if (!StringUtils.hasText(user.getPassword()))
			errors.rejectValue("password", "error.field.empty");

		for (User u : userDao.getUsers()) {
			if (u.getEmail().toLowerCase().equals(user.getEmail().toLowerCase()))
				errors.rejectValue("email", "error.field.exist");
		}

	}

}
