package com.dwarfeng.notify.impl.service.telqos;

import com.dwarfeng.notify.stack.bean.key.SenderInfoKey;
import com.dwarfeng.notify.stack.handler.Sender;
import com.dwarfeng.notify.stack.service.NotifyQosService;
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
import java.util.Objects;

@TelqosCommand
public class SlcCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String IDENTITY = "slc";

    // region 指令选项

    private static final String COMMAND_OPTION_LOOKUP = "l";
    private static final String COMMAND_OPTION_CLEAR = "c";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_LOOKUP,
            COMMAND_OPTION_CLEAR
    };

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String COMMAND_SUB_OPTION_NSID = "nsid";
    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String COMMAND_SUB_OPTION_TID = "tid";

    // endregion

    private final NotifyQosService notifyQosService;

    public SlcCommand(NotifyQosService notifyQosService) {
        super(IDENTITY);
        this.notifyQosService = notifyQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return context -> "发送器本地缓存运维模块";
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_LOOKUP) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_NSID) + " notify-setting-id] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_TID) + " topic-id]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_CLEAR)
        };
        return CliCommandUtil.cliSyntax(patterns);
    }

    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_LOOKUP).optionalArg(true).hasArg(false).desc("查询发送器").build());
        list.add(Option.builder(COMMAND_OPTION_CLEAR).optionalArg(true).hasArg(false).desc("清除发送器").build());
        list.add(
                Option.builder(COMMAND_SUB_OPTION_NSID).hasArg(true).type(Number.class).desc("通知设置 ID").build()
        );
        list.add(
                Option.builder(COMMAND_SUB_OPTION_TID).hasArg(true).type(String.class).desc("主题 ID").build()
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
                notifyQosService.clearSenderLocalCache();
                context.sendMessage("本地缓存已清除");
                break;
            default:
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
        }
    }

    private void handleLookup(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        if (!cmd.hasOption(COMMAND_SUB_OPTION_NSID) || !cmd.hasOption(COMMAND_SUB_OPTION_TID)) {
            context.sendMessage(context.getCommandManual(context.getRuntimeIdentity()));
            return;
        }
        long notifySettingId = ((Number) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_NSID)).longValue();
        String topicId = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_TID);
        Sender sender = notifyQosService.getSender(new SenderInfoKey(notifySettingId, topicId));
        if (Objects.isNull(sender)) {
            context.sendMessage("not exists");
        } else {
            context.sendMessage(sender.toString());
        }
    }
}
