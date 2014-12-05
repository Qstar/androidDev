#define LOG_TAG "HelloWorldService"

#include <utils/Log.h>
#include <sys/types.h>

#include <utils/Errors.h>
#include <binder/Parcel.h>
#include <binder/IServiceManager.h>
#include <binder/Binder.h>
#include <binder/IInterface.h>

#include "Ihelloworldservice.h"

namespace android {

IMPLEMENT_META_INTERFACE(HelloWorldService, "Hello.World");

status_t BnHelloWorldService::onTransact(uint32_t code,
                                                const Parcel &data,
                                                Parcel *reply,
                                                uint32_t flags)
{
        switch(code) {
	 case HELLO_GET:{
		const char* err;
		CHECK_INTERFACE(IHelloWorldService, data, reply);
                
		err = hello_get();
		reply->writeCString(err);
		  
		return 0;
	}
        default:
                return BBinder::onTransact(code, data, reply, flags);
        }
}

};
