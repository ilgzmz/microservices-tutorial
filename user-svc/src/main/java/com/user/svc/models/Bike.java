package com.user.svc.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Bike {

    private String brand;
    private String model;
    
    // Not required when working with Rest Template
    private Long userId;

}
