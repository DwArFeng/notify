package com.dwarfeng.notify.impl.service.telqos;

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
        return context -> "清除处理器操作/查看";
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
        list.add(Option.builder(COMMAND_OPTION_ONLINE).optionalArg(true).hasArg(false).desc("上线清除处理器").build());
        list.add(Option.builder(COMMAND_OPTION_OFFLINE).optionalArg(true).hasArg(false).desc("下线清除处理器").build());
        list.add(Option.builder(COMMAND_OPTION_START).optionalArg(true).hasArg(false).desc("启动清除处理器").build());
        list.add(Option.builder(COMMAND_OPTION_STOP).optionalArg(true).hasArg(false).desc("停止清除处理器").build());
        list.add(Option.builder(COMMAND_OPTION_STATUS).optionalArg(true).hasArg(false).desc("查看清除处理器状态").build());
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
                context.sendMessage("清除处理器已上线!");
                break;
            case COMMAND_OPTION_OFFLINE:
                purgeQosService.offline();
                context.sendMessage("清除处理器已下线!");
                break;
            case COMMAND_OPTION_START:
                purgeQosService.start();
                context.sendMessage("清除处理器已启动!");
                break;
            case COMMAND_OPTION_STOP:
                purgeQosService.stop();
                context.sendMessage("清除处理器已停止!");
                break;
            case COMMAND_OPTION_STATUS:
                printStatus(context);
                break;
            default:
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
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
