#include <stdio.h>
#include <stdlib.h>
#include <string.h>


const char * const read(void)
{
        char buf[128];
        gets(buf);
        return strdup(buf);
}

void f(void)
{
        printf("f called!\n");
        exit(0);
}

int main()
{
        printf("You wrote: `%s'\n", read());
        return 0;
}
