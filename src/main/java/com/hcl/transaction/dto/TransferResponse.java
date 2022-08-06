package com.hcl.transaction.dto;

import com.hcl.transaction.enums.ApiStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class TransferResponse implements Serializable {
    ApiStatus status;
    String description;
    String transctionId;
    String comment;
    String date;
}
