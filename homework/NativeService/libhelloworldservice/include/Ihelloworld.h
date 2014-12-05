#include <utils/RefBase.h>
#include "Ihelloworldservice.h"

namespace android {

class IHelloWorld: virtual public RefBase
{
public:
	IHelloWorld() {};
	virtual ~IHelloWorld() {};
	static const sp<IHelloWorldService>& getHelloWorldService();
	
private:
	static  sp<IHelloWorldService>     sHelloWorldService;
	};
};
