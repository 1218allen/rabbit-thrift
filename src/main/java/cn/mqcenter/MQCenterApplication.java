package cn.mqcenter;

import cn.mqcenter.thrift.ThriftServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class MQCenterApplication {
	private static ThriftServer thriftServer;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MQCenterApplication.class, args);

		try {
			thriftServer = context.getBean(ThriftServer.class);
			thriftServer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
