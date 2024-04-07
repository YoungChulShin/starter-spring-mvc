package starter.spring.mvc.mvcstarter.adapter.in.web.model.request;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CreateDeliveryRequest(
    @NotNull String sourceAddress,
    @NotNull String destinationAddress,
    @NotNull BigDecimal deliveryFee
) {
}
