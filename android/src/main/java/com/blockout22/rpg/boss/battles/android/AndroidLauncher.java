package com.blockout22.rpg.boss.battles.android;

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
        initialize(new RPGBossBattles(), configuration);

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