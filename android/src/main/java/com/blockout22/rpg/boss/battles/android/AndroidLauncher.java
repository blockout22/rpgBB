package com.blockout22.rpg.boss.battles.android;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.blockout22.rpg.boss.battles.RPGBossBattles;

import java.util.List;

/** Launches the Android application. */
public class AndroidLauncher extends AndroidApplication {

//    private BillingClient billingClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        PackageInfo i = null;
        try {
            i = this.getPackageManager().getPackageInfo(getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        initialize(new RPGBossBattles(i != null ? i.versionCode : -1), configuration);

//        billingClient = BillingClient.newBuilder(this).setListener(new PurchasesUpdatedListener() {
//            @Override
//            public void onPurchasesUpdated(int responseCode, @Nullable List<Purchase> purchases) {
//
//            }
//        }).build();
//
//        billingClient.startConnection(new BillingClientStateListener() {
//            @Override
//            public void onBillingSetupFinished(int responseCode) {
//
//            }
//
//            @Override
//            public void onBillingServiceDisconnected() {
//
//            }
//        });
    }
}