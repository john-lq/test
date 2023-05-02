package com.lqgioan.test.domains;

import org.jetbrains.annotations.NotNull;

import io.reactivex.rxjava3.core.Single;

public interface IProcessMessageUseCase {
    /**
     * This method will get the mention names and links from the @message
     * - Mention names: without @ at the beginning
     * - Links: with per link will get the title from the internet by using Jsoup library
     * @param message the input message
     * @return a Json string with the below format
     *  {
     *      "mentions": ["name1", "name2"],
     *      "links": [
     *          {
     *              "url": "https://google.com",
     *              "title": "Google"
     *          }
     *      ]
     *  }
     */
    @NotNull
    public Single<String> processMessage(String message);
}
