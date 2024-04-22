package com.example.test02.slice;

import com.example.test01.IMyIdlInterface;
import com.example.test01.MyIdlInterfaceStub;
import com.example.test02.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.IAbilityConnection;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.bundle.ElementName;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.rpc.IRemoteObject;

public class MainAbilitySlice extends AbilitySlice {

    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "CLIENT");

    IMyIdlInterface proxy;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
    }

    @Override
    public void onActive() {
        super.onActive();
        Button btn = findComponentById(ResourceTable.Id_text_helloworld);
        btn.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                Intent intent = new Intent();
                ElementName elementName = new ElementName("", "com.example.test01",
                        "com.example.test01.ability.ServiceAbility");
                intent.setElement(elementName);
                intent.setParam("name", "欧阳俊涛");
                intent.setParam("num", 123);
                HiLog.info(LABEL_LOG, "Start conn。。。");
                startAbility(intent);
                HiLog.info(LABEL_LOG, "Start over。。。" + String.valueOf("over"));
//                boolean res = connectAbility(intent, conn);
//                HiLog.info(LABEL_LOG, "Start conn。。。" + String.valueOf(res));
//                try {
//                    int ans = proxy.returnPlus1(10);
//                    HiLog.info(LABEL_LOG, "res = " + ans);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });
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

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

}
