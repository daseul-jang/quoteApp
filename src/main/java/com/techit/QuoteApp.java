package com.techit;

import com.techit.controller.QuoteController;
import com.techit.dto.QuoteDto;
import com.techit.dto.RequestDto;
import com.techit.service.FileService;
import com.techit.service.QuoteService;

import java.util.List;
import java.util.Scanner;

public class QuoteApp {
    private final Scanner sc;

    public QuoteApp() {
        sc = new Scanner(System.in);
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        QuoteController quoteController = new QuoteController(sc);

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            RequestDto req = new RequestDto(cmd);

            switch (req.getAction()) {
                case "종료" :
                    quoteController.exitAndFileSave();
                    return;
                case "등록" :
                    quoteController.registerQuote();
                    break;
                case "목록" :
                    quoteController.listOfQuote();
                    break;
                case "삭제" :
                    quoteController.deleteQuote(req.getQuoteNo());
                    break;
                case "수정" :
                    quoteController.updateQuote(req.getQuoteNo());
                    break;
                case "빌드" :
                    quoteController.runBuild();
                    break;
                default:
                    System.out.println("유효하지 않은 명령어입니다.");
                    break;
            }
        }
    }
}
