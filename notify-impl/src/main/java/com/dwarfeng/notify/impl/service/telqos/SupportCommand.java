package com.dwarfeng.notify.impl.service.telqos;

import com.dwarfeng.notify.stack.service.SupportQosService;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.sdk.configuration.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.util.CliCommandUtil;
import com.dwarfeng.springtelqos.stack.command.CommandDescriptor;
import com.dwarfeng.springtelqos.stack.command.CommandExecutor;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

@TelqosCommand
public class SupportCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String IDENTITY = "support";

    // region 指令选项

    private static final String COMMAND_OPTION_RESET_ROUTER = "reset-router";
    private static final String COMMAND_OPTION_RESET_SENDER = "reset-sender";
    private static final String COMMAND_OPTION_RESET_DISPATCHER = "reset-dispatcher";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_RESET_ROUTER,
            COMMAND_OPTION_RESET_SENDER,
            COMMAND_OPTION_RESET_DISPATCHER,
    };

    // endregion

    private final SupportQosService supportQosService;

    public SupportCommand(SupportQosService supportQosService) {
        super(IDENTITY);
        this.supportQosService = supportQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return context -> "支持操作";
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_ROUTER),
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_SENDER),
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_DISPATCHER)
        };
        return CliCommandUtil.cliSyntax(patterns);
    }

    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder().longOpt(COMMAND_OPTION_RESET_ROUTER).optionalArg(true).hasArg(false)
                .desc("重置路由器").build());
        list.add(Option.builder().longOpt(COMMAND_OPTION_RESET_SENDER).optionalArg(true).hasArg(false)
                .desc("重置发送器").build());
        list.add(Option.builder().longOpt(COMMAND_OPTION_RESET_DISPATCHER).optionalArg(true).hasArg(false)
                .desc("重置调度器").build());
        return list;
    }

    @Override
    protected void executeWithCmd(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        Pair<String, Integer> pair = CliCommandUtil.analyseCommand(cmd, COMMAND_OPTION_ARRAY);
        if (pair.getRight() != 1) {
            context.sendMessage(CliCommandUtil.optionMismatchMessage(COMMAND_OPTION_ARRAY));
            context.sendMessage(context.getCommandManual(context.getRuntimeIdentity()));
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
            default:
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
        }
    }
}
