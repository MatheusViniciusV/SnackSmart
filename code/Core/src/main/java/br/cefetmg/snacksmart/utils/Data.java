/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.snacksmart.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author eloym
 */
public class Data {
    private LocalDate data;
    private static final DateTimeFormatter formatador;
    
    static {
        formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public Data(LocalDate data) {
        this.data = data;
    }

    public Data(String data) {
        this.data = LocalDate.parse(data, formatador);
    }

    public static DateTimeFormatter getFormatador() {
        return formatador;
    }

    public String getDia() {
        return data.format(DateTimeFormatter.ofPattern("dd"));
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return data.format(formatador);
    }
}
