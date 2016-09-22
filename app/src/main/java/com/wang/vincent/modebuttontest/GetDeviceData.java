package com.wang.vincent.modebuttontest;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.os.StatFs;
import android.util.DisplayMetrics;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by vincent on 16-9-22.
 */

public class GetDeviceData {

    public GetDeviceData() {
    }

    public int getNumCores() {
        //Private Class to display only CPU devices in the directory listing
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                //Check if filename is "cpu", followed by a single digit number
                if(Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }

        try {
            //Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            //Filter to only list the devices we care about
            File[] files = dir.listFiles(new CpuFilter());
            Log.d("hehe", "CPU Count: "+files.length);
            //Return the number of cores (virtual CPU devices)
            return files.length;
        } catch(Exception e) {
            //Print exception
            Log.d("hehe", "CPU Count: Failed.");
            e.printStackTrace();
            //Default to return 1 core
            return 1;
        }
    }



    public long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    public static String getCpuName(){
        try{
            FileReader fr = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split(":\\s+",2);
            for(int i = 0; i < array.length; i++){
            }
            return array[1];

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public String getScreenResolution(Context con) {

        DisplayMetrics dm = con.getResources().getDisplayMetrics();
        String strOpt = dm.heightPixels + " * " + dm.widthPixels;
        return strOpt;
    }

}
