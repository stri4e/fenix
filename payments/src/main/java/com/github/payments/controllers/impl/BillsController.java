package com.github.payments.controllers.impl;

import com.github.payments.controllers.IBillsController;
import com.github.payments.dto.BillDto;
import com.github.payments.entity.*;
import com.github.payments.payload.Report;
import com.github.payments.service.*;
import com.github.payments.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.github.payments.utils.TransferObj.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/bills")
public class BillsController implements IBillsController {

    private final IBillsService billService;

    private final IBillNotifyService billPushService;

    private final IPaymentTypesService paymentTypesService;

    private final IAssetsService assetsService;

    private final IOrdersService ordersService;

    private final IAliasService aliasService;

    private final IUsersAliasService usersAliasService;

    private final IWhomService whomService;

    private final IWhoService whoService;

    private final IBitcoinService bitcoinService;

    private final IEthereumService ethereumService;

    @Override
    public BillDto saveForDef(BillDto payload) {
        PaymentTypes type = this.paymentTypesService
                .readByAlias(payload.getPaymentType());
        Asset asset = this.assetsService.readByName(payload.getAssetName());
        Whom whom = this.whomService.create(toWhom(payload.getWhom()));
        Who who = this.whoService.create(toWho(payload.getWho()));
        Bill bill = toBill(payload).forCreate(asset, type).forCreate(who, whom);
        return fromBill(this.billService.create(bill));
    }

    @Override
    public BillDto saveForOther(Long userId, BillDto payload) {
        PaymentTypes type = this.paymentTypesService
                .readByAlias(payload.getPaymentType());
        Asset asset = this.assetsService.readByName(payload.getAssetName());
        Bill tmp = toBill(payload).forCreate(asset, type);
        Bill bill = this.billService.create(tmp);
        this.aliasService.create(new Alias(bill, userId));
        return fromBill(bill);
    }

    @Override
    public Object findByParams(Long id, EntityStatus status) {
        if (Objects.isNull(id)) {
            return this.billService.readByStatus(status).stream()
                    .map(TransferObj::fromBill)
                    .collect(Collectors.toList());
        }
        return fromBill(this.billService.readById(id));
    }

    @Override
    public Report updateCrypto(String address, BigInteger value, String transfer) {
        Bill bill = this.billService.readByByAddressAndStatus(address, EntityStatus.on);
        var amount = bill.getAmount();
        var amountPaid = bill.getAmountPaid().add(value);
        if (amount.equals(amountPaid)) {
            bill.forUpdate(EntityStatus.off, amountPaid, transfer);
            var different = amount.subtract(amountPaid);
            this.billService.update(bill);
            this.ordersService.update(bill.getId());
            billNotify(bill);
            return new Report(amount, amountPaid, different);
        } else {
            var different = amount.subtract(amountPaid);
            bill.forUpdate(EntityStatus.on, amountPaid, transfer);
            billNotify(bill);
            return new Report(amount, amountPaid, different);
        }
    }

    @Override
    public void updateMastercard(Long billId, BigInteger value, String transfer) {
        Bill bill = this.billService.readById(billId);
        var amount = bill.getAmount();
        var amountPaid = bill.getAmountPaid().add(value);
        if (amount.equals(amountPaid)) {
            bill.forUpdate(EntityStatus.off, amountPaid, transfer);
            this.billService.update(bill);
            this.ordersService.update(bill.getId());
        }
    }

    @Override
    public void remove(Long id) {
        Bill bill = this.billService.readById(id);
        Asset asset = bill.getAsset();
        switch (asset.getName()) {
            case "eth":
                this.ethereumService.remove(bill.getAddress());
                break;
            case "btc":
                this.bitcoinService.remove(bill.getAddress());
                break;
            default:
                break;
        }
        this.billService.remove(id);
    }

    private void billNotify(Bill bill) {
        if (bill.isOther()) {
            Alias notify = this.aliasService.findByBillId(bill.getId());
            var ending = this.usersAliasService.findEndingUrl(notify.getId())
                    .orElse("default");
            this.billPushService.billNotify(ending, fromBill(bill));
        }
    }
}
