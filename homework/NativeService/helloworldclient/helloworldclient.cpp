#define LOG_TAG "HelloWorldService"
#define CMD_NR 2

#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <grp.h>

#include <binder/IPCThreadState.h>
#include <binder/ProcessState.h>
#include <binder/IServiceManager.h>
#include <utils/Log.h>
#include "helloworld.h"

using namespace android;

int input()
{
while(1)
	{
	int command;
	char s[20];
	printf("Please input cmd 1 ? 0 will exit!\ncmd = ");
	scanf("%d",&command);
	gets(s);
	if(command >= 0 && command <= CMD_NR) return command; 
	}
}

int main(int argc, char *argv[])
{
	int cmd;
	const char *err1;
	const char *err2;

	printf("HelloWorldclient is starting now!\n");
	
	sp<ProcessState> proc(ProcessState::self());
    	sp<IServiceManager> sm = defaultServiceManager();
	
	cmd = input();
	
	while(cmd != 0)
	{	
	switch(cmd)
		{
	case 1:
		printf("Try to get string HelloWorld!\n");
		err1 = HelloWorld::hello_get();
	
		if(strcmp(err1,"HelloWorld")==0)
		{
			printf("Call hello_get() right!\nget \"%s\" from server!\n",err1);	
		}
		else
		{
			printf("Get HelloWorld error....\n");
		}
		break;
	default:
		printf("Nothing to do!\n");
		}
		
		cmd = input();
	};
		printf("Hello client exit now!\n");

	return 0;
}
