#include <stdio.h>
#include <string.h>
#include "minunit.h"

#include "config_access.h"

int tests_run = 0;


static char* test_SuccessfulTest(void)
{
    mu_assert(1, "You will never see this message.");
    return 0;
}

static char* test_GettingOptionValueForEmptyStringShouldYieldEmptyString(void)
{
    const char* value = get_option_value("");
    mu_assert(strcmp(value,"") == 0, "Invalid string returned by get_option_value");
    return 0;
}

static char* run_all_tests(){
    mu_run_test(test_SuccessfulTest);
    mu_run_test(test_GettingOptionValueForEmptyStringShouldYieldEmptyString);
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
