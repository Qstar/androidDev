#define LOG_TAG "HelloWorldService"

#include <sys/types.h>
#include <unistd.h>
#include <grp.h>

#include <binder/IPCThreadState.h>
#include <binder/ProcessState.h>
#include <binder/IServiceManager.h>
#include <utils/Log.h>

#include "helloworldservice.h"
#include "Ihelloworldservice.h"

using namespace android;

int main(int argc, char *argv[])
{
	sp<ProcessState> proc(ProcessState::self());
        sp<IServiceManager> sm = defaultServiceManager();
	HelloWorldService::instantiate();

	ProcessState::self()->startThreadPool();

	IPCThreadState::self()->joinThreadPool();
	printf("HelloWorldServer exit!\n");
	return(0);
}
