package com.example.apiregistrousuarios.messages;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {

    private int code;
    private String message;


    public CustomErrorResponse() {
    }

    public CustomErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
