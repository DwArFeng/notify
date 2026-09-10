package com.dwarfeng.notify.impl.service.telqos;

import com.dwarfeng.notify.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessages;

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
        return _ -> ImplMessages.message(ImplMessageKey.TELQOS_SENDER_LOCAL_CACHE_DESCRIPTION);
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
        list.add(
                Option.builder(COMMAND_OPTION_LOOKUP).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_SENDER_LOCAL_CACHE_OPTION_LOOKUP)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_CLEAR).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_SENDER_LOCAL_CACHE_OPTION_CLEAR)).get()
        );
        list.add(
                Option.builder(COMMAND_SUB_OPTION_NSID).hasArg(true).type(Number.class)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_SENDER_LOCAL_CACHE_OPTION_NOTIFY_SETTING_ID))
                        .get()
        );
        list.add(
                Option.builder(COMMAND_SUB_OPTION_TID).hasArg(true).type(String.class)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_SENDER_LOCAL_CACHE_OPTION_TOPIC_ID)).get()
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
                context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_LOCAL_CACHE_CLEARED));
                break;
            default:
                throw new IllegalStateException(ImplMessages.message(ImplMessageKey.ERROR_INTERNAL_UNREACHABLE));
        }
    }

    private void handleLookup(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        if (!cmd.hasOption(COMMAND_SUB_OPTION_NSID) || !cmd.hasOption(COMMAND_SUB_OPTION_TID)) {
            context.sendMessage(context.getCommandManual(context.getRuntimeIdentity()));
            return;
        }
        long notifySettingId = ((Number) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_NSID)).longValue();
        String topicId = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_TID);
        Sender sender = notifyQosService.getSender(new SenderInfoKey(notifySettingId, topicId));
        if (Objects.isNull(sender)) {
            context.sendMessage("not exists");
        } else {
            context.sendMessage(sender.toString());
        }
    }
}
