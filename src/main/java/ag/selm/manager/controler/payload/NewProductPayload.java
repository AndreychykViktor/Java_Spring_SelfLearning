package ag.selm.manager.controler.payload;

import java.math.BigDecimal;

public record NewProductPayload(
        String name,
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