package com.blockout22.rpg.boss.battles;

import java.util.Random;

public class NameGenerator {

    private static String[] prefix = {"The Great", "Super", "The Hollow", "The Grand", "Kington", "Mega"};
    private static String[] names = {"Golioth", "Doom", "Falcon", "Rock", "Omega", "Hyper", "Torque"};
    private static String[] sufix = {"Destroyer", "Hunter", "Seas", "Afterlife", ""};

    private static Random rand = new Random();

    public static String[] getNames(int amt, boolean removeDupes){
        String[] nameList = new String[amt];
        for(int i = 0; i < amt; i++){
            boolean hasPrefix = false;
            boolean hasSufix = false;

            int r = rand.nextInt(3);

            if(r == 0){
                hasPrefix = true;
                hasSufix = true;
            }else if(r == 1){
                hasPrefix = true;
                hasSufix = false;
            }else if(r == 2){
                hasPrefix = false;
                hasSufix = true;
            }

            String name = "";

            if(hasPrefix){
                name += prefix[rand.nextInt(prefix.length)] + " ";
            }

            //
            name += names[rand.nextInt(names.length)] + " ";

            if(hasSufix){
                name += sufix[rand.nextInt(sufix.length)];
            }

            System.out.println(i);

            if(removeDupes){
                for (String n : nameList) {
                    if(n != null) {
                        if (n.equals(name)) {
                            System.out.println("Remove Dupe: " + name + " : " + n);
//                                i--;
                            continue;
                        }
                    }
                }
            }

            nameList[i] = name;
        }

//        System.out.println("Max Names: " + ((prefix.length * names.length * sufix.length) + (prefix.length * names.length) + (names.length * sufix.length) + (names.length)));
        return nameList;
    }
}
