package com.biryanify.parichay.biryanify;

public class SingletonDateClass {

        private static SingletonDateClass sSoleInstance;
        public String hrDate = "", dbDate = "";

        //private constructor.
        private SingletonDateClass(){
            //Prevent form the reflection api.
            if (sSoleInstance != null){
                throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
            }
        }

        public static SingletonDateClass getInstance(){
            if (sSoleInstance == null){
                //if there is no instance available... create new one
                sSoleInstance = new SingletonDateClass();
            }

            return sSoleInstance;
        }



}
