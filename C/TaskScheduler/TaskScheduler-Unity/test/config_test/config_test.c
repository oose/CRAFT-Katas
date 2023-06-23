#include <unity.h>
#include <string.h>

#include <config_access.h>

/* More info about Unity: http://www.throwtheswitch.org/unity */

void test_SuccessfulTest(void)
{
    TEST_ASSERT(1);
}

void test_GettingOptionValueForEmptyStringShouldYieldEmptyString(void)
{
    const char* value = get_option_value("");
    TEST_ASSERT_MESSAGE(strcmp(value,"") == 0, "Invalid string returned by get_option_value");
}


int main(void)
{
    UNITY_BEGIN();

    RUN_TEST(test_SuccessfulTest);
    RUN_TEST(test_GettingOptionValueForEmptyStringShouldYieldEmptyString);

    return UNITY_END();
}
