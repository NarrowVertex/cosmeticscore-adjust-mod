package com.narrowvertex.cam;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public class CAMClient {

    private static CAMClient instance;

    public static Logger LOGGER = LogUtils.getLogger();

    public CAMClient() {
        instance = this;
    }

    public void init() {

    }

    public static CAMClient getInstance() {
        return instance;
    }
}
