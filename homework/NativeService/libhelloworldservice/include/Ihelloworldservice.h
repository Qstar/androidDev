#define LOG_TAG "HelloWorldService"

#ifndef _IHELLOWORLDSERVICE_H_
#define _IHELLOWORLDSERVICE_H_

#include <utils/Log.h>
#include <utils/RefBase.h>
#include <binder/IInterface.h>
#include <binder/Parcel.h>

namespace android {

enum {
       HELLO_GET = 1,
};

class IHelloWorldService: public IInterface {
public:

	DECLARE_META_INTERFACE(HelloWorldService);
	virtual const char* hello_get() = 0;
};

class BpHelloWorldService : public BpInterface<IHelloWorldService>
{
public:
    BpHelloWorldService(const sp<IBinder>& impl)
        :BpInterface<IHelloWorldService>(impl)
    {
	char* mObj = "BpHelloWorldService";
    }
	virtual const char* hello_get()
	{   
              Parcel data, reply;
              data.writeInterfaceToken(IHelloWorldService::getInterfaceDescriptor());
              remote()->transact(HELLO_GET, data, &reply);
	      return reply.readCString();
	}
};

class BnHelloWorldService : public BnInterface<IHelloWorldService>
{
public:
    virtual status_t    onTransact( uint32_t code,
                                    const Parcel& data,
                                    Parcel* reply,
                                    uint32_t flags = 0);
};

};
#endif
