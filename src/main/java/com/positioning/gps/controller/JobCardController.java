package com.positioning.gps.controller;

import com.positioning.gps.entity.JobCardEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class JobCardController {
    List<JobCardEntity> jobCardEntityVOList = new ArrayList<>();
    @PostMapping("/get-info")
    public String getInFo(@RequestBody Map jobCardRequest){
        String data = jobCardRequest.get("data").toString();
        if (AalysisRule.functionCode(data).equals("01")){
            JobCardEntity jobcard = new JobCardEntity(
                    AalysisRule.time(data),
                    AalysisRule.deveui(data),
                    AalysisRule.latitude(data),
                    AalysisRule.longitude(data),
                    AalysisRule.speed(data),
                    AalysisRule.azimuth(data),
                    AalysisRule.latitude(data),
                    AalysisRule.stepCount(data),
                    AalysisRule.battery(data)
                    );
            jobCardEntityVOList.add(jobcard);
        }
        if (jobCardEntityVOList.size()%10 == 0){
            OutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream("D:\\日志.xls");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            AalysisRule.downLoadToExcel(outputStream, jobCardEntityVOList);
            System.out.println("存入");
        }
        return "data：" + jobCardRequest.get("data");
    }

}
