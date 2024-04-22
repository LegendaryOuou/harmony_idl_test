package com.example.test01.ability;

import com.example.test01.MyIdlInterfaceStub;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.IntentParams;
import ohos.rpc.IRemoteObject;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.rpc.RemoteException;

public class ServiceAbility extends Ability {
    private IRemoteObject myIdl = new MyIdlInterfaceStub("MyIdl") {
        @Override
        public int returnPlus1(int _num1) throws RemoteException {
            return _num1 + 1;
        }
    };

    public static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "OYJT");

    @Override
    public IRemoteObject onConnect(Intent intent) {
        HiLog.info(LABEL_LOG, "onConnect: "+intent.getElement().getBundleName());
        HiLog.info(LABEL_LOG, "onConnect: "+intent.getElement().getAbilityName());
        HiLog.info(LABEL_LOG, "onConnect: "+myIdl);
        return myIdl;
    }

    @Override
    public void onStart(Intent intent) {
        HiLog.info(LABEL_LOG, "ServiceAbility::onStart: "+intent);
        super.onStart(intent);
    }

    @Override
    public void onBackground() {
        super.onBackground();
        HiLog.info(LABEL_LOG, "ServiceAbility::onBackground");
    }

    @Override
    public void onStop() {
        super.onStop();
        HiLog.info(LABEL_LOG, "ServiceAbility::onStop");
    }

    @Override
    public void onCommand(Intent intent, boolean restart, int startId) {
        super.onCommand(intent, restart, startId);
        String value = (String) intent.getParams().getParam("name");
        int num = (Integer) intent.getParams().getParam("num");
        HiLog.info(LABEL_LOG, "ServiceAbility::onCommand:"+value + ", "+ num +", "+String.valueOf(restart) + ", " + startId);
    }

    @Override
    public void onDisconnect(Intent intent) {
        super.onDisconnect(intent);
        HiLog.info(LABEL_LOG, "ServiceAbility::onDisconnect");
    }
}