package com.github.orders.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.orders.dto.CustomerDto;
import com.github.orders.dto.DeliveryDto;
import com.github.orders.dto.OrderDetailDto;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EmailNotification {

    @NotBlank
    @JsonProperty(value = "email")
    private String email;

    @NotNull
    @JsonProperty(value = "information")
    private Map<String, Object> information;

    public static EmailNotification registrationOrderNotify(OrderDetailDto order) {
        CustomerDto c = order.getCustomer();
        return new EmailNotification(
                c.getCustomerEmail(),
                information(order, c.getCustomerName(), c.getCustomerPhone())
        );
    }

    private static Map<String, Object>
    information(OrderDetailDto order, String customerName, String customerPhone) {
        DeliveryDto delivery = order.getDelivery();
        String payment = order.getBill().getPaymentType();
        Map<String, Object> information = Maps.newHashMap();
        information.put("customerName", customerName);
        information.put("products", order.getProducts());
        information.put("delivery", String.format("%s: %s", delivery.getCompanyName(), delivery.getAddress()));
        information.put("payment", payment);
        information.put("customer", String.format("%s, %s", customerName, customerPhone));
        information.put("recipient", customerName);
        information.put("amount", order.getAmount());
        return information;
    }

}
