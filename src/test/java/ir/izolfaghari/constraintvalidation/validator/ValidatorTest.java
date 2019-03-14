package ir.izolfaghari.constraintvalidation.validator;

import ir.izolfaghari.constraintvalidation.dao.DeviceRepository;
import ir.izolfaghari.constraintvalidation.entity.Device;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ValidatorTest {

	@Autowired
	private DeviceRepository deviceRepository;

	@Test
	public void validIpTest() {
		Device device = new Device(null, "aaa", "127.0.0.1");
		assertNotNull(device);
		assertNotNull(device.getIp());
		deviceRepository.save(device);
		assertNotNull(device.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void invalidIpTest() {
		Device device = new Device(null, "aaa", "1234");
		assertNotNull(device);
		assertNotNull(device.getIp());
		deviceRepository.save(device);
	}

	@Test(expected = RuntimeException.class)
	public void validateDeviceManuallyTest() {
		Device device = new Device(null, "aaa", "1234");
		validate(device);
	}

	private void validate(Device device) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Device>> violations = validator.validate(device);
		if (violations.size() > 0) {
			ConstraintViolation<Device> violation1 = violations.iterator().next();
			throw new RuntimeException(String.format("ConstraintViolation exception : %s", violation1.getMessage()));
		}
	}

}
