package com.github.orders.controllers;

import com.github.orders.dto.OrderDetailDto;
import com.github.orders.entity.Customer;
import com.github.orders.entity.OrderDetail;
import com.github.orders.exceptions.BadRequest;
import com.github.orders.exceptions.TypeMessage;
import com.github.orders.service.ICustomerService;
import com.github.orders.service.IOrderDetailService;
import com.github.orders.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class OrdersDetailController implements IOrdersDetailController {

    private final ICustomerService customerService;

    private final IOrderDetailService orderService;

    @Override
    //@Logging(isTime = true, isReturn = false)
    public ResponseEntity<Void> createOrder(Long userId, OrderDetailDto payload) {
        Customer customer = TransferObj.customer(payload.getCustomer());
        Customer c = this.customerService.create(customer);
        OrderDetail data = TransferObj.orderDetail(
                c, payload.getProductsIds(),
                payload.getAmount(), userId, payload.getStatus()
        );
        OrderDetail result = this.orderService.crete(data);
        if (Objects.isNull(result)) {
            throw new BadRequest(TypeMessage.badOrderData);
        }
//        Message<OrderDetailDto> message = this.sender.createMsg(
//                Topics.websocket, TransferObj.orderDetailDto(result)
//        );
//        this.sender.send(message);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
