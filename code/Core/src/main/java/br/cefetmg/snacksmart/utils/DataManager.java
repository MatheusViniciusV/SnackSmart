/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.snacksmart.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

/**
 *
 * @author eloym
 */
public class DataManager {
    private final LocalDate data;
    private static final DateTimeFormatter formatador, formatadorPrint;
    
    static {
        formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatadorPrint = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    public DataManager(LocalDate data) {
        this.data = data;
    }

    public DataManager(Date data) {
        this.data = data.toLocalDate();
    }

    public DataManager(String data) {
        this.data = LocalDate.parse(data, formatador);
    }

    public static DateTimeFormatter getFormatador() {
        return formatador;
    }

    public int getDia() {
        return data.getDayOfMonth();
    }

    public int getMes() {
        return data.getMonthValue();
    }

    public int getAno() {
        return data.getYear();
    }

    public boolean apos(LocalDate data) {
        return this.data.isAfter(data);
    }

    public boolean antes(LocalDate data) {
        return this.data.isBefore(data);
    }

    public LocalDate getData() {
        return data;
    }

    public Date getSqlDate() { return Date.valueOf(data); }

    @Override
    public String toString() {
        return data.format(formatadorPrint);
    }
}
