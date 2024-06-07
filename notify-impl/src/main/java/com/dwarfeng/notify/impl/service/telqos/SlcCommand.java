package com.dwarfeng.notify.impl.service.telqos;

import com.dwarfeng.notify.stack.bean.key.SenderInfoKey;
import com.dwarfeng.notify.stack.handler.Sender;
import com.dwarfeng.notify.stack.service.NotifyQosService;
import com.dwarfeng.springtelqos.node.config.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@TelqosCommand
public class SlcCommand extends CliCommand {

    private static final String COMMAND_OPTION_LOOKUP = "l";
    private static final String COMMAND_OPTION_CLEAR = "c";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_LOOKUP,
            COMMAND_OPTION_CLEAR
    };

    private static final String COMMAND_OPTION_LOOKUP_NSID = "nsid";
    private static final String COMMAND_OPTION_LOOKUP_TID = "tid";

    private static final String IDENTITY = "slc";
    private static final String DESCRIPTION = "发送器本地缓存运维模块";

    private static final String CMD_LINE_SYNTAX_LOOKUP = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_LOOKUP) + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_LOOKUP_NSID) + " notify-setting-id " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_LOOKUP_TID) + " topic-id";
    private static final String CMD_LINE_SYNTAX_CLEAR = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_CLEAR);

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_LOOKUP,
            CMD_LINE_SYNTAX_CLEAR
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final NotifyQosService notifyQosService;

    public SlcCommand(NotifyQosService notifyQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.notifyQosService = notifyQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_LOOKUP).desc("查询发送器").build());
        list.add(Option.builder(COMMAND_OPTION_CLEAR).desc("清除发送器").build());
        list.add(Option.builder(COMMAND_OPTION_LOOKUP_NSID).desc("通知设置 ID").hasArg().type(Number.class).build());
        list.add(Option.builder(COMMAND_OPTION_LOOKUP_TID).desc("主题 ID").hasArg().type(String.class).build());
        return list;
    }

    @Override
    protected void executeWithCmd(Context context, CommandLine cmd) throws TelqosException {
        try {
            Pair<String, Integer> pair = CommandUtil.analyseCommand(cmd, COMMAND_OPTION_ARRAY);
            if (pair.getRight() != 1) {
                context.sendMessage(CommandUtil.optionMismatchMessage(COMMAND_OPTION_ARRAY));
                context.sendMessage(CMD_LINE_SYNTAX);
                return;
            }
            switch (pair.getLeft()) {
                case COMMAND_OPTION_LOOKUP:
                    handleLookup(context, cmd);
                    break;
                case COMMAND_OPTION_CLEAR:
                    notifyQosService.clearSenderLocalCache();
                    context.sendMessage("本地缓存已清除");
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void handleLookup(Context context, CommandLine cmd) throws Exception {
        if (!cmd.hasOption(COMMAND_OPTION_LOOKUP_NSID)) {
            context.sendMessage("缺少选项: " + COMMAND_OPTION_LOOKUP_NSID);
            context.sendMessage("正确格式: " + CMD_LINE_SYNTAX_LOOKUP);
            return;
        }
        if (!cmd.hasOption(COMMAND_OPTION_LOOKUP_TID)) {
            context.sendMessage("缺少选项: " + COMMAND_OPTION_LOOKUP_TID);
            context.sendMessage("正确格式: " + CMD_LINE_SYNTAX_LOOKUP);
            return;
        }
        long notifySettingId = ((Number) cmd.getParsedOptionValue(COMMAND_OPTION_LOOKUP_NSID)).longValue();
        String topicId = (String) cmd.getParsedOptionValue(COMMAND_OPTION_LOOKUP_TID);
        Sender sender = notifyQosService.getSender(new SenderInfoKey(notifySettingId, topicId));
        if (Objects.isNull(sender)) {
            context.sendMessage("not exists");
        } else {
            context.sendMessage(sender.toString());
        }
    }
}
