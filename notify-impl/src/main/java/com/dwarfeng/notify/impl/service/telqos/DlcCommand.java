package com.dwarfeng.notify.impl.service.telqos;

import com.dwarfeng.notify.stack.handler.Dispatcher;
import com.dwarfeng.notify.stack.service.NotifyQosService;
import com.dwarfeng.springtelqos.node.config.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@TelqosCommand
public class DlcCommand extends CliCommand {

    private static final String COMMAND_OPTION_LOOKUP = "l";
    private static final String COMMAND_OPTION_CLEAR = "c";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_LOOKUP,
            COMMAND_OPTION_CLEAR
    };

    private static final String IDENTITY = "dlc";
    private static final String DESCRIPTION = "调度器本地缓存运维模块";

    private static final String CMD_LINE_SYNTAX_LOOKUP = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_LOOKUP) + " id";
    private static final String CMD_LINE_SYNTAX_CLEAR = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_CLEAR);

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_LOOKUP,
            CMD_LINE_SYNTAX_CLEAR
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final NotifyQosService notifyQosService;

    public DlcCommand(NotifyQosService notifyQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.notifyQosService = notifyQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_LOOKUP).desc("查询调度器").type(String.class).build());
        list.add(Option.builder(COMMAND_OPTION_CLEAR).desc("清除调度器").build());
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
                    notifyQosService.clearDispatcherLocalCache();
                    context.sendMessage("本地缓存已清除");
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void handleLookup(Context context, CommandLine cmd) throws Exception {
        StringIdKey dispatcherInfoKey = new StringIdKey((String) cmd.getParsedOptionValue(COMMAND_OPTION_LOOKUP));
        Dispatcher dispatcher = notifyQosService.getDispatcher(dispatcherInfoKey);
        if (Objects.isNull(dispatcher)) {
            context.sendMessage("not exists");
        } else {
            context.sendMessage(dispatcher.toString());
        }
    }
}
