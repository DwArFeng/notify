package com.dwarfeng.notify.impl.service.telqos;

import com.dwarfeng.notify.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessages;

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
        return _ -> ImplMessages.message(ImplMessageKey.TELQOS_SUPPORT_DESCRIPTION);
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
        list.add(
                Option.builder().longOpt(COMMAND_OPTION_RESET_ROUTER).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_SUPPORT_OPTION_RESET_ROUTER)).get()
        );
        list.add(
                Option.builder().longOpt(COMMAND_OPTION_RESET_SENDER).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_SUPPORT_OPTION_RESET_SENDER)).get()
        );
        list.add(
                Option.builder().longOpt(COMMAND_OPTION_RESET_DISPATCHER).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_SUPPORT_OPTION_RESET_DISPATCHER)).get()
        );
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
                context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_SUPPORT_RESET_ROUTER_SUCCEEDED));
                break;
            case COMMAND_OPTION_RESET_SENDER:
                supportQosService.resetSender();
                context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_SUPPORT_RESET_SENDER_SUCCEEDED));
                break;
            case COMMAND_OPTION_RESET_DISPATCHER:
                supportQosService.resetDispatcher();
                context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_SUPPORT_RESET_DISPATCHER_SUCCEEDED));
                break;
            default:
                throw new IllegalStateException(ImplMessages.message(ImplMessageKey.ERROR_INTERNAL_UNREACHABLE));
        }
    }
}
