package com.haier.neusoft.o2o.common.util;

import com.haier.openplatform.console.audit.AuditInfoCollector;
import com.haier.openplatform.util.HOPConstant;

/**
 * Created by king on 2014/8/6.
 */
public class SystemInitUtil {

    public void init(){
        AuditInfoCollector.setAppNM("CDK");
        HOPConstant.setAppName("CDK");
    }
}
