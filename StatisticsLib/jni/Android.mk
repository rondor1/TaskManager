LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := libstatistics
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := statistics.c

include $(BUILD_SHARED_LIBRARY)
