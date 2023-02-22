#include <stdio.h>
#include <stdlib.h>
#include "config_access.h"
#include "scheduler.h"

extern const char* filename;


int main(int argc, const char** argv) {
	filename = argc == 2? argv[1] : "local_config";
	default_init();
	init_done = 1;
	execute_schedule();
	return 0;
}
