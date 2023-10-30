package com.techit.service;

import com.techit.dto.QuoteDto;

import java.util.List;
import java.util.Scanner;

public class QuoteService {
    QuoteDto quoteDto;

    // 테스트
    public void insertTest() {
        for(int i = 1; i <= 10; i++) {

        }
    }

    // 등록
    public QuoteDto quoteInsert(Scanner sc, List<QuoteDto> quotes, int quoteNo) {
        System.out.print("명언 : ");
        String quote = sc.nextLine();

        System.out.print("작가 : ");
        String author = sc.nextLine();

        // data.json 파일이 있을 경우 마지막 인덱스 값을 가져와 번호 설정
        if (!quotes.isEmpty()) {
            QuoteDto temp = quotes.get(quotes.size() - 1);
            quoteNo = temp.getNo() + 1;
        }

        quoteDto = new QuoteDto(quoteNo, author, quote);
        /*QuoteDto quoteDto = new QuoteDto();
        quoteDto.setNo(no);
        quoteDto.setAuthor(author);
        quoteDto.setQuoteTxt(quote);*/

        return quoteDto;
    }

    // 수정
    public void quoteUpdate(Scanner sc, List<QuoteDto> quotes, int no) {
        System.out.println("업뎃");
        String resultMsg = "";

        if (quotes.isEmpty()) {
            resultMsg = "등록된 명언이 없습니다.\n";
        } else {
            resultMsg = "해당 번호를 가진 명언이 없습니다.\n";

            for (QuoteDto quote : quotes) {
                if (quote.getNo() == no) {
                    System.out.println("명언(기존) : " + quote.getQuoteTxt());
                    System.out.print("명언 : ");
                    String editQuote = sc.nextLine();

                    System.out.println(editQuote);

                    System.out.println("작가(기존) : " + quote.getAuthor());
                    System.out.print("작가 : ");
                    String editAuthor = sc.nextLine();

                    System.out.println(editAuthor);

                    boolean isEditQuote = editQuote == null || editQuote.isEmpty();
                    boolean isEditAuthor = editAuthor == null || editAuthor.isEmpty();

                    if(isEditQuote || isEditAuthor) {
                        if(isEditQuote) editQuote = quote.getQuoteTxt();
                        else editAuthor = quote.getAuthor();
                    }

                    quote.setQuoteTxt(editQuote);
                    quote.setAuthor(editAuthor);

                    resultMsg = "%d번 명언이 수정되었습니다.\n";

                    break;
                }
            }
        }

        System.out.printf(resultMsg, no);
    }

    // 삭제
    public void quoteRemove(List<QuoteDto> quotes, int no) {
        String resultMsg = "";

        if (quotes.isEmpty()) {
            resultMsg = "등록된 명언이 없습니다.\n";
        } else {
            if (quotes.removeIf(quote -> quote.getNo() == no)) {
                resultMsg = "%d번 명언이 삭제되었습니다.\n";
            } else {
                resultMsg = "%d번 명언은 존재하지 않습니다.\n";
            }
            ;
        }

        System.out.printf(resultMsg, no);
    }

    // 목록
    public void quoteList(List<QuoteDto> quotes) {
        System.out.println("  번호  |    작가    |          명언          ");
        System.out.println("=============================================");

        if(quotes == null || quotes.isEmpty()) {
            return;
        }

        // 오름차순 정렬
        /*for (QuoteDto quoteDto : quotes) {
            int no = quoteDto.getNo();
            String author = quoteDto.getAuthor();
            String quoteTxt = quoteDto.getQuoteTxt();

            System.out.println("   " + no + "    |    " + author + "    |  " + quoteTxt);
        }*/

        // 내림차순 정렬
        for (int i = quotes.size() - 1; i >= 0; i--) {
            int no = quotes.get(i).getNo();
            String author = quotes.get(i).getAuthor();
            String quoteTxt = quotes.get(i).getQuoteTxt();

            System.out.printf("   %d    |    %s    |  %s \n", no, author, quoteTxt);
        }
    }
}
