package com.prospring.ch8;

import com.prospring.ch8.service.SingerSummaryService;
import com.prospring.ch8.view.SingerSummary;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SingerSummaryServiceImplTest {
    private static final Log logger = LogFactory.getLog(SingerSummaryServiceImplTest.class);

    private GenericApplicationContext ctx;
    private SingerSummaryService singerSummaryService;

    @BeforeEach
    public void setUp() {
//        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        ctx = new GenericXmlApplicationContext("classpath:spring/jpa-app-context.xml");
        assertNotNull(ctx);
        singerSummaryService = (SingerSummaryService) ctx.getBean("singerSummaryService");
        assertNotNull(singerSummaryService);
    }

    @Test
    public void testDisplayAllSummary() {
        List<SingerSummary> all = singerSummaryService.findAll();
        assertEquals(2, all.size());
        showSingerSummary(all);
    }

    private void showSingerSummary(List<SingerSummary> singerSummaries) {
        singerSummaries.forEach(System.out::println);
    }

    @AfterEach
    public void theEnd() {
        ctx.close();
    }
}
