package com.yc.batch.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * 描述:
 *
 * @author YC
 * @create 2018-10-07 9:48
 */
public class CsvJobListener implements JobExecutionListener {
    /**
     * Callback before a job executes.
     *
     * @param jobExecution the current {@link JobExecution}
     */
    long startTime;
    long endTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
        System.out.println("任务处理开始");
    }

    /**
     * Callback after completion of a job. Called after both both successful and
     * failed executions. To perform logic on a particular status, use
     * "if (jobExecution.getStatus() == BatchStatus.X)".
     *
     * @param jobExecution the current {@link JobExecution}
     */
    @Override
    public void afterJob(JobExecution jobExecution) {
        endTime = System.currentTimeMillis();
        System.out.println("任务处理结束");
        System.out.println("耗时:"+ (endTime-startTime)/1000 +"s");
    }
}
