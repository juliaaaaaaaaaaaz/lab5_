package org.example.core;

import org.example.data.Messages;


/**
 * Простой принтер для вывода сообщений в консоль.
 */
public class ConsolePrinter implements Printer {

    /**
     * Печатает сообщение без перевода строки.
     *
     * @param message Сообщение для печати.
     */

    @Override
    public void print(String message) {
        System.out.print(Messages.OUTPUT + message);
    }

    /**
     * Печатает сообщение с переводом строки.
     *
     * @param message Сообщение для печати.
     */
    @Override
    public void println(String message) {
        System.out.println(Messages.OUTPUT + message);
    }
}
