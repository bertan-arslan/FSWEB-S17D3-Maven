package com.workintech.zoo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ZooErrorResponse {
    private int status;
    private String message;
    private long timestamp;
}
