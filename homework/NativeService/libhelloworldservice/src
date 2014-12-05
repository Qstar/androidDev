#define LOG_TAG "HelloWorldService"

#include "Ihelloworldservice.h"
#include "helloworld.h"

namespace android {
	
	HelloWorld::HelloWorld(){}

	HelloWorld::~HelloWorld(){}
	const char* HelloWorld::hello_get()
	{
		const char* err;
    		const sp<IHelloWorldService>& service(getHelloWorldService());

		if (service != 0)
    		err = service->hello_get();
		return err;
	}
};
