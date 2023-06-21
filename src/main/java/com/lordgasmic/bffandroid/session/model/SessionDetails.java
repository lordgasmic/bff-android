package com.lordgasmic.bffandroid.session.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionDetails implements Serializable {
    private String username;
    private int roles;
    private String authToken;
}
