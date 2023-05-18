package com.prospring.ch13;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class ServiceTestExecutionListener implements TestExecutionListener {

    private IDatabaseTester dataBaseTester;

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        DataSets dataSetAnnotation = testContext.getTestMethod().getAnnotation(DataSets.class);
        if (dataSetAnnotation == null) {
            return;
        }
        String dataSetName = dataSetAnnotation.setUpDataSet();
        if (!"".equals(dataSetAnnotation)) {
            dataBaseTester =
                    (IDatabaseTester) testContext.getApplicationContext().getBean("databaseTester");
            XlsDataFileLoader xlsDataFileLoader =
                    (XlsDataFileLoader) testContext.getApplicationContext().getBean("xlsDataFileLoader");
            IDataSet dataSet = xlsDataFileLoader.load(dataSetName);
            dataBaseTester.setDataSet(dataSet);
            dataBaseTester.onSetup();
        }
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        if (dataBaseTester != null) {
            dataBaseTester.onTearDown();
        }
    }
}
