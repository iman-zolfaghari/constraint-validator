package ir.izolfaghari.constraintvalidation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class IpValidator implements ConstraintValidator<IpConstraint, String> {
	private static final Pattern PATTERN = Pattern.compile(
			"^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

	@Override
	public void initialize(IpConstraint ipConstraint) {
	}

	@Override
	public boolean isValid(String field, ConstraintValidatorContext cxt) {
		return PATTERN.matcher(field).matches();
	}

}