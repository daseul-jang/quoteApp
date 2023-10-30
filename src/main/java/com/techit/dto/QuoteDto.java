package com.techit.dto;

import lombok.*;
import org.json.JSONObject;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuoteDto {
    private int no;
    private String author;
    private String quoteTxt;

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("no", no);
        json.put("author", author);
        json.put("quoteTxt", quoteTxt);

        return json;
    }
}
