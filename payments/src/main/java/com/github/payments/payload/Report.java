package com.github.payments.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    private BigInteger amount;

    private BigInteger amountPaid;

    private BigInteger different;

    private ReportStatus status;

}
