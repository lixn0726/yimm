package indl.lixn.yimm.web_service.service.impl;

import indl.lixn.yimm.web_service.entity.ChatRecord;
import indl.lixn.yimm.web_service.repository.ChatRecordMapper;
import indl.lixn.yimm.web_service.service.ChatRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:55
 **/
@Service
public class ChatRecordServiceImpl implements ChatRecordService {

    private static final Logger log = LoggerFactory.getLogger(ChatRecordServiceImpl.class);

    @Resource
    private ChatRecordMapper chatRecordMapper;

    @Override
    public void addChatRecord(ChatRecord record) {
        log.info("ChatRecordService - addChatRecord: {}", record);
        chatRecordMapper.addChatRecord(record);
    }

}
