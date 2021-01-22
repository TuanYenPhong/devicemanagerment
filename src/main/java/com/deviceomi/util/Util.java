package com.deviceomi.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Util {
    public String generateRandomUuid(){
        return UUID.randomUUID().toString();
    }
}
