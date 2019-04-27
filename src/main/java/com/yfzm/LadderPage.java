package com.yfzm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LadderPage {

    private static Logger LOGGER = LogManager.getLogger(LadderPage.class);

    @RequestMapping("/")
    @ResponseBody
    public LadderBean func(String begin, String end) {
//        BasicConfigurator.configure();

        LOGGER.info("Enter root page");

        WordLadder wl = new WordLadder();
        wl.createLadder(begin, end);

        LOGGER.info("params: begin:" + begin + ", end:" + end);

        LadderBean lb = new LadderBean();
        lb.setBegin(begin);
        lb.setEnd(end);
        lb.setLength(wl.getLadderStep());

        int length = wl.getLadderStep();
        if (length > 0) {
            lb.setLadder(new ArrayList<>(wl.getLadderStack()));
        } else {
            List<String> emptyLadder = new ArrayList<String>();
            lb.setLadder(emptyLadder);
        }

        return lb;
    }
}
