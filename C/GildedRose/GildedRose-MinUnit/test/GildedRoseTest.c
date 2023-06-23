#include <stdio.h>
#include <string.h>
#include "minunit.h"

#include "GildedRose.h"

int tests_run = 0;


static char* test_SuccessfulTest(void)
{
    mu_assert(1, "You will never see this message.");
    return 0;
}

static char* run_all_tests(){
    mu_run_test(test_SuccessfulTest);
    return 0;
} 

int main(int argc, char **argv) {
    char *result = run_all_tests();
    if (result != 0) {
        printf("%s\n", result);
    }
    else {
        printf("ALL TESTS PASSED\n");
    }
    printf("Tests run: %d\n", tests_run);
 
    return result != 0;
}
