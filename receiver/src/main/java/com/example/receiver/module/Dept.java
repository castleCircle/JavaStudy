package com.example.receiver.module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
  Integer deptNo;
  String dName;
  String loc;
  String time;
}
