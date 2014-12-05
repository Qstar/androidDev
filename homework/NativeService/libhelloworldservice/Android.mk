LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_MODULE := libhelloworldservice

LOCAL_SRC_FILES := src/helloworldservice.cpp   \
			src/Ihelloworldservice.cpp 	\
			src/helloworld.cpp		\
			src/Ihelloworld.cpp		\

LOCAL_C_INCLUDES :=         \
    $(LOCAL_PATH)/include

LOCAL_CFLAGS += -DPLATFORM_ANDROID


# for now, until I do a full rebuild.
LOCAL_PRELINK_MODULE := false

LOCAL_SHARED_LIBRARIES += liblog	\
				libutils \
				libui	\
				libbinder

LOCAL_CFLAGS += -Idalvik/libnativehelper/include/nativehelper

include $(BUILD_SHARED_LIBRARY)
