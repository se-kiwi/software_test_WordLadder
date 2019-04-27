package com.yfzm;

import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LadderPageTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    public void testController(String begin, String end, int expectLength) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                //form表单格式传参
                .param("begin", begin)
                .param("end", end)
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE);

        ResultActions result = mvc.perform(requestBuilder);

        MvcResult mvcResult = result.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();// 返回执行请求的结果

        String jsonStr = mvcResult.getResponse().getContentAsString();

        Gson gson = new Gson();
        LadderBean res_bean = gson.fromJson(jsonStr, LadderBean.class);
        assertEquals(begin, res_bean.getBegin());
        assertEquals(end, res_bean.getEnd());
        assertEquals(expectLength, res_bean.getLength());
    }

    @Test
    public void func() throws Exception {
        testController("cat", "dog", 4);
        testController("uuuu", "dddd", 0);
    }
}
