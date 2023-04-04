package com.prospring.ch8;

import com.prospring.ch8.entities.Album;
import com.prospring.ch8.entities.Instrument;
import com.prospring.ch8.entities.Singer;
import com.prospring.ch8.service.SingerSummaryUntypeImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SingerSummaryUntypeImplTest {
    private static final Log logger = LogFactory.getLog(SingerSummaryUntypeImplTest.class);

    private GenericApplicationContext ctx;
    private SingerSummaryUntypeImpl singerSummaryUntype;

    @BeforeEach
    public void setUp() {
//        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        ctx = new GenericXmlApplicationContext("classpath:spring/jpa-app-context.xml");
        assertNotNull(ctx);
        singerSummaryUntype = (SingerSummaryUntypeImpl) ctx.getBean("singerSummaryUntype");
        assertNotNull(singerSummaryUntype);
    }

    @Test
    public void testDisplayAllSummary() {
        singerSummaryUntype.displayAllSummary();
    }

    @AfterEach
    public void theEnd() {
        ctx.close();
    }
}
