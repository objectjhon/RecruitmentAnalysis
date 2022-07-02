package com.jhon.recruitmentanalysis.controller.admin;

import com.jhon.recruitmentanalysis.service.PositionValueService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class PositionValueController {

    @Resource
    PositionValueService positionValueService;

}
