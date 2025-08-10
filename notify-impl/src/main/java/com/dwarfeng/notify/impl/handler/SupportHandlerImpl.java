package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.sdk.handler.DispatcherSupporter;
import com.dwarfeng.notify.sdk.handler.RouterSupporter;
import com.dwarfeng.notify.sdk.handler.SenderSupporter;
import com.dwarfeng.notify.stack.bean.entity.DispatcherSupport;
import com.dwarfeng.notify.stack.bean.entity.RouterSupport;
import com.dwarfeng.notify.stack.bean.entity.SenderSupport;
import com.dwarfeng.notify.stack.handler.SupportHandler;
import com.dwarfeng.notify.stack.service.DispatcherSupportMaintainService;
import com.dwarfeng.notify.stack.service.RouterSupportMaintainService;
import com.dwarfeng.notify.stack.service.SenderSupportMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SupportHandlerImpl implements SupportHandler {

    private final RouterSupportMaintainService routerSupportMaintainService;
    private final SenderSupportMaintainService senderSupportMaintainService;
    private final DispatcherSupportMaintainService dispatcherSupportMaintainService;

    private final List<RouterSupporter> routerSupporters;
    private final List<SenderSupporter> senderSupporters;
    private final List<DispatcherSupporter> dispatcherSupporters;

    public SupportHandlerImpl(
            RouterSupportMaintainService routerSupportMaintainService,
            SenderSupportMaintainService senderSupportMaintainService,
            DispatcherSupportMaintainService dispatcherSupportMaintainService,
            List<RouterSupporter> routerSupporters,
            List<SenderSupporter> senderSupporters,
            List<DispatcherSupporter> dispatcherSupporters
    ) {
        this.routerSupportMaintainService = routerSupportMaintainService;
        this.senderSupportMaintainService = senderSupportMaintainService;
        this.dispatcherSupportMaintainService = dispatcherSupportMaintainService;
        this.routerSupporters = Optional.ofNullable(routerSupporters).orElse(Collections.emptyList());
        this.senderSupporters = Optional.ofNullable(senderSupporters).orElse(Collections.emptyList());
        this.dispatcherSupporters = Optional.ofNullable(dispatcherSupporters).orElse(Collections.emptyList());
    }

    @Override
    @BehaviorAnalyse
    public void resetRouter() throws HandlerException {
        try {
            doResetRouter();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void doResetRouter() throws Exception {
        List<StringIdKey> routerKeys = routerSupportMaintainService.lookupAsList().stream()
                .map(RouterSupport::getKey).collect(Collectors.toList());
        routerSupportMaintainService.batchDelete(routerKeys);
        List<RouterSupport> routerSupports = routerSupporters.stream().map(supporter -> new RouterSupport(
                new StringIdKey(supporter.provideType()),
                supporter.provideLabel(),
                supporter.provideDescription(),
                supporter.provideExampleParam()
        )).collect(Collectors.toList());
        routerSupportMaintainService.batchInsert(routerSupports);
    }

    @Override
    @BehaviorAnalyse
    public void resetSender() throws HandlerException {
        try {
            doResetSender();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void doResetSender() throws Exception {
        List<StringIdKey> senderKeys = senderSupportMaintainService.lookupAsList().stream()
                .map(SenderSupport::getKey).collect(Collectors.toList());
        senderSupportMaintainService.batchDelete(senderKeys);
        List<SenderSupport> senderSupports = senderSupporters.stream().map(supporter -> new SenderSupport(
                new StringIdKey(supporter.provideType()),
                supporter.provideLabel(),
                supporter.provideDescription(),
                supporter.provideExampleParam()
        )).collect(Collectors.toList());
        senderSupportMaintainService.batchInsert(senderSupports);
    }

    @Override
    @BehaviorAnalyse
    public void resetDispatcher() throws HandlerException {
        try {
            doResetDispatcher();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void doResetDispatcher() throws Exception {
        List<StringIdKey> dispatcherKeys = dispatcherSupportMaintainService.lookupAsList().stream()
                .map(DispatcherSupport::getKey).collect(Collectors.toList());
        dispatcherSupportMaintainService.batchDelete(dispatcherKeys);
        List<DispatcherSupport> dispatcherSupports = dispatcherSupporters.stream().map(supporter -> new DispatcherSupport(
                new StringIdKey(supporter.provideType()),
                supporter.provideLabel(),
                supporter.provideDescription(),
                supporter.provideExampleParam()
        )).collect(Collectors.toList());
        dispatcherSupportMaintainService.batchInsert(dispatcherSupports);
    }
}
