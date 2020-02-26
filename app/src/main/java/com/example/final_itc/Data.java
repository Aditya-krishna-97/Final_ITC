package com.example.final_itc;

public class Data {

    private String date,ftno;
    private String block;
    private String units;

    public Data(String block, String ftno, String date, String units) {
        this.date = date;
        this.ftno = ftno;
        this.block = block;
        this.units = units;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFtno() {
        return ftno;
    }

    public void setFtno(String ftno) {
        this.ftno = ftno;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
