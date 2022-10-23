package com.dwarfeng.notify.impl.service.telqos;

import com.dwarfeng.notify.stack.bean.dto.NotifyInfo;
import com.dwarfeng.notify.stack.service.NotifyQosService;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotifyCommand extends CliCommand {

    private static final String COMMAND_OPTION_SEND = "send";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_SEND
    };

    private static final String COMMAND_OPTION_SEND_NSID = "nsid";
    private static final String COMMAND_OPTION_SEND_RINFO = "rinfo";
    private static final String COMMAND_OPTION_SEND_DINFO = "dinfo";
    private static final String COMMAND_OPTION_SEND_SINFO = "sinfo";

    private static final String IDENTITY = "notify";
    private static final String DESCRIPTION = "通知操作运维模块";

    private static final String CMD_LINE_SYNTAX_SEND = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SEND) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SEND_NSID) + " notify-setting-id " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SEND_RINFO) + " route-info" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SEND_DINFO) + " dispatch-info" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SEND_SINFO) + " send-info]";

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_SEND
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final NotifyQosService notifyQosService;

    public NotifyCommand(NotifyQosService notifyQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.notifyQosService = notifyQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_SEND).desc("(指令式/交互式)发送通知").build());
        list.add(Option.builder(COMMAND_OPTION_SEND_NSID).desc("通知设置 ID").hasArg().type(Number.class).build());
        list.add(
                Option.builder(COMMAND_OPTION_SEND_RINFO).desc("路由信息").optionalArg(true).type(String.class).build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SEND_DINFO).desc("调度信息").optionalArg(true).type(String.class).build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SEND_SINFO).desc("发生信息").optionalArg(true).type(String.class).build()
        );
        return list;
    }

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
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
                case COMMAND_OPTION_SEND:
                    handleSend(context, cmd);
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void handleSend(Context context, CommandLine cmd) throws Exception {
        LongIdKey notifySettingKey = obtainNotifySettingKey(context, cmd);
        String routeInfo = obtainRouteInfo(context, cmd);
        String dispatchInfo = obtainDispatchInfo(context, cmd);
        String sendInfo = obtainSendInfo(context, cmd);

        notifyQosService.notify(new NotifyInfo(notifySettingKey, routeInfo, dispatchInfo, sendInfo));
        context.sendMessage("发送成功!");
    }

    private LongIdKey obtainNotifySettingKey(Context context, CommandLine cmd) throws Exception {
        long notifySettingId;
        if (cmd.hasOption(COMMAND_OPTION_SEND_NSID)) {
            notifySettingId = ((Number) cmd.getParsedOptionValue(COMMAND_OPTION_SEND_NSID)).longValue();
        } else {
            context.sendMessage("请输入通知设置的 ID: ");
            notifySettingId = Long.parseLong(context.receiveMessage());
        }
        return new LongIdKey(notifySettingId);
    }

    private String obtainRouteInfo(Context context, CommandLine cmd) throws Exception {
        String routeInfo;
        if (cmd.hasOption(COMMAND_OPTION_SEND_RINFO)) {
            routeInfo = (String) cmd.getParsedOptionValue(COMMAND_OPTION_SEND_RINFO);
        } else {
            context.sendMessage("请输入路由信息: ");
            routeInfo = context.receiveMessage();
        }
        return routeInfo;
    }

    private String obtainDispatchInfo(Context context, CommandLine cmd) throws Exception {
        String dispatchInfo;
        if (cmd.hasOption(COMMAND_OPTION_SEND_RINFO)) {
            dispatchInfo = (String) cmd.getParsedOptionValue(COMMAND_OPTION_SEND_RINFO);
        } else {
            context.sendMessage("请输入调度信息: ");
            dispatchInfo = context.receiveMessage();
        }
        return dispatchInfo;
    }

    private String obtainSendInfo(Context context, CommandLine cmd) throws Exception {
        String sendInfo;
        if (cmd.hasOption(COMMAND_OPTION_SEND_RINFO)) {
            sendInfo = (String) cmd.getParsedOptionValue(COMMAND_OPTION_SEND_RINFO);
        } else {
            context.sendMessage("请输入发送信息: ");
            sendInfo = context.receiveMessage();
        }
        return sendInfo;
    }
}
