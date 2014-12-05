LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := 

LOCAL_REQUIRED_MODULES := libhelloworldservice
LOCAL_JNI_SHARED_LIBRARIES := libhelloworldservice

# Use the following include to make our test apk.
include $(call all-makefiles-under,$(LOCAL_PATH))
