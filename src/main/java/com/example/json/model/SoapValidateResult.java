package com.example.json.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SoapValidateResult {
    private String userId;
    private String role;
}