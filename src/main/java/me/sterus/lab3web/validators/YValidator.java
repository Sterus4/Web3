package me.sterus.lab3web.validators;


import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("YValidator")
public class YValidator implements Validator {
    private final Double min = -5d;
    private final Double max = 3d;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        var value = (Double) o;
        if (value >= 3 || value <= -5){
            FacesMessage message = new FacesMessage("Введите значение от -5 до 3 (не включительно)");
            message.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(message);
        }
    }
}
