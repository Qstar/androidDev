#include "Ihelloworldservice.h"

#ifndef _HELLOWORLDSERVICE_H_
#define _HELLOWORLDSERVICE_H_

namespace android {

class HelloWorldService : public BnHelloWorldService
{
public:
    	HelloWorldService();
       ~HelloWorldService();

	static  void instantiate();
	virtual const char* hello_get();
};

};
#endif
