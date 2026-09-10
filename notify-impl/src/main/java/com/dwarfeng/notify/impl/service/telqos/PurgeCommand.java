package com.dwarfeng.notify.impl.service.telqos;

import com.dwarfeng.notify.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessages;

import com.dwarfeng.notify.stack.service.PurgeQosService;
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
public class PurgeCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String IDENTITY = "purge";

    // region 指令选项

    private static final String COMMAND_OPTION_ONLINE = "online";
    private static final String COMMAND_OPTION_OFFLINE = "offline";
    private static final String COMMAND_OPTION_START = "start";
    private static final String COMMAND_OPTION_STOP = "stop";
    private static final String COMMAND_OPTION_STATUS = "status";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_ONLINE,
            COMMAND_OPTION_OFFLINE,
            COMMAND_OPTION_START,
            COMMAND_OPTION_STOP,
            COMMAND_OPTION_STATUS
    };

    // endregion

    private final PurgeQosService purgeQosService;

    public PurgeCommand(PurgeQosService purgeQosService) {
        super(IDENTITY);
        this.purgeQosService = purgeQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return _ -> ImplMessages.message(ImplMessageKey.TELQOS_PURGE_DESCRIPTION);
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_ONLINE),
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_OFFLINE),
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_START),
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_STOP),
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_STATUS)
        };
        return CliCommandUtil.cliSyntax(patterns);
    }

    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(COMMAND_OPTION_ONLINE).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_PURGE_OPTION_ONLINE)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_OFFLINE).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_PURGE_OPTION_OFFLINE)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_START).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_PURGE_OPTION_START)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_STOP).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_PURGE_OPTION_STOP)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_STATUS).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_PURGE_OPTION_STATUS)).get()
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
            case COMMAND_OPTION_ONLINE:
                purgeQosService.online();
                context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_PURGE_ONLINE));
                break;
            case COMMAND_OPTION_OFFLINE:
                purgeQosService.offline();
                context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_PURGE_OFFLINE));
                break;
            case COMMAND_OPTION_START:
                purgeQosService.start();
                context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_PURGE_STARTED));
                break;
            case COMMAND_OPTION_STOP:
                purgeQosService.stop();
                context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_PURGE_STOPPED));
                break;
            case COMMAND_OPTION_STATUS:
                printStatus(context);
                break;
            default:
                throw new IllegalStateException(ImplMessages.message(ImplMessageKey.ERROR_INTERNAL_UNREACHABLE));
        }
    }

    private void printStatus(CommandExecutor.Context context) throws Exception {
        boolean onlineFlag = purgeQosService.isOnline();
        boolean latchHoldingFlag = purgeQosService.isLockHolding();
        boolean startedFlag = purgeQosService.isStarted();
        boolean workingFlag = purgeQosService.isWorking();

        context.sendMessage(String.format(
                "online: %b, latch holding: %b, started: %b, working: %b.",
                onlineFlag, latchHoldingFlag, startedFlag, workingFlag
        ));
    }
}
