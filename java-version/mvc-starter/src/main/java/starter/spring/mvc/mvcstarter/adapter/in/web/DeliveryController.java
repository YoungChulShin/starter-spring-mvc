package starter.spring.mvc.mvcstarter.adapter.in.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/deliveries")
final class DeliveryController {

  @PostMapping("/create")
  String createDelivery() {
    return "ok";
  }
}
