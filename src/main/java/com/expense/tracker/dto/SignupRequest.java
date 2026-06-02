package com.expense.tracker.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String password;
}
