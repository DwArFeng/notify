package com.dwarfeng.notify.impl.service.telqos;

import com.dwarfeng.notify.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessages;

import com.dwarfeng.notify.stack.handler.Dispatcher;
import com.dwarfeng.notify.stack.service.NotifyQosService;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.sdk.configuration.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.util.CliCommandUtil;
import com.dwarfeng.springtelqos.stack.command.CommandDescriptor;
import com.dwarfeng.springtelqos.stack.command.CommandExecutor;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@TelqosCommand
public class DlcCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String IDENTITY = "dlc";

    // region 指令选项

    private static final String COMMAND_OPTION_LOOKUP = "l";
    private static final String COMMAND_OPTION_CLEAR = "c";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_LOOKUP,
            COMMAND_OPTION_CLEAR
    };

    // endregion

    private final NotifyQosService notifyQosService;

    public DlcCommand(NotifyQosService notifyQosService) {
        super(IDENTITY);
        this.notifyQosService = notifyQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return _ -> ImplMessages.message(ImplMessageKey.TELQOS_DISPATCHER_LOCAL_CACHE_DESCRIPTION);
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_LOOKUP) + " id",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_CLEAR)
        };
        return CliCommandUtil.cliSyntax(patterns);
    }

    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(COMMAND_OPTION_LOOKUP).optionalArg(true).hasArg(true).type(String.class)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_DISPATCHER_LOCAL_CACHE_OPTION_LOOKUP)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_CLEAR).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_DISPATCHER_LOCAL_CACHE_OPTION_CLEAR)).get()
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
            case COMMAND_OPTION_LOOKUP:
                handleLookup(context, cmd);
                break;
            case COMMAND_OPTION_CLEAR:
                notifyQosService.clearDispatcherLocalCache();
                context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_LOCAL_CACHE_CLEARED));
                break;
            default:
                throw new IllegalStateException(ImplMessages.message(ImplMessageKey.ERROR_INTERNAL_UNREACHABLE));
        }
    }

    private void handleLookup(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        StringIdKey dispatcherInfoKey = new StringIdKey(cmd.getParsedOptionValue(COMMAND_OPTION_LOOKUP));
        Dispatcher dispatcher = notifyQosService.getDispatcher(dispatcherInfoKey);
        if (Objects.isNull(dispatcher)) {
            context.sendMessage("not exists");
        } else {
            context.sendMessage(dispatcher.toString());
        }
    }
}
