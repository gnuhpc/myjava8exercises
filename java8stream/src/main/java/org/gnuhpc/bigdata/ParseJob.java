package org.gnuhpc.bigdata;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * Created by gnuhpc on 2017/1/6.
 */
public class ParseJob {

    public static void main(String[] args) {
        Map<Integer,String> jobMap = new HashMap();
        Map<Integer,String> jobMap2 = new HashMap();
        jobMap.put(10,"Job1");
        jobMap.put(20,"Job2");
        jobMap.put(30,"Job3");
        jobMap.put(40,"Job4");
        jobMap2.put(50,"Job5");
        jobMap2.put(60,"Job6");
        jobMap2.put(70,"Job7");

        System.out.println("Filter map:");
        jobMap.entrySet().stream().map(job ->{
            SparkJob sparkJob = new SparkJob();
            sparkJob.setJobId(job.getKey());
            sparkJob.setJobName(job.getValue());
            return sparkJob;
        }).filter(job->job.getJobName()!="Job1").forEach(System.out::println);

        System.out.println("Convert Map to Set:");
        Set<Map.Entry<Integer, String>> set = jobMap.entrySet().stream().collect(Collectors.toSet());
        set.forEach(k->{
            System.out.println(k.getValue());
        });


        System.out.println("Merge two Map to a list:");

        List<Map.Entry<Integer, String>> jobList = Stream.of(jobMap.entrySet(), jobMap2.entrySet()).flatMap(job -> job.stream()).collect(Collectors.toList());
        jobList.forEach(job-> System.out.println(job.getKey() + job.getValue()));
        System.out.println("Max Id of jobs"+jobMap2.entrySet().stream().max(Comparator.comparing(job->job.getKey())).get().getValue());
        System.out.println("Number of jobs"+Stream.of(jobMap.entrySet(),jobMap2.entrySet()));

    }
}
