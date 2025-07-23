package ag.selm.manager.controler.payload;

import jakarta.validation.constraints.Size;
import lombok.NonNull;


import java.math.BigDecimal;

public record NewProductPayload(
        @NonNull
        @Size(min = 3, max = 100)
        String name,
        @NonNull
        @Size(min = 3, max = 100)
        String details,
        BigDecimal price
) {
    public NewProductPayload {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (details == null || details.isBlank()) {
            throw new IllegalArgumentException("Details cannot be null or blank");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be null or negative");
        }
    }
}