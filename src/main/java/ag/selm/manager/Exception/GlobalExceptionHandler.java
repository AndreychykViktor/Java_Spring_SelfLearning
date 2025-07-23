package ag.selm.manager.Exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public String handleProductNotFound(NoSuchElementException e) {
        return "redirect:/catalogue/products/list?error=notfound";
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception e, Model model) {
        model.addAttribute("errorMessage", "Виникла системна помилка");
        model.addAttribute("errorCode", "500");
        return "catalogue/products/error/generic";
    }
}
