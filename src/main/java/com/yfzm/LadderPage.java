package com.yfzm;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class LadderPage {

    @RequestMapping("/")
    @ResponseBody
    public LadderBean func(String begin, String end) {
        WordLadder wl = new WordLadder();
        wl.createLadder(begin, end);

        LadderBean lb = new LadderBean();
        lb.setBegin(begin);
        lb.setEnd(end);
        lb.setLength(wl.getLadderStep());

        int length = wl.getLadderStep();
        if (length > 0) {
            lb.setLadder(new ArrayList<>(wl.getLadderStack()));
        } else {
            lb.setLadder(null);
        }

        return lb;
    }
}
