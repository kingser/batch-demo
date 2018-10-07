package com.yc.batch.batch;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 描述:
 *
 * @author YC
 * @create 2018-10-07 9:42
 */
public class CsvBeanValidator<T> implements Validator<T>,InitializingBean {


    private javax.validation.Validator validator;

    /**
     * Method used to validate if the value is valid.
     *
     * @param value object to be validated
     * @throws ValidationException if value is not valid.
     */
    @Override
    public void validate(T value) throws ValidationException {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(value);
        if(constraintViolations.size()>0) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                msg.append(constraintViolation.getMessage() + "\n");
            }
            throw new ValidationException(msg.toString());
        }
    }

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }
}
