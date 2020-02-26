package com.example.final_itc;

public class Member {

        private String date,ftno;
        private String block;
        private String units;

        public Member(String block,String ftno,String date,String units){}


        public String getftno() {
            return ftno;
        }

        public void setftno(String ftno) {
            this.ftno = ftno;
        }



        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
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