package com.techit.controller;

import com.techit.dto.QuoteDto;
import com.techit.service.FileService;
import com.techit.service.QuoteService;

import java.util.List;
import java.util.Scanner;

public class QuoteController {
    QuoteService quoteService;
    FileService fileService;
    List<QuoteDto> quotes;
    Scanner sc;

    public QuoteController(Scanner sc) {
        this.sc = sc;
        quoteService = new QuoteService();
        fileService = new FileService();
        quotes = fileService.loadDataFromJson();
    }

    public void exitAndFileSave() {
        if (!quotes.isEmpty()) fileService.saveDataToJson(quotes);
    }

    public void registerQuote() throws NullPointerException {
        try {
            QuoteDto quoteDto = quoteService.quoteInsert(sc, quotes, 1);

            boolean isQuoteTxt = quoteDto.getQuoteTxt() == null || quoteDto.getQuoteTxt().isEmpty();
            boolean isAuthor = quoteDto.getAuthor() == null || quoteDto.getAuthor().isEmpty();

            if(isQuoteTxt || isAuthor) {
                throw new NullPointerException("명언과 작가는 비워둘 수 없습니다. \n등록을 다시 진행해 주세요.");
            }

            quotes.add(quoteDto);
            System.out.println(quoteDto.getNo() + "번 명언이 등록되었습니다.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listOfQuote() {
        quoteService.quoteList(quotes);
    }

    public void deleteQuote(int QuoteNo) {
        quoteService.quoteRemove(quotes, QuoteNo);
    }

    public void updateQuote(int QuoteNo) {
        quoteService.quoteUpdate(sc, quotes, QuoteNo);
    }

    public void runBuild() {
        if (!quotes.isEmpty()) fileService.saveDataToJson(quotes);
        else System.out.println("갱신할 내용이 없습니다.");
    }
}
