package com.example.springbatchtest;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

public class ParameterValidator implements JobParametersValidator {

  @Override
  public void validate(JobParameters parameters) throws JobParametersInvalidException {
    String fileName = parameters.getString("fileName");
    JobExplorerFactoryBean s = new JobExplorerFactoryBean();
    s.afterPropertiesSet();
    if(!StringUtils.hasText(fileName)){
      throw new JobParametersInvalidException("fileName Parameter is missing");
    }
  }
}
