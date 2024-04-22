package com.example.test02;

import com.example.test01.IMyIdlInterface;
import com.example.test01.MyIdlInterfaceProxy;
import com.example.test01.MyIdlInterfaceStub;
import ohos.aafwk.ability.AbilityPackage;
import ohos.aafwk.ability.IAbilityConnection;
import ohos.aafwk.content.Intent;
import ohos.bundle.ElementName;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.rpc.IRemoteObject;
import ohos.rpc.RemoteException;

public class MyApplication extends AbilityPackage {

    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "CLIENT");

    private static IMyIdlInterface proxy;

    @Override
    public void onInitialize() {
        super.onInitialize();
        Intent intent = new Intent();
        ElementName elementName = new ElementName("", "com.example.test01", "com.example.test01.ability.ServiceAbility");
        intent.setElement(elementName);
        HiLog.info(LABEL_LOG, "Start conn。。。");
        boolean res = connectAbility(intent, conn);
        HiLog.info(LABEL_LOG, "Start conn。。。" + String.valueOf(res));
        try {
            int ans = proxy.returnPlus1(10);
            HiLog.info(LABEL_LOG, "res = " + ans);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    IAbilityConnection conn = new IAbilityConnection() {
        @Override
        public void onAbilityConnectDone(ElementName elementName, IRemoteObject iRemoteObject, int i) {
            HiLog.info(LABEL_LOG, "onAbilityConnectDone: " + iRemoteObject);
            HiLog.info(LABEL_LOG, "onAbilityConnectDone: " + elementName);
            HiLog.info(LABEL_LOG, "onAbilityConnectDone: " + i);
//            IMyIdlInterface proxy = MyIdlInterfaceStub.asInterface(iRemoteObject);
            proxy = MyIdlInterfaceStub.asInterface(iRemoteObject);
            HiLog.info(LABEL_LOG, "onAbilityConnectDone: " + proxy);

        }

        @Override
        public void onAbilityDisconnectDone(ElementName elementName, int i) {
            HiLog.info(LABEL_LOG, "onAbilityDisconnectDone: " + elementName);
            HiLog.info(LABEL_LOG, "onAbilityDisconnectDone: " + i);
        }
    };
}
