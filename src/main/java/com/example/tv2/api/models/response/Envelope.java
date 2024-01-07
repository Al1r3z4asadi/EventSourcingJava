package com.example.tv2.api.models.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.Map;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(NON_NULL)
public class Envelope {
    private LocalDateTime timeStamp ;
    private int statusCode ;
    private HttpStatus status ;
    private String reason ;
    private String message ;
    private String developerMessage ;
    private Map<?,?> data ;
}