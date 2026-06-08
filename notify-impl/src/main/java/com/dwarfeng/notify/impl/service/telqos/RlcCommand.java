package com.dwarfeng.notify.impl.service.telqos;

import com.dwarfeng.notify.stack.handler.Router;
import com.dwarfeng.notify.stack.service.NotifyQosService;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.sdk.configuration.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.util.CliCommandUtil;
import com.dwarfeng.springtelqos.stack.command.CommandDescriptor;
import com.dwarfeng.springtelqos.stack.command.CommandExecutor;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@TelqosCommand
public class RlcCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String IDENTITY = "rlc";

    // region 指令选项

    private static final String COMMAND_OPTION_LOOKUP = "l";
    private static final String COMMAND_OPTION_CLEAR = "c";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_LOOKUP,
            COMMAND_OPTION_CLEAR
    };

    // endregion

    private final NotifyQosService notifyQosService;

    public RlcCommand(NotifyQosService notifyQosService) {
        super(IDENTITY);
        this.notifyQosService = notifyQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return context -> "路由器本地缓存运维模块";
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
                Option.builder(COMMAND_OPTION_LOOKUP).optionalArg(true).hasArg(true).type(Number.class)
                        .desc("查询路由器").build()
        );
        list.add(Option.builder(COMMAND_OPTION_CLEAR).optionalArg(true).hasArg(false).desc("清除路由器").build());
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
                notifyQosService.clearRouterLocalCache();
                context.sendMessage("本地缓存已清除");
                break;
            default:
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
        }
    }

    private void handleLookup(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        LongIdKey routerInfoKey = new LongIdKey(((Number) cmd.getParsedOptionValue(COMMAND_OPTION_LOOKUP)).longValue());
        Router router = notifyQosService.getRouter(routerInfoKey);
        if (Objects.isNull(router)) {
            context.sendMessage("not exists");
        } else {
            context.sendMessage(router.toString());
        }
    }
}
