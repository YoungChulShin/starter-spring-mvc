package starter.spring.mvc.mvcstarter.adapter.in.web;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import starter.spring.mvc.mvcstarter.adapter.in.web.model.request.CreateDeliveryRequest;
import starter.spring.mvc.mvcstarter.adapter.in.web.model.response.ApiResponse;
import starter.spring.mvc.mvcstarter.adapter.in.web.model.response.CreateDeliveryResponse;

@Slf4j
@RestController
@RequestMapping("api/v1/deliveries")
final class DeliveryController {

  @PostMapping("/create")
  ApiResponse<CreateDeliveryResponse> createDelivery(
      @RequestBody @Valid CreateDeliveryRequest request) {
    String deliveryId = UUID.randomUUID().toString();
    CreateDeliveryResponse response = new CreateDeliveryResponse(deliveryId);

    return ApiResponse.success(response);
  }
}
