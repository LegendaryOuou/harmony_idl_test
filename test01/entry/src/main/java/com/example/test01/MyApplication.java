package com.example.test01;

import com.example.test01.ability.ServiceAbility;
import ohos.aafwk.ability.AbilityPackage;
import ohos.hiviewdfx.HiLog;

public class MyApplication extends AbilityPackage {
    @Override
    public void onInitialize() {
        super.onInitialize();
        HiLog.info(ServiceAbility.LABEL_LOG, "MyApplication start...");
    }
}
