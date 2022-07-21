package com.algaworks.algalog.domain.validator;

import com.algaworks.algalog.domain.anotations.Name;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator implements ConstraintValidator<Name, String> {


    private final Pattern patternNoAllowSpecialCharactersAndNumbers = Pattern.compile("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ ]+$");
    private final Pattern patternNoAllowMoreThanThreeEqualsCharactersInSequence = Pattern.compile("^((.)(?!\\2{3}))+$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || "".equals(value)) {
            return true;
        }

        Matcher matcherNoAllowSpecialCharactersAndNumbers = patternNoAllowSpecialCharactersAndNumbers
                .matcher(value);
        Matcher matcherNoAllowMoreThanThreeEqualsCharactersInSequence = patternNoAllowMoreThanThreeEqualsCharactersInSequence
                .matcher(value);
        return matcherNoAllowSpecialCharactersAndNumbers.matches()
                && matcherNoAllowMoreThanThreeEqualsCharactersInSequence.matches();
    }
}
