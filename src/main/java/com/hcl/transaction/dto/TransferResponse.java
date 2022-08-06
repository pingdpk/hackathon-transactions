package com.hcl.transaction.dto;

import lombok.Data;

@Data
public class TransferResponse {
    String status;
    String description;
    String transctionId;
    String comment;
    String date;
}
