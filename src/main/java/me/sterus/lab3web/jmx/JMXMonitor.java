package me.sterus.lab3web.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class JMXMonitor {

    public static void configure(TotalDots totalDotsMBean, PercentHit percentHit) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName totalMDotsBeanName = new ObjectName("me.sterus.lab3web.jmx:type=TotalDots");
        mBeanServer.registerMBean(totalDotsMBean, totalMDotsBeanName);
        ObjectName percentHitMBeanName = new ObjectName("me.sterus.lab3web.jmx:type=PercentHit");
        mBeanServer.registerMBean(percentHit, percentHitMBeanName);
    }
}
