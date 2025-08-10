package com.dwarfeng.notify.impl.service.telqos;

import com.dwarfeng.notify.stack.service.SupportQosService;
import com.dwarfeng.springtelqos.node.config.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

@TelqosCommand
public class SupportCommand extends CliCommand {

    private static final String COMMAND_OPTION_RESET_ROUTER = "reset-router";
    private static final String COMMAND_OPTION_RESET_SENDER = "reset-sender";
    private static final String COMMAND_OPTION_RESET_DISPATCHER = "reset-dispatcher";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_RESET_ROUTER,
            COMMAND_OPTION_RESET_SENDER,
            COMMAND_OPTION_RESET_DISPATCHER,
    };

    private static final String IDENTITY = "support";
    private static final String DESCRIPTION = "支持操作";

    private static final String CMD_LINE_SYNTAX_RESET_ROUTER = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_ROUTER);
    private static final String CMD_LINE_SYNTAX_RESET_SENDER = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_SENDER);
    private static final String CMD_LINE_SYNTAX_RESET_DISPATCHER = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_DISPATCHER);

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_RESET_ROUTER,
            CMD_LINE_SYNTAX_RESET_SENDER,
            CMD_LINE_SYNTAX_RESET_DISPATCHER,
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final SupportQosService supportQosService;

    public SupportCommand(SupportQosService supportQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.supportQosService = supportQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder().longOpt(COMMAND_OPTION_RESET_ROUTER).desc("重置路由器").build());
        list.add(Option.builder().longOpt(COMMAND_OPTION_RESET_SENDER).desc("重置发送器").build());
        list.add(Option.builder().longOpt(COMMAND_OPTION_RESET_DISPATCHER).desc("重置调度器").build());
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
                case COMMAND_OPTION_RESET_ROUTER:
                    supportQosService.resetRouter();
                    context.sendMessage("重置路由器成功。");
                    break;
                case COMMAND_OPTION_RESET_SENDER:
                    supportQosService.resetSender();
                    context.sendMessage("重置发送器成功。");
                    break;
                case COMMAND_OPTION_RESET_DISPATCHER:
                    supportQosService.resetDispatcher();
                    context.sendMessage("重置调度器成功。");
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }
}
