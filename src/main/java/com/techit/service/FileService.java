package com.techit.service;

import com.techit.dto.QuoteDto;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    private final String FILE_DIR = "H:/workspace/techit/file";
    private final String FILE_PATH = FILE_DIR + "/data.json";

    private final Path dirPath = Paths.get(FILE_DIR);
    private final Path jsonFilePath = Paths.get(FILE_PATH);

    public String getFileName() {
        return jsonFilePath.getFileName().toString();
    }

    public void saveDataToJson(List<QuoteDto> quotes) {
        if(quotes.isEmpty()) {
            return;
        }

        JSONArray jsonArray = new JSONArray();

        for (QuoteDto quote : quotes) {
            jsonArray.put(quote.toJson());
        }

        try {
            if (Files.notExists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            Files.write(jsonFilePath, jsonArray.toString().getBytes());

            String fileName = getFileName();
            System.out.println(fileName + " 파일의 내용이 갱신되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<QuoteDto> loadDataFromJson() {
        List<QuoteDto> quotes = new ArrayList<>();

        try {
            if (Files.exists(jsonFilePath)) {
                String jsonStr = Files.readString(jsonFilePath);

                JSONArray jsonArray = new JSONArray(jsonStr);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonData = jsonArray.getJSONObject(i);
                    QuoteDto quoteDto = new QuoteDto();
                    quoteDto.setNo(jsonData.getInt("no"));
                    quoteDto.setAuthor(jsonData.getString("author"));
                    quoteDto.setQuoteTxt(jsonData.getString("quoteTxt"));
                    quotes.add(quoteDto);
                }
            }

        } catch (Exception e) {
            System.out.println("에러 발생");
        }

        return quotes;
    }
}
