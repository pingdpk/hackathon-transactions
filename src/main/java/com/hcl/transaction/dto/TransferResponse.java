package com.hcl.transaction.dto;

import com.hcl.transaction.enums.ApiStatus;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class TransferResponse implements Serializable {
    ApiStatus status;
    String description;
    String transctionId;
    String comment;
    Date date;
}
