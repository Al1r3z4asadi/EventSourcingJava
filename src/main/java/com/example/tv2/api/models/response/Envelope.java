package com.example.tv2.api.models.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(NON_NULL)
public class Envelope {
    protected LocalDateTime timeStamp ;
    protected int statusCode ;
    protected HttpStatus status ;
    protected String reason ;
    protected String message ;
    protected String developerMessage ;
    protected Map<?,?> data ;
}