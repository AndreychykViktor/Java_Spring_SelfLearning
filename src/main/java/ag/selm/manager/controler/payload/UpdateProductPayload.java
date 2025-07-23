package ag.selm.manager.controler.payload;

import java.math.BigDecimal;

public record UpdateProductPayload(
        String name,
        String details,
        BigDecimal price
) {

}