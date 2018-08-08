package cn.mqcenter.thrift;

import cn.mqcenter.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.springframework.stereotype.Component;

@Component
public class ThriftServer {
    private static Logger logger = LogManager.getLogger(ThriftServer.class);
    private TProtocolFactory protocolFactory;
    private TFramedTransport.Factory transportFactory;

    public void init() {
        protocolFactory = new TCompactProtocol.Factory();
        transportFactory = new TFramedTransport.Factory();
    }

    public void start() {
        Message.Processor processor = new Message.Processor<Message.Iface>(new MessageImpl());
        init();

        try {
            TNonblockingServerSocket transport = new TNonblockingServerSocket(7911);
            TNonblockingServer.Args tArgs = new TNonblockingServer.Args(transport);
            tArgs.processor(processor);
            tArgs.protocolFactory(protocolFactory);
            tArgs.transportFactory(transportFactory);
            TServer server = new TNonblockingServer(tArgs);
            logger.info("thrift服务启动成功, 端口=7911");
            server.serve();
        } catch (Exception e) {
            logger.error("thrift服务启动失败");
        }
    }
}
