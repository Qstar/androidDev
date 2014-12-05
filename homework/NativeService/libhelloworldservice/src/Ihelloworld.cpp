#define LOG_TAG "HelloWorldService"

#include <utils/Log.h>
#include <binder/IServiceManager.h>

#include "Ihelloworldservice.h"
#include "Ihelloworld.h"

using namespace std;

namespace android {

        sp<IHelloWorldService>  IHelloWorld::sHelloWorldService;	
        const sp<IHelloWorldService>& IHelloWorld::getHelloWorldService()
	{
            sp<IServiceManager> sm = defaultServiceManager();
            sp<IBinder> binder;

        do {
		binder = sm->getService(String16("hello.world"));
		if (binder != 0)break;
		usleep(500000);
        } while(true);
        sHelloWorldService = interface_cast<IHelloWorldService>(binder);
        return sHelloWorldService;
	}
};
