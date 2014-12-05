LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := helloworldservice.cpp 

LOCAL_C_INCLUDES := $(LOCAL_PATH)/../libhelloworldservice/include

LOCAL_CFLAGS += -DPLATFORM_ANDROID

LOCAL_MODULE := helloworldservice

# for now, until I do a full rebuild.
LOCAL_PRELINK_MODULE := false

LOCAL_SHARED_LIBRARIES += liblog	\
			libutils	\
			libui		\
			libhelloworldservice	\
			libbinder

LOCAL_CFLAGS += -Idalvik/libnativehelper/include/nativehelper

include $(BUILD_EXECUTABLE)
