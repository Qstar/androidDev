#include "Ihelloworld.h"

namespace android {
	
class HelloWorld:public virtual IHelloWorld
{
public:		
		HelloWorld();
		virtual ~HelloWorld();
		static const char* hello_get();
		};
		
};
