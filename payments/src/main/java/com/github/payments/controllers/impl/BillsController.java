package com.github.payments.controllers.impl;

import com.github.payments.controllers.IBillsController;
import com.github.payments.dto.BillDto;
import com.github.payments.entity.Asset;
import com.github.payments.entity.Bill;
import com.github.payments.entity.EntityStatus;
import com.github.payments.entity.PaymentTypes;
import com.github.payments.payload.Report;
import com.github.payments.service.IAssetsService;
import com.github.payments.service.IBillsService;
import com.github.payments.service.IPaymentTypesService;
import com.github.payments.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.payments.utils.TransferObj.fromBill;
import static com.github.payments.utils.TransferObj.toBill;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/bills")
public class BillsController implements IBillsController {

    private final IBillsService billService;

    private final IPaymentTypesService paymentTypesService;

    private final IAssetsService assetsService;

    @Override
    public BillDto save(BillDto payload) {
        PaymentTypes type = this.paymentTypesService
                .readByAlias(payload.getPaymentType());
        Asset asset = this.assetsService.readByName(payload.getAssetName());
        Bill bill = toBill(payload).forCreate(asset, type);
        return fromBill(this.billService.create(bill));
    }

    @Override
    public List<BillDto> findBillsByStatus(EntityStatus status) {
        return this.billService.readByStatus(status).stream()
                .map(TransferObj::fromBill)
                .collect(Collectors.toList());
    }

    @Override
    public Report update(String address, BigInteger value, String transfer) {
        Bill bill = this.billService.readByByAddressAndStatus(address, EntityStatus.on);
        var amount = bill.getAmount();
        var amountPaid = bill.getAmountPaid().add(value);
        if (amount.equals(amountPaid)) {
            bill.forUpdate(EntityStatus.off, amountPaid, transfer);
            var different = amount.subtract(amountPaid);
            this.billService.update(bill);
            return new Report(amount, amountPaid, different);
        } else {
            var different = amount.subtract(amountPaid);
            bill.forUpdate(EntityStatus.on, amountPaid, transfer);
            return new Report(amount, amountPaid, different);
        }
    }

    @Override
    public void delete(Long id) {
        this.billService.remove(id);
    }
}
