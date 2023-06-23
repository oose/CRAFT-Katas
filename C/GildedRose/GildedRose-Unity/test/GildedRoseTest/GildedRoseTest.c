#include <unity.h>
#include <string.h>

#include "GildedRose.h"

/* More info about Unity: http://www.throwtheswitch.org/unity */

void test_SuccessfulTest(void)
{
    TEST_ASSERT(1);
}


int main(void)
{
    UNITY_BEGIN();

    RUN_TEST(test_SuccessfulTest);

    return UNITY_END();
}