#define LOG_TAG "HelloWorldService"

#include <binder/IServiceManager.h>
#include "helloworldservice.h"
#include "stdio.h"
#include "string.h"

namespace android {

HelloWorldService::HelloWorldService(){}

HelloWorldService::~HelloWorldService(){}

void HelloWorldService::instantiate() 
{
	defaultServiceManager()->addService(
        String16("hello.world"),new HelloWorldService());
}

const char* HelloWorldService::hello_get()
{
	char* s = "HelloWorld";
	return s;
}
};
