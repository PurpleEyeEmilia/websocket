package com.demo.websocket.canal;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.exception.CanalClientException;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author pengnian
 * @version V1.0
 * @date 2019/7/29 17:43
 * @Desc
 */
@Slf4j
public class CanalClient {


    public static void main(String[] args) {
        CanalConnector canalConnector = CanalConnectors.newSingleConnector(
                new InetSocketAddress("132.232.104.215", 11111),
                "example",
                "",
                "");

        int batchSize = 100;
        int emptyCount = 0;

        try {
            canalConnector.connect();
            canalConnector.subscribe(".*\\..*");
            canalConnector.rollback();
            int totalEmptyCount = 120;

            while (emptyCount < totalEmptyCount) {
                Message message = canalConnector.getWithoutAck(batchSize);
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    emptyCount++;

                    try {
                        log.info("未拉取到消息，batchId: {}, size: {}, message: {}", batchId, size, JSON.toJSONString(message));
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                       log.error("异常！", e);
                    }

                } else {
                    emptyCount = 0;
                    printEntry(message.getEntries());

                }

                //提交确认
                canalConnector.ack(batchId);
            }

            System.out.println("empty too many times, exit!");
        } catch (CanalClientException e) {
            log.error("canal异常", e);
        } finally {
            canalConnector.disconnect();
        }
    }

    private static void printEntry(List<CanalEntry.Entry> entries) {
        for (CanalEntry.Entry entry : entries) {
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                continue;
            }

            if (entry.getEntryType() == CanalEntry.EntryType.ROWDATA) {
                CanalEntry.RowChange rowChange = null;
                try {
                    rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
                } catch (Exception e) {
                    log.error("Error parse {}", entry.toString(), e);
                    throw new RuntimeException("Error parse entry!");
                }

                CanalEntry.EventType eventType = rowChange.getEventType();
                System.out.println(String.format("======> binlog[%s: %s], name[%s, %s], eventType: %s",
                        entry.getHeader().getLogfileName(),
                        entry.getHeader().getLogfileOffset(),
                        entry.getHeader().getSchemaName(),
                        entry.getHeader().getTableName(),
                        eventType));

                for (CanalEntry.RowData rowData : rowChange.getRowDatasList()) {
                    if (eventType == CanalEntry.EventType.DELETE) {
                        printColumn(rowData.getBeforeColumnsList());
                    } else if (eventType == CanalEntry.EventType.INSERT) {
                        printColumn(rowData.getAfterColumnsList());

                    } else {
                        System.out.println("----before");
                        printColumn(rowData.getBeforeColumnsList());
                        System.out.println("----after");
                        printColumn(rowData.getAfterColumnsList());
                    }
                }
            }
        }
    }

    private static void printColumn(List<CanalEntry.Column> beforeColumnsList) {
        for (CanalEntry.Column column : beforeColumnsList) {
            System.out.println(column.getName() + " : " + column.getValue() + " update=" + column.getUpdated());
        }
    }

}
