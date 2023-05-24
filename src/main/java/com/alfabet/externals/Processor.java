package com.alfabet.externals;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Processor {
    Random random = new Random();

    public Long make_transaction(String src_account, String dst_account, Double amount){
        return random.nextLong();
    }
}
