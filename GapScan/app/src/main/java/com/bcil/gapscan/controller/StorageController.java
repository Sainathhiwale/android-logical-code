package com.bcil.gapscan.controller;

import java.io.File;

public class StorageController {
    public static boolean checkSettingFile(String file_path, String file_name) {
        try {
            File file = new File(file_path + "/" + file_name);
            return file.exists();
        } catch (Exception e) {
            return false;
        }
    }
}
