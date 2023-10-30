package com.techit.dto;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestDto {
    private String cmd;
    private String action;
    private String queryString;
    private Map<String, String> queryParams = new HashMap<String, String>();

    public RequestDto(String cmd) {
        this.cmd = cmd;
        this.action = cmd;

        if(cmd.contains("?")) {
            String[] cmdBits = cmd.split("\\?", 2);
            action = cmdBits[0].trim();
            queryString = cmdBits[1].trim();

            String[] queryStringBits = queryString.split("&");

            for (String queryParamStr : queryStringBits) {
                String[] queryParamStrBits = queryParamStr.split("=", 2);

                String paramName = queryParamStrBits[0];
                String paramValue = queryParamStrBits[1];

                queryParams.put(paramName, paramValue);
            }
        }
    }

    public int getQuoteNo() {
        return Integer.parseInt(queryParams.get("id"));
    }
}
